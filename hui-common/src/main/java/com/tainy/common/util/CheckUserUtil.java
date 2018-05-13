package com.tainy.common.util;

import com.tainy.common.domain.console.ConsoleUser;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Tainy
 * @date 2017/12/22
 */
public class CheckUserUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckUserUtil.class);

    public static ConsoleUser getUser(){
        Object obj = SecurityUtils.getSubject().getSession().getAttribute("currentUser");
        if(obj == null){
            return null;
        }
        return (ConsoleUser) obj;
    }

}
