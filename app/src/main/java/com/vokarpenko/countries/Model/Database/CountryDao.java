package com.vokarpenko.countries.Model.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.vokarpenko.countries.Model.Entity.CountryModel;
import com.vokarpenko.countries.Model.Entity.CurrenciesCountries;
import com.vokarpenko.countries.Model.Entity.CurrencyModel;

import java.util.List;

@Dao
public interface CountryDao {
        // Добавление Country в бд
        @Insert
        long insertCountry(CountryModel country);

        //Добавление Currency в бд
        @Insert
        long insertCurrencies(CurrencyModel currency);

        @Insert
        void insertCurrenciesCountries(CurrenciesCountries currenciesCountries);


        // Получение всех Country из бд
        @Query("SELECT * FROM CountryModel")
        List<CountryModel> getAllCountry();

        // Получение всех Country из бд с условием
        @Query("SELECT capital FROM CountryModel WHERE name LIKE :name")
        String getCapitalCountry(String name);

    }
