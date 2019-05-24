package com.vokarpenko.countries.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.vokarpenko.countries.Model.Entity.CountryModel;
import com.vokarpenko.countries.Presenter.MainPresenter;
import com.vokarpenko.countries.R;
import com.vokarpenko.countries.Utils.GlideApp;

import java.util.ArrayList;
import java.util.List;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.ViewHolder> {
    private List<CountryModel> countries = new ArrayList<>();
    private MainPresenter presenter;

    public CountriesAdapter(MainPresenter presenter) {
        this.presenter = presenter;
    }
    public void setCountries(List<CountryModel> countries) {
        this.countries = countries;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CountriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i ) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_country, viewGroup, false);
        return new ViewHolder(view,presenter);
    }

    @Override
    public void onBindViewHolder(@NonNull CountriesAdapter.ViewHolder viewHolder, int i) {
        viewHolder.countryName.setText(countries.get(i).getName());
        GlideApp.with(viewHolder.itemView.getContext())
                .load(countries.get(i).getFlag())
                .apply(new RequestOptions().centerCrop()).into(viewHolder.countryFlag);
    }


    @Override
    public int getItemCount() {
        return countries.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView countryName;
        ImageView countryFlag;
        MainPresenter presenter;
        ViewHolder(@NonNull View itemView, final MainPresenter presenter) {
            super(itemView);
            countryName = itemView.findViewById(R.id.country_name);
            countryFlag = itemView.findViewById(R.id.country_flag);
            this.presenter = presenter;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("myTag",String.valueOf(getAdapterPosition()));
                    if (presenter != null) {
                        presenter.onItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
