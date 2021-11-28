package com.manager.qlsinhvvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class StartActivity extends AppCompatActivity {
    private ImageView btnStartLop,btnStartSinhVien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        btnStartLop= findViewById(R.id.btnStartLop);
        btnStartSinhVien = findViewById(R.id.btnStartSinhVien);


        btnStartLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnStartSinhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this,SinhvienmainActivity.class);
                startActivity(intent);
            }
        });

    }
}