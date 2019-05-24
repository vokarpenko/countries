package com.vokarpenko.countries.Presenter;

import com.vokarpenko.countries.Model.Repository.DetailCountryRepository;

public class DetailCountryPresenter {
    private DetailCountryView view;
    private DetailCountryRepository repository;

    public DetailCountryPresenter(DetailCountryView view, DetailCountryRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void setData(){
        int itemIndex = view.getItemIndex();
        repository.setCountryModel(itemIndex);
        view.setItem(repository.getCountryName(),repository.getCountryCapital(),repository.getCountryFlag(),repository.getListCurrencies());
    }

}
