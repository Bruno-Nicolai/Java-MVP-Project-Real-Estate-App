package co.imob.version1.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import co.imob.version1.R;

public class ProfilePresenter implements ProfileContract.Presenter {

    private ProfileContract.View view;
    private SharedPreferences sharedPreferences;

    public ProfilePresenter(ProfileContract.View view) {
        this.view = view;
        sharedPreferences = view.getContext()
                .getSharedPreferences(view.getContext().getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    }

    @Override
    public void logoutAction() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("name");
        editor.remove("email");
        editor.remove("password");
        editor.apply();

        view.goToLoginActivity();
    }
}
