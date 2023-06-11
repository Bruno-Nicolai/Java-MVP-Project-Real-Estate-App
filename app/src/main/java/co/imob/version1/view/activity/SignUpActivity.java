package co.imob.version1.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import co.imob.version1.R;
import co.imob.version1.model.Auth;
import co.imob.version1.presenter.SignUpContract;
import co.imob.version1.presenter.SignUpPresenter;

public class SignUpActivity extends AppCompatActivity implements SignUpContract.View {

    private SignUpContract.Presenter presenter;

    private EditText signupEtName, signupEtEmail, signupEtPassword, signupEtConfirmPassword;
    private TextView signupTvRedirect;
    private Button signupBtn;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        presenter = new SignUpPresenter(this);

        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
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

            presenter.validateSignUpForm(name, email, password, confirmPassword);
            presenter.saveUser(new Auth(name, email, password), sharedPreferences);

            Toast.makeText(this, "Welcome, " + name + "! You are now a new member.", Toast.LENGTH_LONG).show();

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

    public void goToMainActivity() {
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void goToLoginActivity() {
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}