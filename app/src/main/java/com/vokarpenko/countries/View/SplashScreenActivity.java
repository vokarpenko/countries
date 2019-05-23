package com.vokarpenko.countries.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.vokarpenko.countries.Model.Database.AppDatabase;
import com.vokarpenko.countries.Model.Repository.SplashScreenRepository;
import com.vokarpenko.countries.Presenter.SplashScreenPresenter;
import com.vokarpenko.countries.Presenter.SplashScreenView;
import com.vokarpenko.countries.R;

import static com.vokarpenko.countries.Model.Repository.SplashScreenRepository.PREFS_NAME;

public class SplashScreenActivity extends AppCompatActivity implements SplashScreenView {
    private SplashScreenPresenter presenter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        init();
    }
    private void init(){
        if (presenter == null) {
            AppDatabase db = AppDatabase.getDatabase(getApplication());
            db.clearAllTables();
            RequestManager glide = Glide.with(getApplicationContext());
            SplashScreenRepository repository = new SplashScreenRepository(getSharedPreferences(PREFS_NAME,0),db.country(),glide);
            //Log.i("myTag",(String.valueOf( repository.hasCache())));
            presenter = new SplashScreenPresenter(this,repository);
        }
        presenter.saveData();
    }


    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(getBaseContext(), "Error: " + errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void openMainActivity() {
        startActivity(new Intent(getBaseContext(),MainActivity.class));
    }
}
