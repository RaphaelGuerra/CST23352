package com.example.cst2335;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String SHARED_PREFS = "sharedPrefs";
    private static final String TEXT = "text";
    private SharedPreferences sp;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        sp = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        email = findViewById(R.id.email);
        Button login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfileActivity();
            }
        });
        loadData();
    }
    private void openProfileActivity() {
        Intent nextPage = new Intent(MainActivity.this, ProfileActivity.class);
        nextPage.putExtra(SHARED_PREFS, email.getText().toString());
        startActivity(nextPage);
    }
    private void saveData() {
        String whatWasTyped = email.getText().toString();
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(TEXT, whatWasTyped);
        editor.apply();
        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
    }
    private void loadData() {
        String savedString = sp.getString(TEXT, "");
        email.setText(savedString);
    }
    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }
}