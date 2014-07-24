package com.gamification.rlrg.settings;

public class Settings
{
	public static final String DATE_FORMAT = "dd/MM/yyyy";
	public static final String DATETIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
	public static final String PASSWORD_COVER = "cb63a03774fdf986";

	public class Assets
	{
		public class Data
		{
			/** Path of data folder in assets */
			public static final String PATH = "data/";

			/** Path of users json in assets */
			public static final String USERS = PATH + "users.json";

			/** Path of badges json in assets */
			public static final String BADGES = PATH + "badges.json";

			/** Path of categories json in assets */
			public static final String CATEGORIES = PATH + "categories.json";

			/** Path of tasks json in assets */
			public static final String TASKS = PATH + "tasks.json";

			/** Path of achievements json in assets */
			public static final String ACHIEVEMENTS = PATH + "achievements.json";
		}
	}
}
