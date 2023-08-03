package co.imob.version1.presenter;

import android.content.Context;

public interface LoginContract {
    interface View {

        void setRememberMeChecked(boolean isChecked);

        void setEmail(String savedEmail);

        void setPassword(String savedPassword);

        void showEmailError(String errorMessage);

        void showPasswordError(String errorMessage);

        void goToMainActivity();

        void showToast(String message);

        Context getContext();

    }

    interface Presenter {

        void loginUser(String email, String password);

        void validateEmail(String email);

        boolean isValidEmail(String email);

        void validatePassword(String password);

        void saveCredentials(String email, String password);

        void clearCredentials();

        void loadSavedCredentials();

        void onDestroy();

    }
}
