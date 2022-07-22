package com.example.tadi.Model;

import java.util.List;

public class LoaiSP {
    private int maLoai;
    private String tenLoai;
    private List<SanPham> sanPhams;

    public LoaiSP() {
    }

    public LoaiSP(int maLoai, String tenLoai, List<SanPham> sanPhams) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
        this.sanPhams = sanPhams;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public List<SanPham> getSanPhams() {
        return sanPhams;
    }

    public void setSanPhams(List<SanPham> sanPhams) {
        this.sanPhams = sanPhams;
    }

    @Override
    public String toString() {
        return  "Mã: " + maLoai + " |Tên: " + tenLoai;
    }
}
