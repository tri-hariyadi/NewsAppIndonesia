package com.example.newsapp.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.newsapp.R;
import com.example.newsapp.holder.NewsViewHolder;
import com.example.newsapp.models.ModelNews;
import com.example.newsapp.utils.TimeUnits;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {
    public List<ModelNews> newsList;
    private Context mContext;
    private final NewsAdapter.onSelectData onSelectData;
    View RootView;
    MaterialCardView CVNews;

    public interface onSelectData {
        void onSelected(ModelNews mdlNews);
    }

    public NewsAdapter(Context context, List<ModelNews> news, NewsAdapter.onSelectData onSelectData) {
        this.mContext = context;
        this.newsList = news;
        this.onSelectData = onSelectData;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RootView = LayoutInflater.from(mContext).inflate(R.layout.list_item_news, viewGroup, false);
        CVNews = RootView.findViewById(R.id.cvNews);
        return new NewsViewHolder(RootView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder viewHolder, int i) {
        final ModelNews news = newsList.get(i);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        if (i >= 0) {
            params.setMargins(20, 20, 20, 0);
            CVNews.setLayoutParams(params);
            if (i > 0) {
                params.setMargins(20, 12, 20, 0);
                CVNews.setLayoutParams(params);
            }
        }

        if (i+1 == newsList.size()) {
            params.setMargins(20, 12, 20, 83);
            CVNews.setLayoutParams(params);
        }
        viewHolder.ActionCard.setGravity(Gravity.START);
        Glide.with(mContext)
                .load(news.getUrlToImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_image_list)
                .centerCrop()
                .into(viewHolder.ImageNews);

        viewHolder.TitleNews.setText(news.getTitle());
        viewHolder.PublishedNews.setText(TimeUnits.getTimeAgo(news.getPublishedAt()));
        viewHolder.DescriptionNews.setText(news.getDescription());
        viewHolder.btnDetail.setOnClickListener(v -> {
            onSelectData.onSelected(news);
        });
        viewHolder.btnGoogleMaps.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
}
