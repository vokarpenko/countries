package com.vokarpenko.countries.Presenter;

import com.vokarpenko.countries.Model.Entity.CountryModel;
import com.vokarpenko.countries.Model.Repository.SplashScreenRepository;
import com.vokarpenko.countries.Utils.LoadDataCallBack;
import com.vokarpenko.countries.Utils.FailureCallback;

import java.util.List;

public class SplashScreenPresenter {
    private SplashScreenView view;
    private SplashScreenRepository repository;

    public SplashScreenPresenter(SplashScreenView view, SplashScreenRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void saveData(){
        repository.saveData(
                new LoadDataCallBack() {
            @Override
            public void openMainActivity() {
                view.openMainActivity();
            }},new FailureCallback() {
            @Override
            public void setError(Throwable throwable) {
                view.showErrorMessage(throwable.getMessage());
            }
        });
    }
}
