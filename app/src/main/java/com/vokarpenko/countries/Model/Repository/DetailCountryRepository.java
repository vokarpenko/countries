package com.vokarpenko.countries.Model.Repository;

import com.vokarpenko.countries.Model.Database.CountryDao;
import com.vokarpenko.countries.Model.Entity.CountryModel;
import com.vokarpenko.countries.Model.Entity.CurrencyModel;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class DetailCountryRepository {
    private CountryDao countryDao;

    public DetailCountryRepository(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    public Single<List<CurrencyModel>> getSingleListCurrencies(int index){
        return countryDao.getListCurrenciesCountry(index)
                .subscribeOn(Schedulers.io());
    }

    public Single<CountryModel> getSingleCountry(int index){
        return countryDao.getCountryFromId(index)
                .subscribeOn(Schedulers.io());
    }
}
