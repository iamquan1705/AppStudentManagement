package com.manager.qlsinhvvien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.manager.qlsinhvvien.model.Lop;
import com.manager.qlsinhvvien.dao.LopDao;

public class AddLopActivity extends AppCompatActivity {
    private EditText edtTenLop,edtGiaoVien,edtMaLop;
    private Button btnThemLOp,btnThoatLop;
    private LopDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lop);
        anhxa();
        dao = new LopDao(this);
        btnThemLOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Lop lop = new Lop(edtTenLop.getText().toString(),edtGiaoVien.getText().toString());
                dao.ThemLop(lop);
                finish();
            }
        });
        btnThoatLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }



    private void anhxa() {
        edtTenLop = findViewById(R.id.edtTenLop);
        edtGiaoVien = findViewById(R.id.edtGiaoVien);
        btnThemLOp = findViewById(R.id.btnThemLop);
        btnThoatLop = findViewById(R.id.btnThoatThemLop);
    }
}