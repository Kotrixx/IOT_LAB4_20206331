package com.example.iot_lab4_20206331.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.iot_lab4_20206331.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;

public class AppActivity extends AppCompatActivity {

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);

        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();

            bottomNav.setOnItemSelectedListener(item -> {
                int destinationId = item.getItemId();

                if (navController.getCurrentDestination() != null &&
                        navController.getCurrentDestination().getId() != destinationId) {

                    NavOptions navOptions = new NavOptions.Builder()
                            .setPopUpTo(R.id.nav_graph, true)
                            .build();

                    navController.navigate(destinationId, null, navOptions);
                }

                return true;
            });
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
