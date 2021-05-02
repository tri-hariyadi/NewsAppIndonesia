package com.example.newsapp.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.newsapp.R;

public class ProfileFragment extends Fragment {
    View RootView;
    Button BtnSendEmail;
//    private int dpWidth;
//    private float dDensity;
//    private int designWidth = 375;
//    private int designHeight = 812

    public static ProfileFragment newInstance(String param1, String param2) {
        return new ProfileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RootView = inflater.inflate(R.layout.fragment_profile, container, false);
//        getActivity().requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        BtnSendEmail = RootView.findViewById(R.id.email);
        BtnSendEmail.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_SENDTO);
            i.setData(Uri.parse("mailto:trihariyadi24@gmail.com"));
            startActivity(i);
        });

        changeStatusBarColor(R.color.white, View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        return RootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        changeStatusBarColor(R.color.colorPrimary, View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }

    public void changeStatusBarColor(int resourceColor, int v) {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(v);
            window.setStatusBarColor(getActivity().getResources().getColor(resourceColor));
        }
    }

//    getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//    getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//    getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//    getActivity().getWindow().setNavigationBarColor(ContextCompat.getColor(context, R.color.white));
//    getActivity().getWindow().setStatusBarColor(ContextCompat.getColor(context, R.color.white));
}