package com.gamification.rlrg.assests;

import java.io.IOException;
import java.util.Scanner;

import com.gamification.rlrg.application.CoreApp;

import android.util.Log;


public class AssestsHelper
{
	public static final String TAG = AssestsHelper.class.getName();
	
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
        	Log.d(TAG, "Load data from " + path + " error!");
        	return null;
        }
	}
}
