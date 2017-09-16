package com.example.marwa.saay2.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.marwa.saay2.BaseActivity;
import com.example.marwa.saay2.R;
import com.example.marwa.saay2.data.user.source.UserRepository;
import com.example.marwa.saay2.data.user.source.locale.UserLocaleDataSource;
import com.example.marwa.saay2.data.user.source.remote.UserRemoteDataSource;
import com.example.marwa.saay2.util.ActivityUtils;


public class LoginActivity extends BaseActivity {
    LoginFragment loginFragment;
    LoginPresenter loginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void startFragment() {
        loginFragment =
                (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (loginFragment == null) {
            // Create the fragment
            loginFragment = LoginFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), loginFragment, R.id.contentFrame);
            // Create the presenter
            loginPresenter = new LoginPresenter(UserRepository.getInstance(UserRemoteDataSource.getInstance(), UserLocaleDataSource.getInstance()), loginFragment);
        }
    }

    @Override
    protected void setToolBarSearchView() {

    }

    @Override
    protected void setToolbar() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginFragment.onFragmentResult(requestCode, resultCode, data);
    }
}
