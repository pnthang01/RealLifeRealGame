package com.gamification.rlrg.data;

import java.util.ArrayList;
import java.util.List;

import com.gamification.rlrg.data.core.BaseEntity;
import com.gamification.rlrg.data.entity.Category;
import com.google.gson.annotations.SerializedName;

public class Categories extends BaseEntity<Categories.CategoryList>
{
	public class CategoryList
	{
		@SerializedName("Categories")
		private List<Category> list = new ArrayList<Category>();

		public List<Category> getElements()
		{
			return list;
		}

		public void setElements(List<Category> list)
		{
			this.list = list;
		}
	}
}
