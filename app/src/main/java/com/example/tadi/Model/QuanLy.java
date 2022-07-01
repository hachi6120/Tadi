package com.example.tadi.Model;

public class QuanLy {
    private String maQL;
    private String matKhauQL;
    private String tenQL;
    private String sdtQL;
    private String CCCD;

    public QuanLy() {
    }

    public QuanLy(String maQL, String matKhauQL, String tenQL, String sdtQL, String CCCD) {
        this.maQL = maQL;
        this.matKhauQL = matKhauQL;
        this.tenQL = tenQL;
        this.sdtQL = sdtQL;
        this.CCCD = CCCD;
    }

    public String getMaQL() {
        return maQL;
    }

    public void setMaQL(String maQL) {
        this.maQL = maQL;
    }

    public String getMatKhauQL() {
        return matKhauQL;
    }

    public void setMatKhauQL(String matKhauQL) {
        this.matKhauQL = matKhauQL;
    }

    public String getTenQL() {
        return tenQL;
    }

    public void setTenQL(String tenQL) {
        this.tenQL = tenQL;
    }

    public String getSdtQL() {
        return sdtQL;
    }

    public void setSdtQL(String sdtQL) {
        this.sdtQL = sdtQL;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }
}
