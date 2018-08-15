package com.example.armando.instapoo.Utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.armando.instapoo.Login.RegisterActivity;
import com.example.armando.instapoo.Models.User;
import com.example.armando.instapoo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;

/**
 * Created by Armando on 12/09/2017.
 */

public class FirebaseMethods {
    private static final String TAG = "FirebaseMethods";

    //firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String userID;

    private Context mContext;

    public FirebaseMethods(Context context){
        mAuth = FirebaseAuth.getInstance();
        mContext=context;

        if(mAuth.getCurrentUser() !=null){
            userID = mAuth.getCurrentUser().getUid();
        }
    }

    public boolean checIfUsernameExists(String username, DataSnapshot datasnapshot){
        Log.d(TAG, "checIfUsernameExists: checking if " + username + "already exists.");

        User user = new User();

        for (DataSnapshot ds:datasnapshot.getChildren()){
            Log.d(TAG, "checIfUsernameExists: datasnapshot"+ ds);

            user.setUsername(ds.getValue(User.class).getUsername());
            Log.d(TAG, "checIfUsernameExists: username"+ user.getUsername());

            if(StringManipulation.expandUsername(user.getUsername()).equals(username)){
                Log.d(TAG, "checIfUsernameExists: FOUND A MATH"+user.getUsername());
                return true;
            }
        }
        return true;
    }
    /**
     * Register a new email and password to Firebase Authentification
     * @param email
     * @param password
     * @param username
     */
    public void registerNewEmail(final String email, String password, final  String username){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.


                        if (!task.isSuccessful()) {
                            Toast.makeText(mContext, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }else if(task.isSuccessful()){
                            userID= mAuth.getCurrentUser().getUid();
                            Log.d(TAG, "onComplete: Authstate changed:" + userID);
                        }
                    }
                });
    }
}

