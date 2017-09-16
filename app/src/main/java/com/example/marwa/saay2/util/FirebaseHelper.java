package com.example.marwa.saay2.util;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.facebook.AccessToken;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Marwa on 9/16/2017.
 */

public class FirebaseHelper {

    private Activity activity;
    public static final String TAG = "FacebookHelper";

    public FirebaseHelper(Activity activity) {
        this.activity = activity;
    }

    public void saveFaceBookToFireBaseAuth(Activity activity, AccessToken token) {
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());

                        } else {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.d(TAG, "saveFaceBookToFireBaseAuth: userEmail " + user.getEmail());
                        }
                    }
                });
    }
}
