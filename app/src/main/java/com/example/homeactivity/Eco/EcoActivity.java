package com.example.homeactivity.Eco;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.homeactivity.Login.LoginActivity;
import com.example.homeactivity.Login.RegisterActivity;
import com.example.homeactivity.Profile.SignOutActivity;
import com.example.homeactivity.R;
import com.example.homeactivity.utils.BottomNavigationViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class EcoActivity extends AppCompatActivity{
    private static final String TAG = "EcoActivity";
    private Context mcontext= EcoActivity.this;
    private static final int ACTIVITY_NUM =2;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupBottomNavigationView();

    }
    private void setupBottomNavigationView(){
        Log.d(TAG, "setupBottoNavigationView: setting up bottom navigation view");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mcontext,this , bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }


}
