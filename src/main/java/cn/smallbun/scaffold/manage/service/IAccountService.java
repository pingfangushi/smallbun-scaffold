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

package cn.smallbun.scaffold.manage.service;

import cn.smallbun.scaffold.manage.pojo.LoginDTO;
import cn.smallbun.scaffold.manage.pojo.LoginResultDTO;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;
import java.io.Serializable;

/**
 * 账户业务
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2019/10/27 20:10
 */
public interface IAccountService extends Serializable, UserDetailsService {
	/**
	 * 登录
	 * @param login {@link LoginDTO}
	 * @return {@link LoginResultDTO}
	 */
	LoginResultDTO login(LoginDTO login);

	/**
	 * 绘制验证码
	 * @param key key
	 * @throws IOException IOException
	 * @return ImageCaptchaResult
	 */
	ImageCaptchaResult imageCaptcha(String key) throws IOException;

	/**
	 * 登录公钥
	 * @return LoginPublicSecretResult
	 */
	PublicSecretResult getPublicSecret();

	/**
	 * 图片验证码返回
	 */
	@Data
	class ImageCaptchaResult {
		/**
		 * base64
		 */
		private String image;
	}


	/**
	 * 登录公钥返回
	 */
	@Data
	class PublicSecretResult {
		/**
		 * key
		 */
		private String key;
		/**
		 * 秘钥
		 */
		private String secret;
	}
}
