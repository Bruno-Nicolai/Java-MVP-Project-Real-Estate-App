package co.imob.version1.view.activity;

import android.content.Intent;
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

public class SignUpActivity extends AppCompatActivity {

    private EditText signupEtName, signupEtEmail, signupEtPassword, signupEtConfirmPassword;
    private TextView signupTvRedirect;
    private Button signupBtn;

    private FirebaseDatabase db;
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signupEtName = findViewById(R.id.signup_et_name);
        signupEtEmail = findViewById(R.id.signup_et_email);
        signupEtPassword = findViewById(R.id.signup_et_password);
        signupEtConfirmPassword = findViewById(R.id.signup_et_confirm_password);

        signupBtn = findViewById(R.id.signup_btn_next);
        signupBtn.setOnClickListener(view -> {

            db = FirebaseDatabase.getInstance();
            dbRef = db.getReference();

            String name = signupEtName.getText().toString();
            String email = signupEtEmail.getText().toString();
            String password = signupEtPassword.getText().toString();

            Auth auth = new Auth(name, email, password);
            dbRef.child(name).setValue(auth);

            Toast.makeText(this, "Welcome, " + name + "! You are now a new member.", Toast.LENGTH_LONG).show();

            goToMainActivity();
        });

        signupTvRedirect = findViewById(R.id.signup_btn_go_to_login);
        signupTvRedirect.setOnClickListener(view -> {
            goToLoginActivity();
        });

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