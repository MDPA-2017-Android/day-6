package com.lasalle.mdpa.busybudgeter.view.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.google.common.base.Charsets;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import com.lasalle.mdpa.busybudgeter.manager.UserManager;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.google.common.base.Preconditions.checkArgument;

@Singleton
public class LoggedUserViewModel extends ViewModel {

    @Inject UserManager userManager;

    private MutableLiveData<User> user;
    private String userOldPassword;

    public LoggedUserViewModel() {
        this.user = new MutableLiveData<>();
    }

    public LiveData<User> getUser() {
        return user;
    }

    public void updateUserPassword(String newPassword) {
        checkArgument(newPassword != null && !newPassword.isEmpty(), "Password parameter must not be null or empty");

        Hasher hasher = Hashing.sha256().newHasher();
        hasher.putString(newPassword, Charsets.UTF_8);

        //TODO: check they can not be the same

        userManager.updateUserPassword(userOldPassword, hasher.hash().toString());
    }

    // Inner class
    public class User {
        public String name;
        public String lastname;
        public String username;
    }
}
