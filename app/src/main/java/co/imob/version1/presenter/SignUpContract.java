package co.imob.version1.presenter;

import android.content.Context;

import co.imob.version1.model.Auth;

public interface SignUpContract {
    interface View {
        void showNameError(String errorMessage);

        void showEmailError(String errorMessage);

        void showPasswordError(String errorMessage);

        void showConfirmPasswordError(String errorMessage);

        void goToLoginActivity();

        void showToast(String message);

        Context getContext();
    }

    interface Presenter {
        boolean validateSignUpForm(String name, String email, String password, String confirmPassword);

        void saveUser(Auth user);

    }
}
