package com.example.homeactivity.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.example.homeactivity.Login.LoginActivity;
import com.example.homeactivity.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignOutActivity extends AppCompatActivity {
    private static final String TAG = "SignOutActivity";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private Button tvSignout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signout);
        setupFirebaseAuth();
        tvSignout=(Button) findViewById(R.id.signOutCompletely);

        setupFirebaseAuth();
        tvSignout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mAuth.signOut();
                SignOutActivity.this.finish();
            }
        });

    }

    private void setupFirebaseAuth(){
        mAuth = FirebaseAuth.getInstance();
        mAuthListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user!=null){
                    Log.d(TAG,"USER logged in"+ user.getUid());
                }
                else{
                    Log.d(TAG,"USER not logged in");
                    Intent intent = new Intent(SignOutActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        };
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}
