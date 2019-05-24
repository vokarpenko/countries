package com.vokarpenko.countries.Model.Repository;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;
import com.vokarpenko.countries.Model.Database.CountryDao;
import com.vokarpenko.countries.Model.Entity.CountryModel;
import com.vokarpenko.countries.Utils.SetDataCallback;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class ListCountriesRepository {
    public static final String POSITION = "Position";
    private CountryDao countryDao;
    private Context context;

    public ListCountriesRepository(CountryDao countryDao, Context context) {
        this.countryDao = countryDao;
        this.context = context;
    }

    public void getDataFromCache(SetDataCallback setDataCallback){
        List<CountryModel> countries = countryDao.getAllCountry();
        setDataCallback.setList(countries);
    }

}
