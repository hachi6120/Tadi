package com.example.tadi.Model;

public class NguoiDung {
    private String maND;
    private String matKhauND;
    private String tenND;
    private String sdtND;
    private String diaChiND;
    private int tuoiND;
    private String email;

    public NguoiDung() {
    }

    public NguoiDung(String maND, String matKhauND, String tenND, String sdtND, String diaChiND, int tuoiND, String email) {
        this.maND = maND;
        this.matKhauND = matKhauND;
        this.tenND = tenND;
        this.sdtND = sdtND;
        this.diaChiND = diaChiND;
        this.tuoiND = tuoiND;
        this.email = email;
    }

    public String getMaND() {
        return maND;
    }

    public void setMaND(String maND) {
        this.maND = maND;
    }

    public String getMatKhauND() {
        return matKhauND;
    }

    public void setMatKhauND(String matKhauND) {
        this.matKhauND = matKhauND;
    }

    public String getTenND() {
        return tenND;
    }

    public void setTenND(String tenND) {
        this.tenND = tenND;
    }

    public String getSdtND() {
        return sdtND;
    }

    public void setSdtND(String sdtND) {
        this.sdtND = sdtND;
    }

    public String getDiaChiND() {
        return diaChiND;
    }

    public void setDiaChiND(String diaChiND) {
        this.diaChiND = diaChiND;
    }

    public int getTuoiND() {
        return tuoiND;
    }

    public void setTuoiND(int tuoiND) {
        this.tuoiND = tuoiND;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
