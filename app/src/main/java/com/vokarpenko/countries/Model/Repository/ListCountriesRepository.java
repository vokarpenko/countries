package com.vokarpenko.countries.Model.Repository;

import com.vokarpenko.countries.Adapter.CountriesAdapter;
import com.vokarpenko.countries.Model.Database.CountryDao;
import com.vokarpenko.countries.Model.Entity.CountryModel;

import java.util.List;
import java.util.Observable;

import io.reactivex.Emitter;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class ListCountriesRepository {
    public static final String POSITION = "Position";
    private CountryDao countryDao;
    private CountriesAdapter adapter;
    public ListCountriesRepository(CountryDao countryDao, CountriesAdapter adapter) {
        this.countryDao = countryDao;
        this.adapter=adapter;
    }

    public Single<List<CountryModel>> getSingleDataFromCache(){
        return countryDao
                .getAllCountry()
                .subscribeOn(Schedulers.io());
    }
    public io.reactivex.Observable<Integer> getObservableItemClick(){
        return  adapter.getViewClickedObservable();
    }

}
