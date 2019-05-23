package com.vokarpenko.countries.Model.Repository;

import com.vokarpenko.countries.Model.Database.CountryDao;
import com.vokarpenko.countries.Model.Entity.CountryModel;
import com.vokarpenko.countries.Utils.SetDataCallback;

import java.util.List;

public class MainRepository {

    private CountryDao countryDao;

    public MainRepository(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    public void getDataFromCache(SetDataCallback setDataCallback){
        List<CountryModel> countries = countryDao.getAllCountry();
        setDataCallback.setList(countries);
    }
}
