package co.imob.version1.presenter;

import android.content.SharedPreferences;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import co.imob.version1.model.Auth;

public class SignUpPresenter implements SignUpContract.Presenter {

    private SignUpContract.View view;

    private DatabaseReference dbRef;

    public SignUpPresenter(SignUpContract.View view) {
        this.view = view;
        dbRef = FirebaseDatabase.getInstance().getReference("users");
    }

    @Override
    public void validateSignUpForm(String name, String email, String password, String confirmPassword) {
        if (name.isEmpty()) {
            view.showNameError("Please enter your name.");
        } else if (email.isEmpty()) {
            view.showEmailError("Please enter your email.");
        } else if (!isValidEmail(email)) {
            view.showEmailError("Invalid email address.");
        } else if (password.isEmpty()) {
            view.showPasswordError("Please enter a password.");
        } else if (confirmPassword.isEmpty()) {
            view.showConfirmPasswordError("Please confirm your password.");
        } else if (!password.equals(confirmPassword)) {
            view.showConfirmPasswordError("Passwords do not match.");
        } else {
            view.goToMainActivity();
        }
    }

    private boolean isValidEmail(String email) {
        // Add email validation logic here
        return email.contains("@");
    }

    @Override
    public void saveUser(Auth user, SharedPreferences sharedPreferences) {
        // Perform user data saving logic here
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", user.getName());
        editor.putString("email", user.getEmail());
        editor.putString("password", user.getPassword());
        editor.apply();
    }

}