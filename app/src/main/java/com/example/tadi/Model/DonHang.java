package com.example.tadi.Model;

public class DonHang {

    private int maDon;
    private String maSP;
    private String maND;
    private String maQL;
    private int soLuongMua;
    private int sizeDathang;
    private String thoiGian;
    private int trangThai;

    public DonHang() {
    }

    public DonHang(int maDon, String maSP, String maND, String maQL, int soLuongMua, int sizeDathang, String thoiGian, int trangThai) {
        this.maDon = maDon;
        this.maSP = maSP;
        this.maND = maND;
        this.maQL = maQL;
        this.soLuongMua = soLuongMua;
        this.sizeDathang = sizeDathang;
        this.thoiGian = thoiGian;
        this.trangThai = trangThai;
    }

    public int getMaDon() {
        return maDon;
    }

    public void setMaDon(int maDon) {
        this.maDon = maDon;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getMaND() {
        return maND;
    }

    public void setMaND(String maND) {
        this.maND = maND;
    }

    public String getMaQL() {
        return maQL;
    }

    public void setMaQL(String maQL) {
        this.maQL = maQL;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    public int getSizeDathang() {
        return sizeDathang;
    }

    public void setSizeDathang(int sizeDathang) {
        this.sizeDathang = sizeDathang;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
