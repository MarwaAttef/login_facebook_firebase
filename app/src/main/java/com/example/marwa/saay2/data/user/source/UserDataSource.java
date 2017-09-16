package com.example.marwa.saay2.data.user.source;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.example.marwa.saay2.data.user.User;
import com.facebook.CallbackManager;

/**
 * Created by Marwa on 9/16/2017.
 */

public interface UserDataSource {

    interface UserGenericCallback {
        void onSuccess(User user);

        void onServerError();

        void onConnectionError();

        void onAuthenticationError();

        void onCustomError(@NonNull String message);
    }


    void saveUser(User user,@NonNull UserGenericCallback callback);

    void getUser(CallbackManager facebookCallbackManager, Activity activity, @NonNull UserGenericCallback callback);
}
