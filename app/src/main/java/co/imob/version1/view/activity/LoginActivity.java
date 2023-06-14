package co.imob.version1.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import co.imob.version1.R;
import co.imob.version1.presenter.LoginContract;
import co.imob.version1.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private LoginContract.Presenter presenter;

    private SharedPreferences sharedPreferences;

    private CheckBox loginCheckboxRememberMe;

    private EditText loginEtEmail, loginEtPassword;
    private TextView loginTvRedirect;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginPresenter(this);

//        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
//        savedEmail = sharedPreferences.getString("email", "");
//        savedPassword = sharedPreferences.getString("password", "");

        loginEtEmail = findViewById(R.id.login_et_email);
        loginEtPassword = findViewById(R.id.login_et_password);
        loginCheckboxRememberMe = findViewById(R.id.login_check_remember_me);

//        if (email.isEmpty() && password.isEmpty() && loginCheckboxRememberMe.isChecked()) {
//            if (!savedEmail.isEmpty() && !savedPassword.isEmpty()) {
//                loginEtEmail.setText(savedEmail);
//                loginEtPassword.setText(savedPassword);
//                email = savedEmail;
//                password = savedPassword;
//            }
//        }

        loginBtn = findViewById(R.id.login_btn_next);
        loginBtn.setOnClickListener(view -> {

            String email = loginEtEmail.getText().toString();
            String password = loginEtPassword.getText().toString();

            presenter.validateEmail(email);
            presenter.validatePassword(password);
            presenter.loginUser(email, password);

            if (loginCheckboxRememberMe.isChecked()) {
                presenter.saveCredentials(email, password);
            } else {
                presenter.clearCredentials();
            }

        });

        loginTvRedirect = findViewById(R.id.login_btn_go_to_signup);
        loginTvRedirect.setOnClickListener(view -> {
            goToSignupActivity();
        });

        presenter.loadSavedCredentials();

    }

    @Override
    public void setRememberMeChecked(boolean isChecked) {
        loginCheckboxRememberMe.setChecked(isChecked);
    }

    @Override
    public void setEmail(String savedEmail) {
        loginEtEmail.setText(savedEmail);
    }

    @Override
    public void setPassword(String savedPassword) {
        loginEtPassword.setText(savedPassword);
    }

    @Override
    public void showEmailError(String errorMessage) {
        loginEtEmail.setError(errorMessage);
        loginEtEmail.requestFocus();
    }

    @Override
    public void showPasswordError(String errorMessage) {
        loginEtPassword.setError(errorMessage);
        loginEtPassword.requestFocus();
    }

    @Override
    public void goToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void goToSignupActivity() {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

}