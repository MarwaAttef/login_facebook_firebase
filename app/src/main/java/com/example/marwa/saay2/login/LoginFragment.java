package com.example.marwa.saay2.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.marwa.saay2.BaseFragment;
import com.example.marwa.saay2.R;
import com.facebook.CallbackManager;

/**
 * Created by Marwa on 9/16/2017.
 */


public class LoginFragment extends BaseFragment implements LoginContract.View {
    public Button btnFacebookLogin;
    String TAG = "LoginFragment";
    private ProgressBar progress;
    private LoginContract.Presenter presenter;
    CallbackManager facebookCallbackManager;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_login, container, false);
        btnFacebookLogin = (Button) root.findViewById(R.id.login_button);
        setUpLoginClickListener();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (presenter != null)
            presenter.start();
    }

    private void setUpLoginClickListener() {
        facebookCallbackManager = CallbackManager.Factory.create();

        btnFacebookLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.loginFacebookUser(facebookCallbackManager, getActivity());
            }
        });

    }

    @Override
    protected void onFragmentResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG,String.valueOf(requestCode));
        facebookCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void navigateToMainActivity() {

    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        if (presenter != null)
            this.presenter = presenter;

    }

    @Override
    public void showServerErrorMessage() {

    }

    @Override
    public void showAuthenticationErrorMessage() {

    }

    @Override
    public void showConnectionErrorMessage() {

    }

    @Override
    public void showMessage(String message) {

    }
}
