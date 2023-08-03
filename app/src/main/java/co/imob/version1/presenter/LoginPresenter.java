package co.imob.version1.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Patterns;

import com.google.firebase.auth.FirebaseAuth;

import co.imob.version1.R;

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View view;
    private final SharedPreferences sharedPreferences;
    private final FirebaseAuth auth;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        this.sharedPreferences = view.getContext()
                .getSharedPreferences(view.getContext().getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        this.auth = FirebaseAuth.getInstance();
    }

    @Override
    public void loginUser(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    view.showToast("Logged In Successfully!");
                    view.goToMainActivity();
                })
                .addOnFailureListener(e -> {
                    view.showToast("Login Failure; " + e.getMessage());
                });
    }

    @Override
    public void validateEmail(String email) {
        if (email.isEmpty()) {
            view.showEmailError("Please, enter your email.");
        } else if (!isValidEmail(email)) {
            view.showEmailError("There was an issue during the login attempt.");
        } else {
            view.showEmailError(null);
        }
    }

    @Override
    public boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
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

}
