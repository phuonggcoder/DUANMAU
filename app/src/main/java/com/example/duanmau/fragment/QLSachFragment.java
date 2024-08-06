package com.example.duanmau.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.DAO.LoaiSachDAO;
import com.example.duanmau.DAO.SachDAO;
import com.example.duanmau.R;
import com.example.duanmau.Adapter.SachAdapter;
import com.example.duanmau.model.LoaiSach;
import com.example.duanmau.model.Sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class QLSachFragment extends Fragment {
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private SachAdapter sachAdapter;
    private SachDAO sachDAO;
    private ArrayList<Sach> sachList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlsach, container, false);
        recyclerView = view.findViewById(R.id.rv);
        fab = view.findViewById(R.id.themmoi);
        sachDAO = new SachDAO(getContext());



        // Gán OnClickListener cho FloatingActionButton
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogThemSach();
            }
        });
        loadData();

        return view;
    }

    private void loadData() {
        sachList = sachDAO.getDSDauSach();
        sachAdapter = new SachAdapter(sachList, new SachAdapter.OnItemClickListener() {
            @Override
            public void onEditClick(Sach sach) {
                showDialogSuaSach(sach);
            }

            @Override
            public void onDeleteClick(Sach sach) {
                showDialogXoaSach(sach);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(sachAdapter);
    }




    private void showDialogSuaSach(Sach sach) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View dialogView = inflater.inflate(R.layout.dailog_loaisach, null);
        builder.setView(dialogView);

        EditText ettensach = dialogView.findViewById(R.id.ettensach);
        EditText ettacgia = dialogView.findViewById(R.id.ettacgia);
        EditText etgiaban = dialogView.findViewById(R.id.etgiaban);
        EditText etmaloai = dialogView.findViewById(R.id.etmaloai);

        ettensach.setText(sach.getTensach());
        ettacgia.setText(sach.getTacgia());
        etgiaban.setText(String.valueOf(sach.getGiathue()));
        etmaloai.setText(String.valueOf(sach.getMaloai()));

        builder.setPositiveButton("Lưu", (dialog, which) -> {
            String tensach = ettensach.getText().toString();
            String tacgia = ettacgia.getText().toString();
            String giabanString = etgiaban.getText().toString();
            String maloaiString = etmaloai.getText().toString();

            if (!tensach.isEmpty() && !tacgia.isEmpty() && !giabanString.isEmpty() && !maloaiString.isEmpty()) {
                try {
                    int giathue = Integer.parseInt(giabanString);
                    int maloai = Integer.parseInt(maloaiString);

                    sach.setTensach(tensach);
                    sach.setTacgia(tacgia);
                    sach.setGiathue(giathue);
                    sach.setMaloai(maloai);

                    sachDAO.capNhatSach(sach);

                    Toast.makeText(getContext(), "Đã cập nhật sách", Toast.LENGTH_SHORT).show();
                    loadData(); // Tải lại dữ liệu sau khi cập nhật

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
    private void showDialogXoaSach(Sach sach) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Xóa sách");
        builder.setMessage("Bạn có chắc chắn muốn xóa sách này?");
        builder.setPositiveButton("Xóa", (dialog, which) -> {
            sachDAO.xoaSach(sach.getMasach());
            Toast.makeText(getContext(), "Đã xóa sách", Toast.LENGTH_SHORT).show();
            loadData(); // Tải lại dữ liệu sau khi xóa
        });
        builder.setNegativeButton("Hủy", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showDialogThemSach() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View dialogView = inflater.inflate(R.layout.dialog_themsach, null);
        builder.setView(dialogView);

        EditText etTenSach = dialogView.findViewById(R.id.etTenSach);
        EditText etTacGia = dialogView.findViewById(R.id.etTacGia);
        EditText etGiaThue = dialogView.findViewById(R.id.etGiaThue);
        Spinner spTheLoai = dialogView.findViewById(R.id.spTheLoai);



        SimpleAdapter simpleAdapter =new SimpleAdapter(
                getContext(),
                getDSLoaiSach(),
                android.R.layout.simple_list_item_1,
                new String[]{"tenloai"},
                new int[]{android.R.id.text1}
        );
        spTheLoai.setAdapter(simpleAdapter);



        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String tensach = etTenSach.getText().toString();
                String giaThueString = etGiaThue.getText().toString();
                String tacgia = etTacGia.getText().toString();

                if (!giaThueString.isEmpty()) {
                    int giathue = Integer.parseInt(giaThueString);
                    HashMap<String, Object> hs = (HashMap<String, Object>) spTheLoai.getSelectedItem();
                    int maloai = (int) hs.get("maloai");

                    boolean check = sachDAO.themSach(tensach, tacgia, giathue,maloai);
                    if (check) {
                        Toast.makeText(getContext(), "Thêm sách thành công", Toast.LENGTH_SHORT).show();
                        loadData();
                    } else {
                        Toast.makeText(getContext(), "Thêm không thành công", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Vui lòng nhập giá thuê", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });


        AlertDialog dialog = builder.create();
        dialog.show();


    }

    public ArrayList<HashMap<String, Object>> getDSLoaiSach() {
        LoaiSachDAO loaiSachDAO = new LoaiSachDAO(getContext());
        ArrayList<LoaiSach> loaiSachList = loaiSachDAO.getDSLoaiSach();
        ArrayList<HashMap<String, Object>> listhm = new ArrayList<>();

        for (LoaiSach loaiSach: loaiSachList){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maloai", loaiSach.getMaloai());
            hs.put("tenloai", loaiSach.getTenloai());
            listhm.add(hs);
        }

        return listhm;
    }
}
