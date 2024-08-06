package com.example.duanmau.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.R;
import com.example.duanmau.model.ThanhVien;

import java.util.ArrayList;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ThanhVienViewHolder> {

    private ArrayList<ThanhVien> thanhVienList;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onEditClick(ThanhVien thanhVien);
        void onDeleteClick(ThanhVien thanhVien);
    }

    public ThanhVienAdapter(ArrayList<ThanhVien> thanhVienList, OnItemClickListener listener) {
        this.thanhVienList = thanhVienList;
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ThanhVienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.thongtinthanhvien, parent, false);
        return new ThanhVienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThanhVienViewHolder holder, int position) {
        ThanhVien thanhVien = thanhVienList.get(position);
        holder.matv.setText(String.valueOf(thanhVien.getMatv())); // Đã sửa ID đúng
        holder.hoten.setText(thanhVien.getHoten()); // Đã sửa ID đúng
        holder.namsinh.setText(thanhVien.getNamsinh()); // Đã sửa ID đúng
        holder.gioitinh.setText(thanhVien.getGioitinh()); // Đã giữ nguyên vì đúng

        holder.btnEdit.setOnClickListener(v -> onItemClickListener.onEditClick(thanhVien));
        holder.btnDelete.setOnClickListener(v -> onItemClickListener.onDeleteClick(thanhVien));
    }

    @Override
    public int getItemCount() {
        return thanhVienList.size();
    }


        public static class ThanhVienViewHolder extends RecyclerView.ViewHolder {
            TextView matv, hoten, namsinh, gioitinh;
            Button btnEdit, btnDelete;

            public ThanhVienViewHolder(@NonNull View itemView) {
                super(itemView);
                matv = itemView.findViewById(R.id.manv); // Sửa từ matv thành manv
                hoten = itemView.findViewById(R.id.ten); // Sửa từ hoten thành ten
                namsinh = itemView.findViewById(R.id.tvNamsinh); // Sửa từ namsinh thành tvNamsinh
                gioitinh = itemView.findViewById(R.id.gioitinh); // Giữ nguyên vì đã đúng
                btnEdit = itemView.findViewById(R.id.btCapNhat);
                btnDelete = itemView.findViewById(R.id.btXoa);
            }
        }
}
