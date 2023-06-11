package co.imob.version1.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import co.imob.version1.R;
import co.imob.version1.model.Auth;
import co.imob.version1.presenter.SignUpContract;
import co.imob.version1.presenter.SignUpPresenter;

public class SignUpActivity extends AppCompatActivity implements SignUpContract.View {

    private SignUpContract.Presenter presenter;

    private EditText signupEtName, signupEtEmail, signupEtPassword, signupEtConfirmPassword;
    private TextView signupTvRedirect;
    private Button signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        presenter = new SignUpPresenter(this);

        signupEtName = findViewById(R.id.signup_et_name);
        signupEtEmail = findViewById(R.id.signup_et_email);
        signupEtPassword = findViewById(R.id.signup_et_password);
        signupEtConfirmPassword = findViewById(R.id.signup_et_confirm_password);

        signupBtn = findViewById(R.id.signup_btn_next);
        signupBtn.setOnClickListener(view -> {

            String name = signupEtName.getText().toString();
            String email = signupEtEmail.getText().toString();
            String password = signupEtPassword.getText().toString();
            String confirmPassword = signupEtConfirmPassword.getText().toString();

            if (!presenter.validateSignUpForm(name, email, password, confirmPassword)) {
                showToast("Try Again");
            } else {
                presenter.saveUser(new Auth(name, email, password));
            }

        });

        signupTvRedirect = findViewById(R.id.signup_btn_go_to_login);
        signupTvRedirect.setOnClickListener(view -> {
            goToLoginActivity();
        });

    }

    @Override
    public void showNameError(String errorMessage) {
        signupEtName.setError(errorMessage);
    }

    @Override
    public void showEmailError(String errorMessage) {
        signupEtEmail.setError(errorMessage);
    }

    @Override
    public void showPasswordError(String errorMessage) {
        signupEtPassword.setError(errorMessage);
    }

    @Override
    public void showConfirmPasswordError(String errorMessage) {
        signupEtConfirmPassword.setError(errorMessage);
    }

    @Override
    public void goToMainActivity() {
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void goToLoginActivity() {
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}