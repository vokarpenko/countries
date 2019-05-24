package com.vokarpenko.countries.Model.Entity;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class CountryModel {
    @NonNull
    @PrimaryKey()
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("capital")
    @Expose
    private String capital;

    @Ignore
    @SerializedName("currencies")
    @Expose
    private List<CurrencyModel> currencies;


    @SerializedName("flag")
    @Expose
    private String flag;


    public List<CurrencyModel> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<CurrencyModel> currencies) {
        this.currencies = currencies;
    }




    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }
}
