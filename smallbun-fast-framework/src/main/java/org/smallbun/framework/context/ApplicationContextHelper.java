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

package org.smallbun.framework.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.Objects;

/**
 * ApplicationContextHelper
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/10/25 20:01
 */
@Component
public class ApplicationContextHelper implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(@Nullable ApplicationContext applicationContext) throws BeansException {
		ApplicationContextHelper.applicationContext = applicationContext;
	}

	/**
	 * 获取Bean
	 *
	 * @param aClass Class
	 * @return Object
	 */
	public static Object getBean(Class aClass) {
		return ApplicationContextHelper.applicationContext.getBean(Objects.requireNonNull(aClass));
	}

	/**
	 * 获取Bean
	 *
	 * @param aClass Class
	 * @return Object
	 */
	public static Object getBean(String aClass) {
		return ApplicationContextHelper.applicationContext.getBean(aClass);
	}
}
