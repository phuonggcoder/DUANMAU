package com.example.duanmau.Intent;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.duanmau.DoiPASS;
import com.example.duanmau.R;
import com.example.duanmau.Intent.capnhatthongtin;

public class nguoidung extends AppCompatActivity {
ImageView imv,imv1,imv2;
TextView tv1,tv2,tv3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nguoidung);

         imv1 = findViewById(R.id.imcapnhat);
         imv2 = findViewById(R.id.imdoimatkhau);
         tv1 = findViewById(R.id.tvthongtinthanhvien);
         tv2 = findViewById(R.id.tvthongtinthanhvien);
         tv3 = findViewById(R.id.tvdoimatkhau);
         imv = findViewById(R.id.imgthongtinthanhvien);
        imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(nguoidung.this, thongtinthanhvien.class);
                startActivity(i);
                finish();
            }
        });
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(nguoidung.this, thongtinthanhvien.class);
                startActivity(i);
                finish();
            }
        });
        imv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(nguoidung.this, capnhatthongtin.class);
                startActivity(i);
                finish();
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(nguoidung.this, capnhatthongtin.class);
                startActivity(i);
                finish();
            }
        });
        imv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(nguoidung.this, DoiPASS.class);
                startActivity(i);
                finish();
            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(nguoidung.this, DoiPASS.class);
                startActivity(i);
                finish();
            }
        });


    }
}