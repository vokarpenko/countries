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
        void insertCountry(List<CountryModel> countryModelList);

        //Добавление Currency в бд
        @Insert
        void insertCurrencies(List<CurrencyModel> currencyModelList);

        //Создание таблицы страна валюта
        @Insert
        void insertCurrenciesCountries(List<CurrenciesCountries> currenciesCountries);

        @Query("SELECT * FROM CurrencyModel WHERE id in (SELECT idCurrency FROM CurrenciesCountries WHERE idCountry=:idCountry)")
        List<CurrencyModel> getListCurrenciesCountry(int idCountry);



        // Получение всех Country из бд
        @Query("SELECT * FROM CountryModel")
        List<CountryModel> getAllCountry();

        /*// Получение всех Country из бд с условием
        @Query("SELECT name FROM CountryModel WHERE name LIKE :name")
        String getCapitalCountry(String name);*/

    }
