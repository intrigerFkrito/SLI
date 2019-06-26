package com.project.resource.note;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.sli.R;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder> {
    private List<Words> wordList;
    private Context context;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView from;
        TextView to;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            from = view.findViewById(R.id.menu_note_word_data);
            to = view.findViewById(R.id.menu_note_word_transdata);
        }
    }

    public WordAdapter(List<Words> wordList) {
        this.wordList = wordList;
    }

    @Override
    public WordAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (context == null) {
            context = viewGroup.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.menu_note_word_recycle, viewGroup, false);

        return new WordAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final WordAdapter.ViewHolder holder, final int position) {
        Words words = wordList.get(position);
        holder.from.setText(words.getHead());
        holder.to.setText(words.getBody());


//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = holder.getAdapterPosition();
//                EnglishNews englishNews = NewList.get(position);
//                Intent intent = new Intent(MainActivity.mainActivity, NewsWebView.class);
//                intent.putExtra("url",englishNews.getUrl());
//                context.startActivity(intent);
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }
}
