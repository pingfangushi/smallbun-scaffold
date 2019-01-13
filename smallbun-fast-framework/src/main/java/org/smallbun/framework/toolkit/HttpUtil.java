/*
 *
 *  * Copyright(c)[2018] [smallbun] www.smallbun.org
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package org.smallbun.framework.toolkit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * 网络请求工具类
 *
 * @author SanLi
 * Created  by 2689170096@qq.com  on 2018/1/15
 */
@Slf4j
public class HttpUtil {

	public static String client(String url, HttpMethod method, MultiValueMap<String, String> params) {
		RestTemplate client = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		try {
			ResponseEntity<String> response;
			//  请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
			//  执行HTTP请求
			response = client.exchange(url, method, requestEntity, String.class);
			return response.getBody();
		} catch (RestClientException e) {
			log.error("HTTP请求异常:{}", e.getLocalizedMessage());
		}
		return "-";

	}
}
