package com.example.newsapp.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.newsapp.R;
import com.example.newsapp.adapter.NewsAdapter;
import com.example.newsapp.adapter.RecreationAdapter;
import com.example.newsapp.api.FakeJSONDataRecreation;
import com.example.newsapp.api.NewsApi;
import com.example.newsapp.models.ModelNews;
import com.example.newsapp.models.ModelRecreations;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecreationFragment extends Fragment implements RecreationAdapter.onSelectData {
    RecyclerView RVRecreation;
    RecreationAdapter recreationAdapter;
    List<ModelRecreations> modelRecreations = new ArrayList<>();
    View RootView;
    RelativeLayout LoadingData,
            AlertError;

    public static RecreationFragment newInstance(String param1, String param2) {
        return new RecreationFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RootView = inflater.inflate(R.layout.fragment_recreation, container, false);
        LoadingData = RootView.findViewById(R.id.loadingData);
        AlertError = RootView.findViewById(R.id.alertError);
        initViews();
        return RootView;
    }

    private void initViews() {
        RVRecreation = RootView.findViewById(R.id.rvRecreation);
        RVRecreation.setHasFixedSize(true);
        RVRecreation.setLayoutManager(new LinearLayoutManager(getContext()));
        loadJSON();
    }

    private void loadJSON() {
        FakeJSONDataRecreation data = new FakeJSONDataRecreation();
        JSONObject response = data.getData();
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    LoadingData.setVisibility(View.GONE);
                    JSONArray playerArray = response.getJSONArray("data");
                    for (int i = 0; i < playerArray.length(); i++) {
                        JSONObject temp = playerArray.getJSONObject(i);
                        ModelRecreations dataApi = new ModelRecreations();
                        dataApi.setName(temp.getString("Name"));
                        dataApi.setAddress(temp.getString("Address"));
                        dataApi.setDesc(temp.getString("Desc"));
                        dataApi.setLatitude(temp.getString("Lat"));
                        dataApi.setLongitude(temp.getString("Long"));
                        dataApi.setUrlToImage(temp.getString("UrlImage"));

                        modelRecreations.add(dataApi);
                        show();
                    }
                } catch (JSONException e) {
                    LoadingData.setVisibility(View.GONE);
                    AlertError.setVisibility(View.VISIBLE);
                    Log.d("Error JSONException", e.toString());
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Gagal menampilkan data", Toast.LENGTH_SHORT).show();
                }
            }
        }, 2000);
    }

    private void show() {
       recreationAdapter = new RecreationAdapter(getContext(), modelRecreations, this);
       RVRecreation.setAdapter(recreationAdapter);
    }

    @Override
    public void onSelected(ModelRecreations mdlRecreations) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://www.google.com/maps/search/?api=1&query=" + mdlRecreations.getLatitude() + "," + mdlRecreations.getLongitude()));
        startActivity(i);
    }
}