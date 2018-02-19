package com.lasalle.mdpa.busybudgeter.manager;

public interface UserManager {

    void loginUser(String username, String password);

    void updateUserPassword(String oldPassword, String newPassword);

}
