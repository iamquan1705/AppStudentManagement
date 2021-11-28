package com.manager.qlsinhvvien;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.manager.qlsinhvvien.adapter.SinhVienAdapter;
import com.manager.qlsinhvvien.dao.SinhVienDao;
import com.manager.qlsinhvvien.model.SinhVien;

import java.util.ArrayList;
import java.util.List;

public class SinhvienmainActivity extends AppCompatActivity {
    private SinhVienDao dao;
    private ListView lvSinhVienMain;
    private Button btnThemSVMAin;
    private SearchView svSinhVienMain;
    private List<SinhVien> svList;
    private SinhVienAdapter sinhVienAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinhvienmain);
        anhxa();
        svList = new ArrayList<>();
        dao = new SinhVienDao(this);
        svList = dao.DanhSachSinhVienMain();
        sinhVienAdapter = new SinhVienAdapter(this,svList);
        lvSinhVienMain.setAdapter(sinhVienAdapter);
        registerForContextMenu(lvSinhVienMain);

        btnThemSVMAin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent thisintent = new Intent(SinhvienmainActivity.this,AddSvActivity.class);
                thisintent.putExtra("TenLop","");
                startActivity(thisintent);
            }
        });

        lvSinhVienMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SinhVien sv = svList.get(i);
                Intent in = new Intent(SinhvienmainActivity.this, ThongtinActivity.class);
                in.putExtra("masv",sv.getMaSV());
                startActivity(in);
            }
        });
        lvSinhVienMain.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Dialog dialog = new Dialog(SinhvienmainActivity.this);
                dialog.setContentView(R.layout.dialog_xoasinhvien);
                dialog.setCanceledOnTouchOutside(false);
                Button btnYes = dialog.findViewById(R.id.btnYes);
                Button btnNo = dialog.findViewById(R.id.btnNo);
                dialog.show();
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dao.XoaSinhVien(svList.get(i).getMaSV());
                        Intent intent = new Intent(SinhvienmainActivity.this, SinhvienmainActivity.class);
                        startActivity(intent);
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

        svSinhVienMain.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
        lvSinhVienMain = findViewById(R.id.lvSinhVienMain);
        btnThemSVMAin = findViewById(R.id.btnThemSVMAin);
        svSinhVienMain = findViewById(R.id.svSinhVienMain);
    }
    @Override
    protected void onResume() {
        super.onResume();
        svList.clear();
        svList.addAll(dao.DanhSachSinhVienMain());
        sinhVienAdapter.notifyDataSetChanged();
    }
}