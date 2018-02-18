package com.lasalle.mdpa.busybudgeter.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lasalle.mdpa.busybudgeter.R;
import com.lasalle.mdpa.busybudgeter.view.model.UserLoginViewModel;

import javax.inject.Inject;

import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.config.Binding;
import toothpick.config.Module;

public class LoginActivity extends AppCompatActivity {

    private Scope scope;

    @Inject UserLoginViewModel userLoginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        scope = Toothpick.openScopes(getApplication(), this);
        scope.installModules(new Module() {{
            bind(UserLoginViewModel.class);
        }});
        super.onCreate(savedInstanceState);
        Toothpick.inject(this, scope);

        setContentView(R.layout.activity_login);

        //TODO: remove, only to test if it works
        userLoginViewModel.OnLoginUser("1234","1234");
    }

    @Override
    protected void onDestroy() {
        Toothpick.closeScope(this);
        super.onDestroy();
    }
}
