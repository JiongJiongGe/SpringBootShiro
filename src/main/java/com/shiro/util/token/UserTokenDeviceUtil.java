package com.shiro.util.token;

/**
 * Created by yunkai on 2017/10/17.
 */
public class UserTokenDeviceUtil {

    public static String[] checkDeviceAndReturnUserID(String userToken) {
        String [] list = new String[2];
        list[0]="";
        list[1]="";
        if(userToken != null && !("".equals(userToken))) {
            String idEncoding = userToken.substring(0, 44);
            String keyString = userToken.substring(44);

            String userID = DESUtil.decoding(idEncoding);
            list[0] = userID;
            //key
            list[1] = "";
        }
        return list;
    }

}
