package com.example.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau.Database.DBHelper;
import com.example.duanmau.model.Sach;
import com.example.duanmau.model.ThanhVien;


import java.util.ArrayList;

public class ThanhVienDAO {
    static DBHelper dbHelper;

    public ThanhVienDAO(Context context) {dbHelper = new DBHelper(context);}

    public static ArrayList<ThanhVien> getDSThanhVien() {
        ArrayList<ThanhVien> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM ThanhVien", null);

        if (cursor.moveToFirst()) {
            do {
                list.add(new ThanhVien(cursor.getInt(0),
                        cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            } while (cursor.moveToNext());
        }
        cursor.close(); // Đừng quên đóng cursor sau khi sử dụng
        db.close(); // Đừng quên đóng database sau khi sử dụng

        return list;
    }
    public static void capNhattv(ThanhVien thanhVien) {
        ContentValues values = new ContentValues();
        values.put("matv", thanhVien.getMatv());
        values.put("hoten", thanhVien.getHoten());
        values.put("namsinh", thanhVien.getNamsinh());
        values.put("gioitinh", thanhVien.getGioitinh());
        String whereClause = "matv=?";
        String[] whereArgs = {String.valueOf(thanhVien.getMatv())};
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.update("Sach", values, whereClause, whereArgs);
        db.close();
    }
    public static void xoatv(int masach) {
        String whereClause = "matv=?";
        String[] whereArgs = {String.valueOf(masach)};
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("ThanhVien", whereClause, whereArgs);
        db.close();
    }
    public void themtv(ThanhVien thanhVien) {
        ContentValues values = new ContentValues();
        values.put("matv", thanhVien.getMatv());
        values.put("hoten", thanhVien.getHoten());
        values.put("namsinh", thanhVien.getNamsinh());
        values.put("gioitinh", thanhVien.getGioitinh());


        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert("ThanhVien", null, values);
        db.close();
    }


}
