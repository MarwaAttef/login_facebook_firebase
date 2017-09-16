package com.example.marwa.saay2.login;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.marwa.saay2.data.user.User;
import com.example.marwa.saay2.data.user.source.UserDataSource;
import com.example.marwa.saay2.data.user.source.UserRepository;
import com.facebook.CallbackManager;

/**
 * Created by Marwa on 9/16/2017.
 */

public class LoginPresenter implements LoginContract.Presenter {
    private final String TAG = "LoginPresenter";
    private final UserRepository userRepository;
    private final LoginContract.View loginView;


    public LoginPresenter(@NonNull UserRepository userRepository, @NonNull LoginContract.View loginView) {
        this.userRepository = userRepository;
        this.loginView = loginView;
        this.loginView.setPresenter(this);
    }

    @Override
    public void loginFacebookUser(CallbackManager facebookCallbackManager, Activity activity) {
        loginView.setLoadingIndicator(true);

        userRepository.getUser(facebookCallbackManager,activity,new UserDataSource.UserGenericCallback() {
            @Override
            public void onSuccess(User user) {
                loginView.setLoadingIndicator(false);
                if(user!=null && user.getUserName()!=null) {
                    Log.d(TAG, user.getUserName());
                }
                loginView.navigateToMainActivity();
            }

            @Override
            public void onServerError() {
                loginView.setLoadingIndicator(false);
                loginView.showServerErrorMessage();

            }

            @Override
            public void onConnectionError() {
                loginView.setLoadingIndicator(false);

            }

            @Override
            public void onAuthenticationError() {
                loginView.setLoadingIndicator(false);
                loginView.showAuthenticationErrorMessage();

            }

            @Override
            public void onCustomError(@NonNull String message) {
                loginView.setLoadingIndicator(false);
                loginView.showMessage(message);

            }
        });

    }

    @Override
    public void start() {

    }
}
