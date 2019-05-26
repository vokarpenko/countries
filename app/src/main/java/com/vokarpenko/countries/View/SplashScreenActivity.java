package com.vokarpenko.countries.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.vokarpenko.countries.Model.Database.AppDatabase;
import com.vokarpenko.countries.Model.Repository.SplashScreenRepository;
import com.vokarpenko.countries.Presenter.SplashScreenPresenter;
import com.vokarpenko.countries.Presenter.SplashScreenView;
import com.vokarpenko.countries.R;

public class SplashScreenActivity extends AppCompatActivity implements SplashScreenView {
    private SplashScreenPresenter presenter = null;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        init();
    }
    private void init(){
        progressBar = findViewById(R.id.progress_bar);
        if (presenter == null) {
            AppDatabase db = AppDatabase.getDatabase(getApplication());
            SplashScreenRepository repository = new SplashScreenRepository(getBaseContext(),db.country());
            presenter = new SplashScreenPresenter(this,repository);
        }
        presenter.saveData();
    }


    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(getBaseContext(), "Ошибка " + errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void openListCountriesActivity() {
        startActivity(new Intent(getBaseContext(),ListCountriesActivity.class));
        finish();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}
