package cn.smallbun.scaffold.configuration;

import com.github.xiaoymin.knife4j.spring.annotations.EnableSwaggerBootstrapUi;
import cn.smallbun.scaffold.framework.common.result.ApiRestResult;
import cn.smallbun.scaffold.framework.common.toolkit.AppVersionUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.nio.ByteBuffer;

import static cn.smallbun.scaffold.manage.constant.ManageConstant.MANAGE_API_PATH;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * SwaggerConfiguration
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2019/5/16 20:28
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUi
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {

	/**
	 * 管理配置RestAPI
	 * @return {@link Docket}
	 */
	@Bean
	public Docket manageRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				//group
				.groupName("管理系统")
				//INFO
				.apiInfo(info()).forCodeGeneration(true).directModelSubstitute(ByteBuffer.class, String.class)
				.genericModelSubstitutes(ApiRestResult.class, ResponseEntity.class)
				.ignoredParameterTypes(Pageable.class)
				//查询路径
				.select()
				//API路径
				.paths(regex(MANAGE_API_PATH + "/.*")).build();
	}


	/**
	 * API INFO
	 * @return  {@link ApiInfo}
	 */
	private ApiInfo info() {
		return new ApiInfoBuilder()
				//title
				.title("SmallBun 企业级开发脚手架")
				//描述
				.description("REST API 文档")
				//服务条款网址
				.termsOfServiceUrl("http://www.smallbun.cn")
				//内容
				.contact(new Contact("SanLi", "http://www.pingfangushi.com", "2689170096@qq.com"))
				//版本
				.version(AppVersionUtil.getVersion(SwaggerConfiguration.class)).build();
	}

}
