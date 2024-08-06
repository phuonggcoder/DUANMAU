package com.example.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau.Database.DBHelper;
import com.example.duanmau.model.Sach;

import java.util.ArrayList;

public class SachDAO {
    DBHelper helper;

    public SachDAO(Context context) {
        helper = new DBHelper(context);
    }

    public ArrayList<Sach> getDSDauSach() {
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT sc.masach, sc.tensach, sc.tacgia, sc.giathue, sc.maloai, ls.tenloai FROM Sach sc,loaisach ls WHERE sc.maloai = ls.maloai", null);
        if (c.getCount() !=0){
            c.moveToFirst();
            do{
                list.add(new Sach(c.getInt(0),c.getString(1),c.getString(2),c.getInt(3),c.getInt(4),c.getString(5)));
            }while(c.moveToNext());
        }
        c.close();
        db.close();
        return list;
    }
    public void capNhatSach(Sach sach) {
        ContentValues values = new ContentValues();
        values.put("tensach", sach.getTensach());
        values.put("tacgia", sach.getTacgia());
        values.put("giaban", sach.getGiathue());
        values.put("maloai", sach.getMaloai());

        String whereClause = "masach=?";
        String[] whereArgs = {String.valueOf(sach.getMasach())};
        SQLiteDatabase db = helper.getWritableDatabase();
        db.update("Sach", values, whereClause, whereArgs);
        db.close();
    }
    public void xoaSach(int masach) {
        String whereClause = "masach=?";
        String[] whereArgs = {String.valueOf(masach)};
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("Sach", whereClause, whereArgs);
        db.close();
    }



    public boolean themSach(String tensach, String tacgia, int giathue, int maloai) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tensach", tensach);
        values.put("tacgia", tacgia);
        values.put("giathue", giathue);
        values.put("maloai", maloai);
        try {
            long check = db.insert("Sach", null, values);
            if (check == -1)
                return false;
            return true;
        } finally {
            db.close();
        }
    }
}
