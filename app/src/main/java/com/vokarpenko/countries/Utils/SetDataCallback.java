package com.vokarpenko.countries.Utils;

import com.vokarpenko.countries.Model.Entity.CountryModel;

import java.util.List;

public interface SetDataCallback {
    void setList(List<CountryModel> models);
}
