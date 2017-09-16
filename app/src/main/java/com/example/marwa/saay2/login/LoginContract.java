package com.example.marwa.saay2.login;

import android.app.Activity;

import com.example.marwa.saay2.BasePresenter;
import com.example.marwa.saay2.BaseView;
import com.facebook.CallbackManager;

/**
 * Created by Marwa on 9/16/2017.
 */


public interface LoginContract {

    interface View extends BaseView<Presenter> {
        void setLoadingIndicator(boolean active);

        void navigateToMainActivity();

    }

    interface Presenter extends BasePresenter {

        void loginFacebookUser(CallbackManager facebookCallbackManager, Activity activity);
    }
}
