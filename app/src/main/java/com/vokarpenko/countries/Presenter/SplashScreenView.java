package com.vokarpenko.countries.Presenter;

public interface  SplashScreenView {
    void showErrorMessage(String errorMessage);
    void openListCountriesActivity();
    void showProgressBar();
    void hideProgressBar();
}
