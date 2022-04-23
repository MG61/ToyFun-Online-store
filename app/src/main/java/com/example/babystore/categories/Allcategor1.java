package com.example.babystore.categories;

import static android.os.Build.ID;
import static com.example.babystore.RecyclerAllProduct.MyDataBase.COLUMN_ID;
import static com.example.babystore.RecyclerAllProduct.MyDataBase.TABLE_NAME;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.babystore.R;
import com.example.babystore.RecyclerAllProduct.AddRecycler;
import com.example.babystore.RecyclerAllProduct.CustomAdapter;
import com.example.babystore.RecyclerAllProduct.MyDataBase;
import com.example.babystore.RecyclerAllProduct.Toy;
//import com.example.babystore.dowbase.DataBaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Allcategor1 extends AppCompatActivity {

    MyDataBase dBmain;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    CustomAdapter myAdapter;
    FloatingActionButton add_button;
    TextView maintextsheet;


    @Override
    protected void  onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allcategor1);

        maintextsheet = findViewById(R.id.maintextsheet);

        recyclerView = findViewById(R.id.recycleView);
        add_button = findViewById(R.id.floatingActionButton);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Allcategor1.this, AddRecycler.class);
                startActivity(intent);
            }
        });
        dBmain = new MyDataBase(this);
        displayData();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    public void gettext()
    {
        sqLiteDatabase = dBmain.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from total_name where COLUMN_ID = ?", new String[] {COLUMN_ID+""});
        ArrayList<Toy>text = new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            String atr = cursor.getString(3);
            byte[]image = cursor.getBlob(4);
            text.add(new Toy(id,name,price,atr,image));
        }
        cursor.close();
    }

    private void displayData() {
        sqLiteDatabase = dBmain.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + "total_name" +"", null);
        ArrayList<Toy>models = new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            String atr = cursor.getString(3);
            byte[]image = cursor.getBlob(4);
            models.add(new Toy(id,name,price,atr,image));
        }
        cursor.close();
        myAdapter = new CustomAdapter(this, R.layout.itemforrecyclerview, models, sqLiteDatabase);
        recyclerView.setAdapter(myAdapter);
    }
}
