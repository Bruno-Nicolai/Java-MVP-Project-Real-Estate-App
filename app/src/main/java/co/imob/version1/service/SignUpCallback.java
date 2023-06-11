package co.imob.version1.service;

public interface SignUpCallback {
    public void onRegisterSuccess(String message);
    public void onRegisterFailure(String errorMessage);
}
