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

public class Login extends AppCompatActivity {
    private NguoiDungDAO nguoiDungDAO;
    EditText et1, et2;
    Button bt1, bt2;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        tv1 = findViewById(R.id.tv2);
        bt2 = findViewById(R.id.bt2);
        bt1 = findViewById(R.id.bt1);

        nguoiDungDAO = new NguoiDungDAO(this);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = et1.getText().toString();
                String pass = et2.getText().toString();
                int role = nguoiDungDAO.KiemTraDangNhap(user, pass);

                if (role == 1) {
                    Toast.makeText(Login.this, "Đăng nhập thành công với vai trò Thủ Thư!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, TrangChu.class);
                    startActivity(intent);
                } else if (role == 2) {
                    Toast.makeText(Login.this, "Đăng nhập thành công với vai trò Admin!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, TrangChu.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Login.this, "Tên Đăng Nhập Hoặc Mật Khẩu Bị Sai", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, DangKy.class);
                startActivity(i);
            }
        });

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, QuenMatKhau.class);
                startActivity(intent);
            }
        });
    }
}
