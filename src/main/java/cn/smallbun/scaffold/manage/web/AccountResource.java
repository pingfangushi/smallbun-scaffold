/*
 * Copyright (c) 2018-2019. ‭‭‭‭‭‭‭‭‭‭‭‭[zuoqinggang] www.pingfangushi.com
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

package cn.smallbun.scaffold.manage.web;

import cn.smallbun.scaffold.framework.common.result.ApiRestResult;
import cn.smallbun.scaffold.framework.common.toolkit.RsaUtil;
import cn.smallbun.scaffold.framework.logger.annotation.Logger;
import cn.smallbun.scaffold.framework.redis.RedisClient;
import cn.smallbun.scaffold.framework.security.jwt.JwtFilter;
import cn.smallbun.scaffold.framework.security.utils.SecurityUtils;
import cn.smallbun.scaffold.framework.web.BaseResource;
import cn.smallbun.scaffold.manage.pojo.CurrentUserDTO;
import cn.smallbun.scaffold.manage.pojo.LoginDTO;
import cn.smallbun.scaffold.manage.pojo.LoginResultDTO;
import cn.smallbun.scaffold.manage.service.IAccountService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.io.IOException;

import static cn.smallbun.scaffold.framework.common.constant.SystemConstants.COLON;
import static cn.smallbun.scaffold.framework.exception.enums.Exception.EX000102;
import static cn.smallbun.scaffold.framework.exception.enums.Exception.EX900005;
import static cn.smallbun.scaffold.manage.constant.ManageConstant.MANAGE_API_PATH;
import static cn.smallbun.scaffold.manage.service.impl.AccountServiceImpl.CAPTCHA_CACHE_NAME;
import static cn.smallbun.scaffold.manage.service.impl.AccountServiceImpl.SECRET_CACHE_NAME;

/**
 * 账户相关
 * 包括账户登录
 * 账户拥有的菜单
 * 等
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2019/10/27 15:23
 */
@Logger(module = AccountResource.API)
@Validated
@RestController
@RequestMapping(MANAGE_API_PATH)
@Api(tags = AccountResource.API)
public class AccountResource extends BaseResource {

	final static String API = "系统账户API";

	private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

	public AccountResource(IAccountService iAccountService, RedisClient redisClient) {
		this.iAccountService = iAccountService;
		this.redisClient = redisClient;
	}

	/**
	 * 账户认证
	 * @param login 登录实体
	 * @return {@link ResponseEntity}
	 */
	@ApiOperation(value = "账户认证", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 1)
	@PostMapping(value = "account/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiRestResult<LoginResultDTO>> login(@RequestBody LoginDTO login) {
		// 验证码
		if (!validateCaptcha(login.getKey(), login.getCaptcha())) {
			return ResponseEntity
					.ok(new ApiRestResult<LoginResultDTO>().status(EX000102.getCode()).message(EX000102.getMessage())
							.build());
		}
		// 密码
		try {
			if (!redisClient.hasKey(SECRET_CACHE_NAME + COLON + login.getKey())) {
				logger.error("根据KEY:{}未在缓存中查询到秘钥", login.getKey());
				return ResponseEntity.ok(new ApiRestResult<LoginResultDTO>().status(EX900005.getCode())
						.message(EX900005.getMessage()).build());
			}
			//拿到秘钥，解密
			String key = (String) redisClient.get(SECRET_CACHE_NAME + COLON + login.getKey());
			String password = RsaUtil.decrypt(RsaUtil.loadPrivateKey(key), RsaUtil.strToBase64(login.getPassword()));
			login.setPassword(password);
		} catch (Exception e) {
			logger.error("根据KEY:{}获取私钥解密密码失败:{}", login.getKey(), e.getMessage());
			return ResponseEntity
					.ok(new ApiRestResult<LoginResultDTO>().status(EX900005.getCode()).message(EX900005.getMessage())
							.build());
		}
		// 调用service登录逻辑
		LoginResultDTO result = iAccountService.login(login);
		//封装返回
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, JwtFilter.BEARER + result.getToken());
		return new ResponseEntity<>(new ApiRestResult<LoginResultDTO>().result(result).build(), httpHeaders,
				HttpStatus.OK);
	}

	/**
	 * 验证验证码
	 * @param key key
	 * @param captcha captcha
	 * @return boolean
	 */
	private boolean validateCaptcha(String key, String captcha) {
		//如果验证码错误，返回错误
		if (!redisClient.hasKey(CAPTCHA_CACHE_NAME + COLON + key)) {
			return false;
		}
		boolean equals = captcha.equals(redisClient.get(CAPTCHA_CACHE_NAME + COLON + key));
		//删除缓存
		redisClient.del(CAPTCHA_CACHE_NAME + COLON + key);
		return equals;
	}


	/**
	 * {@code GET  /authenticate} : check if the user is authenticated, and return its login.
	 *
	 * @param request the HTTP request.
	 * @return the login if the user is authenticated.
	 */
	@ApiOperation(value = "是否认证", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 2)
	@GetMapping("/authenticate")
	public ApiRestResult<String> isAuthenticated(HttpServletRequest request) {
		return new ApiRestResult<String>().result(request.getRemoteUser()).build();
	}

	/**
	 * {@code GET  /account} : get the current user.
	 *
	 * @return the current user.
	 * @throws RuntimeException {@code 500 (Internal Server Error)} if the user couldn't be returned.
	 */
	@ApiOperation(value = "账户信息", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperationSupport(order = 3)
	@GetMapping("/account")
	public ApiRestResult<CurrentUserDTO> getAccount() {
		CurrentUserDTO user = new CurrentUserDTO();
		if (SecurityUtils.getCurrentUserLogin().isPresent()) {
			user.setName(SecurityUtils.getCurrentUserLogin().get());
		}
		//默认头像
		user.setAvatar("https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png");
		return new ApiRestResult<CurrentUserDTO>().result(user).build();
	}

	/**
	 * get  public key
	 *
	 * @return Result Objects
	 */
	@GetMapping(value = "public_secret")
	public ApiRestResult<IAccountService.PublicSecretResult> getPublicSecret() {
		return new ApiRestResult<IAccountService.PublicSecretResult>().result(iAccountService.getPublicSecret());
	}

	/**
	 * get captcha
	 *
	 * @param key key
	 */
	@GetMapping(value = "image_captcha")
	public ApiRestResult<IAccountService.ImageCaptchaResult> imageCaptcha(@NotBlank(message = "请求KEY不能为空") String key)
			throws IOException {
		return new ApiRestResult<IAccountService.ImageCaptchaResult>().result(iAccountService.imageCaptcha(key));
	}

	/**
	 * IAccountService
	 */
	private final IAccountService iAccountService;
	/**
	 * RedisClient
	 */
	private final RedisClient redisClient;
}
