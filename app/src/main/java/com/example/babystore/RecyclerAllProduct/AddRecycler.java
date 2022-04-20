package com.example.babystore.RecyclerAllProduct;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.babystore.R;
import com.example.babystore.RecyclerAllProduct2.MyDataBase2;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddRecycler extends AppCompatActivity {

    EditText name_input, price_input, art_input;
    Button add_button, btnChoose;
    ImageView imageView;
    final int REQUEST_CODE_GALLERY = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recycler);

        imageView = findViewById(R.id.imageView);
        btnChoose = findViewById(R.id.btnChoose);
        name_input = findViewById(R.id.Firstparamsql);
        price_input = findViewById(R.id.Secondparamsql);
        art_input = findViewById(R.id.Thirdparamsql);
        add_button = findViewById(R.id.add_button);

        btnChoose.setOnClickListener(new View.OnClickListener() {//Выбор фотографии
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        AddRecycler.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY//Где это есть, везде фотка
                );
            }
        });

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDataBase myDB = new MyDataBase(AddRecycler.this);
                Spinner spinner = findViewById(R.id.ComboBox);
                String selected = spinner.getSelectedItem().toString();

                myDB.addproduk(name_input.getText().toString().trim(),
                        price_input.getText().toString().trim() + " ₽","#"+
                        art_input.getText().toString().trim(),
                        ImageViewToByte(imageView));
                switch(selected) {
                    case ("Лего"):
                        myDB.addproduk2(name_input.getText().toString().trim(),
                                price_input.getText().toString().trim() + " ₽","#"+
                                        art_input.getText().toString().trim(),
                                ImageViewToByte(imageView));
                        break;
                    case ("Фигурки"):
                        myDB.addproduk3(name_input.getText().toString().trim(),
                                price_input.getText().toString().trim() + " ₽","#"+
                                        art_input.getText().toString().trim(),
                                ImageViewToByte(imageView));
                        break;
                    case ("На радиоуправлении"):
                        myDB.addproduk4(name_input.getText().toString().trim(),
                                price_input.getText().toString().trim() + " ₽","#"+
                                        art_input.getText().toString().trim(),
                                ImageViewToByte(imageView));
                        break;
                    case ("Активные игры"):
                        myDB.addproduk5(name_input.getText().toString().trim(),
                                price_input.getText().toString().trim() + " ₽","#"+
                                        art_input.getText().toString().trim(),
                                ImageViewToByte(imageView));
                        break;
                    case ("Праздник"):
                        myDB.addproduk6(name_input.getText().toString().trim(),
                                price_input.getText().toString().trim() + " ₽","#"+
                                        art_input.getText().toString().trim(),
                                ImageViewToByte(imageView));
                        break;
                    case ("Развивающие игрушки"):
                        myDB.addproduk7(name_input.getText().toString().trim(),
                                price_input.getText().toString().trim() + " ₽","#"+
                                        art_input.getText().toString().trim(),
                                ImageViewToByte(imageView));
                        break;
                    case ("Головоломки"):
                        myDB.addproduk8(name_input.getText().toString().trim(),
                                price_input.getText().toString().trim() + " ₽","#"+
                                        art_input.getText().toString().trim(),
                                ImageViewToByte(imageView));
                        break;
                    case ("Интерактивные игрушки"):
                        myDB.addproduk9(name_input.getText().toString().trim(),
                                price_input.getText().toString().trim() + " ₽","#"+
                                        art_input.getText().toString().trim(),
                                ImageViewToByte(imageView));
                        break;
                }

            }
        });
    }

    private byte[] ImageViewToByte(ImageView imageView) {
        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_GALLERY){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else{
                Toast.makeText(getApplicationContext(), "Файл не найден", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}