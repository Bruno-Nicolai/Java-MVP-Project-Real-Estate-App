package co.imob.version1.presenter;

public interface LoginContract {
    interface View {
        void showEmailError(String errorMessage);

        void showPasswordError(String errorMessage);

        void goToMainActivity();

        void goToSignupActivity();

    }

    interface Presenter {
        void loginUser(String email, String password);

        void validateEmail(String email);

        void validatePassword(String password);

        void onDestroy();
    }
}
