package com.lasalle.mdpa.busybudgeter.view;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.lasalle.mdpa.busybudgeter.R;
import com.lasalle.mdpa.busybudgeter.view.model.UserLoginViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.config.Module;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.username) EditText usernameEditText;
    @BindView(R.id.password) EditText passwordEditText;

    @Inject UserLoginViewModel userLoginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("TestThing", "Activity OnCreate");
        super.onCreate(savedInstanceState);

        userLoginViewModel = ViewModelProviders.of(this).get(UserLoginViewModel.class);

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_button)
    public void onLoginButtonClicked() {
        try {
            userLoginViewModel.OnLoginUser(usernameEditText.getText().toString(), passwordEditText.getText().toString());
        }
        catch (IllegalArgumentException e) {
            Toast.makeText(this, R.string.login_empty, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        Log.d("TestThing", "Activity onDestroy");
        super.onDestroy();
    }
}
