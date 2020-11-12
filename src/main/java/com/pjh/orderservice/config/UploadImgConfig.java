package com.pjh.orderservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class UploadImgConfig implements WebMvcConfigurer {

	//MVC配置使http://IP:端口号/${server.context-path}/profile/图片路径可以访问到配置的文件夹
	@Value("${uploadFilePath}")
	private String uploadFilePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /** 图片传路径 */
        registry.addResourceHandler("/profile/**").addResourceLocations("file:" + uploadFilePath);

    }

}
