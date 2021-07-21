package com.example.cyberbullingdetect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;




public class MainActivity extends AppCompatActivity {

    EditText text_id;
    Button btn_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
        startActivity(intent);
        text_id = findViewById(R.id.text_id);
        btn_id = findViewById(R.id.btn_id);

    }
}