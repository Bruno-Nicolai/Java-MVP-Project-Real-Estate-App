package co.imob.version1.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import co.imob.version1.R;

public class FirstSightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_sight);

        Button btn = (Button) findViewById(R.id.btn_init);
        btn.setOnClickListener(view -> {
            goToLoginActivity();
        });

    }

    public void goToLoginActivity() {
        Intent intent = new Intent(FirstSightActivity.this, LoginActivity.class);
        startActivity(intent);
    }

}