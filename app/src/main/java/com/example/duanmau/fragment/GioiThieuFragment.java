package com.example.duanmau.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SharedMemory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.duanmau.GioiThieuNguoiDung;
import com.example.duanmau.GioiThieuQuanLy;
import com.example.duanmau.GioiThieuSach;
import com.example.duanmau.GioiThieuTheLoai;
import com.example.duanmau.GioiThieuThongKe;
import com.example.duanmau.GioiThieuYeuThich;
import com.example.duanmau.Login;
import com.example.duanmau.R;

public class GioiThieuFragment extends Fragment {
    ImageView img, img1, img2, img3, img4, img5;
    Button bt;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_aaaaa, container, false);

        img = view.findViewById(R.id.nguoidung);
        img1 = view.findViewById(R.id.sach);
        img2 = view.findViewById(R.id.thongke);
        img3 = view.findViewById(R.id.quanlithanhvien);
        img4 = view.findViewById(R.id.theloai);
        img5 = view.findViewById(R.id.yeuthich);
        bt = view.findViewById(R.id.btdangxuat);




        // Set click listeners for image views
        img.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), GioiThieuNguoiDung.class);
            startActivity(intent);
        });

        img1.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), GioiThieuSach.class);
            startActivity(intent);
        });

        img2.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), GioiThieuThongKe.class);
            startActivity(intent);
        });

        img3.setOnClickListener(v -> {
             Intent intent = new Intent(getContext(), GioiThieuQuanLy.class);
             startActivity(intent);
        });

        img4.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), GioiThieuTheLoai.class);
            startActivity(intent);
        });

        img5.setOnClickListener(v -> {
             Intent intent = new Intent(getContext(), GioiThieuYeuThich.class);
             startActivity(intent);
        });


        return view;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
