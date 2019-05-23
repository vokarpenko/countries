package com.vokarpenko.countries.View.Adapter;

import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.vokarpenko.countries.Model.Entity.CountryModel;
import com.vokarpenko.countries.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.ViewHolder> {

    private List<CountryModel> countries = new ArrayList<>();

    public void setCountries(List<CountryModel> countries) {
        this.countries = countries;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CountriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_country, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountriesAdapter.ViewHolder viewHolder, int i) {
        viewHolder.countryName.setText(countries.get(i).getName());
        //Glide.with(viewHolder.itemView).load(countries.get(i).getFlag()).into(viewHolder.countryFlag);
        RequestBuilder<PictureDrawable> requestBuilder = Glide.with(viewHolder.itemView.getContext())
                .as(PictureDrawable.class);
                //.error(R.drawable.image_error)
                //.transition(withCrossFade())
                //.listener(new SvgSoftwareLayerSetter());
        Uri uri = Uri.parse("https://sun9-8.userapi.com/c824201/v824201969/173426/YW0DIgHPsvw.jpg?ava=1");
        requestBuilder.load(uri).into(viewHolder.countryFlag);
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView countryName;
        ImageView countryFlag;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            countryName = itemView.findViewById(R.id.country_name);
            countryFlag = itemView.findViewById(R.id.country_flag);
        }
    }
}
