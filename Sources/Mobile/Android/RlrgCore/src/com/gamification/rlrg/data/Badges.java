package com.gamification.rlrg.data;

import java.util.ArrayList;
import java.util.List;

import com.gamification.rlrg.data.core.BaseEntity;
import com.gamification.rlrg.data.entity.Badge;
import com.google.gson.annotations.SerializedName;

public class Badges extends BaseEntity<Badges.BadgeList>
{
	public class BadgeList
	{
		@SerializedName("Badges")
		private List<Badge> list = new ArrayList<Badge>();

		public List<Badge> getElements()
		{
			return list;
		}

		public void setElements(List<Badge> list)
		{
			this.list = list;
		}
	}
}
