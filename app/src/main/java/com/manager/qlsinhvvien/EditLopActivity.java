package com.manager.qlsinhvvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.manager.qlsinhvvien.R;
import com.manager.qlsinhvvien.dao.LopDao;
import com.manager.qlsinhvvien.model.Lop;

public class EditLopActivity extends AppCompatActivity {

    private EditText edtTenLop,edtGiaoVien,edtMaLop;
    private Button btnSuaLop,btnThoatSuaLop;
    private LopDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_lop);
        anhxa();
        dao = new LopDao(this);
        Intent intent= getIntent();
        final Lop lop=(Lop) intent.getSerializableExtra("DuLieu");
        edtMaLop.setText(String.valueOf(lop.getMaLop()) );
        edtTenLop.setText(lop.getTenLop());
        edtGiaoVien.setText(lop.getGiaoVien());
        btnSuaLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Lop lop = new Lop(Integer.parseInt(edtMaLop.getText().toString()),edtTenLop.getText().toString(),edtGiaoVien.getText().toString());
                dao.SuaLop(lop);
                finish();
            }
        });
        btnThoatSuaLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }



    private void anhxa() {
        edtTenLop = findViewById(R.id.edtTenLop);
        edtGiaoVien = findViewById(R.id.edtGiaoVien);
        btnSuaLop = findViewById(R.id.btnSuaLop);
        btnThoatSuaLop = findViewById(R.id.btnThoatSuaLop);
        edtMaLop = findViewById(R.id.edtMaLop);
    }
}