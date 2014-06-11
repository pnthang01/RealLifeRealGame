package com.rlrg.dataserver.utils.base.domain;

import java.util.Date;

public class UserToken {
    private Long id;
    private String name;
    private long time;

    public UserToken(){
    }
    
    public UserToken(Long id, String name, long time){
    	this.id = id;
    	this.name = name;
    	this.time = time;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
