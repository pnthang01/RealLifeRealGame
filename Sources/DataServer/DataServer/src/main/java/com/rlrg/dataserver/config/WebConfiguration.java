package com.rlrg.dataserver.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class WebConfiguration extends WebMvcAutoConfigurationAdapter{
	
	private Logger logger = LoggerFactory.getLogger(WebMvcAutoConfigurationAdapter.class);

}
