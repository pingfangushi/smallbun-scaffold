/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 ‭‭‭‭‭‭‭‭‭‭‭‭[smallbun] www.smallbun.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.smallbun.fast;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.smallbun.framework.auto.SmallBunProperties;
import org.smallbun.framework.constant.SystemConstant;
import org.smallbun.framework.toolkit.DefaultProfileUtil;
import org.smallbun.framework.toolkit.SpringContextUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.springframework.security.config.Elements.HTTP;


/**
 * SpringBoot启动类
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/4/15
 */
@Slf4j
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
@ComponentScan(basePackages = "org.smallbun")
public class Application {


	/**
	 * 主方法
	 *
	 * @param args args
	 * @throws UnknownHostException 未知的主机异常
	 */
	public static void main(String[] args) throws UnknownHostException {
		//获取开始时间
		long start = System.currentTimeMillis();
		SpringApplication app = new SpringApplication(Application.class);
		DefaultProfileUtil.addDefaultProfile(app);
		Environment env = app.run(args).getEnvironment();
		String protocol = HTTP;
		if (env.getProperty(SystemConstant.SERVER_SSL_KEY_STORE) != null) {
			protocol = SystemConstant.HTTPS;
		}
		//获取结束时间
		long end = System.currentTimeMillis();
		log.info("\n----------------------------------------------------------\n\t"
						+ "名称:\t'{}' is running! Access URLs:\n\t" + "本地:\t {}://localhost:{}\n\t" + "外部:\t {}://{}:{}\n\t"
						+ "环境:\t {}\n\t" + "用时:\t {}\n----------------------------------------------------------",
				((SmallBunProperties) SpringContextUtil.getBean(SmallBunProperties.class)).getProject().getName(),
				protocol, env.getProperty("server.port"), protocol, InetAddress.getLocalHost().getHostAddress(),
				env.getProperty("server.port"), env.getActiveProfiles(), (end - start) + "ms");
	}

}

