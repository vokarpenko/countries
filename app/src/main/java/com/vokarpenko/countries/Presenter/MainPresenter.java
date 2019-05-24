package com.vokarpenko.countries.Presenter;

import com.vokarpenko.countries.Model.Entity.CountryModel;
import com.vokarpenko.countries.Model.Repository.ListCountriesRepository;
import com.vokarpenko.countries.Utils.SetDataCallback;

import java.util.List;

public class MainPresenter {

    private ListCountriesView view;
    private ListCountriesRepository repository;

    public MainPresenter(ListCountriesView view, ListCountriesRepository repository) {
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
    public void onItemClick(int position){
        view.openDetailActivity(position);
    }
}
