package com.example.babystore.categories;

import static com.example.babystore.RecyclerAllProduct.MyDataBase.TABLE_NAME2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.babystore.R;
import com.example.babystore.RecyclerAllProduct.AddRecycler;
import com.example.babystore.RecyclerAllProduct.CustomAdapter;
import com.example.babystore.RecyclerAllProduct.MyDataBase;
import com.example.babystore.RecyclerAllProduct.Toy;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Categories1 extends AppCompatActivity {

    MyDataBase dBmain;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    CustomAdapter myAdapter;
    FloatingActionButton add_button;

    @Override
    protected void  onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories1);

        recyclerView = findViewById(R.id.recycleView);

        dBmain = new MyDataBase(this);
        displayData();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void displayData() {
        sqLiteDatabase = dBmain.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME2 +"", null);
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