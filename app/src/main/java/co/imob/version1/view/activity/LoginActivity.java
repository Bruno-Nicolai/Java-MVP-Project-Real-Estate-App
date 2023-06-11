package co.imob.version1.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

import co.imob.version1.R;
import co.imob.version1.presenter.LoginContract;
import co.imob.version1.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private LoginContract.Presenter presenter;

    private EditText loginEtEmail, loginEtPassword;
    private TextView loginTvRedirect;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginPresenter(this);

        loginEtEmail = findViewById(R.id.login_et_email);
        loginEtPassword = findViewById(R.id.login_et_password);

        loginBtn = findViewById(R.id.login_btn_next);
        loginBtn.setOnClickListener(view -> {

            String email = loginEtEmail.getText().toString();
            String password = loginEtPassword.getText().toString();

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