package com.gamification.rlrg.data;

import java.util.List;

import com.gamification.rlrg.core.components.BaseEntity;
import com.gamification.rlrg.data.Badges.Badge;
import com.google.gson.annotations.SerializedName;

public class Achievements extends BaseEntity<Achievements.AchievementList>
{
	public class AchievementList
	{
		@SerializedName("Achievements")
		private List<Achievement> list;
		
		public List<Achievement> getElements()
		{
			return list;
		}
	
		public void setElements(List<Achievement> list)
		{
			this.list = list;
		}
	}

	public class Achievement
	{
		@SerializedName("ID")
		private String id;
		
		@SerializedName("Badge")
		private Badge badge;
		
		@SerializedName("AchievedTime")
		private long achievedTime;

		public String getId()
		{
			return id;
		}

		public void setId(String id)
		{
			this.id = id;
		}

		public Badge getBadge()
		{
			return badge;
		}

		public void setBadge(Badge badge)
		{
			this.badge = badge;
		}

		public long getAchievedTime()
		{
			return achievedTime;
		}

		public void setAchievedTime(long achievedTime)
		{
			this.achievedTime = achievedTime;
		}
	}
}
