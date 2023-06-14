package co.imob.version1.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import co.imob.version1.R;

public class FirstSightPresenter implements FirstSightContract.Presenter {

    private FirstSightContract.View view;
    private SharedPreferences sharedPreferences;

    public FirstSightPresenter(FirstSightContract.View view) {
        this.view = view;
        sharedPreferences = view.getContext()
                .getSharedPreferences(view.getContext().getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    }

    @Override
    public void checkFirstRun() {
        boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);

        if (isFirstRun) {
            view.showWelcomeScreen();

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isFirstRun", false);
            editor.apply();
        } else {
            view.goToLoginActivity();
        }

    }
}
