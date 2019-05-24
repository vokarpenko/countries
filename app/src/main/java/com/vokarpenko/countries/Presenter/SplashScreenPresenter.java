package com.vokarpenko.countries.Presenter;

import com.vokarpenko.countries.Model.Repository.SplashScreenRepository;
import com.vokarpenko.countries.Utils.SuccessfulLoadCallBack;
import com.vokarpenko.countries.Utils.FailureLoadCallback;

public class SplashScreenPresenter {
    private SplashScreenView view;
    private SplashScreenRepository repository;

    public SplashScreenPresenter(SplashScreenView view, SplashScreenRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void saveData(){
        repository.saveData(
                new SuccessfulLoadCallBack() {
            @Override
            public void openListCountriesActivity() {
                view.openMainActivity();
            }},new FailureLoadCallback() {
            @Override
            public void setError(Throwable throwable) {
                view.showErrorMessage(throwable.getMessage());
            }
        });
    }
}
