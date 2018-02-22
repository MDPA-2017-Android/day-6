package com.lasalle.mdpa.busybudgeter.view.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.google.common.base.Charsets;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import com.lasalle.mdpa.busybudgeter.manager.UserManager;
import com.lasalle.mdpa.busybudgeter.model.User;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.google.common.base.Preconditions.checkArgument;

@Singleton
public class LoggedUserViewModel extends ViewModel {

    @Inject UserManager userManager;

    private MutableLiveData<UserPresentationData> user;
    private String userOldPassword;

    public LoggedUserViewModel() {
        this.user = new MutableLiveData<>();
    }

    public LiveData<UserPresentationData> getUser() {
        return user;
    }

    public void retrieveUserData() {
        User retrievedUser = userManager.retrieveUserData();
        userOldPassword = retrievedUser.getPassword();

        UserPresentationData userData = new UserPresentationData(retrievedUser );
        user.setValue(userData);
    }

    public void updateUserPassword(String newPassword) {
        checkArgument(newPassword != null && !newPassword.isEmpty(), "Password parameter must not be null or empty");

        Hasher hasher = Hashing.sha256().newHasher();
        hasher.putString(newPassword, Charsets.UTF_8);

        String calculatedSha256Password = hasher.hash().toString();
        //TODO: check they can not be the same

        checkArgument( !userOldPassword.equals(calculatedSha256Password), "Password can not be the same");


        userManager.updateUserPassword(userOldPassword, calculatedSha256Password);
    }

    // Inner class
    public class UserPresentationData {
        public String name;
        public String lastname;
        public String username;

        public UserPresentationData(com.lasalle.mdpa.busybudgeter.model.User systemUser) {
            name = systemUser.getName();
            lastname = systemUser.getLastName();
            username = systemUser.getUsername();
        }
    }
}
