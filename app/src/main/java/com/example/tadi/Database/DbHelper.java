package com.example.tadi.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHelper extends SQLiteOpenHelper {

    static final String dbName="Qlt";
    static final int dbVersion=1;

    public DbHelper(Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

            String createTableThuThu="create table QuanLy (" +
                    "maQL text PRIMARY KEY, " +
                    "hoTen text NOT NULL, " +
                    "sdt text NOT NULL, " +
                    "email text NOT NULL, " +
                    "matKhau text NOT NULL)";
            db.execSQL(createTableThuThu);

        String createTableThanhVien="create table ThanhVien (" +
                "maTV INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "hoTen text NOT NULL, " +
                "sdt text NOT NULL, " +
                "email text NOT NULL, " +
                "namSinh text NOT NULL)";
        db.execSQL(createTableThanhVien);

        String createTableSanPham="create table SanPham (" +
                "maSP INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tenSP text NOT NULL, " +
                "size INTEGER NOT NULL, " +
                "soLuong INTEGER NOT NULL)";
        db.execSQL(createTableSanPham);

        String createTableHoaDon="create table HoaDon (" +
                "maPM INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "maTV INTEGER REFERENCES ThanhVien(maSP), " +
                "maSP INTEGER REFERENCES SanPham(maSP), " +
                "tienThue INTEGER NOT NULL, " +
                "ngay DATE NOT NULL, " +
                "traSach INTEGER NOT NULL )";
        db.execSQL(createTableHoaDon);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String dropTableThuThu = "drop table if exists ThuThu";
        db.execSQL(dropTableThuThu);
        String dropTableThanhVien = "drop table if exists ThanhVien";
        db.execSQL(dropTableThanhVien);
        String dropTableLoaiSach = "drop table if exists LoaiSach";
        db.execSQL(dropTableLoaiSach);
        String dropTableSach = "drop table if exists Sach";
        db.execSQL(dropTableSach);
        String dropTablePhieuMuon = "drop table if exists PhieuMuon";
        db.execSQL(dropTablePhieuMuon);

        onCreate(db);
    }
}
