package com.manager.qlsinhvvien.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.manager.qlsinhvvien.R;
import com.manager.qlsinhvvien.model.SinhVien;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class SinhVienAdapter extends BaseAdapter {
    private Context context;
    private List<SinhVien> SVList;
    private ArrayList<SinhVien> arl = new ArrayList<>();


    public SinhVienAdapter(Context context, List<SinhVien> SVList) {
        this.context = context;
        this.SVList = SVList;
        this.arl.addAll(SVList);
    }

    @Override
    public int getCount() {
        return SVList.size();
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
        if(convertView==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.item_sinhvien,null);
            // ánh xạ
            viewHolder.tvNgaySinhItem=convertView.findViewById(R.id.tvNgaySinhItem);
            viewHolder.tvDiaChiItem=convertView.findViewById(R.id.tvDiaChiItem);
            viewHolder.tvLopItem=convertView.findViewById(R.id.tvLopItem);
            viewHolder.tvTenSinhVienItem=convertView.findViewById(R.id.tvTenSinhVienItem);
            viewHolder.tvMaSVItem=convertView.findViewById(R.id.tvMaSVItem);

            viewHolder.imgAnhTheItem=convertView.findViewById(R.id.imgAnhTheItem);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder) convertView.getTag();
        }

        SinhVien sv=SVList.get(position);
        viewHolder.tvMaSVItem.setText("Mã sinh viên : "+sv.getMaSV());
        viewHolder.tvTenSinhVienItem.setText("Tên sinh viên: "+ sv.getTenSV());
        viewHolder.tvDiaChiItem.setText("Địa chỉ : "+ sv.getDiaChi());
        viewHolder.tvNgaySinhItem.setText("Năm sinh : "  +sv.getNamSinh());
        viewHolder.tvLopItem.setText("Lớp : "+sv.getTenLop());
        Bitmap bitmap = BitmapFactory.decodeByteArray(sv.getAnhThe(), 0, sv.getAnhThe().length);
        viewHolder.imgAnhTheItem.setImageBitmap(bitmap);
        return convertView;
    }
    class  ViewHolder{
        TextView tvNgaySinhItem,tvDiaChiItem,tvLopItem,tvTenSinhVienItem,tvMaSVItem;
        CircleImageView imgAnhTheItem;
    }
    public void filter(String text) {
        String textSearch = text.toLowerCase(Locale.getDefault());
        SVList.clear();
        if (textSearch.length() == 0) {
            SVList.clear();
            SVList.addAll(arl);
        } else {
            for (int i = 0; i < arl.size(); i++) {
                if (arl.get(i).getTenSV().toLowerCase(Locale.getDefault()).contains(textSearch)) {
                    SVList.add(arl.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }
}


