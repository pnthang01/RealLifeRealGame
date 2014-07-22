package com.gamification.rlrg.data;

import java.util.ArrayList;
import java.util.List;

import com.gamification.rlrg.application.DataPreferencesManager;
import com.gamification.rlrg.application.RlrgApp;
import com.gamification.rlrg.data.core.BaseEntity;
import com.gamification.rlrg.data.entity.Category;
import com.gamification.rlrg.data.entity.Task;
import com.google.gson.Gson;
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
    
    public int getTaskCount(Category category)
    {
        int count = 0;
        for (Task task : getData().getElements())
        {
            if (task.getCategory().getName().equals(category.getName()))
            {
                count++;
            }
        }
        return count;
    }
    
    public int getTaskLastId()
    {
        List<Task> list = getData().getElements();
        return Integer.parseInt(list.get(list.size() - 1).getId());
    }

    public void addTask(String category, long completeTime, String difficultyLevel, String name, String point, String status)
    {
        String id = String.valueOf(getTaskLastId() + 1);
        Task task = new Task();
        task.setCategory(RlrgApp.getInstance().getCategories().getCatgory(category));
        if (completeTime != 0)
        {
            task.setCompleteTime(completeTime);
        }
        if (difficultyLevel != null)
        {
            task.setDifficultyLevel(difficultyLevel);
        }
        task.setId(id);
        if (name != null)
        {
            task.setName(name);
        }
        else
        {
            task.setName(category+ " " + id);
        }
        if (point != null)
        {
            task.setPoint(point);
        }
        if (status != null)
        {
            task.setStatus(status);
        }        
        getData().getElements().add(task);
        DataPreferencesManager.getInstance().saveJsonTasks(new Gson().toJson(this));
    }
}
