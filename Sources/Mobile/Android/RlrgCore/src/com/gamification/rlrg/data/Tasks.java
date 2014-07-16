package com.gamification.rlrg.data;

import java.util.ArrayList;
import java.util.List;

import com.gamification.rlrg.data.core.BaseEntity;
import com.gamification.rlrg.data.entity.Task;
import com.google.gson.annotations.SerializedName;

public class Tasks extends BaseEntity<Tasks.TaskList>
{
	public class TaskList
	{
		@SerializedName("Task")
		private List<Task> list = new ArrayList<Task>();
		
		public List<Task> getElements()
		{
			return list;
		}
	
		public void setElements(List<Task> list)
		{
			this.list = list;
		}
	}
}
