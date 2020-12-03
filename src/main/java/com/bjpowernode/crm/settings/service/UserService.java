package com.bjpowernode.crm.settings.service;

import com.bjpowernode.crm.exception.LoginException;
import com.bjpowernode.crm.settings.domain.User;

import java.util.List;

/**
 * @author jun
 * @date 2020/11/27 - 13:35
 */
public interface UserService {
    User login(String loginAct, String loginPsw, String ip) throws LoginException;

    List<User> getUserList();
}
