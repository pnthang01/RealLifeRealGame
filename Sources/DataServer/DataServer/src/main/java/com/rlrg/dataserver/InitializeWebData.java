package com.rlrg.dataserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rlrg.dataserver.badge.dto.AchievementDTO;
import com.rlrg.dataserver.badge.dto.BadgeDTO;
import com.rlrg.dataserver.badge.entity.Achievement;
import com.rlrg.dataserver.badge.entity.Badge;
import com.rlrg.dataserver.badge.service.AchievementService;
import com.rlrg.dataserver.badge.service.BadgeService;
import com.rlrg.dataserver.base.service.IAchievementService;
import com.rlrg.dataserver.base.service.IBadgeLanguageService;
import com.rlrg.dataserver.base.service.IBadgeService;
import com.rlrg.dataserver.base.service.ICategoryLanguageService;
import com.rlrg.dataserver.base.service.ICategoryService;
import com.rlrg.dataserver.base.service.ILanguageService;
import com.rlrg.dataserver.base.service.IPermissionService;
import com.rlrg.dataserver.base.service.IRoleService;
import com.rlrg.dataserver.base.service.IStatisticService;
import com.rlrg.dataserver.base.service.ITaskService;
import com.rlrg.dataserver.base.service.IUserLogService;
import com.rlrg.dataserver.base.service.IUserService;
import com.rlrg.dataserver.language.dto.BadgeLangDTO;
import com.rlrg.dataserver.language.dto.CateLangDTO;
import com.rlrg.dataserver.language.dto.LanguageDTO;
import com.rlrg.dataserver.language.entity.BadgeLanguage;
import com.rlrg.dataserver.language.entity.CategoryLanguage;
import com.rlrg.dataserver.language.entity.Language;
import com.rlrg.dataserver.language.service.BadgeLanguageService;
import com.rlrg.dataserver.language.service.CategoryLanguageService;
import com.rlrg.dataserver.language.service.LanguageService;
import com.rlrg.dataserver.profile.dto.PermissionDTO;
import com.rlrg.dataserver.profile.dto.RoleDTO;
import com.rlrg.dataserver.profile.dto.UserDTO;
import com.rlrg.dataserver.profile.entity.Permission;
import com.rlrg.dataserver.profile.entity.Role;
import com.rlrg.dataserver.profile.entity.User;
import com.rlrg.dataserver.profile.entity.UserLog;
import com.rlrg.dataserver.profile.service.PermissionService;
import com.rlrg.dataserver.profile.service.RoleService;
import com.rlrg.dataserver.profile.service.UserLogService;
import com.rlrg.dataserver.profile.service.UserService;
import com.rlrg.dataserver.statistic.dto.StatisticDTO;
import com.rlrg.dataserver.statistic.service.StatisticService;
import com.rlrg.dataserver.task.dto.CategoryDTO;
import com.rlrg.dataserver.task.dto.TaskDTO;
import com.rlrg.dataserver.task.entity.Category;
import com.rlrg.dataserver.task.entity.Task;
import com.rlrg.dataserver.task.scheduler.TaskScheduler;
import com.rlrg.dataserver.task.service.CategoryService;
import com.rlrg.dataserver.task.service.TaskService;

@Configuration
public class InitializeWebData {
	
	@Bean
	public TaskScheduler taskScheduler(){
		return new TaskScheduler();
	}
	
	@Bean
	public IStatisticService<?, StatisticDTO> getStatisticService(){
		return new StatisticService();
	}
	
	@Bean
	public IUserLogService<UserLog> getUserLogService(){
		return new UserLogService();
	}

	@Bean
	public IBadgeService<Badge, BadgeDTO> getBadgeService(){
		return new BadgeService();
	}
	
	@Bean(name="badgeLangService")
	public IBadgeLanguageService<BadgeLanguage, BadgeLangDTO> getBadgeLanguageService(){
		return new BadgeLanguageService();
	}
	
	@Bean(name="cateLangService")
	public ICategoryLanguageService<CategoryLanguage, CateLangDTO> getCategoryLanguageService(){
		return new CategoryLanguageService();
	}
	
	@Bean
	public ILanguageService<Language, LanguageDTO> getLanguageService(){
		return new LanguageService();
	}
	
	@Bean
	public IAchievementService<Achievement, AchievementDTO> getAchievementService(){
		return new AchievementService();
	}

	@Bean
	public IPermissionService<Permission, PermissionDTO> getPermissionService(){
		return new PermissionService();
	}
	
	@Bean
	public IRoleService<Role, RoleDTO> getRoleService(){
		return new RoleService();
	}
	
	@Bean
	public IUserService<User, UserDTO> getUserService(){
		return new UserService();
	}
	
	@Bean
	public ITaskService<Task, TaskDTO> getTaskService(){
		return new TaskService();
	}
	
	@Bean
	public ICategoryService<Category, CategoryDTO> getCategoryService(){
		return new CategoryService();
	}
}
