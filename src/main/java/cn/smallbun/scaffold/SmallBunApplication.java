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

package cn.smallbun.scaffold;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static cn.smallbun.scaffold.framework.common.constant.SystemConstants.*;

/**
 * SmallBun启动类
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019/9/24
 */
@SpringBootApplication(scanBasePackages = {"cn.smallbun"})
public class SmallBunApplication {
	private final static Logger logger = LoggerFactory.getLogger(SpringApplication.class);

	public static void main(String[] args) throws UnknownHostException {
		//获取开始时间
		long start = System.currentTimeMillis();
		SpringApplication app = new SpringApplication(SmallBunApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		Environment env = app.run(args).getEnvironment();
		String protocol = HTTP;
		if (env.getProperty(SERVER_SSL_KEY_STORE) != null) {
			protocol = HTTPS;
		}
		//获取结束时间
		long end = System.currentTimeMillis();
		logger.info("\n----------------------------------------------------------\n\t"
						+ "名称:\t'{}' is running! Access URLs:\n\t" + "本地:\t {}://localhost:{}\n\t" + "外部:\t {}://{}:{}\n\t"
						+ "环境:\t {}\n\t" + "用时:\t {}\n----------------------------------------------------------",
				env.getProperty("spring.application.name"), protocol, env.getProperty("server.port"), protocol,
				InetAddress.getLocalHost().getHostAddress(), env.getProperty("server.port"), env.getActiveProfiles(),
				(end - start) + "ms");
	}

}
