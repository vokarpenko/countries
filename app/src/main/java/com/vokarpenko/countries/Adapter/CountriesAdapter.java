package com.vokarpenko.countries.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.jakewharton.rxbinding2.view.RxView;
import com.vokarpenko.countries.Model.Entity.CountryModel;
import com.vokarpenko.countries.Presenter.ListCountriesPresenter;
import com.vokarpenko.countries.R;
import com.vokarpenko.countries.Utils.GlideApp;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.subjects.PublishSubject;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.ViewHolder> {
    private List<CountryModel> countries = new ArrayList<>();
    private ListCountriesPresenter presenter;
    private PublishSubject<Integer> mViewClickSubject = PublishSubject.create();


    public Observable<Integer> getViewClickedObservable() {
        return mViewClickSubject;
    }

    public CountriesAdapter(ListCountriesPresenter presenter) {
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
    public void onBindViewHolder(@NonNull final CountriesAdapter.ViewHolder viewHolder, int i) {
        viewHolder.countryName.setText(countries.get(i).getName());
        GlideApp.with(viewHolder.itemView.getContext())
                .load(countries.get(i).getFlag())
                .transition(withCrossFade())
                .apply(new RequestOptions().centerCrop())
                .into(viewHolder.countryFlag);
        RxView.clicks(viewHolder.itemView)
                .map(new Function<Object, Integer>() {
                    @Override
                    public Integer apply(Object aVoid) {
                        return viewHolder.getAdapterPosition();
                    }
                }).subscribe(mViewClickSubject);
    }


    @Override
    public int getItemCount() {
        return countries.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView countryName;
        ImageView countryFlag;
        ListCountriesPresenter presenter;
        ViewHolder(@NonNull final View itemView, final ListCountriesPresenter presenter) {
            super(itemView);
            countryName = itemView.findViewById(R.id.country_name);
            countryFlag = itemView.findViewById(R.id.country_flag);
            this.presenter = presenter;
        }
    }
}
