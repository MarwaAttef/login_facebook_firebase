package com.example.marwa.saay2.util;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.marwa.saay2.data.user.User;
import com.example.marwa.saay2.data.user.source.UserDataSource;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * Created by Marwa on 9/16/2017.
 */

public class FacebookHelper {
    private Activity context;
    public static final String EMAIL = "email";
    public static final String PUBLIC_PROFILE = "public_profile";
    public static final String TAG = "FacebookHelper";
    public static final String NAME = "name";
    public static final String ID = "id";
    public static final String GENDER = "gender";
    public static final String FIELDS = "fields";

    public FacebookHelper(Activity context) {
        this.context = context;
    }

    public void loginWithFacebook(List<String> desiredPermissions) {
        LoginManager.getInstance().logInWithReadPermissions(
                context, desiredPermissions
        );

    }

    public Boolean isFacebookAppExists() {
        try {
            ApplicationInfo info = context.getPackageManager().
                    getApplicationInfo("com.facebook.katana", 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public void fetchUserDataByToken(AccessToken token, String desiredDataCommaSeparated, final FacebookUserDataCallBack callback) {
        GraphRequest request = GraphRequest.newMeRequest(
                token, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object,
                                            GraphResponse response) {
                        Log.d(TAG, "fetchUserDataByToken:onCompleted " + response.toString());
                        callback.onCompleted(object);
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString(FIELDS, desiredDataCommaSeparated);
        request.setParameters(parameters);
        request.executeAsync();
    }

    public interface FacebookUserDataCallBack {
        public void onCompleted(JSONObject result);
    }

    public void registerFacebookCallBack(final Activity activity, CallbackManager facebookCallbackManager, @NonNull final UserDataSource.UserGenericCallback callback) {

    LoginManager.getInstance().

    registerCallback(facebookCallbackManager,
                     new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess ( final LoginResult loginResult){
            // App code
            fetchUserDataByToken(loginResult.getAccessToken(), "id, name, email, gender", new FacebookHelper.FacebookUserDataCallBack() {

                @Override
                public void onCompleted(JSONObject result) {
//
                    //TODO: return callback
                    Log.d(TAG, "registerFacebookCallBack: jsonResult" + result.toString());
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        User user = mapper.readValue(result.toString(), User.class);
                       user.setUserPhoto("https://graph.facebook.com/" + user.getUserId() + "/picture?type=large");
                        callback.onSuccess(user);
                        FirebaseHelper  firebaseHelper = new FirebaseHelper(activity);
                        firebaseHelper.saveFaceBookToFireBaseAuth(activity, loginResult.getAccessToken());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
        }

        @Override
        public void onCancel () {

        }

        @Override
        public void onError (FacebookException error){

        }
    }

    );
}
}
