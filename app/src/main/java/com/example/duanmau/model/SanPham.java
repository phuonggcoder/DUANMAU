package com.example.duanmau.model;

public class SanPham {
        public int masp,maPM,tienthue;
    public String tacgia,theloai,trangthai,tieude;

    public SanPham(int masp,int maPM,String tieude,String tacgia, String theloai,String trangthai,int tienthue)
    {
        this.masp= masp;
        this.maPM=maPM;
        this.tieude=tieude;
        this.theloai=theloai;
        this.tacgia=tacgia;
        this.trangthai=trangthai;
        this.tienthue=tienthue;





    }
    public SanPham(int maPM,String tieude,String tacgia, String theloai,String trangthai,int tienthue)
    {

        this.maPM=maPM;
        this.tieude=tieude;
        this.theloai=theloai;
        this.tacgia=tacgia;
        this.trangthai=trangthai;
        this.tienthue=tienthue;


    }




}
