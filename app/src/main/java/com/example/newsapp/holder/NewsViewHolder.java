package com.example.newsapp.holder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.google.android.material.card.MaterialCardView;

public class NewsViewHolder extends RecyclerView.ViewHolder {
    public MaterialCardView cvNews;
    public Button btnDetail,
            btnGoogleMaps;
    public ImageView ImageNews;
    public TextView TitleNews,
            PublishedNews,
            DescriptionNews;
    public View view;
    public LinearLayout ActionCard;

    public NewsViewHolder(@NonNull View view) {
        super(view);
        cvNews = view.findViewById(R.id.cvNews);
        ImageNews = view.findViewById(R.id.imageNews);
        TitleNews = view.findViewById(R.id.titleNews);
        PublishedNews = view.findViewById(R.id.publishedNews);
        DescriptionNews = view.findViewById(R.id.descriptionNews);
        btnDetail = view.findViewById(R.id.btnLinkNews);
        btnGoogleMaps = view.findViewById(R.id.googleMaps);
        ActionCard = view.findViewById(R.id.actionCard);
        this.view = view;
    }
}
