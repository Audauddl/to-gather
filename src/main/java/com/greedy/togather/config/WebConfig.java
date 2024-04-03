package com.greedy.togather.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		/* upload 경로를 추가하여 업로드 된 이미지를 정적 리소스로 요청할 수 있게 함 */
		registry.addResourceHandler("/upload/**") /* upload로 시작하는 요청이 왔을 경우, */
			.addResourceLocations("classpath:/upload/"); /* resources의 upload 경로로 GO */
	
	}

}
