package com.vokarpenko.countries.Model.Repository;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.bumptech.glide.RequestManager;
import com.vokarpenko.countries.Model.Database.CountryDao;
import com.vokarpenko.countries.Model.Entity.CountryModel;
import com.vokarpenko.countries.Model.Entity.CurrenciesCountries;
import com.vokarpenko.countries.Model.Entity.CurrencyModel;
import com.vokarpenko.countries.Model.Retrofit.CountriesRestApi;
import com.vokarpenko.countries.Model.Retrofit.NetworkService;
import com.vokarpenko.countries.Utils.LoadDataCallBack;
import com.vokarpenko.countries.Utils.FailureCallback;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenRepository {
    public final static String PREFS_NAME = "Preference";
    private SharedPreferences settings;
    private CountryDao countryDao;
    private CountriesRestApi api;
    private RequestManager glide;
    public SplashScreenRepository(SharedPreferences settings, CountryDao countryDao, RequestManager glide) {
        this.settings = settings;
        this.countryDao = countryDao;
        this.api =NetworkService.getInstance().getApi();
        this.glide=glide;
    }

    public void saveData(final LoadDataCallBack callBack, final FailureCallback failureCallback){
        if (!hasCache()) {
            api.getCountries().enqueue(new Callback<List<CountryModel>>() {
                @Override
                public void onResponse(@NonNull Call<List<CountryModel>> call, @NonNull Response<List<CountryModel>> response) {
                    List<CountryModel> models = response.body();
                    if (models != null) {
                        for (CountryModel country : models
                                ) {
                            int idCountry = (int) countryDao.insertCountry(country);
                            for (CurrencyModel currency : country.getCurrencies()
                                    ) {
                                int idCurrency = (int) countryDao.insertCurrencies(currency);
                                CurrenciesCountries cc = new CurrenciesCountries(idCurrency, idCountry);
                                countryDao.insertCurrenciesCountries(cc);
                            }
                        }
                    }

                    callBack.openMainActivity();
                }

                @Override
                public void onFailure(@NonNull Call<List<CountryModel>> call, @NonNull Throwable t) {
                    Log.i("myTag", t.toString());
                    failureCallback.setError(t);
                }
            });
        }
        else callBack.openMainActivity();
    }
    private boolean hasCache(){
        if(settings.getString("HasCache", "null").equals("null")){
            Log.i("myTag","nocache");
            return false;
        }
        else {
            Log.i("myTag","havecache");
            settings.edit().putString("HasCache","has_cache").apply();
            return true;
        }
    }
}
