package com.rlrg.dataserver.badge.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rlrg.dataserver.badge.entity.Achievement;
import com.rlrg.dataserver.badge.entity.Badge;
import com.rlrg.dataserver.badge.repository.AchievementRepository;
import com.rlrg.dataserver.profile.entity.User;

@Service
public class AchievementService implements AchievementServiceInterface {

	@Autowired
	private AchievementRepository achievementRepo;

	@Override
	public List<Achievement> getAchievementByUser(User user) {
		// TODO Auto-generated method stub
		return achievementRepo.getAchievementByUser(user.getId());
	}

	@Override
	public List<Achievement> getAchievementByUserAndBadge(User user, Badge badge) {
		// TODO Auto-generated method stub
		return achievementRepo.getAchievementByUserAndBadge(user.getId(), badge.getId());
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Achievement> getAchievementByUserAndDate(User user, Date achivedTime) {
		// TODO Auto-generated method stub
		return achievementRepo.getAchievementByUserAndDate(user.getId(), achivedTime.getYear());
	}

}
