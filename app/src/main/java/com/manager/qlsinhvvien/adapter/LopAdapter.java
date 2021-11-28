package com.manager.qlsinhvvien.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.manager.qlsinhvvien.MainActivity;
import com.manager.qlsinhvvien.R;
import com.manager.qlsinhvvien.dao.LopDao;
import com.manager.qlsinhvvien.model.Lop;

import java.util.List;

public class LopAdapter extends BaseAdapter {
        private Context context;
        private List<Lop> SPList;
        private LopDao lopDao;

        public LopAdapter(Context context, List<Lop> SPList) {
            this.context = context;
            this.SPList = SPList;
        }

        @Override
        public int getCount() {
            return SPList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_lop, null);
                // ánh xạ
                viewHolder.tvTenLop = convertView.findViewById(R.id.tvTenLop);
                viewHolder.tvGiaoVien = convertView.findViewById(R.id.tvGiaoVien);
//                viewHolder.btnSuaLopItem = convertView.findViewById(R.id.btnSuaLopItem);
//                viewHolder.btnXoaLopItem = convertView.findViewById(R.id.btnXoaLopItem);
                convertView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            Lop sp = SPList.get(position);
            viewHolder.tvTenLop.setText("Tên lớp : " +sp.getTenLop());
            viewHolder.tvGiaoVien.setText("Giáo viên : " + sp.getGiaoVien());
            return convertView;
        }
    class  ViewHolder{
        TextView tvTenLop,tvGiaoVien;
       // Button btnSuaLopItem,btnXoaLopItem;
    }
    }
