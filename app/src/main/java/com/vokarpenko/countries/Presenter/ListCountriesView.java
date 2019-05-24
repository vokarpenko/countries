package com.vokarpenko.countries.Presenter;

import com.vokarpenko.countries.Model.Entity.CountryModel;

import java.util.List;

public interface ListCountriesView {
    void setList(List<CountryModel> models);
    void openDetailActivity(int position);
}
