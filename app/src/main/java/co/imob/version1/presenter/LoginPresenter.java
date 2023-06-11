package co.imob.version1.presenter;

import co.imob.version1.service.LoginCallback;
import co.imob.version1.service.LoginService;

public class LoginPresenter implements LoginContract.Presenter, LoginCallback {

    private LoginContract.View view;
    private LoginService loginService;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        loginService = new LoginService();
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
