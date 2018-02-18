package com.lasalle.mdpa.busybudgeter.view.model;

import android.util.Log;

import com.google.common.base.Charsets;
import com.google.common.base.Utf8;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import com.lasalle.mdpa.busybudgeter.manager.UserManager;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.google.common.base.Preconditions.checkArgument;

public class UserLoginViewModel {

    private UserManager userManager;

    public UserLoginViewModel(UserManager userManager) {
        this.userManager = userManager;
    }

    public void OnLoginUser(String username, String password) throws IllegalArgumentException {
        checkArgument(username != null && !username.isEmpty(), "Username parameter must not be null or empty");
        checkArgument(password != null && !password.isEmpty(), "Password parameter must not be null or empty");

        Hasher hasher = Hashing.sha256().newHasher();
        hasher.putString(password, Charsets.UTF_8);
        userManager.LoginUser(username, hasher.hash().toString());
    }
}
