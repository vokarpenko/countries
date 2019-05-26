package com.vokarpenko.countries.Model.Repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.PictureDrawable;

import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.vokarpenko.countries.Model.Database.CountryDao;
import com.vokarpenko.countries.Model.Entity.CountryModel;
import com.vokarpenko.countries.Model.Entity.CurrenciesCountries;
import com.vokarpenko.countries.Model.Entity.CurrencyModel;
import com.vokarpenko.countries.Model.Retrofit.NetworkService;
import com.vokarpenko.countries.Utils.GlideApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class SplashScreenRepository {
    private final static String PREFS_NAME = "Preference";
    private final static String NOPE_CAPITAL = "отсутствует";
    private SharedPreferences settings;
    private CountryDao countryDao;
    private RequestBuilder requestBuilder;
    public SplashScreenRepository(Context context, CountryDao countryDao) {
        this.settings = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        this.countryDao = countryDao;
        requestBuilder = GlideApp.with(context)
                        .as(PictureDrawable.class)
                        .diskCacheStrategy(DiskCacheStrategy.DATA)
                        .apply(new RequestOptions().centerCrop());
    }

    public void saveToDB(List<CountryModel> countryModelList) {
        List<CountryModel> countryModels = new ArrayList<>();
        List<CurrencyModel> currencyModels = new ArrayList<>();
        Map<String, Integer> currencyMap = new HashMap<>();
        List<CurrenciesCountries> currenciesCountries = new ArrayList<>();
        int countryId = 0, currencyId = 0;
        for (CountryModel countryModel :
                countryModelList) {
            if (countryModel.getCapital().equals("")) countryModel.setCapital(NOPE_CAPITAL);
            savePicture(countryModel.getFlag());
            countryModel.setId(countryId);
            countryModels.add(countryModel);
            for (CurrencyModel currencyModel :
                    countryModel.getCurrencies()) {
                if (!currencyMap.containsKey(currencyModel.getCode())) {
                    currencyMap.put(currencyModel.getCode(), currencyId);
                    currencyModel.setId(currencyId);
                    currencyModels.add(currencyModel);
                    currenciesCountries.add(new CurrenciesCountries(currencyId, countryId));
                    currencyId++;
                } else
                    currenciesCountries.add(new CurrenciesCountries(currencyMap.get(currencyModel.getCode()), countryId));
            }
            countryId++;
        }
        countryDao.insertCountry(countryModels);
        countryDao.insertCurrencies(currencyModels);
        countryDao.insertCurrenciesCountries(currenciesCountries);
    }

    public void setCache(){
        settings.edit().putString("HasCache", "has_cache").apply();
    }
    public boolean hasCache(){
        return !Objects.requireNonNull(settings.getString("HasCache", "null")).equals("null");
    }


    private void savePicture(String path){
        requestBuilder.load(path).submit();
    }

    public Single<List<CountryModel>> getSingleCountriesList() {
        return NetworkService.getInstance().getApi()
                .getCountries()
                .subscribeOn(Schedulers.io());
    }
}
