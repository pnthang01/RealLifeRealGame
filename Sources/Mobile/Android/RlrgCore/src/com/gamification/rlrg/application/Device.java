package com.gamification.rlrg.application;

import java.security.MessageDigest;

import nghiem.app.core.utils.Utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;


public class Device
{
    private static Device sInstance;
    
    private Context mContext;
    
    public static Device getInstance()
    {
        if (sInstance == null)
        {
            synchronized(Device.class)
            {
                if (sInstance == null)
                {
                    sInstance = new Device();
                }
            }            
        }
        return sInstance;
    }

    
    private Device()
    {
        mContext = CoreApp.getInstance();
    }
    
    public String getKeystoreHashkey(String packageId)
    {
        try
        {
            PackageInfo info = mContext.getPackageManager().getPackageInfo(packageId, PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures)
            {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                return Base64.encodeToString(md.digest(), Base64.DEFAULT);
            }
        }
        catch (Exception e)
        {
            Utils.logException(e);
        }
        return "";
    }

    public int getScreenWidth()
    {
        DisplayMetrics displayMetrics = getCurrentDisplayMetrics();
        return Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    public int getScreenHeight()
    {
        DisplayMetrics displayMetrics = getCurrentDisplayMetrics();
        return Math.max(displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    public float getScreenDensity()
    {
        return getCurrentDisplayMetrics().density;
    }

    private DisplayMetrics getCurrentDisplayMetrics()
    {
        WindowManager sWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = sWindowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        return displayMetrics;
    }
}
