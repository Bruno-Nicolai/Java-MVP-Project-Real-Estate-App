package co.imob.version1.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import co.imob.version1.R;

public class SignUpPresenter implements SignUpContract.Presenter {

    private final SignUpContract.View view;
    private final SharedPreferences sharedPreferences;
    private final FirebaseAuth auth;

    public SignUpPresenter(SignUpContract.View view) {
        this.view = view;
        sharedPreferences = view.getContext()
                .getSharedPreferences(view.getContext().getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        this.auth = FirebaseAuth.getInstance();
    }

    @Override
    public void signupUser(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                view.showToast("Signed Up Successfully");
                view.goToMainActivity();
            } else {
                view.showToast("Sign Up Failed; "+ Objects.requireNonNull(task.getException()).getMessage());
            }
        });
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

    @Override
    public boolean isValidEmail(String email) {
        return email.contains("@");
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
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name", String.valueOf(auth.getCurrentUser()));
            editor.apply();
        }
    }

    @Override
    public void onDestroy() {

    }

}