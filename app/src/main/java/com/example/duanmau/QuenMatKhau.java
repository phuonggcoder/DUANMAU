package com.example.duanmau;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duanmau.DAO.NguoiDungDAO;

public class QuenMatKhau extends AppCompatActivity {

    private EditText etEmail;
    private Button btnSubmit;
    private TextView tv;
    private NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mat_khau);

        etEmail = findViewById(R.id.etEmail);
        btnSubmit = findViewById(R.id.btnSubmit);
        tv = findViewById(R.id.tvBackToLogin);
        nguoiDungDAO = new NguoiDungDAO(this); // Initialize here

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuenMatKhau.this, Login.class);
                startActivity(intent);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                if (!email.isEmpty()) {
                    if (nguoiDungDAO.checkEmailExist(email)) {
                        Intent intent = new Intent(QuenMatKhau.this, DoiPASS.class);
                        intent.putExtra("email", email);
                        startActivity(intent);
                    } else {
                        Toast.makeText(QuenMatKhau.this, "Email không tồn tại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(QuenMatKhau.this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}