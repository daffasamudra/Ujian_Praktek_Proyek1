package com.id.proyek1App;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class LoginActivity extends AppCompatActivity {
    public static final String FILENAME = "login";
    private EditText etUsername;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        Button btLogin = findViewById(R.id.btLogin);
        Button btRegister = findViewById(R.id.btRegister);

        btLogin.setOnClickListener(v -> login());

        btRegister.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    void simpanFileLogin() {
        String isiFile = etUsername.getText().toString() + ";" + etPassword.getText().toString();
        File file = new File(getFilesDir(), FILENAME);

        try (FileOutputStream fos = new FileOutputStream(file, false)) {
            fos.write(isiFile.getBytes());
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show();

        // Start the second splash screen
        Intent intent = new Intent(this, SecondSplashScreenActivity.class);
        startActivity(intent);
        finish();
    }

    void login() {
        File file = new File(getFilesDir(), etUsername.getText().toString());

        if (file.exists()) {
            StringBuilder text = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line = br.readLine();
                text.append(line);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String[] dataUser = text.toString().split(";");

            if (dataUser.length == 6 &&
                    dataUser[0].equals(etUsername.getText().toString()) &&
                    dataUser[1].equals(etPassword.getText().toString())) {
                // Jika username dan password cocok, simpan login dan lanjutkan
                simpanFileLogin();
            } else {
                Toast.makeText(this, "Username atau Password Tidak Sesuai", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "User Tidak Ditemukan", Toast.LENGTH_SHORT).show();
        }
    }
}
