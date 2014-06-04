package com.rlrg.dataserver.badge.service;

import java.util.Date;
import java.util.List;

import com.rlrg.dataserver.badge.entity.Achievement;
import com.rlrg.dataserver.badge.entity.Badge;
import com.rlrg.dataserver.badge.entity.User;

public interface AchievementServiceInterface {
	public List<Achievement> getAchievementByUser(User user);

	public List<Achievement> getAchievementByUserAndBadge(User user, Badge badge);

	public List<Achievement> getAchievementByUserAndDate(User user, Date achivedTime);
}
