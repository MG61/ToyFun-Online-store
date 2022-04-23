package com.example.babystore.RecyclerAllProduct;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.babystore.R;

import java.util.ArrayList;

public class Bottom_Sheet extends AppCompatActivity {

    MyDataBase dBmain;
    SQLiteDatabase sqLiteDatabase;

    long DIFFICULTY_EASY;
    TextView konname;
    TextView konphone;
    long id;
    String name;
    int phone;
    CustomAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_sheet);

    }

//    public void setDB(){
//        id = getIntent().getLongExtra("id", DIFFICULTY_EASY);
//        Log.d("my","лог "+ id);
//
//        konname=(TextView)findViewById(R.id.maintextsheet);
//        konphone=(TextView)findViewById(R.id.pricetextsheet);
//        sqlHelper = new CustomAdapter(getApplicationContext());
//        db = sqlHelper.getReadableDatabase();
//        String selection = "_id = ?";
//        String[] selectionArgs = new String[] {String.valueOf(userId)};
//        Cursor c = db.query("kontakts", null, selection, selectionArgs, null, null, null);
//        if(c.moveToFirst()){
//            name  = c.getString(c.getColumnIndexOrThrow ("COLUMN_NAME"));
//            phone = c.getInt(c.getColumnIndexOrThrow ("COLUMN_PHONE"));
//        }
//        Log.d("my","лог "+name+phone);
//    }

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
        konname.setText(name);
//        myAdapter = new CustomAdapter(this, R.layout.itemforrecyclerview, models, sqLiteDatabase);
//        .setAdapter(myAdapter);
    }
}
