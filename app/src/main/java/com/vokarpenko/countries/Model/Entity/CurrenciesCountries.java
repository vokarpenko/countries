package com.vokarpenko.countries.Model.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class CurrenciesCountries {
    @PrimaryKey(autoGenerate = true)
    private
    int id;
    private int idCurrency, idCountry;

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCurrency() {
        return idCurrency;
    }

    public void setIdCurrency(int idCurrency) {
        this.idCurrency = idCurrency;
    }

    public CurrenciesCountries(int idCurrency, int idCountry) {
        this.idCurrency = idCurrency;
        this.idCountry = idCountry;
    }

    public int getIdCountry() {
        return idCountry;

    }

    public void setIdCountry(int idCountry) {
        this.idCountry = idCountry;
    }
}
