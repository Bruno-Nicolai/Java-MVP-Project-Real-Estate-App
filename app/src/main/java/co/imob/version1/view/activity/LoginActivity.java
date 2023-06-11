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

    private DatabaseReference dbRef;

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
/*
    public void saveData() {
        String email = loginEtEmail.getText().toString();
        String password = loginEtPassword.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", email);
        editor.putString("password", password);
        editor.apply();
    }

    public boolean validateEmail() {
        String value = loginEtEmail.getText().toString();
        if (value.isEmpty()) {
            loginEtEmail.setError("Invalid Email Address.");
            return false;
        } else {
            loginEtEmail.setError(null);
            return true;
        }
    }

    public boolean validatePassword() {
        String value = loginEtPassword.getText().toString();
        if (value.isEmpty()) {
            loginEtPassword.setError("Invalid Password!");
            return false;
        } else {
            loginEtPassword.setError(null);
            return true;
        }
    }

    public void checkUser() {
        String email = loginEtEmail.getText().toString().trim();
        String password = loginEtPassword.getText().toString().trim();

        dbRef = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDb = dbRef.orderByChild("email").equalTo(email);
        checkUserDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    loginEtEmail.setError(null);
                    String pwdFromDb = snapshot.child(email).child("email").getValue(String.class);

                    if (!Objects.equals(pwdFromDb, password)) {
                        loginEtPassword.setError(null);
                        goToMainActivity();
                    } else {
                        loginEtPassword.setError("Enter your password again, please.");
                        loginEtPassword.requestFocus();
                    }

                } else {
                    loginEtEmail.setError("Email does not exist.");
                    loginEtEmail.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void goToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void goToSignupActivity() {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }*/

    @Override
    public void showEmailError(String errorMessage) {
        loginEtEmail.setError(errorMessage);
    }

    @Override
    public void showPasswordError(String errorMessage) {
        loginEtPassword.setError(errorMessage);
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
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

}