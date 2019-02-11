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

package org.smallbun.framework.toolkit;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Response 工具类
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/4/29
 */
public class ResponseUtil {
	/**
	 * 返回JSON数据
	 *
	 * @param response HttpServletResponse
	 * @param status   状态码
	 * @param data     数据
	 */
	public static void responseJson(HttpServletResponse response, int status, Object data) {
		try {
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "*");
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(status);
			response.getWriter().write(JSONObject.toJSONString(data));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Wrap the optional into a {@link ResponseEntity} with an {@link HttpStatus#OK} status, or if it's empty, it
	 * returns a {@link ResponseEntity} with {@link HttpStatus#NOT_FOUND}.
	 *
	 * @param <X>           type of the response
	 * @param maybeResponse response to return if present
	 * @return response containing {@code maybeResponse} if present or {@link HttpStatus#NOT_FOUND}
	 */
	public static <X> ResponseEntity<X> wrapOrNotFound(Optional<X> maybeResponse) {
		return wrapOrNotFound(maybeResponse, null);
	}

	/**
	 * Wrap the optional into a {@link ResponseEntity} with an {@link HttpStatus#OK} status with the headers, or if it's
	 * empty, it returns a {@link ResponseEntity} with {@link HttpStatus#NOT_FOUND}.
	 *
	 * @param <X>           type of the response
	 * @param maybeResponse response to return if present
	 * @param header        headers to be added to the response
	 * @return response containing {@code maybeResponse} if present or {@link HttpStatus#NOT_FOUND}
	 */
	public static <X> ResponseEntity<X> wrapOrNotFound(Optional<X> maybeResponse, HttpHeaders header) {
		return maybeResponse.map(response -> ResponseEntity.ok().headers(header).body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
