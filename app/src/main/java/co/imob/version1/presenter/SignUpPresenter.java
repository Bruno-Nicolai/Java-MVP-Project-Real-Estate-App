package co.imob.version1.presenter;

import android.content.SharedPreferences;

import co.imob.version1.model.Auth;
import co.imob.version1.service.SignUpCallback;
import co.imob.version1.service.SignUpService;

public class SignUpPresenter implements SignUpContract.Presenter, SignUpCallback {

    private SignUpContract.View view;
    private SignUpService signUpService;

    public SignUpPresenter(SignUpContract.View view) {
        this.view = view;
        signUpService = new SignUpService();
    }

    @Override
    public boolean validateSignUpForm(String name, String email, String password, String confirmPassword) {
        if (name.isEmpty()) {
            view.showNameError("Please enter your name.");
            return false;
        } else if (email.isEmpty()) {
            view.showEmailError("Please enter your email.");
            return false;
        } else if (!isValidEmail(email)) {
            view.showEmailError("Invalid email address.");
            return false;
        } else if (password.isEmpty()) {
            view.showPasswordError("Please enter a password.");
            return false;
        } else if (confirmPassword.isEmpty()) {
            view.showConfirmPasswordError("Please confirm your password.");
            return false;
        } else if (!password.equals(confirmPassword)) {
            view.showConfirmPasswordError("Passwords do not match.");
            return false;
        } else {
            return true;
        }
    }

    private boolean isValidEmail(String email) {
        return email.contains("@");
    }

    @Override
    public void saveUser(Auth user, SharedPreferences sharedPreferences) {
        signUpService.registerUser(user.getName(), user.getEmail(), user.getPassword(), this);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", user.getName());
        editor.putString("email", user.getEmail());
        editor.putString("password", user.getPassword());
        editor.apply();

    }

    @Override
    public void onRegisterSuccess(String message) {
        view.showToast(message);
        view.goToLoginActivity();
    }

    @Override
    public void onRegisterFailure(String errorMessage) {
        view.showToast(errorMessage);
    }

}