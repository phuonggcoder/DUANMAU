package com.example.duanmau.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.Adapter.ThanhVienAdapter;
import com.example.duanmau.DAO.ThanhVienDAO;
import com.example.duanmau.R;
import com.example.duanmau.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class QLThanhVienFragment extends Fragment {
    private RecyclerView recyclerView;
    private ThanhVienDAO thanhVienDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.qlthanhvien_fragment, container, false);
        recyclerView = view.findViewById(R.id.rvthanhvien);
        FloatingActionButton fab = view.findViewById(R.id.floatadd);
        thanhVienDAO = new ThanhVienDAO(getContext());

        // Gán OnClickListener cho FloatingActionButton
        fab.setOnClickListener(v -> showDialogThemThanhVien());

        loadData();

        return view;
    }

    private void loadData() {
        ArrayList<ThanhVien> thanhVienList = ThanhVienDAO.getDSThanhVien();
        ThanhVienAdapter thanhVienAdapter = new ThanhVienAdapter(thanhVienList, new ThanhVienAdapter.OnItemClickListener() {
            @Override
            public void onEditClick(ThanhVien thanhVien) {
                showDialogSuaThanhVien(thanhVien);
            }

            @Override
            public void onDeleteClick(ThanhVien thanhVien) {
                showDialogXoaThanhVien(thanhVien);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(thanhVienAdapter);
    }

    private void showDialogSuaThanhVien(ThanhVien thanhVien) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View dialogView = inflater.inflate(R.layout.dialog_themtv, null);
        builder.setView(dialogView);

        EditText etMatv = dialogView.findViewById(R.id.matv);
        EditText etTen = dialogView.findViewById(R.id.tentv);
        EditText etNamSinh = dialogView.findViewById(R.id.namsinh);
        EditText etGioiTinh = dialogView.findViewById(R.id.gioitinh);

        etMatv.setText(String.valueOf(thanhVien.getMatv()));
        etTen.setText(thanhVien.getHoten());
        etNamSinh.setText(thanhVien.getNamsinh());
        etGioiTinh.setText(thanhVien.getGioitinh());

        builder.setPositiveButton("Lưu", (dialog, which) -> {
            String maTV = etMatv.getText().toString().trim();
            String ten = etTen.getText().toString().trim();
            String namSinh = etNamSinh.getText().toString().trim();
            String gioiTinh = etGioiTinh.getText().toString().trim();

            if (!maTV.isEmpty() && !ten.isEmpty() && !namSinh.isEmpty() && !gioiTinh.isEmpty()) {
                try {
                    int matv = Integer.parseInt(maTV);
                    thanhVien.setMatv(matv);
                    thanhVien.setHoten(ten);
                    thanhVien.setNamsinh(namSinh);
                    thanhVien.setGioitinh(gioiTinh);

                    ThanhVienDAO.capNhattv(thanhVien);

                    Toast.makeText(getContext(), "Đã cập nhật thành viên", Toast.LENGTH_SHORT).show();
                    loadData();

                } catch (NumberFormatException e) {
                    Toast.makeText(getContext(), "Giá trị không hợp lệ", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Hủy", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showDialogXoaThanhVien(ThanhVien thanhVien) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Xóa thành viên");
        builder.setMessage("Bạn có chắc chắn muốn xóa thành viên này?");
        builder.setPositiveButton("Xóa", (dialog, which) -> {
            ThanhVienDAO.xoatv(thanhVien.getMatv());
            Toast.makeText(getContext(), "Đã xóa thành viên", Toast.LENGTH_SHORT).show();
            loadData();
        });
        builder.setNegativeButton("Hủy", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showDialogThemThanhVien() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View dialogView = inflater.inflate(R.layout.dialog_themtv, null);
        builder.setView(dialogView);

        EditText etMatv = dialogView.findViewById(R.id.matv); // ID chính xác
        EditText etTen = dialogView.findViewById(R.id.tentv); // ID chính xác
        EditText etNamSinh = dialogView.findViewById(R.id.namsinh); // ID chính xác
        EditText etGioiTinh = dialogView.findViewById(R.id.gioitinh); // ID chính xác
        Button btnThem = dialogView.findViewById(R.id.btCapNhat); // ID chính xác
        Button btnCancel = dialogView.findViewById(R.id.btXoa); // ID chính xác

        AlertDialog dialog = builder.create();
        dialog.show();

        btnThem.setOnClickListener(v -> {
            String maTV = etMatv.getText().toString().trim();
            String ten = etTen.getText().toString().trim();
            String namSinh = etNamSinh.getText().toString().trim();
            String gioiTinh = etGioiTinh.getText().toString().trim();

            if (!maTV.isEmpty() && !ten.isEmpty() && !namSinh.isEmpty() && !gioiTinh.isEmpty()) {
                try {
                    int matv = Integer.parseInt(maTV);
                    ThanhVien thanhVien = new ThanhVien(matv, ten, namSinh, gioiTinh);
                    thanhVienDAO.themtv(thanhVien);

                    Toast.makeText(getContext(), "Đã thêm thành viên", Toast.LENGTH_SHORT).show();
                    loadData();

                } catch (NumberFormatException e) {
                    Toast.makeText(getContext(), "Giá trị không hợp lệ", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            }
        });

        btnCancel.setOnClickListener(v -> dialog.dismiss());
    }
}
