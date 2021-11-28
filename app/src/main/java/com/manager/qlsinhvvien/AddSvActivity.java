package com.manager.qlsinhvvien;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.manager.qlsinhvvien.dao.LopDao;
import com.manager.qlsinhvvien.dao.SinhVienDao;
import com.manager.qlsinhvvien.model.Lop;
import com.manager.qlsinhvvien.model.SinhVien;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class AddSvActivity extends AppCompatActivity {


    private EditText edtTenSVAdd,edtNamSinhAdd,edtMaSVAdd,edtDiaChiAdd;
    private Button btnAddSV,btnCamera,btnLibrary,btnThoatAddsv;
    private ImageView imgAnhTheAdd;
    private TextView tvTenLopAdd;
    private int Resquet_code_camera=123;
    private int Resquet_code_library=456;
    private SinhVienDao sinhVienDao;
    private Spinner spnTenLop;
    private LopDao lDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sv);
        anhxa();
        sinhVienDao = new SinhVienDao(this);
        lDao = new LopDao(this);
        Intent intent = getIntent();
        String tenlop = intent.getStringExtra("TenLop");
        ArrayList listtenHH = new ArrayList();
        if (tenlop.equals("")) {

            listtenHH = (ArrayList) lDao.getAllLop();
        } else {
            listtenHH.clear();
            listtenHH.add(tenlop);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                listtenHH);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spnTenLop.setAdapter(adapter);


        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,Resquet_code_camera);
            }
        });

        btnLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,Resquet_code_library);

            }
        });

        btnAddSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String masv =edtMaSVAdd.getText().toString();
                String tensv =edtTenSVAdd.getText().toString();
                String namsinh =edtNamSinhAdd.getText().toString();
                String diachi =edtDiaChiAdd.getText().toString();
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgAnhTheAdd.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream bytearray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,bytearray);
                byte[] hinh = bytearray.toByteArray();
                String tl = spnTenLop.getSelectedItem().toString();
                SinhVien sv = new SinhVien(masv,tensv,namsinh,diachi,hinh,tl);
                sinhVienDao.ThemSinhVien(sv);
                finish();
            }
        });

        btnThoatAddsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Resquet_code_camera && resultCode == RESULT_OK && data != null) {
            Bitmap bimap = (Bitmap) data.getExtras().get("data");
            imgAnhTheAdd.setImageBitmap(bimap);
        }
        if (requestCode == Resquet_code_library && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgAnhTheAdd.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
        private void anhxa () {
            edtTenSVAdd = findViewById(R.id.edtTenSVAdd);
            edtNamSinhAdd = findViewById(R.id.edtNamSinhAdd);
            edtMaSVAdd = findViewById(R.id.edtMaSVAdd);
            edtDiaChiAdd = findViewById(R.id.edtDiaChiAdd);
            imgAnhTheAdd = findViewById(R.id.imgAnhTheAdd);
            btnAddSV = findViewById(R.id.btnAddSinhVien);
            btnCamera = findViewById(R.id.btnCamera);
            btnLibrary = findViewById(R.id.btnLibrary);
            btnThoatAddsv = findViewById(R.id.btnThoatAddSv);
            spnTenLop = findViewById(R.id.spnTenLop);
        }
    }