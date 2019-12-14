package cn.smallbun.scaffold;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static cn.smallbun.scaffold.framework.common.constant.SystemConstants.*;

/**
 * SmallBun启动类
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2019/9/24
 */
@ComponentScan(value = {"cn.smallbun", "com.pingfangushi"})
@SpringBootApplication
public class SmallBunApplication {
	private final static Logger logger = LoggerFactory.getLogger(SpringApplication.class);

	public static void main(String[] args) throws UnknownHostException {
		//获取开始时间
		long start = System.currentTimeMillis();
		SpringApplication app = new SpringApplication(SmallBunApplication.class);
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
