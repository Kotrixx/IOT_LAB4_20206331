package com.example.iot_lab4_20206331.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import android.provider.Settings;

import com.example.iot_lab4_20206331.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button enterButton = findViewById(R.id.enter_button);

        enterButton.setOnClickListener(v -> {
            if (isConnectedToInternet()) {
                startActivity(new Intent(MainActivity.this, AppActivity.class));
                finish();
            } else {
                // Si no hay conexión, mostrar el dialog
                showNoInternetDialog();
            }
        });
    }

    // Método para verificar la conexión a Internet
    private boolean isConnectedToInternet() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    // Mostrar un dialog si no hay conexión a Internet
    private void showNoInternetDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("No Internet Connection")
                .setMessage("You need an internet connection to proceed. Would you like to go to settings?")
                .setCancelable(false)
                .setPositiveButton("Settings", (dialog, id) -> {
                    // Redirige a los ajustes de Wi-Fi
                    Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                    startActivity(intent);
                })
                .setNegativeButton("Exit", (dialog, id) -> {
                    // Cierra la aplicación
                    finish();
                })
                .show();
    }
}
