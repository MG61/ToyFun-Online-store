package com.example.babystore.RecyclerAllProduct;

import static android.os.Build.ID;

import static com.example.babystore.RecyclerAllProduct.MyDataBase.COLUMN_ID;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.babystore.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class Item extends AppCompatActivity {

    MyDataBase dBmain;
    SQLiteDatabase sqLiteDatabase;
    CustomAdapter myAdapter;
    ImageView imagesheet;
    TextView maintextsheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemforrecyclerview);
        imagesheet = findViewById(R.id.imagesheet);
        maintextsheet = findViewById(R.id.maintextsheet);
    }

    public void gettext()
    {
        sqLiteDatabase = dBmain.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from total_name where COLUMN_ID = ?", new String[] {COLUMN_ID+""});
        cursor.moveToFirst();
        maintextsheet.setText(cursor.getString(1));
        cursor.close();
    }
}
