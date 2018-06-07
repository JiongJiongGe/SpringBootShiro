package com.shiro.util.token;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class ParseTokenUtil {

    private static Logger logger = LoggerFactory.getLogger(ParseTokenUtil.class);

    public static Integer parse(String X_userToken){
        Integer userID = -1;
        if(!StringUtils.isEmpty(X_userToken)) {
            String[] keylist = UserTokenDeviceUtil.checkDeviceAndReturnUserID(X_userToken);
            if (!"".equals(keylist[0])) {
                String fillID = keylist[0];
                String[] fillArr = fillID.split(",");
                userID = Integer.parseInt(fillArr[0]);
            }
        }
        return userID;
    }
}
