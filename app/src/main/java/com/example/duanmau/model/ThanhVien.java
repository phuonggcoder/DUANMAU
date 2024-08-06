package com.example.duanmau.model;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ThanhVien   {
    private int matv;
    private String hoten, namsinh, gioitinh;

    public int getMatv() {
        return matv;
    }

    public void setMatv(int matv) {
        this.matv = matv;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getNamsinh() {
        return namsinh;
    }

    public void setNamsinh(String namsinh) {
        this.namsinh = namsinh;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public ThanhVien(int matv, String hoten, String namsinh, String gioitinh) {
        this.matv = matv;
        this.hoten = hoten;
        this.namsinh = namsinh;
        this.gioitinh = gioitinh;
    }


}


