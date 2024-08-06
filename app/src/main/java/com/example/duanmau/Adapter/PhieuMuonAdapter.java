package com.example.duanmau.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.DAO.PhieuMuonDAO;
import com.example.duanmau.R;
import com.example.duanmau.model.PhieuMuon;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.ViewHolder> {

    private ArrayList<PhieuMuon> list;
    Context context;

    public PhieuMuonAdapter(ArrayList<PhieuMuon> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_rv_phieumuon, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvMapm.setText("Mã Phiếu Mượn: " + list.get(position).getMapm());
        holder.tvMand.setText("Mã Người Dùng: " + list.get(position).getMand());
        holder.tvMaSach.setText("Mã Sách: " + list.get(position).getMasach());
        holder.tvTenSach.setText("Tên Sách: " + list.get(position).getTensach());
        holder.tvNgayMuon.setText("Ngày Mượn: " + list.get(position).getNgaymuon());
        holder.tvNgayTra.setText("Ngày Trả: " + list.get(position).getNgaytra());
        holder.tvTienThue.setText("Tiền Thuê: " + list.get(position).getTienthue());
        holder.tvTenND.setText("Tên Người Dùng: " + list.get(position).getTennd());
        holder.tvTenKH.setText("Tên Khách Hàng: " + list.get(position).getTenkhachhang());
        holder.tvSDT.setText("Số Điện Thoại: " + list.get(position).getSdt());
        String trangThai ="";
        if(list.get(position).getTrasach() == 1){
            trangThai = "Đã Trả Sách";
            holder.btnTrasach.setVisibility(View.GONE);
        }else {
            trangThai = "Chưa Trả Sách";
        }
        holder.tvTrangThai.setText("Trạng Thái: " + trangThai);

        holder.btnTrasach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhieuMuonDAO phieuMuonDAO = new PhieuMuonDAO(context);
                boolean check = phieuMuonDAO.thayDoiTrangThai(list.get(holder.getAdapterPosition()).getMapm());

                if(check){
                    list.clear();
                    list = phieuMuonDAO.getDSPhieuMuon();
                     notifyDataSetChanged();
                     Toast.makeText(context, "Trả sách thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvMapm, tvMand, tvMaSach, tvTenSach, tvNgayMuon, tvNgayTra, tvTienThue, tvTenND, tvTenKH, tvSDT, tvTrangThai;
        Button btnTrasach;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMapm = itemView.findViewById(R.id.tvMapm);
            tvMand = itemView.findViewById(R.id.tvMand);
            tvMaSach = itemView.findViewById(R.id.tvMaSach);
            tvTenSach = itemView.findViewById(R.id.tvTenSach);
            tvNgayMuon = itemView.findViewById(R.id.tvNgayMuon);
            tvNgayTra = itemView.findViewById(R.id.tvNgayTra);
            tvTienThue = itemView.findViewById(R.id.tvTienThue);
            tvTenND = itemView.findViewById(R.id.tvTenND);
            tvTenKH = itemView.findViewById(R.id.tvTenKH);
            tvSDT = itemView.findViewById(R.id.tvSDT);
            tvTrangThai = itemView.findViewById(R.id.tvTrangThai);
            btnTrasach = itemView.findViewById(R.id.btnTrasach);

        }
    }
}
