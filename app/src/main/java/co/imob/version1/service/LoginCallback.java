package co.imob.version1.service;

public interface LoginCallback {
    void onLoginSuccess();
    void onLoginFailure(String errorMessage);
}
