package com.example.tadi.Model;

public class SanPham {
    private int maSP;
    private int maLoai;
    private int maSize;
    private String tenSP;
    private int giaNhap;
    private int giaBan;
    private int soLuong;
    private String nhaSX;
    private String chatLieu;
    private String mauSac;
    private String moTa;
    private byte[] imgSP;

    public SanPham() {
    }

    public SanPham(int maSP, int maLoai, int maSize, String tenSP, int giaNhap, int giaBan, int soLuong, String nhaSX, String chatLieu, String mauSac, String moTa, byte[] imgSP) {
        this.maSP = maSP;
        this.maLoai = maLoai;
        this.maSize = maSize;
        this.tenSP = tenSP;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.nhaSX = nhaSX;
        this.chatLieu = chatLieu;
        this.mauSac = mauSac;
        this.moTa = moTa;
        this.imgSP = imgSP;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public int getMaSize() {
        return maSize;
    }

    public void setMaSize(int maSize) {
        this.maSize = maSize;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(int giaNhap) {
        this.giaNhap = giaNhap;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getNhaSX() {
        return nhaSX;
    }

    public void setNhaSX(String nhaSX) {
        this.nhaSX = nhaSX;
    }

    public String getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(String chatLieu) {
        this.chatLieu = chatLieu;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public byte[] getImgSP() {
        return imgSP;
    }

    public void setImgSP(byte[] imgSP) {
        this.imgSP = imgSP;
    }
}

