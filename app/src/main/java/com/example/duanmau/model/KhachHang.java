package com.example.duanmau.model;

public class KhachHang {
    private String tenKhachHang;
    private String sdtKhachHang;

    public KhachHang(String tenKhachHang, String sdtKhachHang) {
        this.tenKhachHang = tenKhachHang;
        this.sdtKhachHang = sdtKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSdtKhachHang() {
        return sdtKhachHang;
    }

    public void setSdtKhachHang(String sdtKhachHang) {
        this.sdtKhachHang = sdtKhachHang;
    }
}
