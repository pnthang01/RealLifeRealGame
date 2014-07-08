package com.rlrg.webserver.admin.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rlrg.webserver.admin.service.MvcAchievementService;
import com.rlrg.webserver.admin.service.MvcBadgeService;
import com.rlrg.webserver.admin.service.MvcCategoryService;
import com.rlrg.webserver.admin.service.MvcLanguageService;
import com.rlrg.webserver.admin.service.MvcPermissionService;
import com.rlrg.webserver.admin.service.MvcRoleService;
import com.rlrg.webserver.admin.service.MvcTaskService;
import com.rlrg.webserver.admin.service.MvcUserService;


@Configuration
public class MvcConfig {
	@Bean(name="categoryService")
	public MvcCategoryService getCategoryService(){
		return new MvcCategoryService();
	}
	
	@Bean(name="achievementService")
	public MvcAchievementService getAchievementService(){
		return new MvcAchievementService();
	}
	
	@Bean(name="badgeService")
	public MvcBadgeService getBadgeService(){
		return new MvcBadgeService();
	}
	
	@Bean(name="languageService")
	public MvcLanguageService getLanguageService(){
		return new MvcLanguageService();
	}
	
	@Bean(name="permissionService")
	public MvcPermissionService getPermissionService(){
		return new MvcPermissionService();
	}
	
	@Bean(name="roleService")
	public MvcRoleService getRoleService(){
		return new MvcRoleService();
	}
	
	@Bean(name="taskService")
	public MvcTaskService getTaskService(){
		return new MvcTaskService();
	}
	
	@Bean(name="userService")
	public MvcUserService getUserService(){
		return new MvcUserService();
	}
}
