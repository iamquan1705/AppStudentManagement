package com.manager.qlsinhvvien.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.manager.qlsinhvvien.database.Database;
import com.manager.qlsinhvvien.model.Lop;
import com.manager.qlsinhvvien.model.SinhVien;

import java.util.ArrayList;
import java.util.List;

public class SinhVienDao {
    private Database database;
    public SinhVienDao(Context context) {
        database = new Database(context);
    }

    public List<SinhVien> DanhSachSinhVien(String tl) {
        List<SinhVien> SVList = new ArrayList<SinhVien>();
        String sql = "SELECT * FROM t_sinhvien where tenLop like '"+tl+"'";
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String maSV = cursor.getString(0);
            String tenSV = cursor.getString(1);
            String namSinh = cursor.getString(2);
            String diaChi = cursor.getString(3);
            byte[] anhThe = cursor.getBlob(4);
            String tenLop = cursor.getString(5);
            SinhVien sv = new SinhVien(maSV, tenSV, namSinh,diaChi,anhThe,tenLop);
            SVList.add(sv);
            cursor.moveToNext();
        }
        return SVList;
    }

    public List<SinhVien> DanhSachSinhVienMain() {
        List<SinhVien> svs = new ArrayList<SinhVien>();
        String sql = "SELECT * FROM t_sinhvien";
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String maSV = cursor.getString(0);
            String tenSV = cursor.getString(1);
            String namSinh = cursor.getString(2);
            String diaChi = cursor.getString(3);
            byte[] anhThe = cursor.getBlob(4);
            String tenLop = cursor.getString(5);
            SinhVien sv = new SinhVien(maSV, tenSV, namSinh,diaChi,anhThe,tenLop);
            svs.add(sv);
            cursor.moveToNext();
        }
        return svs;
    }

    public void ThemSinhVien(SinhVien sv) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maSV", sv.getMaSV());
        values.put("tenSV", sv.getTenSV());
        values.put("namSinh", sv.getNamSinh());
        values.put("diaChi", sv.getDiaChi());
        values.put("anhThe", sv.getAnhThe());
        values.put("tenLop", sv.getTenLop());
        db.insert("t_sinhvien", null, values);

    }

    // Cập nhật HV
    public void SuaSinhVien(SinhVien sv) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenSV", sv.getTenSV());
        values.put("namSinh", sv.getNamSinh());
        values.put("diaChi", sv.getDiaChi());
        values.put("anhThe", sv.getAnhThe());
        values.put("tenLop", sv.getTenLop());
        db.update("t_sinhvien",values,"maSV=?",new String[]{sv.getMaSV()});
        db.close();

    }

    // Xoa hv
    public void XoaSinhVien(String id) {
        SQLiteDatabase db = database.getWritableDatabase();
        String delete = "Delete from t_sinhvien where maSV='"+id+"'";
        db.execSQL(delete);
        db.close();
    }

    public void  XoaLopSinhVien(String lop) {
        SQLiteDatabase db = database.getWritableDatabase();
        String delete = "Delete from t_sinhvien where tenLop='"+lop+"'";
        db.execSQL(delete);
        db.close();
    }

    public SinhVien getSinhVien(String maSV) {
        String sql = "SELECT * FROM t_sinhvien where maSV = '"+maSV+"'";
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        SinhVien sv = null;
        if (!cursor.isAfterLast()) {
            String ma = cursor.getString(0);
            String tenSV = cursor.getString(1);
            String namSinh = cursor.getString(2);
            String diaChi = cursor.getString(3);
            byte[] anhThe = cursor.getBlob(4);
            String tenLop = cursor.getString(5);
            sv = new SinhVien(ma, tenSV, namSinh,diaChi,anhThe,tenLop);
            cursor.moveToNext();
        }
        return sv;
    }


}
