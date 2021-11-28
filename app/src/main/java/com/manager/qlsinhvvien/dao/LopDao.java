package com.manager.qlsinhvvien.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.manager.qlsinhvvien.model.Lop;
import com.manager.qlsinhvvien.database.Database;

import java.util.ArrayList;
import java.util.List;

public class LopDao {
    private Database database;

    public LopDao(Context context) {
        database = new Database(context);
    }
    public List<Lop> DanhSachLop() {
        List<Lop> HVList = new ArrayList<Lop>();
        String sql = "SELECT * FROM t_lop";
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int maLop = cursor.getInt(0);
            String tenLop = cursor.getString(1);
            String giaoVien = cursor.getString(2);
            Lop sp = new Lop(maLop, tenLop, giaoVien);
            HVList.add(sp);
            cursor.moveToNext();
        }
        return HVList;
    }

    public void ThemLop(Lop lop) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenLop", lop.getTenLop());
        values.put("giaoVien", lop.getGiaoVien());
        db.insert("t_lop", null, values);

    }

    // Cập nhật HV
    public void SuaLop(Lop sp) {
        SQLiteDatabase db = database.getWritableDatabase();
        String sql = "UPDATE t_lop SET tenLop='" + sp.getTenLop() + "', giaoVien='" + sp.getGiaoVien() + "' WHERE maLop= '" + sp.getMaLop() + "' ";
        db.execSQL(sql);
        db.close();

    }

    // Xoa hv
    public int XoaSp(int id) {
        SQLiteDatabase db = database.getWritableDatabase();
        int kq = db.delete("t_lop", "maLop=?", new String[]{String.valueOf(id)});
        db.close();
        return kq;
    }

    public List<String> getAllLop() {
        List<String> HHList = new ArrayList<>();
        String sql = "SELECT tenLop FROM t_lop";
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if(cursor!=null && cursor.getCount() > 0)
        {
            if (cursor.moveToFirst())
            {
                do {
                    HHList.add(cursor.getString(0));
                } while (cursor.moveToNext());
            }
        }
        return HHList;
    }
}
