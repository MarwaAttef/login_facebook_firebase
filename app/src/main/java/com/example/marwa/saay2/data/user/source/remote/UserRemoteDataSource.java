package com.example.marwa.saay2.data.user.source.remote;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.marwa.saay2.CustomApplication;
import com.example.marwa.saay2.data.user.User;
import com.example.marwa.saay2.data.user.source.UserDataSource;
import com.example.marwa.saay2.util.FacebookHelper;
import com.example.marwa.saay2.util.FirebaseHelper;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * Created by Marwa on 9/16/2017.
 */

public class UserRemoteDataSource implements UserDataSource {

    private static UserRemoteDataSource INSTANCE;
    private String TAG = "UserRemoteDataSource";
    private List<String> desiredPermissions;
    private FacebookHelper facebookHelper;
    private FirebaseHelper firebaseHelper;

    public static UserRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserRemoteDataSource();
        }
        return INSTANCE;
    }


    @Override
    public void saveUser(final User user, @NonNull final UserGenericCallback callback) {
    }

    @Override
    public void getUser(CallbackManager facebookCallbackManager, Activity activity, @NonNull UserGenericCallback callback) {
        facebookHelper = new FacebookHelper(activity);
        if (facebookHelper.isFacebookAppExists()) {
            desiredPermissions = Arrays.asList(facebookHelper.EMAIL, facebookHelper.PUBLIC_PROFILE);
            facebookHelper.loginWithFacebook(desiredPermissions);
            facebookHelper.registerFacebookCallBack(activity, facebookCallbackManager, callback);
        } else {
            //TODO no facebook App
        }

    }
}


