package com.example.duanmau.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.Adapter.PhieuMuonAdapter;
import com.example.duanmau.DAO.PhieuMuonDAO;
import com.example.duanmau.R;
import com.example.duanmau.model.PhieuMuon;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class QLPhieuMuonFragment extends Fragment {
    RecyclerView rvPhieuMuon;
    FloatingActionButton fadd;
    PhieuMuonAdapter adapter;
    ArrayList<PhieuMuon> list;
    PhieuMuonDAO phieuMuonDAO;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlphieumuon, container, false);

        rvPhieuMuon = view.findViewById(R.id.rvPhieuMuon);
        fadd = view.findViewById(R.id.fadd);


        //data
        phieuMuonDAO = new PhieuMuonDAO(getContext());
        list = phieuMuonDAO.getDSPhieuMuon();

        //adapter
        loadPhieuMuon();



        fadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddPhieuMuonDialog();
            }


        });




        return view;

    }
    private void loadPhieuMuon(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvPhieuMuon.setLayoutManager(linearLayoutManager);
        adapter = new PhieuMuonAdapter(list, getContext());
        rvPhieuMuon.setAdapter(adapter);
    }


    private void showAddPhieuMuonDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.dialog_add_phieumuon, null);
        EditText edtTenKhachHang = view.findViewById(R.id.etTenKhachHang);
        EditText edtSDT = view.findViewById(R.id.etSdtKhachHang);
        EditText edtMaSach = view.findViewById(R.id.etMaSach);
        TextView tvTenSach = view.findViewById(R.id.tvTenSach);
        TextView tvThanhvien = view.findViewById(R.id.tvThanhvien);
        TextView tvNgayMuon = view.findViewById(R.id.tvNgayMuon);
        TextView tvTienThue = view.findViewById(R.id.tvTienThue);

        // Set current date to tvNgayMuon
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        String currentDate = dateFormat.format(new Date());
        tvNgayMuon.setText(currentDate);

    }


}

