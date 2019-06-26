package com.project.resource.country;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.internet.EnglishNew;
import com.project.internet.NewsWebView;
import com.project.resource.englishnew.EnglishNewAdapter;
import com.project.resource.englishnew.EnglishNews;
import com.project.sli.MainActivity;
import com.project.sli.R;

import java.util.List;

/**
 * @author dmt
 * @date 2019/06/02
 */
public class CountryAdapter extends RecyclerView.Adapter <CountryAdapter.ViewHolder>{
    private List<Countries> CountryList;
    private Context context;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView countryName;
        ImageView countryFlag;

        public ViewHolder(View view) {
            super(view);
            countryName = view.findViewById(R.id.main_country_name);
            countryFlag = view.findViewById(R.id.main_country_image);
        }
    }

    public CountryAdapter(List<Countries> CountryList) {
        this.CountryList = CountryList;
    }

    @Override
    public CountryAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (context == null) {
            context = viewGroup.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.country_item, viewGroup, false);
        return new CountryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CountryAdapter.ViewHolder holder, int position) {
        Countries countries = CountryList.get(position);
        holder.countryName.setText(countries.getCountryName());
        Glide.with(context).load(countries.getCountryImageId()).into(holder.countryFlag);



        holder.countryFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Countries countries = CountryList.get(position);
                MainActivity.mainActivity.englishNew.setCountry(countries.getCountryNameSimple());
                MainActivity.mainActivity.englishNew.getResponse();
                MainActivity.mainActivity.swipeRefresh.setRefreshing(true);
                MainActivity.mainActivity.recycleCountry.setVisibility(View.GONE);
                MainActivity.mainActivity.check.setText(countries.getCountryName());
            }
        });


    }

    @Override
    public int getItemCount() {
        return CountryList.size();
    }
}
