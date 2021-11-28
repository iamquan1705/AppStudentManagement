package com.manager.qlsinhvvien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.manager.qlsinhvvien.dao.SinhVienDao;
import com.manager.qlsinhvvien.model.Lop;
import com.manager.qlsinhvvien.adapter.LopAdapter;
import com.manager.qlsinhvvien.dao.LopDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LopDao dao;
    private ListView lvLop;
    private Button btnThemLop;
    private List<Lop> lList;
    private LopAdapter lopAdapter;
    private SinhVienDao svd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnThemLop = findViewById(R.id.btnThem);
        lvLop = findViewById(R.id.lvLop);
        lList = new  ArrayList<Lop>();
        dao = new LopDao(this);
        svd = new SinhVienDao(this);
        lList= dao.DanhSachLop();
        lopAdapter=new LopAdapter(getApplicationContext(),lList);
        lvLop.setAdapter(lopAdapter);
        registerForContextMenu(lvLop);
        btnThemLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddLopActivity.class);
                startActivity(intent);
            }
        });
        lvLop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, SinhVienActivity.class);
                intent.putExtra("TenLop",lList.get(i).getTenLop());
                startActivity(intent);
            }
        });

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_lop,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position=info.position;
        Lop lop=lList.get(position);
        switch (item.getItemId()){
            case R.id.mnSuaLop:{
                Intent intent=new Intent(MainActivity.this, EditLopActivity.class);
                  intent.putExtra("DuLieu",lop);
                startActivity(intent);
                onResume();
                break;
            }
            case R.id.mnXoaLop:{
                svd.XoaLopSinhVien(lop.getTenLop());
                dao.XoaSp(lop.getMaLop());
                lopAdapter.notifyDataSetChanged();
                onResume();
                break;
            }
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        lList.clear();
        lList.addAll(dao.DanhSachLop());
        lopAdapter.notifyDataSetChanged();
    }
}