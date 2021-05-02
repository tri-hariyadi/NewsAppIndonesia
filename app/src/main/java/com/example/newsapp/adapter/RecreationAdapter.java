package com.example.newsapp.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.newsapp.R;
import com.example.newsapp.holder.NewsViewHolder;
import com.example.newsapp.models.ModelRecreations;

import java.util.List;

public class RecreationAdapter extends RecyclerView.Adapter<NewsViewHolder> {
    public List<ModelRecreations> recreationsList;
    private Context mContext;
    private final RecreationAdapter.onSelectData onSelectData;

    public interface onSelectData {
        void onSelected(ModelRecreations mdlRecreations);
    }

    public RecreationAdapter(Context context, List<ModelRecreations> recreations, RecreationAdapter.onSelectData onSelectData) {
        this.mContext = context;
        this.recreationsList = recreations;
        this.onSelectData = onSelectData;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        final ModelRecreations recreations = recreationsList.get(position);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        if (position >= 0) {
            params.setMargins(20, 20, 20, 0);
            holder.cvNews.setLayoutParams(params);
            if (position > 0) {
                params.setMargins(20, 12, 20, 0);
                holder.cvNews.setLayoutParams(params);
            }
        }

        if (position+1 == recreationsList.size()) {
            params.setMargins(20, 12, 20, 83);
            holder.cvNews.setLayoutParams(params);
        }

        Glide.with(mContext)
                .load(recreations.getUrlToImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_image_list)
                .centerCrop()
                .into(holder.ImageNews);
        holder.TitleNews.setText(recreations.getName());
        holder.PublishedNews.setText(recreations.getAddress());
        holder.DescriptionNews.setText(recreations.getDesc());
        holder.btnGoogleMaps.setOnClickListener(v -> {
            onSelectData.onSelected(recreations);
        });
        holder.btnDetail.setVisibility(View.GONE);
        holder.ActionCard.setGravity(Gravity.CENTER);
    }

    @Override
    public int getItemCount() {
        return recreationsList.size();
    }
}
