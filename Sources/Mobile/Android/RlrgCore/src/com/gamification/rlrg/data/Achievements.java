package com.gamification.rlrg.data;

import java.util.List;

import com.gamification.rlrg.core.components.BaseEntity;
import com.google.gson.annotations.SerializedName;

public class Achievements extends BaseEntity<Achievements.AchievementList>
{
	public class AchievementList
	{
		@SerializedName("Achievements")
		private List<Achievement> list;
		
		public List<Achievement> getList()
		{
			return list;
		}
	
		public void setList(List<Achievement> list)
		{
			this.list = list;
		}
	}

	public class Achievement
	{
		@SerializedName("ID")
		private String id;
		
		@SerializedName("Badge")
		private Badges badge;
		
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

		public Badges getBadge()
		{
			return badge;
		}

		public void setBadge(Badges badge)
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
