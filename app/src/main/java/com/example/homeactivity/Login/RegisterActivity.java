package com.example.homeactivity.Login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homeactivity.R;
import com.example.homeactivity.utils.FirebaseMethods;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseMethods firebaseMethods;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;

    private Context mContext;
    private String email, username, password,repassword,city;
    private String phoneNumber;
    private EditText mEmail, mPassword, mName, mRePassword, mCity, mPhoneNumber;
    private TextView mLoginHere;
    private Button mRegister;
    private String append="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mContext=RegisterActivity.this;
        firebaseMethods=new FirebaseMethods(mContext);

        initWidgets();
        setupFirebaseAuth();
        init();
    }

    private void init() {
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = mEmail.getText().toString();
                username = mName.getText().toString();
                password = mPassword.getText().toString();
                repassword = mRePassword.getText().toString();
                city = mCity.getText().toString();
                phoneNumber = mPhoneNumber.getText().toString();

                if(checkInputs(email, username,password,repassword,city,phoneNumber)){
                    firebaseMethods.registerNewEmail(email,password,username, city, phoneNumber);
                }

            }
        });
    }
        private Boolean checkInputs(String email, String username, String password, String repassword, String city,String phoneNumber){
            if(email.equals("") || username.equals("") || password.equals("") || repassword.equals("") || city.equals("") || phoneNumber.equals("")) {
                Toast.makeText(mContext, "Fill all the fields", Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
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
        mFirebaseDatabase=FirebaseDatabase.getInstance();
        myRef=mFirebaseDatabase.getReference();
        mAuthListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user!=null){
                    Log.d(TAG,"USER logged in"+ user.getUid());


                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(firebaseMethods.checkIfUsernameExists(username, dataSnapshot)){
                                append=myRef.push().getKey().substring(3,10);
                            }

                            username=username+append;

                            firebaseMethods.addNewUser(city, email,username, Long.parseLong(phoneNumber),"",username,"");
                            Toast.makeText(mContext, "Successful signup. email verification sent", Toast.LENGTH_SHORT).show();
                            mAuth.signOut();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    finish();
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
