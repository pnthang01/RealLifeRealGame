package com.rlrg.test.code;

import com.rlrg.dataserver.base.controller.BaseUtils;
import com.rlrg.dataserver.profile.entity.enums.UserStatus;
import com.rlrg.dataserver.task.entity.enums.TaskStatus;

public class ProfileTest {
    public static void main( String[] args )
    {
    	System.out.println(UserStatus.BLOCKED.name());
    	System.out.println(BaseUtils.md5("passwordcreate"));
    	
    	System.out.println(TaskStatus.PROGRESSING.toString());
    }
}
