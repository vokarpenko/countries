package com.vokarpenko.countries.Model.Retrofit;

import com.vokarpenko.countries.Model.Entity.CountryModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CountriesRestApi {
    @GET("/rest/v2/all?fields=name;capital;currencies;flag")
    Call<List<CountryModel>> getCountries();
    @GET("/data/{path}")
    Call<ResponseBody> getSvg(@Path("path") String path);
}
