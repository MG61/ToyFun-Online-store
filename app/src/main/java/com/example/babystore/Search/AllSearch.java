package com.example.babystore.Search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.babystore.R;
import com.example.babystore.RecyclerAllProduct.CustomAdapter;
import com.example.babystore.RecyclerAllProduct.MyDataBase;
import com.example.babystore.RecyclerAllProduct.Toy;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Locale;

public class AllSearch extends AppCompatActivity {

    MyDataBase dBmain;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    CustomAdapter myAdapter;
    SearchView search_view;
    TextView errortext;
    ImageView errorimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_search);

        recyclerView = findViewById(R.id.recycleView);
        search_view = findViewById(R.id.search_view);
        errorimage = findViewById(R.id.errorimage);
        errortext = findViewById(R.id.errortext);

        dBmain = new MyDataBase(this);//Инициализируем базу данных
        displayData();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        search_view.requestFocus();
        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterlist(newText);
                return true;
            }
        });
    }

    private void filterlist(String text) {
        ArrayList<Toy> mod = new ArrayList<>();
        sqLiteDatabase = dBmain.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + "total_name" +"", null);
        ArrayList<Toy> model = new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            String atr = cursor.getString(3);
            byte[]image = cursor.getBlob(4);
            model.add(new Toy(id,name,price,atr,image));
        }
        for (Toy Item: model){
            if (Item.getName().toLowerCase().contains(text.toLowerCase())){
                mod.add(Item);
            }
        }

        if (mod.isEmpty()){
            errortext.setVisibility(View.VISIBLE);
            errorimage.setVisibility(View.VISIBLE);
        }else{
            errortext.setVisibility(View.INVISIBLE);
            errorimage.setVisibility(View.INVISIBLE);
            myAdapter.setFilteredList(mod);
        }
        myAdapter = new CustomAdapter(this, R.layout.itemforrecyclerview, mod, sqLiteDatabase);
        recyclerView.setAdapter(myAdapter);

    }

    private void displayData() {
        sqLiteDatabase = dBmain.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + "total_name" +"", null);
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