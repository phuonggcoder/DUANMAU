package com.example.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau.Database.DBHelper;
import com.example.duanmau.model.SanPham;



import java.util.ArrayList;

public class SanPhamDAO {

   public DBHelper helper;

    public SanPhamDAO(Context c)

    {
        helper = new DBHelper(c);
    }

    public void xoaSP(int masp) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("sanpham","masp=?",new String[]{masp+""});
        db.close();
    }


    public void themSanPham(SanPham sp) {

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("tacgia", sp.tacgia);
        values.put("theloai", sp.theloai);
        values.put("tienthue", sp.tienthue);
        values.put("trangthai", sp.trangthai);
        values.put("tieude", sp.tieude);
        values.put("maPM", sp.maPM);

        db.insert("sanpham", null, values);
        Log.d("SanPhamDAO", "Thêm sản phẩm: " + sp.toString());
        db.close();
    }
    public void SuaSanPham(SanPham sp) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tacgia", sp.tacgia);
        values.put("theloai", sp.theloai);
        values.put("tienthue", sp.tienthue);
        values.put("trangthai", sp.trangthai);

        db.update("sanpham", values, "masp=?", new String[]{String.valueOf(sp.masp)});
        db.close();
    }


    public  ArrayList<SanPham> xemSanPham(){
        ArrayList<SanPham> list= new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = db.rawQuery("select * from sanpham   ",null);
        if(c.moveToFirst())// có dữ liệu sẽ trả về true
        {
            do {
                int masp = c.getInt(0);
                String tieude = c.getString(2);
                int maPM = c.getInt(1);
                String tacgia = c.getString(3);
                String theloai = c.getString(4);
                String trangthai = c.getString(5);
                int tienthue = c.getInt(6);



                SanPham sp = new SanPham(masp, maPM, tieude,tacgia, theloai, trangthai, tienthue);
                list.add(sp);
                Log.d("SanPham", "ID: " + masp + ", Title: " + tieude);
            }while( c.moveToNext());
        }
        c.close();
        db.close();
        return list;
    }

}
