package com.lasalle.mdpa.busybudgeter;

import android.app.Application;

import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.config.Module;

public class BudgetingApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Scope applicationScope = Toothpick.openScope(this);
        applicationScope.installModules(new Module() {
            //TODO: fill when needed
        });
    }
}
