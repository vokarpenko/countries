package com.vokarpenko.countries.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.vokarpenko.countries.Model.Database.AppDatabase;
import com.vokarpenko.countries.Model.Entity.CurrencyModel;
import com.vokarpenko.countries.Model.Repository.DetailCountryRepository;
import com.vokarpenko.countries.Presenter.DetailCountryPresenter;
import com.vokarpenko.countries.Presenter.DetailCountryView;
import com.vokarpenko.countries.R;
import com.vokarpenko.countries.Utils.GlideApp;
import com.vokarpenko.countries.Adapter.CurrenciesAdapter;

import java.util.List;

import static com.vokarpenko.countries.Model.Repository.ListCountriesRepository.POSITION;

public class DetailCountryActivity extends AppCompatActivity implements DetailCountryView {
    TextView textCountryName,textCountryCapital;
    RecyclerView listViewCurrencies;
    DetailCountryPresenter presenter;
    AppDatabase db;
    CurrenciesAdapter adapter;
    ImageView flagImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_country);
        init();
        presenter.setData();
    }

    private void init(){
        db=AppDatabase.getMemoryDatabase(getApplicationContext());
        textCountryName = findViewById(R.id.country_name_detail);
        textCountryCapital= findViewById(R.id.country_capital);
        listViewCurrencies = findViewById(R.id.country_currencies_list);
        flagImage = findViewById(R.id.country_flag_detail);
        adapter = new CurrenciesAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        listViewCurrencies.setLayoutManager(layoutManager);
        listViewCurrencies.setAdapter(adapter);
        if (presenter==null){
            presenter = new DetailCountryPresenter(this,new DetailCountryRepository(db.country()));
        }

    }


    @Override
    public void setItem(String nameCountry, String capital, String flag, final List<CurrencyModel> currencies) {
        GlideApp.with(getApplicationContext()).load(flag)
                .apply(new RequestOptions().centerCrop()).into(flagImage);
        textCountryName.setText(nameCountry);
        textCountryCapital.setText(capital);
        listViewCurrencies.post(new Runnable() {
            @Override
            public void run() {
                adapter.setCurrencies(currencies);
            }
        });
    }

    @Override
    public int getItemIndex() {
        return getIntent().getIntExtra(POSITION,-1);
    }
}
