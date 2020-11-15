package com.example.homeactivity.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.homeactivity.R;
import com.example.homeactivity.models.User;
import com.example.homeactivity.models.UserAccountSettings;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseMethods {
    private static final String TAG = "FirebaseMethod";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private String userID;

    private Context mContext;

    public FirebaseMethods(Context context) {
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        mContext = context;
        mAuth = FirebaseAuth.getInstance();


        if (mAuth.getCurrentUser() != null) {
            userID = mAuth.getCurrentUser().getUid();
        }
    }

    public boolean checkIfUsernameExists(String username, DataSnapshot dataSnapshot) {
        User user = new User();
        for (DataSnapshot ds : dataSnapshot.child(userID).getChildren()) {
            user.setUsername(ds.getValue(User.class).getUsername());
            if (StringManuplation.expandUsername(user.getUsername()).equals(username)) {
                return true;
            }
        }
        return false;
    }

    public void registerNewEmail(final String email, String password, final String username, String city, String phoneNumber) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(mContext, "authentication failed", Toast.LENGTH_SHORT).show();

                        } else if (task.isSuccessful()) {
                            sendVerificationEmail();
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:success", task.getException());
                            userID = mAuth.getCurrentUser().getUid();
                        }
                    }
                });
    }

    public void sendVerificationEmail(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user!=null){
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){

                    }else{
                        Toast.makeText(mContext, "could'nt send email verification", Toast.LENGTH_SHORT).show();

                    }
                }
            });

        }
    }

    public void addNewUser(String city, String email, String username, long phone_number,String description, String display_name, String profile_photo) {

        User user = new User(city, email, 4, 6, phone_number, userID, StringManuplation.condenseUsername(username));

        myRef.child(mContext.getString(R.string.dbname_user)).child(userID).setValue(user);


        UserAccountSettings userAccountSettings = new UserAccountSettings(description, display_name, 0, 0, 0, profile_photo, StringManuplation.condenseUsername(username));

        myRef.child(mContext.getString(R.string.dbname_user_account_settings)).child(userID).setValue(userAccountSettings);
    }


}