package com.example.duanmau.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau.Database.DBHelper;
import com.example.duanmau.model.PhieuMuon;

import java.util.ArrayList;

public class PhieuMuonDAO {
    private DBHelper dbHelper;

    public PhieuMuonDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public ArrayList<PhieuMuon> getDSPhieuMuon() {
        ArrayList<PhieuMuon> dsPhieuMuon = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT pm.mapm, pm.mand, s.masach, s.tensach,pm.ngaymuon, pm.ngaytra,pm.trasach,pm.tienthue, nd.tennd ,kh.tenkhachhang, kh.sdt FROM PhieuMuon pm,  NguoiDung nd,KhachHang kh, Sach s WHERE pm.mand = nd.mand and pm.masach = s.masach and pm.tenkhachhang = kh.tenkhachhang", null);

        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                    dsPhieuMuon.add( new PhieuMuon(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6),cursor.getInt(7),cursor.getString(8),cursor.getString(9),cursor.getInt(10)));

            } while (cursor.moveToNext());
        }
        return dsPhieuMuon;
    }

    public boolean thayDoiTrangThai(int mapm){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("trasach",1);
        long check = sqLiteDatabase.update("PhieuMuon",contentValues,"mapm=?",new String[]{String.valueOf(mapm)});
        if (check == -1)
        {
            return false;
        }
        return true;
    }

    public boolean themPhieuMuon(PhieuMuon phieuMuon){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //mapm integer primary key autoincrement, mand integer references NguoiDung(mand),masach integer references Sach(masach),tenkhachhang text references KhachHang(tenkhachhang), ngaymuon text, ngaytra text,tienthue integer,trasach integer

        contentValues.put("mapm",phieuMuon.getMapm());
        contentValues.put("mand",phieuMuon.getMand());
        contentValues.put("masach",phieuMuon.getMasach());
        contentValues.put("tenkhachhang",phieuMuon.getTenkhachhang());
        contentValues.put("ngaymuon",phieuMuon.getNgaymuon());
        contentValues.put("ngaytra",phieuMuon.getNgaytra());
        contentValues.put("tienthue",phieuMuon.getTienthue());
        contentValues.put("trasach",phieuMuon.getTrasach());
        long check = sqLiteDatabase.insert("PhieuMuon",null,contentValues);
        if (check == -1)
        {
            return false;
        }
        return true;
    }

    public int getdoanhthu(String ngaybatdau, String ngayketthuc){
        int doanhthu = 0;
        SQLiteDatabase db =null;
        Cursor cursor = null;
        try {
            db = dbHelper.getReadableDatabase();
            String query = "SELECT SUM(tienthue) FROM PhieuMuon WHERE ngaymuon BETWEEN ? AND ?";
            cursor = db.rawQuery(query, new String[]{ngaybatdau, ngayketthuc});
            if (cursor.moveToFirst()) {
                doanhthu = cursor.getInt(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return doanhthu;
    }
}