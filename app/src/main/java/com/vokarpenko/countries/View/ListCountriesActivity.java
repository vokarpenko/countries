package com.vokarpenko.countries.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.vokarpenko.countries.Model.Database.AppDatabase;
import com.vokarpenko.countries.Model.Entity.CountryModel;
import com.vokarpenko.countries.Model.Repository.ListCountriesRepository;
import com.vokarpenko.countries.Presenter.MainPresenter;
import com.vokarpenko.countries.Presenter.ListCountriesView;
import com.vokarpenko.countries.R;
import com.vokarpenko.countries.Adapter.CountriesAdapter;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.vokarpenko.countries.Model.Repository.ListCountriesRepository.POSITION;

public class ListCountriesActivity extends Activity implements ListCountriesView {
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


        Observable.fromCallable(new CallableLongAction("5"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(final Integer integer) throws Exception {
                        Log.i("myTag","onacept");
                    }
                });


    }
    void init(){
        db = AppDatabase.getMemoryDatabase(getBaseContext());
        if (presenter == null) {
            presenter = new MainPresenter(this,(new ListCountriesRepository(db.country(), getBaseContext())));
        }
        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CountriesAdapter(presenter);
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

    @Override
    public void openDetailActivity(int position) {
        Intent intent = new Intent(getBaseContext(),DetailCountryActivity.class);
        intent.putExtra(POSITION,position);
        startActivity(intent);
    }

    private int longAction(String text) {
        Log.i("myTag","longaction");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(text);
    }

    class CallableLongAction implements Callable<Integer> {

        private final String data;

        public CallableLongAction(String data) {
            this.data = data;
        }

        @Override
        public Integer call() {
            return longAction(data);
        }
    }
}