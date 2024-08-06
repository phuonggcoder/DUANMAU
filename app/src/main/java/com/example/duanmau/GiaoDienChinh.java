//package com.example.duanmau;
//
//import android.content.DialogInterface;
//import android.graphics.drawable.ColorDrawable;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.widget.Toolbar;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//
//import com.example.duanmau.DAO.SanPhamDAO;
//import com.example.duanmau.model.SanPham;
//
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//
//import java.util.ArrayList;
//
//public class GiaoDienChinh extends AppCompatActivity {
//    RecyclerView rv;
//    SanPhamDAO sanPhamDAO;
//    ArrayList<SanPham> ds;
//
//    FloatingActionButton flbtn;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_giao_dien_chinh);
//
//        FloatingActionButton floatadd = findViewById(R.id.themmoi);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Thư Viện Phương Nam Library");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbar.setNavigationIcon(android.R.drawable.btn_dialog);
//
//
//        rv = findViewById(R.id.rv);
//        sanPhamDAO = new SanPhamDAO(GiaoDienChinh.this);
//        ds=new ArrayList<SanPham>();
//
//        // Kiểm tra nếu bảng không tồn tại và thêm dữ liệu nếu cần
//        // Điều này nên được thực hiện một lần, không phải mỗi lần khởi tạo activity
//
//            sanPhamDAO.themSanPham(new SanPham(20, "tensach", "tacgia", "theloai", "tragnthai",10000));
//
//            dodulieu();
//
//
//        floatadd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showDialogthem();
//
//
//            }
//        });
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return true;
//    }
//
////    @Override
////    public boolean onOptionsItemSelected(MenuItem item) {
////        if (item.getItemId() == R.id.action_avatar) {
////            // Xử lý khi nhấn vào avatar
////            return true;
////        } else if (item.getItemId() == android.R.id.home) {
////            // Xử lý khi nhấn vào menu
////            return true;
////        }
////        return super.onOptionsItemSelected(item);
////    }
//
//    public void themSanPham(SanPham sanPham) {
//        sanPhamDAO.themSanPham(sanPham);
//        dodulieu();
//    }
//
//    public void xoasp(int index) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(GiaoDienChinh.this);
//        builder.setTitle("Cảnh Báo");
//        builder.setMessage("Bạn có chắc muốn xóa sản phẩm này không?");
//        builder.setCancelable(false);
//
//        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                sanPhamDAO.xoaSP(index);
//                dodulieu();
//            }
//        });
//
//        builder.setPositiveButton("No", null);
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }
//
//    public void suaSP(SanPham sp) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(GiaoDienChinh.this);
//        LayoutInflater inflater = getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.dailog_loaisach, null);
//        builder.setView(dialogView);
//
//        EditText ettensach = dialogView.findViewById(R.id.ettensach);
//        EditText ettacgia = dialogView.findViewById(R.id.ettacgia);
//        EditText ettheloai = dialogView.findViewById(R.id.ettheloai);
//        EditText ettienthue = dialogView.findViewById(R.id.ettienthue);
//        EditText ettieude = dialogView.findViewById(R.id.ettieude);
//        TextView tv_title = dialogView.findViewById(R.id.txttieude);
//
//        // Ánh xạ dữ liệu vào các trường
//        ettensach.setText(sp.trangthai); // Lưu ý: đảm bảo trường này đúng với dữ liệu của bạn
//        ettacgia.setText(sp.tacgia);
//        ettheloai.setText(sp.theloai);
//        ettienthue.setText(String.valueOf(sp.tienthue)); // Chuyển int sang String để hiển thị
//        ettieude.setText(sp.tieude); // Thêm dòng này để hiển thị tiêu đề
//
//        tv_title.setText("Sửa Sách");
//
//        builder.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String tacgia = ettacgia.getText().toString();
//                String theloai = ettheloai.getText().toString();
//                String tienthueString = ettienthue.getText().toString();
//                String trangthai = ettensach.getText().toString();
//                String tieude = ettieude.getText().toString();
//
//                // Kiểm tra và cập nhật thông tin vào cơ sở dữ liệu
//                if (!tacgia.isEmpty() && !tienthueString.isEmpty() && !theloai.isEmpty() && !trangthai.isEmpty() && !tieude.isEmpty()) {
//                    try {
//                        int tienthue = Integer.parseInt(tienthueString);
//
//                        // Tạo đối tượng SanPham mới với các giá trị cập nhật
//                        SanPham sanphammoi = new SanPham(sp.masp, sp.maPM, tieude, tacgia, theloai, trangthai, tienthue);
//
//                        // Cập nhật sản phẩm mới vào cơ sở dữ liệu
//                        sanPhamDAO.SuaSanPham(sanphammoi);
//
//                        // Cập nhật dữ liệu UI (nếu cần)
//                        dodulieu();
//
//                        Toast.makeText(GiaoDienChinh.this, "Đã cập nhật sản phẩm", Toast.LENGTH_SHORT).show();
//                    } catch (NumberFormatException e) {
//                        Toast.makeText(GiaoDienChinh.this, "Giá trị tiền thuê không hợp lệ", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(GiaoDienChinh.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        builder.setNegativeButton("Hủy", null);
//
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }
//
//
//
//
//    public void dodulieu() {
//        ds = sanPhamDAO.xemSanPham();
//        SanPhamAdapter adapter = new SanPhamAdapter(GiaoDienChinh.this, ds);
//        LinearLayoutManager lmanager = new LinearLayoutManager(GiaoDienChinh.this);
//        rv.setLayoutManager(lmanager);
//        rv.setAdapter(adapter);
//    }
//
//    private void showDialogthem() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        LayoutInflater inflater = getLayoutInflater();
//        View v = inflater.inflate(R.layout.dailog_loaisach, null);
//        builder.setView(v);
//
//        EditText ettensach = v.findViewById(R.id.ettensach);
//        EditText ettacgia = v.findViewById(R.id.ettacgia);
//        EditText ettheloai = v.findViewById(R.id.ettheloai);
//        EditText ettienthue = v.findViewById(R.id.ettienthue);
//        EditText ettieude = v.findViewById(R.id.ettieude);
//
//        builder.setPositiveButton("Hủy", null);
//
//        builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String tensach = ettensach.getText().toString();
//                String tacgia = ettacgia.getText().toString();
//                String theloai = ettheloai.getText().toString();
//                String tienthueString = ettienthue.getText().toString();
//                String tieude = ettieude.getText().toString();
//                int tieudet = -1; // Giá trị mặc định hoặc logic để xác định
//
//                // Kiểm tra và chuyển đổi giá trị tienthue từ String sang int
//                try {
//                    int tienthue = Integer.parseInt(tienthueString);
//
//                    // Sử dụng constructor không có masp
//                    SanPham sp = new SanPham(tieudet, tieude, tacgia, theloai, tensach, tienthue);
//                    sanPhamDAO.themSanPham(sp);
//                    dodulieu();
//                } catch (NumberFormatException e) {
//                    Toast.makeText(GiaoDienChinh.this, "Giá trị tiền thuê không hợp lệ", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        AlertDialog alertDialog = builder.create();
//        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//        alertDialog.show();
//    }
//
//
//
//}
