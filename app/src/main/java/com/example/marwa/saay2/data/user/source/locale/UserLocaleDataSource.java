package com.example.marwa.saay2.data.user.source.locale;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.example.marwa.saay2.data.user.User;
import com.example.marwa.saay2.data.user.source.UserDataSource;
import com.facebook.CallbackManager;

/**
 * Created by Marwa on 9/16/2017.
 */

public class UserLocaleDataSource implements UserDataSource {
    private static UserLocaleDataSource INSTANCE;
    private String TAG = "UserLocaleDataSource";
    private UserPreferencesHelper helper;

    private UserLocaleDataSource() {
        helper = new UserPreferencesHelper();
    }

    public static UserLocaleDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserLocaleDataSource();
        }
        return INSTANCE;
    }


    @Override
    public void saveUser(User user, @NonNull UserGenericCallback callback) {

    }

    @Override
    public void getUser(CallbackManager facebookCallbackManager, Activity activity, @NonNull UserGenericCallback callback) {

    }
}
