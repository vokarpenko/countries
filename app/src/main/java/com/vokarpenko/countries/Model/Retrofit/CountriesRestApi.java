package com.vokarpenko.countries.Model.Retrofit;

import com.vokarpenko.countries.Model.Entity.CountryModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountriesRestApi {
    @GET("/rest/v2/all?fields=name;capital;currencies;flag")
    Call<List<CountryModel>> getCountries();
}
