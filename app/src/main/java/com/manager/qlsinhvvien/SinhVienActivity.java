package com.manager.qlsinhvvien;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.manager.qlsinhvvien.adapter.LopAdapter;
import com.manager.qlsinhvvien.adapter.SinhVienAdapter;
import com.manager.qlsinhvvien.dao.LopDao;
import com.manager.qlsinhvvien.dao.SinhVienDao;
import com.manager.qlsinhvvien.model.Lop;
import com.manager.qlsinhvvien.model.SinhVien;

import java.util.ArrayList;
import java.util.List;

public class SinhVienActivity extends AppCompatActivity {
    private SinhVienDao dao;
    private ListView lvSinhVien;
    private TextView tvTiltle;
    private Button btnThemSinhVien;
    private SearchView searchSinhVien;
    private List<SinhVien> svList;
    private SinhVienAdapter sinhVienAdapter;
    private String tenLop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinh_vien);
        anhxa();
        svList = new ArrayList<>();
        Intent intent = getIntent();
        tenLop = intent.getStringExtra("TenLop");
        tvTiltle.setText("Danh sách sinh viên lớp "+tenLop);
        dao = new SinhVienDao(this);
        svList = dao.DanhSachSinhVien(tenLop);
        sinhVienAdapter = new SinhVienAdapter(this,svList);
        lvSinhVien.setAdapter(sinhVienAdapter);
        registerForContextMenu(lvSinhVien);
        btnThemSinhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent thisintent = new Intent(SinhVienActivity.this,AddSvActivity.class);
                thisintent.putExtra("TenLop",tenLop);
                startActivity(thisintent);
            }
        });

        lvSinhVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SinhVien sv = svList.get(i);
                Intent in = new Intent(SinhVienActivity.this, ThongtinActivity.class);
                System.out.println(sv.toString());
                in.putExtra("masv",sv.getMaSV());
                startActivity(in);
            }
        });
        lvSinhVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Dialog dialog = new Dialog(SinhVienActivity.this);
                dialog.setContentView(R.layout.dialog_xoasinhvien);
                dialog.setCanceledOnTouchOutside(false);
                Button btnYes = dialog.findViewById(R.id.btnYes);
                Button btnNo = dialog.findViewById(R.id.btnNo);
                dialog.show();
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dao.XoaSinhVien(svList.get(i).getMaSV());
                        Intent intent = new Intent(SinhVienActivity.this, SinhVienActivity.class);
                        startActivity(intent);
                        dialog.cancel();
                    }
                });
                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                sinhVienAdapter.notifyDataSetChanged();
                onResume();
                return true;
            }
        });

        searchSinhVien.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String text = newText;

                if (text != null) {
                    sinhVienAdapter.filter(text);
                }

                return false;
            }
        });

    }




    private void anhxa() {
        lvSinhVien = findViewById(R.id.lvSinhVien);
        btnThemSinhVien = findViewById(R.id.btnThemSinhVien);
        searchSinhVien = findViewById(R.id.svSinhVien);
        tvTiltle = findViewById(R.id.tvTieuDe);
    }
    @Override
    protected void onResume() {
        super.onResume();
        svList.clear();
        svList.addAll(dao.DanhSachSinhVien(tenLop));
        sinhVienAdapter.notifyDataSetChanged();
    }
}