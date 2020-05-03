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

import cn.smallbun.scaffold.framework.mybatis.domain.IdEntity;
import cn.smallbun.scaffold.framework.security.authority.AuthorityInfo;
import cn.smallbun.scaffold.framework.security.authority.SecurityAuthorizeProvider;
import cn.smallbun.scaffold.framework.security.exception.HaveNotAuthorityException;
import cn.smallbun.scaffold.framework.security.utils.SecurityUtils;
import cn.smallbun.scaffold.manage.entity.SysAuthorizeItemEntity;
import cn.smallbun.scaffold.manage.entity.SysRoleEntity;
import cn.smallbun.scaffold.manage.entity.SysUserEntity;
import cn.smallbun.scaffold.manage.enums.AuthorizeType;
import cn.smallbun.scaffold.manage.service.IAccountService;
import cn.smallbun.scaffold.manage.service.ISysAuthorizeItemService;
import cn.smallbun.scaffold.manage.service.ISysRoleService;
import cn.smallbun.scaffold.manage.service.ISysUserService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SecurityAuthorizeProviderImpl
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/4/30 9:02
 */
@Component
public class SecurityAuthorizeProviderImpl implements SecurityAuthorizeProvider {

    /**
     * 获取权限
     *
     * @param userId userId
     * @return {@link AuthorityInfo}
     */
    @Override
    public AuthorityInfo getAuthorityInfo(String userId) {
        //根据用户获取角色
        SysUserEntity user = iSysUserService.getById(userId);
        List<SysRoleEntity> roles = user.getRoles();
        List<String> roleIds = roles.stream().map(IdEntity::getId).collect(Collectors.toList());
        AuthorityInfo info = findAuthByRole(roleIds);
        logger.info("用户信息：{}", JSON.toJSONString(info));
        return info;
    }

    /**
     * 获取授权信息
     *
     * @return {@link AuthorityInfo}
     */
    @Override
    public AuthorityInfo getAuthorityInfo() {
        //系统所有角色
        List<String> roleIds = iSysRoleService.list().stream().map(IdEntity::getId)
            .collect(Collectors.toList());
        // 过滤所有启用的权限
        return findAuthByRole(roleIds);
    }

    /**
     * 获取所有授权信息
     *
     * @return {@link List<GrantedAuthority>}
     */
    @Override
    public List<GrantedAuthority> getGrantedAuthority() {
        return SecurityUtils.getGrantedAuthority(this.getAuthorityInfo());
    }

    /**
     * 通过用户ID获取授予的权限
     *
     * @param id {@link String} 用户ID
     * @return {@link List<GrantedAuthority>}
     */
    @Override
    public List<GrantedAuthority> getGrantedAuthority(String id) {
        return SecurityUtils.getGrantedAuthority(this.getAuthorityInfo(id));
    }

    /**
     * 根据角色获取权限信息
     *
     * @param roleIds {@link List<String>} 角色
     */
    private AuthorityInfo findAuthByRole(List<String> roleIds) {
        AuthorityInfo info = new AuthorityInfo();
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
        return info;
    }

    /**
     * 根据权限类型过滤权限项
     *
     * @param authorizeItems {@link List<SysAuthorizeItemEntity>}
     * @param type           {@link List<AuthorizeType>}
     * @return {@link List<SysAuthorizeItemEntity>}
     */
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
     * 系统角色
     */
    private final ISysRoleService          iSysRoleService;

    /**
     * ISysAuthorizeItemService
     */
    private final ISysAuthorizeItemService iSysAuthorizeItemService;
    /**
     * 系统用户
     */
    private final ISysUserService          iSysUserService;
    /**
     * Logger
     */
    private final Logger                   logger = LoggerFactory.getLogger(IAccountService.class);

    /**
     * 构造函数
     * @param iSysRoleService {@link ISysRoleService}
     * @param iSysAuthorizeItemService {@link ISysAuthorizeItemService}
     * @param iSysUserService {@link ISysUserService}
     */
    public SecurityAuthorizeProviderImpl(ISysRoleService iSysRoleService,
                                         ISysAuthorizeItemService iSysAuthorizeItemService,
                                         ISysUserService iSysUserService) {
        this.iSysRoleService = iSysRoleService;
        this.iSysAuthorizeItemService = iSysAuthorizeItemService;
        this.iSysUserService = iSysUserService;
    }
}
