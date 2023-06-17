package co.imob.version1.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import co.imob.version1.R;
import co.imob.version1.presenter.ProfileContract;
import co.imob.version1.presenter.ProfilePresenter;

public class ProfileActivity extends AppCompatActivity implements ProfileContract.View {

    private ProfilePresenter presenter;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        presenter = new ProfilePresenter(this);

        btnLogout = findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(view -> {
            logout();
        });
    }

    @Override
    public void logout() {
        presenter.logoutAction();
    }

    @Override
    public void goToLoginActivity() {
        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public Context getContext() {
        return this;
    }
}