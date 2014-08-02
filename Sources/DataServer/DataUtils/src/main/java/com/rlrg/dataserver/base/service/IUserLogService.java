package com.rlrg.dataserver.base.service;

import java.util.List;

public interface IUserLogService<T> {
    public void logUserAction(Long userId, String action);
    
    public List<T> getUserLogByUserId(Long userId);
    
    public Long countUserLogByUserIdAndAction(Long userId, String action);
    
    public Long countLoginActionByTime(int month, int year);
}
