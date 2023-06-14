package co.imob.version1.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import co.imob.version1.R;
import co.imob.version1.presenter.FirstSightContract;
import co.imob.version1.presenter.FirstSightPresenter;

public class FirstSightActivity extends AppCompatActivity implements FirstSightContract.View {

    private FirstSightContract.Presenter presenter;

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_sight);

        presenter = new FirstSightPresenter(this);
        presenter.checkFirstRun();

        btn = findViewById(R.id.btn_init);
        btn.setOnClickListener(view -> {
            goToSignupActivity();
        });

    }

    @Override
    public void showWelcomeScreen() {

    }

    public void goToLoginActivity() {
        Intent intent = new Intent(FirstSightActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void goToSignupActivity() {
        Intent intent = new Intent(FirstSightActivity.this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public Context getContext() {
        return this;
    }

}