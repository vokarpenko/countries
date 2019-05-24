package com.vokarpenko.countries.Presenter;

import com.vokarpenko.countries.Model.Entity.CurrencyModel;

import java.util.List;

public interface DetailCountryView {
    void setItem(String nameCountry, String capital, String flag, List<CurrencyModel> currencyModelList);
    int getItemIndex();
}
