package com.example.newsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.holder.HospitalViewHolder;
import com.example.newsapp.models.ModelHospitals;

import java.util.List;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalViewHolder> {
    public List<ModelHospitals> hospitalsList;
    private Context mContext;
    private final HospitalAdapter.onSelectData onSelectData;

    public interface onSelectData {
        void onSelected(ModelHospitals mdlHospitals);
    }

    public HospitalAdapter(Context context, List<ModelHospitals> hospitals, HospitalAdapter.onSelectData onSelectData) {
        this.mContext = context;
        this.hospitalsList = hospitals;
        this.onSelectData = onSelectData;
    }

    @NonNull
    @Override
    public HospitalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_hospitals, parent, false);
        return new HospitalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalViewHolder holder, int position) {
        final ModelHospitals hospital = hospitalsList.get(position);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        if (position >= 0) {
            params.setMargins(20, 20, 20, 0);
            holder.CVHospital.setLayoutParams(params);
            if (position > 0) {
                params.setMargins(20, 12, 20, 0);
                holder.CVHospital.setLayoutParams(params);
            }
        }

        if (position+1 == hospitalsList.size()) {
            params.setMargins(20, 12, 20, 83);
            holder.CVHospital.setLayoutParams(params);
        }
        holder.Name.setText(hospital.getName());
        holder.Address.setText(hospital.getAddress());
        holder.Phone.setText(hospital.getPhone());
        holder.BtnPhone.setOnClickListener(v -> {
            onSelectData.onSelected(hospital);
        });
    }

    @Override
    public int getItemCount() {
        return hospitalsList.size();
    }
}
