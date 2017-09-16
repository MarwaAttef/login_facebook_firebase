package com.example.marwa.saay2.data.user.source;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.marwa.saay2.CustomApplication;
import com.example.marwa.saay2.data.user.User;
import com.facebook.CallbackManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Marwa on 9/16/2017.
 */

public class UserRepository implements UserDataSource {

    private static UserRepository INSTANCE = null;

    private final UserDataSource userRemoteDataSource;
    private final UserDataSource userLocalDataSource;

    // Prevent direct instantiation.
    private UserRepository(@NonNull UserDataSource tasksRemoteDataSource, @NonNull UserDataSource tasksLocaleDataSource){
        userRemoteDataSource = tasksRemoteDataSource;
        userLocalDataSource=tasksLocaleDataSource;
    }


    public static UserRepository getInstance(UserDataSource userRemoteDataSource,UserDataSource userLocaleDataSource){
        if (INSTANCE == null) {
            INSTANCE = new UserRepository(userRemoteDataSource,userLocaleDataSource);
        }
        return INSTANCE;
    }


    @Override
    public void saveUser(User user, @NonNull UserGenericCallback callback) {

    }

    @Override
    public void getUser(CallbackManager facebookCallbackManager, Activity activity, @NonNull final UserGenericCallback callback) {
        //TODO: Login to facebook

        userRemoteDataSource.getUser(facebookCallbackManager,activity,new UserGenericCallback() {
            @Override
            public void onSuccess(User user) {
                callback.onSuccess(user);
            }

            @Override
            public void onServerError() {

            }

            @Override
            public void onConnectionError() {

            }

            @Override
            public void onAuthenticationError() {

            }

            @Override
            public void onCustomError(@NonNull String message) {

            }
        });
    }
}

