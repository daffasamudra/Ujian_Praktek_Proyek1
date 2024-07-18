package com.id.proyek1App;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SecondSplashScreenActivity extends AppCompatActivity {

    private static final int SPLASH_DELAY = 2000; // Delay in milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_splash_screen);

        // Use Handler to delay starting DashboardActivity
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SecondSplashScreenActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish(); // Finish this activity so users can't go back to it
        }, SPLASH_DELAY);
    }
}
