package com.gamification.rlrg.data;

import java.util.ArrayList;
import java.util.List;

import com.gamification.rlrg.data.core.BaseEntity;
import com.gamification.rlrg.data.entity.User;
import com.google.gson.annotations.SerializedName;

public class Users extends BaseEntity<Users.UserList>
{
	public class UserList
	{
		@SerializedName("Users")
		private List<User> list = new ArrayList<User>();
		
		public List<User> getElements()
		{
			return list;
		}
	
		public void setElements(List<User> list)
		{
			this.list = list;
		}
	}
}