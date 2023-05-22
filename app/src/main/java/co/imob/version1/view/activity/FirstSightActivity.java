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

        initView();
    }

    private void initView() {
        Button btn = findViewById(R.id.btn_init);
        btn.setOnClickListener(view -> {
            Intent intent = new Intent(FirstSightActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
