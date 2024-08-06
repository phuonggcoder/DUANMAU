package com.example.duanmau.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.DAO.LoaiSachDAO;
import com.example.duanmau.DAO.PhieuMuonDAO;
import com.example.duanmau.R;
import com.example.duanmau.model.LoaiSach;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.ViewHolder> implements Filterable {

    private Context context;
    private ArrayList<LoaiSach> list;
    private LoaiSachDAO loaiSachDAO;

    //fooce- tim keyword phu hop
    private ArrayList<LoaiSach> listold;

    public LoaiSachAdapter(Context context, ArrayList<LoaiSach> list, LoaiSachDAO loaiSachDAO, ArrayList<LoaiSach> listold) {
        this.context = context;
        this.list = list;
        this.loaiSachDAO = loaiSachDAO;
        this.listold = listold;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_qlloaisach,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvML.setText("Mã PM: PNL " + String.valueOf(list.get(position).getMaloai()));
        holder.tvTL.setText("Thể Loại: " + list.get(position).getTenloai());

        holder.imgSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogUpdate(list.get(holder.getAdapterPosition()));
            }
        });
        holder.imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có muốn xóa thể loại này?");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int check = loaiSachDAO.xoaLoaiSach(list.get(holder.getAdapterPosition()).getMaloai());
                        switch (check){
                            case -1:
                                Toast.makeText(context, "Không thể xóa", Toast.LENGTH_SHORT).show();
                                break;
                            case 0:
                                Toast.makeText(context, "Vui lòng xóa sách trước", Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                loadData();
                                break;
                        }
                    }
                });

                builder.setNegativeButton("Không",null);
                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();


    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvML, tvTL;
        ImageView imgSua, imgXoa;
        public ViewHolder(@NonNull View itemview){
            super(itemview);

            tvML=itemview.findViewById(R.id.tvma);
            tvTL=itemview.findViewById(R.id.tenTL);
            imgSua=itemview.findViewById(R.id.imgSua);
            imgXoa=itemview.findViewById(R.id.imgXoa);
        }

    }

    private void showDialogUpdate(LoaiSach loaiSach){
        AlertDialog.Builder builder= new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_them_loaisach,null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();

        TextView txtTieuDe = view.findViewById(R.id.tvTieuDe);
        EditText edtTenLoai = view.findViewById(R.id.etTenTL);
        Button btnLuu = view.findViewById(R.id.btnAdd);
        Button btnHuy = view.findViewById(R.id.btnCancel);

        txtTieuDe.setText("Cập nhật thông tin");
        edtTenLoai.setText("" + loaiSach.getTenloai());
        btnLuu.setText("Cập nhật");

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenLoai = edtTenLoai.getText().toString();
                LoaiSach loaiSachUpdate = new LoaiSach(loaiSach.getMaloai(),tenLoai);
                boolean check = loaiSachDAO.suaLoaiSach(loaiSachUpdate);
                if(check){
                    Toast.makeText(context, "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show();
                    loadData();
                    dialog.dismiss();
                }else {
                    Toast.makeText(context, "Cập nhật thông tin thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });



    }

    private void loadData(){
        list.clear();
        list = loaiSachDAO.getDSLoaiSach();
        notifyDataSetChanged();



    }
    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String search = constraint.toString();
                    if(search.isEmpty()){
                        list=listold;
                    }else {
                        ArrayList<LoaiSach> listFilter = new ArrayList<>();
                        for(LoaiSach loaiSach : listold){
                            if (loaiSach.getTenloai().toLowerCase().contains(search.toLowerCase())){
                                listFilter.add(loaiSach);
                            }
                        }
                        list = listFilter;
                    }

                    FilterResults filterResults = new FilterResults();
                     filterResults.values = list;


                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (ArrayList<LoaiSach>) results.values;
                notifyDataSetChanged();

            }
        };
    }
}
