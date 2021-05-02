package com.example.newsapp.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.example.newsapp.R;
import com.example.newsapp.adapter.HospitalAdapter;
import com.example.newsapp.api.NewsApi;
import com.example.newsapp.models.ModelHospitals;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HospitalsFragment extends Fragment implements HospitalAdapter.onSelectData {
    private RecyclerView RVHospitals;
    HospitalAdapter hospitalAdapter;
    List<ModelHospitals> modelHospitals = new ArrayList<>();
    View RootView;
    RelativeLayout LoadingData,
                AlertError;

    public static HospitalsFragment newInstance(String param1, String param2) {
        return new HospitalsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RootView = inflater.inflate(R.layout.fragment_hospitals, container, false);
        LoadingData = RootView.findViewById(R.id.loadingData);
        AlertError = RootView.findViewById(R.id.alertError);
        initViews();
        return RootView;
    }

    private void initViews() {
        RVHospitals = (RecyclerView) RootView.findViewById(R.id.rvHospital);
        RVHospitals.setHasFixedSize(true);
        RVHospitals.setLayoutManager(new LinearLayoutManager(getContext()));
        loadJSON();
    }

    private void loadJSON() {
        AndroidNetworking.get(NewsApi.GET_HOSPITALS)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                       for (int i = 0; i < response.length(); i++) {
                           try {
                               LoadingData.setVisibility(View.GONE);
                               JSONObject temp = response.getJSONObject(i);
                               ModelHospitals dataApi = new ModelHospitals();
                               if (!temp.isNull("phone")) {
                                   dataApi.setName(temp.getString("name"));
                                   dataApi.setAddress(temp.getString("address"));
                                   dataApi.setPhone(temp.getString("phone"));

                                   modelHospitals.add(dataApi);
                                   show();
                               }
                           } catch (JSONException e) {
                               LoadingData.setVisibility(View.GONE);
                               AlertError.setVisibility(View.VISIBLE);
                               e.printStackTrace();
                               Log.d("Error JSONException", e.toString());
                               Toast.makeText(getContext(), "Gagal menampilkan data", Toast.LENGTH_SHORT).show();
                           }
                       }
                    }

                    @Override
                    public void onError(ANError anError) {
                        LoadingData.setVisibility(View.GONE);
                        AlertError.setVisibility(View.VISIBLE);
                        Log.d("ANError", anError.toString());
                        Toast.makeText(getContext(), "Gagal menampilkan data", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void show() {
        hospitalAdapter = new HospitalAdapter(getContext(), modelHospitals, this);
        RVHospitals.setAdapter(hospitalAdapter);
    }

    @Override
    public void onSelected(ModelHospitals mdlHospitals) {
        callPhoneNumber(mdlHospitals);
    }

    public void callPhoneNumber(ModelHospitals mdlHospitals) {
        try {
            if (Build.VERSION.SDK_INT > 22) {
                if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(getContext()), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()), new String[] {Manifest.permission.CALL_PHONE}, 101);
                    return;
                }
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:" + mdlHospitals.getPhone()));
                startActivity(i);
            } else {
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:" + mdlHospitals.getPhone()));
                startActivity(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Error Call Intent", e.toString());
            Toast.makeText(getContext(), "Gagal melakukan panggilan", Toast.LENGTH_SHORT).show();
        }
    }
}