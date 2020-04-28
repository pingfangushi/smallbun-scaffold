/*
 * Copyright (c) 2018-2020. ‭‭‭‭‭‭‭‭‭‭‭‭[zuoqinggang] www.pingfangushi.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package cn.smallbun.scaffold.manage.service.impl;

import cn.smallbun.scaffold.framework.common.toolkit.IpUtil;
import cn.smallbun.scaffold.framework.common.toolkit.RsaUtil;
import cn.smallbun.scaffold.framework.configurer.SmallBunProperties;
import cn.smallbun.scaffold.framework.mybatis.domain.IdEntity;
import cn.smallbun.scaffold.framework.redis.RedisClient;
import cn.smallbun.scaffold.framework.security.AuthorityInfo;
import cn.smallbun.scaffold.framework.security.SecurityAuthorizeProvider;
import cn.smallbun.scaffold.framework.security.domain.User;
import cn.smallbun.scaffold.framework.security.exception.HaveNotAuthorityException;
import cn.smallbun.scaffold.framework.security.jwt.TokenProvider;
import cn.smallbun.scaffold.manage.entity.SysAuthorizeItemEntity;
import cn.smallbun.scaffold.manage.entity.SysLoggerLoginEntity;
import cn.smallbun.scaffold.manage.entity.SysRoleEntity;
import cn.smallbun.scaffold.manage.entity.SysUserEntity;
import cn.smallbun.scaffold.manage.enums.AuthorizeType;
import cn.smallbun.scaffold.manage.enums.UserStatus;
import cn.smallbun.scaffold.manage.pojo.LoginDTO;
import cn.smallbun.scaffold.manage.pojo.LoginResultDTO;
import cn.smallbun.scaffold.manage.service.*;
import com.alibaba.fastjson.JSON;
import com.google.code.kaptcha.Producer;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

import static cn.smallbun.scaffold.framework.common.address.Address.getCityInfoByDb;
import static cn.smallbun.scaffold.framework.common.constant.SystemConstants.*;
import static cn.smallbun.scaffold.framework.common.result.ApiRestResult.SUCCESS;
import static cn.smallbun.scaffold.framework.common.toolkit.UserAgentUtil.getUserAgent;
import static cn.smallbun.scaffold.framework.security.enums.Security.*;

/**
 * 账户业务逻辑实现类
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2019/10/27 20:12
 */
@Service(value = "userDetailsService")
public class AccountServiceImpl implements IAccountService, SecurityAuthorizeProvider {
    private final Logger       logger             = LoggerFactory.getLogger(IAccountService.class);

    public static final String SECRET_CACHE_NAME  = "secret";
    public static final String CAPTCHA_CACHE_NAME = "captcha";

    public AccountServiceImpl(ISysLoggerLoginService iSysLoggerLoginService,
                              Producer captchaProducer, ISysUserService iSysUserService,
                              ISysRoleService iSysRoleService, SmallBunProperties properties,
                              RedisClient redisClient,
                              AuthenticationManagerBuilder authenticationManagerBuilder,
                              TokenProvider tokenProvider,
                              ISysAuthorizeItemService iSysAuthorizeItemService) {
        this.iSysLoggerLoginService = iSysLoggerLoginService;
        this.captchaProducer = captchaProducer;
        this.iSysUserService = iSysUserService;
        this.iSysRoleService = iSysRoleService;
        this.properties = properties;
        this.redisClient = redisClient;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.tokenProvider = tokenProvider;
        this.iSysAuthorizeItemService = iSysAuthorizeItemService;
    }

