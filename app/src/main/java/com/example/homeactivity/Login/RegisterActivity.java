package com.example.homeactivity.Login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.homeactivity.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private Context mContext;
    private EditText mEmail, mPassword, mName, mRePassword, mCity, mPhoneNumber;
    private TextView mLoginHere;
    private Button mRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupFirebaseAuth();
        initWidgets();
    }

    private void initWidgets(){
        mEmail=(EditText) findViewById(R.id.regemail);
        mPassword=(EditText) findViewById(R.id.regpassword);
        mRePassword=(EditText) findViewById(R.id.reEnterPassword);
        mName=(EditText) findViewById(R.id.fullName);
        mCity=(EditText) findViewById(R.id.city);
        mPhoneNumber=(EditText) findViewById(R.id.phoneNumber);
        mRegister=(Button) findViewById(R.id.register);
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
        mAuth.removeAuthStateListener(mAuthListener);
    }

}
