package com.example.duanmau.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.R;
import com.example.duanmau.model.Sach;

import java.util.ArrayList;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.SachViewHolder> {

    private ArrayList<Sach> sachList;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onEditClick(Sach sach);
        void onDeleteClick(Sach sach);
    }

    public SachAdapter(ArrayList<Sach> sachList, OnItemClickListener listener) {
        this.sachList = sachList;
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public SachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sach, parent, false);
        return new SachViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SachViewHolder holder, int position) {
        Sach sach = sachList.get(position);
        holder.tensach.setText("Tên Sách: " + sach.getTensach());
        holder.tacgia.setText("Tác Giả: " +sach.getTacgia());
        holder.giathue.setText("Giá Bán: " + String.valueOf(sach.getGiathue()));
        holder.masach.setText("Mã Sách: " + String.valueOf(sach.getMasach()));
        holder.maloai.setText("Mã Loại: " + String.valueOf(sach.getMaloai()));
        holder.tenloai.setText("Thể Loại: " + String.valueOf(sach.getTenloai()));

        holder.btnEdit.setOnClickListener(v -> onItemClickListener.onEditClick(sach));
        holder.btnDelete.setOnClickListener(v -> onItemClickListener.onDeleteClick(sach));
    }

    @Override
    public int getItemCount() {
        return sachList.size();
    }

    public static class SachViewHolder extends RecyclerView.ViewHolder {
        TextView tensach, tacgia, giathue, masach, maloai, tenloai;
        ImageButton btnEdit, btnDelete;

        public SachViewHolder(@NonNull View itemView) {
            super(itemView);
            masach = itemView.findViewById(R.id.tvtdt);
            maloai = itemView.findViewById(R.id.tvtth);
            tenloai = itemView.findViewById(R.id.tvtenloai);
            tensach = itemView.findViewById(R.id.tvtieude);
            tacgia = itemView.findViewById(R.id.tvtg);
            giathue = itemView.findViewById(R.id.tvtt);
            btnEdit = itemView.findViewById(R.id.btnsua);
            btnDelete = itemView.findViewById(R.id.btnchitiet);
        }
    }
}
