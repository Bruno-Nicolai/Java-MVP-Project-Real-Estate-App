package co.imob.version1.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import co.imob.version1.R;

public class MainActivity extends AppCompatActivity {

    private NavHostFragment navHostFragment;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        SharedPreferences sharedPreferences = getSharedPreferences(
//                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

//        String savedEmail = sharedPreferences.getString("email", "");
//        String savedPassword = sharedPreferences.getString("password", "");



        initNavigation();
    }


    public void initNavigation() {
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fcv_main);
        navController = navHostFragment.getNavController();

        BottomNavigationView bottomNavigation = findViewById(R.id.main_bottom_nav);
        NavigationUI.setupWithNavController(bottomNavigation, navController);
    }


}