package com.vokarpenko.countries.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vokarpenko.countries.Model.Database.AppDatabase;
import com.vokarpenko.countries.Model.Entity.CountryModel;
import com.vokarpenko.countries.Model.Repository.ListCountriesRepository;
import com.vokarpenko.countries.Presenter.ListCountriesPresenter;
import com.vokarpenko.countries.Presenter.ListCountriesView;
import com.vokarpenko.countries.R;
import com.vokarpenko.countries.Adapter.CountriesAdapter;

import java.util.List;

import static com.vokarpenko.countries.Model.Repository.ListCountriesRepository.POSITION;

public class ListCountriesActivity extends Activity implements ListCountriesView {
    RecyclerView recyclerView;
    CountriesAdapter adapter;
    LinearLayoutManager layoutManager;
    ListCountriesPresenter presenter;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_countries);
        init();
        if (presenter == null) {
            presenter = new ListCountriesPresenter(this,
                    (new ListCountriesRepository(db.country(),adapter)));
        }
        presenter.setData();
        presenter.setOnItemClickListener();
    }
    void init(){
        db = AppDatabase.getMemoryDatabase(getBaseContext());
        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CountriesAdapter(presenter);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setList(final List<CountryModel> models) {
       adapter.setCountries(models);
    }

    @Override
    public void openDetailActivity(int position) {
        Intent intent = new Intent(getBaseContext(),DetailCountryActivity.class);
        intent.putExtra(POSITION,position);
        startActivity(intent);
    }
}