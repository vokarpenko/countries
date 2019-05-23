package com.vokarpenko.countries.View;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vokarpenko.countries.Model.Database.AppDatabase;
import com.vokarpenko.countries.Model.Entity.CountryModel;
import com.vokarpenko.countries.Model.Repository.MainRepository;
import com.vokarpenko.countries.Presenter.MainPresenter;
import com.vokarpenko.countries.Presenter.MainView;
import com.vokarpenko.countries.R;
import com.vokarpenko.countries.View.Adapter.CountriesAdapter;

import java.io.File;
import java.util.List;

public class MainActivity extends Activity implements MainView {
    RecyclerView recyclerView;
    CountriesAdapter adapter;
    LinearLayoutManager layoutManager;
    MainPresenter presenter;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    void init(){
        db = AppDatabase.getMemoryDatabase(getBaseContext());
        if (presenter == null) {
            presenter = new MainPresenter(this,(new MainRepository(db.country())));
        }
        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CountriesAdapter();
        recyclerView.setSaveEnabled(true);
        recyclerView.setAdapter(adapter);
        presenter.setData();
    }

    @Override
    public void setList(final List<CountryModel> models) {
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                adapter.setCountries(models);
            }
        });
    }
}