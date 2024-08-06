package com.example.duanmau.model;

public class Sach {
    private int masach;
    private int giathue;
    private int maloai;
    private String tensach;
    private String tacgia;
    private int soluongdamuon;
    private String tenloai;

    public Sach(int masach, String tensach, String tacgia, int giathue, int maloai, String tenloai) {
        this.masach = masach;
        this.tensach = tensach;
        this.tacgia = tacgia;
        this.giathue = giathue;
        this.maloai = maloai;
        this.tenloai = tenloai;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    // Constructor đầy đủ
    public Sach(int masach, String tensach, String tacgia, int giathue, int maloai) {
        this.masach = masach;
        this.tensach = tensach;
        this.tacgia = tacgia;
        this.giathue = giathue;
        this.maloai = maloai;
    }

    // Constructor cho một số thuộc tính cần thiết
    public Sach(int masach, int soluongdamuon, String tensach) {
        this.masach = masach;
        this.soluongdamuon = soluongdamuon;
        this.tensach = tensach;
    }

    public int getMasach() {
        return masach;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public int getGiathue() {
        return giathue;
    }

    public void setGiathue(int giathue) {
        this.giathue = giathue;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public int getSoluongdamuon() {
        return soluongdamuon;
    }

    public void setSoluongdamuon(int soluongdamuon) {
        this.soluongdamuon = soluongdamuon;
    }
}