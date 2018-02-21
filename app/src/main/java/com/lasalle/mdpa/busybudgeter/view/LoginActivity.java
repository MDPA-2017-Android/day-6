package com.lasalle.mdpa.busybudgeter.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.lasalle.mdpa.busybudgeter.R;
import com.lasalle.mdpa.busybudgeter.view.model.UserLoginViewModel;

import javax.inject.Inject;

import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.config.Module;

public class LoginActivity extends AppCompatActivity {

    @Inject UserLoginViewModel userLoginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Scope scope = Toothpick.openScopes(getApplication(), this);
        scope.installModules(new Module() {{
            bind(UserLoginViewModel.class);
        }});

        super.onCreate(savedInstanceState);
        Toothpick.inject(this, scope);

        setContentView(R.layout.activity_login);

        findViewById(R.id.login_button).setOnClickListener(view -> onLoginButtonClicked());
    }

    public void onLoginButtonClicked() {
        EditText usernameEditText = findViewById(R.id.username);
        EditText passwordEditText = findViewById(R.id.password);

        try {
            userLoginViewModel.OnLoginUser(usernameEditText.getText().toString(), passwordEditText.getText().toString());
        }
        catch (IllegalArgumentException e) {
            Toast.makeText(this, R.string.login_empty, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        Toothpick.closeScope(this);
        super.onDestroy();
    }
}
