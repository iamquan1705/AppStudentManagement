package com.manager.qlsinhvvien.model;

import java.io.Serializable;
import java.util.Arrays;

public class SinhVien implements Serializable {
    private String maSV;
    private String tenSV;
    private String namSinh;
    private String diaChi;
    private byte[] anhThe;
    private String tenLop;


    public SinhVien() {
    }


    public SinhVien(String maSV, String tenSV, String namSinh, String diaChi, byte[] anhThe) {
        this.maSV = maSV;
        this.tenSV = tenSV;
        this.namSinh = namSinh;
        this.diaChi = diaChi;
        this.anhThe = anhThe;
    }

    public SinhVien(String maSV, String tenSV, String namSinh, String diaChi, byte[] anhThe, String tenLop) {
        this.maSV = maSV;
        this.tenSV = tenSV;
        this.namSinh = namSinh;
        this.diaChi = diaChi;
        this.anhThe = anhThe;
        this.tenLop = tenLop;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public byte[] getAnhThe() {
        return anhThe;
    }

    public void setAnhThe(byte[] anhThe) {
        this.anhThe = anhThe;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    @Override
    public String toString() {
        return "SinhVien{" +
                "maSV='" + maSV + '\'' +
                ", tenSV='" + tenSV + '\'' +
                ", namSinh='" + namSinh + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", anhThe=" + Arrays.toString(anhThe) +
                ", tenLop='" + tenLop + '\'' +
                '}';
    }
}
