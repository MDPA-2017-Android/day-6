package com.lasalle.mdpa.busybudgeter.manager.impl;

import com.lasalle.mdpa.busybudgeter.manager.UserManager;

import javax.inject.Singleton;

@Singleton
public class UserManagerImpl implements UserManager {

    @Override
    public void loginUser(String username, String password) {
        int i = 0;
        i++;
    }

    @Override
    public void updateUserPassword(String oldPassword, String newPassword) {

    }
}
