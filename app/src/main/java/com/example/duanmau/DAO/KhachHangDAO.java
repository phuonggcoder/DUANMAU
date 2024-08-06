package com.example.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau.Database.DBHelper;
import com.example.duanmau.model.KhachHang;

public class KhachHangDAO {
    private DBHelper dbHelper;

    public KhachHangDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public long themKhachHang(KhachHang khachHang) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenkh", khachHang.getTenKhachHang());
        values.put("sdtkh", khachHang.getSdtKhachHang());

        return db.insert("KhachHang", null, values);
    }

    public KhachHang getKhachHangByName(String tenKhachHang) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("KhachHang", null, "tenkh = ?", new String[]{tenKhachHang}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            KhachHang khachHang = new KhachHang(
                    cursor.getString(cursor.getColumnIndexOrThrow("tenkh")),
                    cursor.getString(cursor.getColumnIndexOrThrow("sdtkh"))
            );
            cursor.close();
            return khachHang;
        }
        return null;
    }
}
