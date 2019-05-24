package com.vokarpenko.countries.Model.Repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.vokarpenko.countries.Model.Database.CountryDao;
import com.vokarpenko.countries.Model.Entity.CountryModel;
import com.vokarpenko.countries.Model.Entity.CurrenciesCountries;
import com.vokarpenko.countries.Model.Entity.CurrencyModel;
import com.vokarpenko.countries.Model.Retrofit.CountriesRestApi;
import com.vokarpenko.countries.Model.Retrofit.NetworkService;
import com.vokarpenko.countries.Utils.FailureLoadCallback;
import com.vokarpenko.countries.Utils.GlideApp;
import com.vokarpenko.countries.Utils.SuccessfulLoadCallBack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenRepository {
    private final static String PREFS_NAME = "Preference";
    private final static String NOPE_CAPITAL = "отсутствует";
    private SharedPreferences settings;
    private CountryDao countryDao;
    private CountriesRestApi api;
    private Context context;
    public SplashScreenRepository(Context context, CountryDao countryDao) {
        this.context = context;
        this.settings = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        this.countryDao = countryDao;
        this.api =NetworkService.getInstance().getApi();
    }

    public void saveData(final SuccessfulLoadCallBack callBack, final FailureLoadCallback failureLoadCallback){
        if (!hasCache()) {
            api.getCountries().enqueue(new Callback<List<CountryModel>>() {
                @Override
                public void onResponse(@NonNull Call<List<CountryModel>> call, @NonNull Response<List<CountryModel>> response) {
                    List<CountryModel> countryModels = new ArrayList<>();
                    List<CurrencyModel> currencyModels = new ArrayList<>();
                    Map<String,Integer> currencyMap = new HashMap<>();
                    List<CurrenciesCountries> currenciesCountries = new ArrayList<>();
                    int countryId = 0,currencyId = 0;
                    if (response.body() != null) {
                        for (CountryModel countryModel: response.body()
                             ) {
                            if(countryModel.getCapital().equals("")) countryModel.setCapital(NOPE_CAPITAL);
                            savePictures(countryModel.getFlag());
                            countryId++;
                            countryModel.setId(countryId);
                            countryModels.add(countryModel);
                            for (CurrencyModel currencyModel :countryModel.getCurrencies()
                                 ) {
                                if (!currencyMap.containsKey(currencyModel.getCode())) {
                                    currencyId++;
                                    currencyMap.put(currencyModel.getCode(),currencyId);
                                    currencyModel.setId(currencyId);
                                    currencyModels.add(currencyModel);
                                    currenciesCountries.add(new CurrenciesCountries(currencyId,countryId));
                                } else currenciesCountries.add(new CurrenciesCountries(currencyMap.get(currencyModel.getCode()), countryId));
                            }
                        }
                    }
                    countryDao.insertCountry(countryModels);
                    countryDao.insertCurrencies(currencyModels);
                    countryDao.insertCurrenciesCountries(currenciesCountries);
                    settings.edit().putString("HasCache","has_cache").apply();
                    callBack.openListCountriesActivity();
                }

                @Override
                public void onFailure(@NonNull Call<List<CountryModel>> call, @NonNull Throwable t) {
                    failureLoadCallback.setError(t);
                }
            });
        }
        else callBack.openListCountriesActivity();
    }
    private boolean hasCache(){
        return false;//!settings.getString("HasCache", "null").equals("null");
    }
    private void savePictures(String path){
        GlideApp.with(context).load(path)
                .apply(new RequestOptions().centerCrop())
                .diskCacheStrategy(DiskCacheStrategy.DATA).submit();
    }
}
