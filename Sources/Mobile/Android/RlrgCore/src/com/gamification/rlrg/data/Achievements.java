package com.gamification.rlrg.data;

import java.util.ArrayList;
import java.util.List;

import com.gamification.rlrg.data.core.BaseEntity;
import com.gamification.rlrg.data.entity.Achievement;
import com.google.gson.annotations.SerializedName;

public class Achievements extends BaseEntity<Achievements.AchievementList>
{
	public class AchievementList
	{
		@SerializedName("Achievements")
		private List<Achievement> list = new ArrayList<Achievement>();

		public List<Achievement> getElements()
		{
			return list;
		}

		public void setElements(List<Achievement> list)
		{
			this.list = list;
		}
	}
}
