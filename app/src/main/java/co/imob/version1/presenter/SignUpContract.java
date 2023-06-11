package co.imob.version1.presenter;

import android.content.SharedPreferences;

import co.imob.version1.model.Auth;

public interface SignUpContract {
    interface View {
        public void showNameError(String errorMessage);
        public void showEmailError(String errorMessage);
        public void showPasswordError(String errorMessage);
        public void showConfirmPasswordError(String errorMessage);
        public void goToMainActivity();
        public void goToLoginActivity();
        public void showToast(String message);
    }

    interface Presenter {
        public boolean validateSignUpForm(String name, String email, String password, String confirmPassword);

        public void saveUser(Auth user);

    }
}
