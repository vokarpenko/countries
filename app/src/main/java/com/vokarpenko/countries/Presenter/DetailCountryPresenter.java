package com.vokarpenko.countries.Presenter;

import com.vokarpenko.countries.Model.Entity.CountryModel;
import com.vokarpenko.countries.Model.Entity.CurrencyModel;
import com.vokarpenko.countries.Model.Repository.DetailCountryRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;

public class DetailCountryPresenter {
    private DetailCountryView view;
    private DetailCountryRepository repository;

    public DetailCountryPresenter(DetailCountryView view, DetailCountryRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void setData(){
        final int itemIndex = view.getItemIndex();
        repository.getSingleCountry(itemIndex).subscribeWith(new DisposableSingleObserver<CountryModel>() {
            @Override
            public void onSuccess(final CountryModel countryModel) {
                repository.getSingleListCurrencies(itemIndex)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<CurrencyModel>>() {
                    @Override
                    public void onSuccess(List<CurrencyModel> currencyModelList) {
                        view.setItem(countryModel.getName(),countryModel.getCapital(),countryModel.getFlag(),currencyModelList);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
            }

            @Override
            public void onError(Throwable e) {

            }
        });

    }

}
