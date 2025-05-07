package com.example.iot_lab4_20206331.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.iot_lab4_20206331.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

public class AppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        // Obtener el BottomNavigationView
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        // Obtener el NavHostFragment y el NavController
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            NavController navController = navHostFragment.getNavController();
            // Configurar el BottomNavigationView con el NavController
            NavigationUI.setupWithNavController(bottomNav, navController);
        }
    }
}
