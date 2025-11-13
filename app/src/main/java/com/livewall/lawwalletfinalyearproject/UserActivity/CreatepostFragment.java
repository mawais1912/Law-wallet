package com.livewall.lawwalletfinalyearproject.UserActivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.livewall.lawwalletfinalyearproject.R;


public class CreatepostFragment extends Fragment {



    public CreatepostFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_createpost, container, false);
    }
}