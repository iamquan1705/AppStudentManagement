package com.manager.qlsinhvvien.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database  extends SQLiteOpenHelper{
    public static final String DATABASE_NAME="QLSANPHAM.db";
    public static final String TABLE_NAME_LOP="t_lop";
    public static final String TABLE_NAME_SINHVIEN="t_sinhvien";
    private static int version=2;
    private Context context;
    public Database(Context context) {
        super(context, DATABASE_NAME, null, version);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="CREATE TABLE IF NOT EXISTS "+TABLE_NAME_LOP+
                "(maLop integer primary key AUTOINCREMENT, "+
                "tenLop text, "+
                "giaoVien text)";
        String sql1="CREATE TABLE IF NOT EXISTS "+TABLE_NAME_SINHVIEN+
                "(maSV integer primary key, "+
                "tenSV text, "+
                "namSinh text," +
                "diaChi text," +
                "anhThe blob," +
                "tenLop text) ";

        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(sql1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_LOP);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_SINHVIEN);
        onCreate(sqLiteDatabase);
    }
}
