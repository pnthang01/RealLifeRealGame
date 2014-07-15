package com.gamification.rlrg.application;

import java.io.IOException;
import java.util.Scanner;

import nghiem.app.core.utils.Utils;


public class AssestsManager
{
	public static final String TAG = AssestsManager.class.getName();
	
	@SuppressWarnings("resource")
	public static String getData(String path)
	{
		try
        {
            Scanner scanner = new Scanner(CoreApp.getInstance().getAssets().open(path)).useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        }
        catch (IOException e)
        {
        	Utils.logError(TAG, "Load data from " + path + " error!", e);
        	return null;
        }
	}
}
