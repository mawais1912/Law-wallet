package com.livewall.lawwalletfinalyearproject.UserActivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.livewall.lawwalletfinalyearproject.R;

public class LawDiraryDetailsFragment extends Fragment {

    private WebView webView;
    public LawDiraryDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_law_dirary_details, container, false);
        webview(view);
        return  view;
    }
    private void webview(View view){
        webView =view. findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Enable JavaScript

        // Load a website
        webView.loadUrl("https://pakistancode.gov.pk/english/LGu0xVD-apaUY2Fqa-ag%3D%3D&action=primary&catid=1");
    }
}