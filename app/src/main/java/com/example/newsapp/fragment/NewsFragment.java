package com.example.newsapp.fragment;

import android.content.Intent;
import android.net.Uri;
import  android.os.Bundle;

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
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.newsapp.R;
import com.example.newsapp.adapter.NewsAdapter;
import com.example.newsapp.api.NewsApi;
import com.example.newsapp.models.ModelNews;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NewsFragment extends Fragment implements NewsAdapter.onSelectData {
    private RecyclerView RVNews;
    NewsAdapter newsAdapter;
    List<ModelNews> modelNews = new ArrayList<>();
    View RootView;
    RelativeLayout LoadingData,
            AlertError;

    public static NewsFragment newInstance(String param1, String param2) {
        return new NewsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RootView = inflater.inflate(R.layout.fragment_news, container, false);
        LoadingData = RootView.findViewById(R.id.loadingData);
        AlertError = RootView.findViewById(R.id.alertError);
        initViews();
        return RootView;
    }

    private void initViews() {
        RVNews = (RecyclerView) RootView.findViewById(R.id.rvNews);
        RVNews.setHasFixedSize(true);
        RVNews.setLayoutManager(new LinearLayoutManager(getContext()));
        loadJSON();
    }

    private void loadJSON() {
        AndroidNetworking.get(NewsApi.GET_NEWS_HEADLINE)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            LoadingData.setVisibility(View.GONE);
                            JSONArray playerArray = response.getJSONArray("articles");
                            for (int i = 0; i < playerArray.length(); i++) {
                                JSONObject temp = playerArray.getJSONObject(i);
                                ModelNews dataApi = new ModelNews();
                                if (!temp.isNull("description") && !temp.isNull("urlToImage")) {
                                    dataApi.setTitle(temp.getString("title"));
                                    dataApi.setPublishedAt((temp.getString("publishedAt")));
                                    dataApi.setDescription(temp.getString("description"));
                                    dataApi.setUrlToImage(temp.getString("urlToImage"));
                                    dataApi.setUrl(temp.getString("url"));

                                    modelNews.add(dataApi);
                                    showNews();
                                }
                            }
                        } catch (JSONException e) {
                            LoadingData.setVisibility(View.GONE);
                            AlertError.setVisibility(View.VISIBLE);
                            e.printStackTrace();
                            Log.d("Fetch Data", e.toString());
                            Toast.makeText(Objects.requireNonNull(getActivity()).getApplicationContext(), "Gagal menampilkan data", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        LoadingData.setVisibility(View.GONE);
                        AlertError.setVisibility(View.VISIBLE);
                        Log.d("Fetch Data", anError.toString());
                        Toast.makeText(Objects.requireNonNull(getActivity()).getApplicationContext(), "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showNews() {
        newsAdapter = new NewsAdapter(getContext(), modelNews, this);
        RVNews.setAdapter(newsAdapter);
    }

    @Override
    public void onSelected(ModelNews mdlNews) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(mdlNews.getUrl()));
        startActivity(i);
    }
}