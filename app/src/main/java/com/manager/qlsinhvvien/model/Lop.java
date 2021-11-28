package com.manager.qlsinhvvien.model;

import java.io.Serializable;

public class Lop implements Serializable {
    private int maLop;
    private String tenLop;
    private String giaoVien;


    public Lop() {
    }

    public Lop(String tenLop, String giaoVien) {
        this.tenLop = tenLop;
        this.giaoVien = giaoVien;
    }

    public Lop(int maLop, String tenLop, String giaoVien) {
        this.maLop = maLop;
        this.tenLop = tenLop;
        this.giaoVien = giaoVien;
    }

    public int getMaLop() {
        return maLop;
    }

    public void setMaLop(int maLop) {
        this.maLop = maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public String getGiaoVien() {
        return giaoVien;
    }

    public void setGiaoVien(String giaoVien) {
        this.giaoVien = giaoVien;
    }

    @Override
    public String toString() {
        return "Lop{" +
                "maLop=" + maLop +
                ", tenLop='" + tenLop + '\'' +
                ", giaoVien='" + giaoVien + '\'' +
                '}';
    }
}
