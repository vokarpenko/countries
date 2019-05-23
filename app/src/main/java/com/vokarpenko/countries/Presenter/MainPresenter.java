package com.vokarpenko.countries.Presenter;

import android.util.Log;

import com.vokarpenko.countries.Model.Entity.CountryModel;
import com.vokarpenko.countries.Model.Repository.MainRepository;
import com.vokarpenko.countries.Utils.SetDataCallback;

import java.util.List;

public class MainPresenter {

    private MainView view;
    private MainRepository repository;

    public MainPresenter(MainView view, MainRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void setData(){
        repository.getDataFromCache(
                new SetDataCallback() {
                    @Override
                    public void setList(List<CountryModel> models) {
                        view.setList(models);
                    }
                });
    }

}
