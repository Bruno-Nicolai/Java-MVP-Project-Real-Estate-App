package co.imob.version1.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import co.imob.version1.R;
import co.imob.version1.presenter.LoginContract;
import co.imob.version1.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private LoginContract.Presenter presenter;

    private SharedPreferences sharedPreferences;

    private EditText loginEtEmail, loginEtPassword;
    private TextView loginTvRedirect;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginPresenter(this);

        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        loginEtEmail = findViewById(R.id.login_et_email);
        loginEtPassword = findViewById(R.id.login_et_password);

        loginBtn = findViewById(R.id.login_btn_next);
        loginBtn.setOnClickListener(view -> {

            String email = loginEtEmail.getText().toString();
            String password = loginEtPassword.getText().toString();

            if (email.isEmpty() && password.isEmpty()) {
                String savedEmail = sharedPreferences.getString("email", "");
                String savedPassword = sharedPreferences.getString("password", "");
                if (!savedEmail.isEmpty() && !savedPassword.isEmpty()) {
                    loginEtEmail.setText(savedEmail);
                    loginEtPassword.setText(savedPassword);
                    email = savedEmail;
                    password = savedPassword;
                }
            }

            presenter.validateEmail(email);
            presenter.validatePassword(password);
            presenter.loginUser(email, password);

        });

        loginTvRedirect = findViewById(R.id.login_btn_go_to_signup);
        loginTvRedirect.setOnClickListener(view -> {
            goToSignupActivity();
        });

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
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

}