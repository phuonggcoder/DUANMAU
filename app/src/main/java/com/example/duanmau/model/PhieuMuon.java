package com.example.duanmau.model;

public class PhieuMuon {
    private int mapm;
    private int mand;
    private int masach;
    private String tensach;
    private String ngaymuon;
    private String ngaytra;
    private int trasach;
    private int tienthue;
    private String tennd;
    private String tenkhachhang;
    private int sdt;

                    // pm.mapm, pm.mand, s.masach, s.tensach,pm.ngaymuon, pm.ngaytra,pm.trasach,pm.tienthue, nd.tennd ,kh.tenkhachhang, kh.sdt
    public PhieuMuon(int mapm, int mand, int masach, String tensach, String ngaymuon, String ngaytra, int trasach, int tienthue, String tennd, String tenkhachhang, int sdt) {
        this.mapm = mapm;
        this.mand = mand;
        this.masach = masach;
        this.tensach = tensach;
        this.ngaymuon = ngaymuon;
        this.ngaytra = ngaytra;
        this.trasach = trasach;
        this.tienthue = tienthue;
        this.tennd = tennd;
        this.tenkhachhang = tenkhachhang;
        this.sdt = sdt;
    }

    public int getMapm() {
        return mapm;
    }

    public void setMapm(int mapm) {
        this.mapm = mapm;
    }

    public int getMand() {
        return mand;
    }

    public void setMand(int mand) {
        this.mand = mand;
    }

    public int getMasach() {
        return masach;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public String getNgaymuon() {
        return ngaymuon;
    }

    public void setNgaymuon(String ngaymuon) {
        this.ngaymuon = ngaymuon;
    }

    public String getNgaytra() {
        return ngaytra;
    }

    public void setNgaytra(String ngaytra) {
        this.ngaytra = ngaytra;
    }

    public int getTrasach() {
        return trasach;
    }

    public void setTrasach(int trasach) {
        this.trasach = trasach;
    }

    public int getTienthue() {
        return tienthue;
    }

    public void setTienthue(int tienthue) {
        this.tienthue = tienthue;
    }

    public String getTennd() {
        return tennd;
    }

    public void setTennd(String tennd) {
        this.tennd = tennd;
    }

    public String getTenkhachhang() {
        return tenkhachhang;
    }

    public void setTenkhachhang(String tenkhachhang) {
        this.tenkhachhang = tenkhachhang;
    }

    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }
}