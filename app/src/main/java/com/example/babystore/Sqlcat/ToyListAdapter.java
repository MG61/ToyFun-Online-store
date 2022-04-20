package com.example.babystore.Sqlcat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.babystore.R;

import java.util.ArrayList;

public class ToyListAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Toy> toylist;

    public ToyListAdapter(Context context, int layout, ArrayList<Toy> toylist) {
        this.context = context;
        this.layout = layout;
        this.toylist = toylist;
    }

    @Override
    public int getCount() {
        return toylist.size();
    }

    @Override
    public Object getItem(int position) {
        return toylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView txtName, txtPrice;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtName = (TextView) row.findViewById(R.id.priceitem);
            holder.txtPrice = (TextView) row.findViewById(R.id.priceitem);
            holder.imageView = (ImageView) row.findViewById(R.id.img1);
            row.setTag(holder);
        }
        else{
            holder = (ViewHolder) row.getTag();
        }
        Toy toy = toylist.get(position);

        holder.txtName.setText(toy.getName());
        holder.txtPrice.setText(toy.getPrice());

        byte[] toyImage = toy.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(toyImage, 0, toyImage.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}
