package com.example.duanmau;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class HeaderActivity extends AppCompatActivity {

    ImageView imgChao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.headerlayout);

        imgChao=findViewById(R.id.imgChao);

        imgChao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HeaderActivity.this, MainActivity.class);
                startActivity(i);
                finish();
                Toast.makeText(HeaderActivity.this, "Quay ve Trang chu", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

