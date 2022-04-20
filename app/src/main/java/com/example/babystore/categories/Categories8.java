package com.example.babystore.categories;

import static com.example.babystore.RecyclerAllProduct.MyDataBase.TABLE_NAME4;
import static com.example.babystore.RecyclerAllProduct.MyDataBase.TABLE_NAME9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.babystore.R;
import com.example.babystore.RecyclerAllProduct.CustomAdapter;
import com.example.babystore.RecyclerAllProduct.MyDataBase;
import com.example.babystore.RecyclerAllProduct.Toy;

import java.util.ArrayList;

public class Categories8 extends AppCompatActivity {

    MyDataBase dBmain;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    CustomAdapter myAdapter;

    @Override
    protected void  onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories8);

        recyclerView = findViewById(R.id.recycleView);

        dBmain = new MyDataBase(this);
        displayData();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void displayData() {
        sqLiteDatabase = dBmain.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME9 +"", null);
        ArrayList<Toy> models = new ArrayList<>();
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