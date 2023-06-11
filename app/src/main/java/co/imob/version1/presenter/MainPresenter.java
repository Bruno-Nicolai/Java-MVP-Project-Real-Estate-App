package co.imob.version1.presenter;

import android.content.SharedPreferences;

import co.imob.version1.model.Auth;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

//    @Override
//    public void purchaseData(SharedPreferences sharedPreferences) {
//
//        String savedEmail = sharedPreferences.getString("email", "");
//        String savedPassword = sharedPreferences.getString("password", "");
//
//    }
}