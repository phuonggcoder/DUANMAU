package com.example.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau.Database.DBHelper;

public class NguoiDungDAO {
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public NguoiDungDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    // Kiểm tra đăng nhập cho Người Dùng (NguoiDung)
    public int KiemTraDangNhap(String user, String pass) {
        db = dbHelper.getWritableDatabase();
        Cursor cursor = null;
        try {
            String query = "SELECT role FROM NguoiDung WHERE tendangnhap = ? AND matkhau = ?";
            cursor = db.rawQuery(query, new String[]{user, pass});
            if (cursor.moveToFirst()) {
                return cursor.getInt(0); // return the role
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return -1; // return -1 if login fails
    }



    public boolean checkEmailExist (String email){
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM NguoiDung WHERE email = ?", new String[]{email});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }


    public boolean updatePasswordByEmail(String email, String newPassword) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("matkhau", newPassword);
        int result = db.update("NguoiDung", values, "email = ?", new String[]{email});
        return result > 0;
    }



    public boolean capNhatMatKhau(String username, String oldPassword, String newPassword) {
        db = dbHelper.getWritableDatabase(); // Open the connection here
        String selection = "tendangnhap=? AND matkhau=?";
        String[] selectionArgs = {username, oldPassword};
        Cursor cursor = db.query("NguoiDung", null, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            ContentValues values = new ContentValues();
            values.put("matkhau", newPassword);
            int rowsUpdated = db.update("NguoiDung", values, "tendangnhap=?", new String[]{username});
            cursor.close();
            db.close(); // Close the connection after use
            return rowsUpdated > 0;
        } else {
            if (cursor != null) cursor.close();
            db.close(); // Close the connection even if the update fails
            return false;
        }
    }
}
