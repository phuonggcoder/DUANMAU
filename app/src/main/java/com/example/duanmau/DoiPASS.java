package com.example.duanmau;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duanmau.DAO.NguoiDungDAO;


public class DoiPASS extends AppCompatActivity {

    private EditText etNewPassword, etConfirmPassword;
    private Button btnResetPassword;
    private String email;
    private NguoiDungDAO nguoiDungDAO;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_pass);

        etNewPassword = findViewById(R.id.etNewPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnResetPassword = findViewById(R.id.btnResetPassword);

        email = getIntent().getStringExtra("EMAIL");

        nguoiDungDAO = new NguoiDungDAO(context);

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPassword = etNewPassword.getText().toString().trim();
                String confirmPassword = etConfirmPassword.getText().toString().trim();

                if (!newPassword.isEmpty() && newPassword.equals(confirmPassword)) {
                    if ( nguoiDungDAO.checkEmailExist(email)) {
                        if (nguoiDungDAO.updatePasswordByEmail(email, newPassword)) {
                            Toast.makeText(DoiPASS.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(DoiPASS.this, "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(DoiPASS.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
