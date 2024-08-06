package com.example.duanmau;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.DAO.NguoiDungDAO;
import com.example.duanmau.DAO.SanPhamDAO;
import com.example.duanmau.fragment.DoanhThuFragment;
import com.example.duanmau.fragment.GioiThieuFragment;

import com.example.duanmau.fragment.QLLoaiSachFragment;

// Import your other fragments here
import com.example.duanmau.fragment.QLPhieuMuonFragment;
import com.example.duanmau.fragment.QLSachFragment;
import com.example.duanmau.fragment.QLThanhVienFragment;
import com.example.duanmau.fragment.QLSachYeuThichFragment;
import com.example.duanmau.fragment.QLGiuSachFragment;

import com.example.duanmau.fragment.ThemThanhVienFragment;


import com.example.duanmau.model.SanPham;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class TrangChu extends AppCompatActivity {

    RecyclerView rv;
    FloatingActionButton floatingActionButton;
    SanPhamDAO sanPhamDAO;
    ArrayList<SanPham>ds;
    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_trangchu);

        Toolbar toolbar = findViewById(R.id.toolbar);
        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        NavigationView navigationView = findViewById(R.id.navigationview);
        drawerLayout = findViewById(R.id.drawerlayout);
        sanPhamDAO=new SanPhamDAO(TrangChu.this);
        ds= new ArrayList<SanPham>();



        // Chuyển đổi các chức năng actionbar cho thằng toolbar
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.hinh);

        Fragment fragment = new GioiThieuFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();

        SharedPreferences sharedPreferences = getSharedPreferences("dataUser", Context.MODE_PRIVATE);
        int role = sharedPreferences.getInt("role", -1);

        switch (role){

            case 1:
                navigationView.getMenu().findItem(R.id.ThemThanhVien).setVisible(true);;
                break;
            case 2:
                break;
            default:
        }


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;

                if (menuItem.getItemId() == R.id.mQLPhieuMuon) {
                 fragment = new QLPhieuMuonFragment();
                } else if (menuItem.getItemId() == R.id.mQLLoaiSach) {
                    fragment= new QLLoaiSachFragment();
                } else if (menuItem.getItemId() == R.id.mQLSach) {
                    fragment = new QLSachFragment();
                } else if (menuItem.getItemId() == R.id.mQLThanhVien) {
                    fragment = new QLThanhVienFragment();
                } else if (menuItem.getItemId() == R.id.mQLSachYeuThich) {
                    fragment = new QLSachYeuThichFragment();
                } else if (menuItem.getItemId() == R.id.mDoanhThu) {
                    fragment = new DoanhThuFragment();
                } else if (menuItem.getItemId() == R.id.mQLGiuSach) {
                    fragment = new QLGiuSachFragment();
                } else if (menuItem.getItemId() == R.id.GioiThieu) {
                    fragment = new GioiThieuFragment();
                } else if (menuItem.getItemId() == R.id.ThemThanhVien) {
                    fragment = new ThemThanhVienFragment();
                } else if (menuItem.getItemId() == R.id.doimatkhau) {
                    showDialogDoimatkhau();
                    return true;
                } else if (menuItem.getItemId() == R.id.dangxuat) {
                    startActivity(new Intent(TrangChu.this, Login.class));
                    getIntent().setFlags(getIntent().FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    finish();
                } else {
//                    fragment = new QLPhieuMuonFragement();
                }

                if (fragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    toolbar.setTitle(menuItem.getTitle());
                }
                return true;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }




    private void showDialogDoimatkhau() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_doimatkhau, null);
        EditText editTextOldPass = view.findViewById(R.id.etMkcu);
        EditText editTextNewPass = view.findViewById(R.id.etMKM);
        EditText editTextConfirmNewPass = view.findViewById(R.id.etMKMM);


        builder.setView(view)
                .setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cập Nhật", null);

        AlertDialog dialog = builder.create();
        dialog.show();

        // Override the Negative button's onClick to handle validation
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPass = editTextOldPass.getText().toString().trim();
                String newPass = editTextNewPass.getText().toString().trim();
                String confirmNewPass = editTextConfirmNewPass.getText().toString().trim();


                if (oldPass.isEmpty() || newPass.isEmpty() || confirmNewPass.isEmpty()) {
                    Toast.makeText(TrangChu.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!newPass.equals(confirmNewPass)) {
                    Toast.makeText(TrangChu.this, "Mật khẩu mới và xác nhận mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                    return;
                }

                NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(TrangChu.this);
                boolean isUpdated = false;

                    isUpdated = nguoiDungDAO.capNhatMatKhau("nguyenvang", oldPass, newPass); // Replace "nguyenvang" with actual username


                if (isUpdated) {
                    Toast.makeText(TrangChu.this, "Cập nhật mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(TrangChu.this, "Cập nhật mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
