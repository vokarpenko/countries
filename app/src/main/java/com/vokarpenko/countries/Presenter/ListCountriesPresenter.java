package com.vokarpenko.countries.Presenter;

import com.vokarpenko.countries.Model.Entity.CountryModel;
import com.vokarpenko.countries.Model.Repository.ListCountriesRepository;

import java.util.List;

import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableSingleObserver;

public class ListCountriesPresenter {

    private ListCountriesView view;
    private ListCountriesRepository repository;

    public ListCountriesPresenter(ListCountriesView view, ListCountriesRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void setOnItemClickListener(){
        repository.getObservableItemClick().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer itemIndex) {
                view.openDetailActivity(itemIndex);
            }
        });
    }

    public void setData(){
        repository.getSingleDataFromCache().subscribeWith(new DisposableSingleObserver<List<CountryModel>>() {

            @Override
            public void onSuccess(List<CountryModel> countryModels) {
                view.setList(countryModels);
            }

            @Override
            public void onError(Throwable e) {
            }
        });
    }
}
