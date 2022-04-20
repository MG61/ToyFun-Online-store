package com.example.babystore.RecyclerAllProduct;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.model.Model;
import com.example.babystore.R;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;
    int singledata;
    ArrayList<Toy> modelArraylist;
    android.database.sqlite.SQLiteDatabase SQLiteDatabase;

    public CustomAdapter(Context context, int singledata, ArrayList<Toy> modelArraylist, android.database.sqlite.SQLiteDatabase SQLiteDatabase) {
        this.context = context;
        this.singledata = singledata;
        this.modelArraylist = modelArraylist;
        this.SQLiteDatabase = SQLiteDatabase;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.itemforrecyclerview, parent ,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Toy model = modelArraylist.get(position);
        byte[] image = model.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        holder.product_name.setText(model.getName());
        holder.product_price.setText(model.getPrice());
        holder.product_atr.setText(model.getAtr());
        holder.product_image1.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return modelArraylist.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView product_name, product_price, product_atr;
        ImageView product_image1;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            product_name = itemView.findViewById(R.id.nameitem);
            product_price = itemView.findViewById(R.id.priceitem);
            product_atr = itemView.findViewById(R.id.artitem);
            product_image1 = itemView.findViewById(R.id.img1);
        }
    }


}