    /**
     * 登录
     *
     * @param login {@link LoginDTO}
     * @return {@link LoginResultDTO}
     */
    @Override
    public LoginResultDTO login(LoginDTO login) {
        //用户名密码验证令牌
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            login.getUsername(), login.getPassword());
        //认证
        Authentication authentication = authenticationManagerBuilder.getObject()
            .authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //是否记住我
        boolean rememberMe = (login.getRememberMe() == null) ? false : login.getRememberMe();
        //创建token
        String jwt = tokenProvider.createToken(authentication, rememberMe);
        // 返回
        LoginResultDTO result = new LoginResultDTO();
        result.setStatus(SUCCESS);
        result.setToken(jwt);
        return result;
    }

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        // 放入用户名
        logger.info("通过用户名:{} 加载用户", username);
        //如果是全局用户
        if (username.equals(properties.getSecurity().getUsername())) {
            return new User(username, properties.getSecurity().getPassword(),
                properties.getSecurity().getId(), true, true, true, true);
        }
        SysUserEntity entity = iSysUserService.getByUserName(username);
        //不存在该用户
        if (ObjectUtils.isEmpty(entity)) {
            logger.info("{}", USER_DOES_NOT_EXIST.getDesc());
            throw new UsernameNotFoundException(USER_DOES_NOT_EXIST.getDesc());
        }
        //锁定
        if (entity.getStatus().equals(UserStatus.LOCKED)) {
            logger.info("{}", USER_IS_LOCKED.getDesc());
            throw new LockedException(USER_IS_LOCKED.getDesc());
        }
        //禁用
        if (entity.getStatus().equals(UserStatus.DISABLE)) {
            logger.info("{}", USER_IS_DISABLED.getDesc());
            throw new DisabledException(USER_IS_DISABLED.getDesc());
        }
        return new User(username, entity.getPasswordHash(), entity.getId(), true, true, true, true);
    }

    /**
     * 绘制验证码
     * 如果key为空，说明是第一次请求，如果不为空，说明用户再刷新，根据key替换缓存中的验证码
     *
     * @param key key
     */
    @Override
    public ImageCaptchaResult imageCaptcha(String key) throws IOException {
        HttpServletResponse response = ((ServletRequestAttributes) Objects
            .requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();

        Objects.requireNonNull(response).setDateHeader("Expires", 0);

        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");

        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");

        // return a jpeg
        response.setContentType("image/jpeg");

        // create the text for the image
        String capText = captchaProducer.createText();

        logger.debug("登录验证码:{}", capText);

        // create the image with the text
        BufferedImage bi = captchaProducer.createImage(capText);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        // write the data out
        ImageIO.write(bi, "png", bos);
        byte[] imageBytes = bos.toByteArray();

        Base64.Encoder encoder = Base64.getEncoder();
        String image = encoder.encodeToString(imageBytes);
        try {
            bos.flush();
        } finally {
            bos.close();
        }
        //验证码存入redis，一分钟过期
        redisClient.set(CAPTCHA_CACHE_NAME + COLON + key, capText, 60);
        //构建图片验证码返回
        ImageCaptchaResult result = new ImageCaptchaResult();
        result.setImage(image);
        return result;
    }

    /**
     * 登录公钥
     *
     * @return LoginPublicSecretResult
     */
    @Override
    public PublicSecretResult getPublicSecret() {
        //调用工具类生成秘钥
        HashMap<String, String> map = RsaUtil.getKeys();
        // key
        String key = UUID.randomUUID().toString();

        //存入redis key 和私钥,公钥没必要 ，五分钟过期
        redisClient.set(SECRET_CACHE_NAME + COLON + key, map.get(PRIVATE_KEY), 60 * 5);
        //返回
        PublicSecretResult result = new PublicSecretResult();
        result.setKey(key);
        result.setSecret(map.get(PUBLIC_KEY));
        return result;
    }

    /**
     * 登录成功事件
     *
     * @param e {@link AuthenticationSuccessEvent}
     */
    @EventListener
    public void authenticationSuccessEvent(AuthenticationSuccessEvent e) {
        //用户名
        String username = ((User) e.getAuthentication().getPrincipal()).getUsername();
        //登录日志
        SysLoggerLoginEntity entity = getLoggerLogin(username, "登录成功", e.getTimestamp());
        iSysLoggerLoginService.save(entity);
    }

    /**
     * 登录失败
     *
     * @param e {@link AuthenticationSuccessEvent}
     */
    @EventListener
    public void authenticationSuccessEvent(AbstractAuthenticationFailureEvent e) {
        //获取用户名
        String username = (String) e.getAuthentication().getPrincipal();
        //登录日志
        SysLoggerLoginEntity entity = getLoggerLogin(username, e.getException().getMessage(),
            e.getTimestamp());
        iSysLoggerLoginService.save(entity);
    }

    /**
     * getLoggerLogin
     *
     * @param username  username
     * @param message   message
     * @param timestamp timestamp
     * @return {@link SysLoggerLoginEntity}
     */
    private SysLoggerLoginEntity getLoggerLogin(String username, String message, long timestamp) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects
            .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        SysLoggerLoginEntity entity = new SysLoggerLoginEntity();
        //用户名
        entity.setUser(username);
        //结果
        entity.setResult(message);
        //操作IP
        entity.setIp(IpUtil.getIpAddr(request));
        //登录地点
        entity.setLocation(getCityInfoByDb(IpUtil.getIpAddr(request)));
        //浏览器
        entity.setBrowser(getUserAgent(request).getBrowser().getName());
        //操作系统
        entity.setOs(getUserAgent(request).getOperatingSystem().getName());
        //操作时间
        entity
            .setLoginTime(LocalDateTime.ofEpochSecond(timestamp / 1000, 0, ZoneOffset.ofHours(8)));
        return entity;
    }

    /**
     * 获取权限
     *
     * @param user {@link String} 用户名
     * @return {@link List<GrantedAuthority>}
     */
    @Override
    public List<GrantedAuthority> getAuthority(String user) {
        AuthorityInfo authorityInfo = this.getAuthorityInfo(user);
        //操作权限
        List<String> auth = authorityInfo.getActions().stream()
            .map(AuthorityInfo.AuthorityItem::getAuth).collect(Collectors.toList());
        //接口权限
        auth.addAll(authorityInfo.getInterfaces().stream().map(AuthorityInfo.AuthorityItem::getAuth)
            .collect(Collectors.toList()));
        //路由权限
        auth.addAll(authorityInfo.getRouters().stream().map(AuthorityInfo.AuthorityItem::getAuth)
            .collect(Collectors.toList()));
        //处理数据
        return authorities(auth);
    }

    /**
     * 获取权限
     *
     * @param userId userId
     * @return {@link AuthorityInfo}
     */
    @Override
    public AuthorityInfo getAuthorityInfo(String userId) {
        AuthorityInfo info = new AuthorityInfo();
        List<String> roleIds;
        //全局超级用户
        if (properties.getSecurity().getId().equals(userId)) {
            //系统所有角色
            roleIds = iSysRoleService.list().stream().map(IdEntity::getId)
                .collect(Collectors.toList());
        } else {
            //根据用户获取角色
            SysUserEntity user = iSysUserService.getById(userId);
            List<SysRoleEntity> roles = user.getRoles();
            roleIds = roles.stream().map(IdEntity::getId).collect(Collectors.toList());
        }
        // 过滤所有启用的权限
        List<SysRoleEntity> list = iSysRoleService.listByIds(roleIds);
        if (CollectionUtils.isEmpty(roleIds)) {
            throw new HaveNotAuthorityException("没有可用角色，请联系管理员");
        }
        //查询权限
        List<SysAuthorizeItemEntity> authorizeItems = iSysAuthorizeItemService
            .getAuthorizeItemsByRole(
                list.stream().map(SysRoleEntity::getId).collect(Collectors.toList()));
        if (CollectionUtils.isEmpty(authorizeItems)) {
            throw new HaveNotAuthorityException("用户没有可用权限");
        }
        //过滤接口权限
        info.setInterfaces(getAuthorityItems(authorizeItems, AuthorizeType.INTERFACE));
        //过滤路由权限
        info.setRouters(getAuthorityItems(authorizeItems, AuthorizeType.ROUTE));
        //操作权限
        info.setActions(getAuthorityItems(authorizeItems, AuthorizeType.OPERATE));
        logger.info("用户信息：{}", JSON.toJSONString(info));
        return info;
    }

    private List<AuthorityInfo.AuthorityItem> getAuthorityItems(List<SysAuthorizeItemEntity> authorizeItems,
                                                                AuthorizeType type) {
        List<SysAuthorizeItemEntity> operates = authorizeItems.stream()
            .filter(i -> i.getType().equals(type)).collect(Collectors.toList());
        List<AuthorityInfo.AuthorityItem> operatesItems = new ArrayList<>();
        operates.forEach(entity -> {
            AuthorityInfo.AuthorityItem item = new AuthorityInfo.AuthorityItem();
            item.setAuth(entity.getPermission());
            item.setDescribe(entity.getName());
            operatesItems.add(item);
        });
        return operatesItems;
    }

    /**
     * 处理权限
     *
     * @param auth {@link List<String>}
     * @return {@link List<GrantedAuthority>}
     */
    private List<GrantedAuthority> authorities(List<String> auth) {
        List<GrantedAuthority> authorities = Lists.newArrayList();
        for (String s : auth) {
            authorities.addAll(AuthorityUtils.createAuthorityList(s));
        }
        return authorities;
    }

    /**
     * 登录日志
     */
    private final ISysLoggerLoginService       iSysLoggerLoginService;

    /**
     * 绘制文本的验证码
     */
    private final Producer                     captchaProducer;
    /**
     * 系统用户
     */
    private final ISysUserService              iSysUserService;
    /**
     * 系统角色
     */
    private final ISysRoleService              iSysRoleService;
    /**
     * SmallBunProperties
     */
    private final SmallBunProperties           properties;
    /**
     * RedisClient
     */
    private final RedisClient                  redisClient;
    /**
     * AuthenticationManagerBuilder
     */
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    /**
     * token 提供者
     */
    private final TokenProvider                tokenProvider;
    /**
     * ISysAuthorizeItemService
     */
    private final ISysAuthorizeItemService     iSysAuthorizeItemService;

}
