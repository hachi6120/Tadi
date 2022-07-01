package com.example.tadi.Model;

public class Size {
    private int maSize;
    private int soLuongSizeS;
    private int soLuongSizeM;
    private int soLuongSizeL;

    public Size() {
    }

    public Size(int maSize, int soLuongSizeS, int soLuongSizeM, int soLuongSizeL) {
        this.maSize = maSize;
        this.soLuongSizeS = soLuongSizeS;
        this.soLuongSizeM = soLuongSizeM;
        this.soLuongSizeL = soLuongSizeL;
    }

    public int getMaSize() {
        return maSize;
    }

    public void setMaSize(int maSize) {
        this.maSize = maSize;
    }

    public int getSoLuongSizeS() {
        return soLuongSizeS;
    }

    public void setSoLuongSizeS(int soLuongSizeS) {
        this.soLuongSizeS = soLuongSizeS;
    }

    public int getSoLuongSizeM() {
        return soLuongSizeM;
    }

    public void setSoLuongSizeM(int soLuongSizeM) {
        this.soLuongSizeM = soLuongSizeM;
    }

    public int getSoLuongSizeL() {
        return soLuongSizeL;
    }

    public void setSoLuongSizeL(int soLuongSizeL) {
        this.soLuongSizeL = soLuongSizeL;
    }
}
