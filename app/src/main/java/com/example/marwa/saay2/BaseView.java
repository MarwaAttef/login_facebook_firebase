package com.example.marwa.saay2;

/**
 * Created by Marwa on 9/16/2017.
 */


public interface BaseView<T> {
    void setPresenter(T presenter);

    void showServerErrorMessage();

    void showAuthenticationErrorMessage();

    void showConnectionErrorMessage();

    void showMessage(String message);
}
