package com.vokarpenko.countries.Model.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.vokarpenko.countries.Model.Entity.CountryModel;
import com.vokarpenko.countries.Model.Entity.CurrenciesCountries;
import com.vokarpenko.countries.Model.Entity.CurrencyModel;

@Database(entities = {CountryModel.class,CurrencyModel.class,CurrenciesCountries.class},exportSchema = false,version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract CountryDao country();

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "countries")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public static AppDatabase getMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}
