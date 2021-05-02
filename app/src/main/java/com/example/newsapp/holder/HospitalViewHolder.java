package com.example.newsapp.holder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.google.android.material.card.MaterialCardView;

public class HospitalViewHolder extends RecyclerView.ViewHolder {
    public MaterialCardView CVHospital;
    public TextView Name,
            Address,
            Phone;
    public Button BtnPhone;
    public View view;


    public HospitalViewHolder(@NonNull View view) {
        super(view);
        Name = view.findViewById(R.id.hospitalName);
        Address = view.findViewById(R.id.addressHospital);
        Phone = view.findViewById(R.id.phoneHospital);
        BtnPhone = view.findViewById(R.id.btnCallHospital);
        CVHospital = view.findViewById(R.id.cvHospitals);
        this.view = view;
    }
}
