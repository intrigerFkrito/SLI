package com.project.resource.englishnew;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.internet.EnglishNew;
import com.project.internet.NewsWebView;
import com.project.sli.MainActivity;
import com.project.sli.R;

import java.util.List;

/**
 * @author dmt
 * @date 2019/5/30
 */
public class EnglishNewAdapter extends RecyclerView.Adapter<EnglishNewAdapter.ViewHolder> {

    private List <EnglishNews> NewList;
    private Context context;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView title;
        ImageView newImage;
        TextView source;
        TextView date;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            title = view.findViewById(R.id.main_new_title);
            newImage = view.findViewById(R.id.main_new_image);
            source = view.findViewById(R.id.main_new_source);
            date = view.findViewById(R.id.main_new_date);
        }
    }

    public EnglishNewAdapter(List<EnglishNews> newList) {
        NewList = newList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (context == null) {
            context = viewGroup.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.englishnews_item, viewGroup, false);

        //不能在这里设立监听事件 position为-1
//        final ViewHolder holder = new ViewHolder(view);
//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = holder.getAdapterPosition();
//                Log.i("WX",""+position);
//                EnglishNews englishNews = NewList.get(5);
//                Intent intent = new Intent(MainActivity.mainActivity, NewsWebView.class);
//                intent.putExtra("url",englishNews.getUrl());
//                context.startActivity(intent);
//            }
//        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        EnglishNews englishNew = NewList.get(position);
        holder.title.setText(englishNew.getTitle());
        Glide.with(context).load(englishNew.getImageURL())
                .placeholder(R.drawable.main_default)
                .error(R.drawable.main_default)
                .into(holder.newImage);
        holder.source.setText(englishNew.getSource());
        holder.date.setText(englishNew.getPublishedAt().substring(0,10));


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                EnglishNews englishNews = NewList.get(position);
                Intent intent = new Intent(MainActivity.mainActivity, NewsWebView.class);
                intent.putExtra("url",englishNews.getUrl());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return NewList.size();
    }
}
