package com.rlrg.webserver.admin.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rlrg.webserver.admin.service.MvcCategoryService;


@Configuration
public class MvcConfig {
	@Bean(name="categoryService")
	public MvcCategoryService getCategoryService(){
		return new MvcCategoryService();
	}
}
