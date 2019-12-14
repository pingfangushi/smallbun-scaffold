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
	ImageCaptchaResult imageCaptcha(String key)
			throws IOException;

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
