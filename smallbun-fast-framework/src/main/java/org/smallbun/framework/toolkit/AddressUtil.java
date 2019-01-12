package org.smallbun.framework.toolkit;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.smallbun.framework.toolkit.HttpUtil.client;
import static org.smallbun.framework.toolkit.IpUtil.internalIp;

/**
 *
 * @author SanLi [隔壁object港哥][https://www.leshalv.net]
 * Created by 2689170096@qq.com on  2019-01-10 12:48
 */
@Log4j2
public class AddressUtil {


	private static final String IP_URL = "http://ip.taobao.com/service/getIpInfo.php";

	public static String getRealAddressByIP(String ip) {
		//如果不是内部IP
		if (!internalIp(ip)) {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			params.add("ip", ip);
			return client(IP_URL, HttpMethod.POST, params);
		}
		return ip;
	}
}
