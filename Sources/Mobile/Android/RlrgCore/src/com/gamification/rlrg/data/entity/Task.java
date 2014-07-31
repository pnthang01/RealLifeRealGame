package com.gamification.rlrg.data.entity;

import org.joda.time.DateTime;

import com.google.gson.annotations.SerializedName;

public class Task
{
    public static final String DIFFICULTY_LEVEL_EASY = "EASY";
    public static final String DIFFICULTY_LEVEL_NORMAL = "NORMAL";
    public static final String DIFFICULTY_LEVEL_HARD = "HARD";
    public static final String STATUS_COMPLETED = "COMPLETED";
    public static final String STATUS_NOTCOMPLETED = "NOTCOMPLETED";

    @SerializedName("ID")
    private String id = "";

    @SerializedName("Category")
    private Category category = new Category();

    @SerializedName("Name")
    private String name = "Unknown";

    @SerializedName("Complete Time")
    private long completeTime = DateTime.now().getMillis();

    @SerializedName("Difficulty Level")
    private String difficultyLevel = DIFFICULTY_LEVEL_EASY;

    @SerializedName("Status")
    private String status = STATUS_NOTCOMPLETED;

    @SerializedName("Point")
    private String point = "0";

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public long getCompleteTime()
    {
        return completeTime;
    }

    public void setCompleteTime(long completeTime)
    {
        this.completeTime = completeTime;
    }

    public String getDifficultyLevel()
    {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel)
    {
        this.difficultyLevel = difficultyLevel;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getPoint()
    {
        return point;
    }

    public void setPoint(String point)
    {
        this.point = point;
    }
}
