package co.imob.version1.presenter;

import android.content.Context;

public interface SignUpContract {
    interface View {

        void showNameError(String errorMessage);

        void showEmailError(String errorMessage);

        void showPasswordError(String errorMessage);

        void showConfirmPasswordError(String errorMessage);

        void goToMainActivity();

        void showToast(String message);

        Context getContext();

    }

    interface Presenter {

        void signupUser(String email, String password);

        boolean validateSignUpForm(String name, String email, String password, String confirmPassword);

        boolean isValidEmail(String email);

        void saveCredentials(String email, String password);

        void clearCredentials();

        void loadSavedCredentials();

        void onDestroy();

    }
}
