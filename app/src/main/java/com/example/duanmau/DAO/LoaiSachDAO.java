package com.example.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau.Database.DBHelper;
import com.example.duanmau.model.LoaiSach;

import java.util.ArrayList;

public class LoaiSachDAO {

    private DBHelper dbHelper;

    public LoaiSachDAO(Context context){
        dbHelper = new DBHelper(context);

    }
    public ArrayList<LoaiSach> getDSLoaiSach(){
        ArrayList<LoaiSach> list =new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LoaiSach",null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                list.add(new LoaiSach(cursor.getInt(0),cursor.getString(1)));
            }while (cursor.moveToNext());

        }
        return list;
    }


    public boolean themLoaiSach(String tenloai){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenloai", tenloai);
        long check = sqLiteDatabase.insert( "LoaiSach", null, contentValues);
        if (check == -1 )
            return false;
        return true;
    }
    public boolean suaLoaiSach(LoaiSach loaiSach){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("tenloai",loaiSach.getTenloai());

        int check = sqLiteDatabase.update("LoaiSach", contentValues, "maloai=?", new String[]{String.valueOf(loaiSach.getMaloai())});

        return check != 0;
    }

    public int xoaLoaiSach(int maloai){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Sach WHERE maloai=?", new String[]{String.valueOf(maloai)});
        if (cursor.getCount()>0){
            return 0; //rang buoc khoa ngoai
        }else {
            int check = sqLiteDatabase.delete("LoaiSach", "maloai=?", new String[]{String.valueOf(maloai)});
            if (check == 0){
                return -1; //loi, khong xoa duoc
            }else {
                return 1;
            }
        }

    }
}
