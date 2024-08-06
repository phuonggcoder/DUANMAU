package com.example.duanmau.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, "QuanLyThuVien", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table for LoaiSach
        String sqlLoaisach = "Create table LoaiSach(maloai integer primary key autoincrement, tenloai text)";
        db.execSQL(sqlLoaisach);
        db.execSQL("insert into LoaiSach values(1, 'Trinh Thám'),(2,'Tình Cảm'),(3,'Cổ Xưa'),(4,'Cổ Điển')");

        // Create table for Sach
        String sqlSach = "Create table Sach(masach integer primary key autoincrement, tensach text, tacgia text, giathue integer, maloai integer references LoaiSach(maloai))";
        db.execSQL(sqlSach);
        db.execSQL("insert into Sach values" +
                "(1, 'Detective Conan', 'Gosho Aoyama', '100000', 1)," +
                "(2, 'Romeo and Juliet', 'William Shakespeare', '150000', 2)," +
                "(3, 'The Tale of Kieu', 'Nguyen Du', '120000', 3), " +
                "(4, 'Pride and Prejudice', 'Jane Austen', '200000', 4)");

        // Create table for NguoiDung
        String sqlNguoiDung = "Create table NguoiDung(mand integer primary key, tennd text, sdt text,email text, diachi text, tendangnhap text, matkhau text, role integer)";
        db.execSQL(sqlNguoiDung);
        db.execSQL("insert into NguoiDung(mand, tennd, sdt, email, diachi, tendangnhap, matkhau, role) values" +
                "(1, 'Nguyen Van A', '0909123456', null, 'Dia chi A', 'nguyenvana', 'password123', 1)," +
                "(2, 'Le Thi B', '0909876543',null, 'Dia chi B', 'lethib', 'pass456', 2)," +
                "(3, 'Tran Van C', '0912123456',null, 'Dia chi C', 'tranvanc', 'mypassword', 1)," +
                "(4, 'Pham Thi D', '0912987654',null, 'Dia chi D', 'nguyenvang', 'adminpass123', 2)");

        //create table KhachHang
        String sqlKhachHang = "Create table KhachHang(tenkhachhang text primary key, sdt integer)";
        db.execSQL(sqlKhachHang);
        db.execSQL("insert into KhachHang(tenkhachhang, sdt) values" +
                "('Nguyen Thi No', '0909123456')");

        // Create table for PhieuMuon
        String sqlPhieuMuon = "Create table PhieuMuon(mapm integer primary key autoincrement, mand integer references NguoiDung(mand),masach integer references Sach(masach),tenkhachhang text references KhachHang(tenkhachhang), ngaymuon text, ngaytra text,tienthue integer,trasach integer)";
        db.execSQL(sqlPhieuMuon);
        db.execSQL("INSERT INTO PhieuMuon (mapm, mand, masach, tenkhachhang, trasach, ngaymuon, ngaytra, tienthue) VALUES\n" +
                "    (1, 2, 1, 'Nguyen Thi No', 1, '28/07/2024', '28/8/2024', 50000),\n" +
                "    (2, 3, 2, 'Nguyen Thi No', 0, '29/07/2024', '28/8/2024', 60000),\n" +
                "    (3, 4, 3, 'Nguyen Thi No', 1, '30/07/2024', '28/8/2024', 70000)");


        String sqlYeuThich = "Create table YeuThich(mand integer references NguoiDung(mand), masach integer references Sach(masach), mayeuthich integer)";
        db.execSQL(sqlYeuThich);
        db.execSQL("INSERT INTO YeuThich (mand, masach, mayeuthich) VALUES " +
                "(1, 1, 1), " +
                "(2, 2, 1), " +
                "(1, 3, 1), " +
                "(4, 4, 1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS LoaiSach");
            db.execSQL("DROP TABLE IF EXISTS Sach");
            db.execSQL("DROP TABLE IF EXISTS NguoiDung");
            db.execSQL("DROP TABLE IF EXISTS PhieuMuon");
            db.execSQL("DROP TABLE IF EXISTS YeuThich");
            onCreate(db);
        }
    }
}