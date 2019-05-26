package com.vokarpenko.countries.Model.Retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    private static NetworkService networkServiceInstance;
    public static final String BASE_URL = "https://restcountries.eu";
    private Retrofit retrofit;

    private NetworkService() {

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    public static NetworkService getInstance() {
        if (networkServiceInstance == null) {
            networkServiceInstance = new NetworkService();
        }
        return networkServiceInstance;
    }

    public CountriesRestApi getApi() {
        return retrofit.create(CountriesRestApi.class);
    }

}
