package co.imob.version1.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import co.imob.version1.R;
import co.imob.version1.service.LoginCallback;
import co.imob.version1.service.LoginService;

public class LoginPresenter implements LoginContract.Presenter, LoginCallback {

    private LoginContract.View view;
    private LoginService loginService;
    private SharedPreferences sharedPreferences;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        loginService = new LoginService();
        sharedPreferences = view.getContext()
                .getSharedPreferences(view.getContext().getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    }

    @Override
    public void loginUser(String email, String password) {
        loginService.loginUser(email, password, this);
    }

    @Override
    public void validateEmail(String email) {
        if (email.isEmpty()) {
            view.showEmailError("Invalid Email Address.");
        } else {
            view.showEmailError(null);
        }
    }

    @Override
    public void validatePassword(String password) {
        if (password.isEmpty()) {
            view.showPasswordError("Invalid Password!");
        } else {
            view.showPasswordError(null);
        }
    }

    @Override
    public void saveCredentials(String email, String password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", email);
        editor.putString("password", password);
        editor.apply();
    }

    @Override
    public void clearCredentials() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("email");
        editor.remove("password");
        editor.apply();
    }

    @Override
    public void loadSavedCredentials() {
        String savedEmail = sharedPreferences.getString("email", "");
        String savedPassword = sharedPreferences.getString("password", "");

        if (!savedEmail.isEmpty() && !savedPassword.isEmpty()) {
            view.setRememberMeChecked(true);
            view.setEmail(savedEmail);
            view.setPassword(savedPassword);
        }
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void onLoginSuccess() {
        view.goToMainActivity();
    }

    @Override
    public void onLoginFailure(String errorMessage) {
        view.showPasswordError(errorMessage);
    }
}
