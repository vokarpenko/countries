package com.vokarpenko.countries.Presenter;

import com.vokarpenko.countries.Model.Entity.CountryModel;
import com.vokarpenko.countries.Model.Repository.SplashScreenRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class SplashScreenPresenter {
    private SplashScreenView view;
    private SplashScreenRepository repository;

    public SplashScreenPresenter(SplashScreenView view, SplashScreenRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void saveData() {
        if(!repository.hasCache()) {
            view.showProgressBar();
            repository.getSingleCountriesList()
                    .subscribeWith(new DisposableSingleObserver<List<CountryModel>>() {
                        @Override
                        public void onSuccess(List<CountryModel> list) {
                            saveToDB(list);
                        }
                        @Override
                        public void onError(Throwable e) {
                        }
                    });
        }
        else view.openListCountriesActivity();
    }

    private void saveToDB(final List<CountryModel> countryModels){
        Completable.complete()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onStart() {
                        repository.saveToDB(countryModels);

                    }

                    @Override
                    public void onError(Throwable error) {
                        view.showErrorMessage(error.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        repository.setCache();
                        view.hideProgressBar();
                        view.openListCountriesActivity();
                    }
                });
    }
}
