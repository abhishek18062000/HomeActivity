package com.example.homeactivity.Login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homeactivity.Home.MainActivity;
import com.example.homeactivity.HomeActivity;
import com.example.homeactivity.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private Context mContext;

    private EditText mEmail, mPassword;
    private TextView mRegisterHere;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acttivity_login);
        mEmail=(EditText) findViewById(R.id.loginEmail);
        mPassword=(EditText) findViewById(R.id.loginPassword);
        mContext = LoginActivity.this;

        setupFirebaseAuth();
        init();
    }
    private boolean isStringNull(String string){
        if(string.equals("")){
            return true;
        }
        else {
            return false;
        }
    }



    private void init(){
        Button btnLogin = (Button) findViewById(R.id.logins);
        btnLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String email= mEmail.getText().toString();
                String password= mPassword.getText().toString();

                if(isStringNull(email) && isStringNull(password)){
                    Toast.makeText(mContext, "you must full all fields", Toast.LENGTH_SHORT).show();
                }
                else
                    {
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "unSUCCESSFUL",Toast.LENGTH_SHORT).show();
                            }
                            else {
                               Toast.makeText(LoginActivity.this,"SUCCESS",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        mRegisterHere = (TextView) findViewById(R.id.register_here);
        mRegisterHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });



        if(mAuth.getCurrentUser()!=null){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
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
