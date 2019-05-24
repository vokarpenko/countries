package com.vokarpenko.countries.Model.Repository;

import com.vokarpenko.countries.Model.Database.CountryDao;
import com.vokarpenko.countries.Model.Entity.CountryModel;
import com.vokarpenko.countries.Model.Entity.CurrencyModel;

import java.util.List;

public class DetailCountryRepository {
    private CountryDao countryDao;
    private CountryModel countryModel;

    public void setCountryModel(int index) {
        this.countryModel = countryDao.getAllCountry().get(index);
    }

    public DetailCountryRepository(CountryDao countryDao) {
        this.countryDao = countryDao;
    }
    public String getCountryName(){
        return countryModel.getName();
    }
    public String getCountryCapital(){
        return countryModel.getCapital();
    }
    public String getCountryFlag(){
        return countryModel.getFlag();
    }
    public List<CurrencyModel> getListCurrencies(){
        return countryDao.getListCurrenciesCountry(countryModel.getId());
    }

}
