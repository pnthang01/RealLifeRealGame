package com.gamification.rlrg.application;

public class Defination
{
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
    	
    	public class ScreenResolution
    	{
    	    /** Indicate that screen width <= 320 */
    	    public static final String RESOLUTION_LOW = "sw320";

    	    /** Indicate that 320 < screen width <= 480 */
    	    public static final String RESOLUTION_NORMAL = "sw480";

    	    /** Indicate that 480 < screen width <= 640 */
    	    public static final String RESOLUTION_HIGH = "sw640";

    	    /** Indicate that 640 < screen width <= 800 */
    	    public static final String RESOLUTION_XHIGH = "sw800";

    	    /** Indicate that 800 < screen width */
    	    public static final String RESOLUTION_XXHIGH = "sw1024";
    	}
    }
}
