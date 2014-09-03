package com.rlrg.dataserver.utillities;

public interface Constants {
	public static final int DEFAULT_CREATE_TASK_POINTS = 2;
	public static final int DEFAULT_COMPLETED_TASK_POINTS = 4;
	public static final int DEFAULT_NOTCOMPLETED_TASK_POINTS = -5;
	public static final int DEFAULT_DELETED_TASK_POINTS = -5;
	
	public static final int PAGE_SIZE = 10;
	public static final int DEFAULT_ROLE = 2;
	public static final String DEFAULT_I18N = "vi_VN";
	public static final long TOKEN_TIMEOUT = 10000;
	public static final long TOKEN_INCREASEMENT = 24 * 60 * 60 * 1000;
	
	public static final int TOTAL_STATISTIC_ROWS = 4;
	
	public static final String PAGE_SIZE_KEY = "PAGE_SIZE";
	public static final String TOTAL_STATISTIC_ROWS_KEY = "TOTAL_STATISTIC_ROWS";
	public static final String STATIC_RESOURCES_URI_KEY = "STATIC_RESOURCES_URI";
}
