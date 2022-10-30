package com.example.babystore;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.babystore.CatFire.ActiveCatFire;
import com.example.babystore.CatFire.CatFire1;
import com.example.babystore.CatFire.EducationalCatFire;
import com.example.babystore.CatFire.FunkoCatFire;
import com.example.babystore.CatFire.HolidayCatFire;
import com.example.babystore.CatFire.LegoCatFire;
import com.example.babystore.CatFire.PuzzlesCatFire;
import com.example.babystore.CatFire.RadioCatFire;
import com.example.babystore.CatFire.SoftCatFire;
import com.example.babystore.categories.Allcategor1;
import com.example.babystore.categories.Categories1;
import com.example.babystore.categories.Categories2;
import com.example.babystore.categories.Categories3;
import com.example.babystore.categories.Categories4;
import com.example.babystore.categories.Categories5;
import com.example.babystore.categories.Categories6;
import com.example.babystore.categories.Categories7;
import com.example.babystore.categories.Categories8;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.Random;

public class Home1 extends Fragment {

    public final static int QRcodeWidth = 500 ;
    Bitmap bitmap;
    TextView maintexthome, numbercard, pricetextsheet22;
    CardView qr, cat1home, cat2home, cat3home, cat4home, cat5home, cat6home, cat7home, cat8home;
    ImageView qrimage, imagesheet22;
    String code1 = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home1, container, false);

        maintexthome = view.findViewById(R.id.maintexthome);
        qrimage = view.findViewById(R.id.qrimage);
        numbercard = view.findViewById(R.id.numbercard);
        qr = view.findViewById(R.id.qr);
        imagesheet22 = view.findViewById(R.id.imagesheet22);
        pricetextsheet22 = view.findViewById(R.id.pricetextsheet22);
        cat1home = view.findViewById(R.id.cat1home);
        cat2home = view.findViewById(R.id.cat2home);
        cat3home = view.findViewById(R.id.cat3home);
        cat4home = view.findViewById(R.id.cat4home);
        cat5home = view.findViewById(R.id.cat5home);
        cat6home = view.findViewById(R.id.cat6home);
        cat7home = view.findViewById(R.id.cat7home);
        cat8home = view.findViewById(R.id.cat8home);

        CardView allcat =  view.findViewById(R.id.allcat);
        allcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CatFire1.class);
                startActivity(intent);
            }
        });
        checkname();
        checkcard();
        checkcard2();
        cat1home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LegoCatFire.class);
                startActivity(intent);
            }
        });
        cat2home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FunkoCatFire.class);
                startActivity(intent);
            }
        });
        cat3home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RadioCatFire.class);
                startActivity(intent);
            }
        });
        cat4home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActiveCatFire.class);
                startActivity(intent);
            }
        });
        cat5home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HolidayCatFire.class);
                startActivity(intent);
            }
        });
        cat6home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EducationalCatFire.class);
                startActivity(intent);
            }
        });
        cat7home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PuzzlesCatFire.class);
                startActivity(intent);
            }
        });
        cat8home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SoftCatFire.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private Bitmap TextToImageEncode(String Value) throws WriterException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    Value,
                    BarcodeFormat.DATA_MATRIX.QR_CODE,
                    QRcodeWidth, QRcodeWidth, null
            );

        } catch (IllegalArgumentException Illegalargumentexception) {

            return null;
        }
        int bitMatrixWidth = bitMatrix.getWidth();

        int bitMatrixHeight = bitMatrix.getHeight();

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {

                pixels[offset + x] = bitMatrix.get(x, y) ?
                        getResources().getColor(R.color.black):getResources().getColor(R.color.white);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }

    public void checkname() {
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usuariosRef = rootRef.child("Пользователи");
        usuariosRef.child(uid).child("name").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                maintexthome.setText("Привет, " + String.valueOf(task.getResult().getValue()) + "!");
            }
        });
    }

    public void checkcard() {
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usuariosRef = rootRef.child("Пользователи");
        usuariosRef.child(uid).child("card").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                numbercard.setText(String.valueOf(task.getResult().getValue()));
            }
        });
    }

    public void checkcard2() {
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usuariosRef = rootRef.child("Пользователи");
        usuariosRef.child(uid).child("card").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                try {
                    bitmap = TextToImageEncode(String.valueOf(task.getResult().getValue()));
                    qrimage.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}