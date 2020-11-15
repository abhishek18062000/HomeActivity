package com.example.homeactivity.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.example.homeactivity.Profile.SignOutActivity;
import com.example.homeactivity.R;

public class CameraFragment extends Fragment {
    private static final String TAG = "CameraFragment";

    private TextView mSignOut;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camera, container, false);
        mSignOut=(TextView) view.findViewById(R.id.camera);

        mSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SignOutActivity.class);
                startActivity(intent);

            }
        });
        return view;
    }




}
