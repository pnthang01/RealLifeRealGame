package com.team.common;

// infor user login here 
import com.team.domain.User;
import java.util.Date;

public class UserToken {

    private int id;
    private String name;
    private long time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void increaseTime() {
        this.time += (60 * 30 * 1000);
    }

    public UserToken convertUser2UserToken(User user) {
        this.setId(user.getId());
        this.time = new Date().getTime();
        this.name = user.getFirstName();
        return this;
    }

}
