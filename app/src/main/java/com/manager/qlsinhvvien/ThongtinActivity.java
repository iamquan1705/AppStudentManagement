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
import android.widget.Toast;

import com.manager.qlsinhvvien.dao.LopDao;
import com.manager.qlsinhvvien.dao.SinhVienDao;
import com.manager.qlsinhvvien.model.SinhVien;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ThongtinActivity extends AppCompatActivity {

    private EditText edtTenSVInfo,edtNamSinhInfo,edtMaSVInfo,edtDiaChiInfo;
    private Button btnSuaInfo,btnCameraSua,btnLibrarySua,btnThoatSuasv,btnSuaSinhVien;
    private CircleImageView imgAnhTheInfo;
    private int Resquet_code_camera=321;
    private int Resquet_code_library=654;
    private SinhVienDao sinhVienDao;
    private LopDao lDao;
    private Spinner spnTenLopSua;
    private ArrayList listtenHH;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtin);
        anhxa();
        sinhVienDao = new SinhVienDao(this);
        lDao = new LopDao(this);
        Intent intee= getIntent();
        String ma = intee.getStringExtra("masv");
        SinhVien sv = sinhVienDao.getSinhVien(ma);
         listtenHH = new ArrayList();
        listtenHH.add(sv.getTenLop());
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                listtenHH);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spnTenLopSua.setAdapter(adapter);


        edtTenSVInfo.setText(sv.getTenSV());
        edtMaSVInfo.setText(sv.getMaSV());
        edtNamSinhInfo.setText(sv.getNamSinh());
        edtDiaChiInfo.setText(sv.getDiaChi());
        Bitmap bitmap = BitmapFactory.decodeByteArray(sv.getAnhThe(), 0, sv.getAnhThe().length);
        imgAnhTheInfo.setImageBitmap(bitmap);


        btnSuaInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtDiaChiInfo.setEnabled(true);
                edtTenSVInfo.setEnabled(true);
                edtNamSinhInfo.setEnabled(true);
                edtMaSVInfo.setEnabled(true);
                edtDiaChiInfo.setEnabled(true);
                btnSuaSinhVien.setVisibility(View.VISIBLE);
                btnLibrarySua.setVisibility(View.VISIBLE);
                btnCameraSua.setVisibility(View.VISIBLE);
                listtenHH.clear();
                listtenHH.addAll(lDao.getAllLop()) ;
                adapter.notifyDataSetChanged();
            }
        });


        btnSuaSinhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String masv =edtMaSVInfo.getText().toString();
                String tensv =edtTenSVInfo.getText().toString();
                String namsinh =edtNamSinhInfo.getText().toString();
                String diachi =edtDiaChiInfo.getText().toString();
                String lop = spnTenLopSua.getSelectedItem().toString();
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgAnhTheInfo.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream bytearray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,bytearray);
                byte[] hinh = bytearray.toByteArray();

                SinhVien sv = new SinhVien(masv,tensv,namsinh,diachi,hinh,lop);
                sinhVienDao.SuaSinhVien(sv);
                finish();
            }
        });




        btnCameraSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,Resquet_code_camera);
            }
        });

        btnLibrarySua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,Resquet_code_library);

            }
        });

        btnThoatSuasv.setOnClickListener(new View.OnClickListener() {
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
            imgAnhTheInfo.setImageBitmap(bimap);
        }
        if (requestCode == Resquet_code_library && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgAnhTheInfo.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }


    private void anhxa () {
        edtDiaChiInfo = findViewById(R.id.edtDiaChiInfo);
        edtMaSVInfo = findViewById(R.id.edtMaSVInfo);
        edtNamSinhInfo = findViewById(R.id.edtNamSinhInfo);
        edtTenSVInfo = findViewById(R.id.edtTenSVInfo);
        imgAnhTheInfo = findViewById(R.id.imgAnhTheInfo);
        btnSuaInfo = findViewById(R.id.btnSuaInFo);
        btnCameraSua = findViewById(R.id.btnCameraSua);
        btnLibrarySua = findViewById(R.id.btnLibrarySua);
        btnThoatSuasv = findViewById(R.id.btnThoatInfoSv);
        btnSuaSinhVien = findViewById(R.id.btnSuaSinhVien);
        spnTenLopSua = findViewById(R.id.spnTenLopSua);
    }

}