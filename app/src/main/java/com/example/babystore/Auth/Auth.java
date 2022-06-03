package com.example.babystore.Auth;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.babystore.MainActivity;
import com.example.babystore.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class Auth extends AppCompatActivity {
    Button sign;
    TextView reg;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;

    ConstraintLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        sign = findViewById(R.id.sign);
        reg = findViewById(R.id.register);

        root = findViewById(R.id.root_element);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Пользователи");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Intent i = new Intent(new Intent(Auth.this, MainActivity.class));
            startActivity(i);
        }else{
            reg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showRegisterWindow();
                }
            });
            sign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showSignInWindow();
                }
            });
        }
    }

    private void showSignInWindow(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Войти");
        dialog.setMessage("Введите все данные для входа");

        LayoutInflater inflater = LayoutInflater.from(this);
        View sing_in_window = inflater.inflate(R.layout.activity_sign, null);
        dialog.setView(sing_in_window);

        TextView email = sing_in_window.findViewById(R.id.email);
        TextView pass = sing_in_window.findViewById(R.id.password);

        dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });
        dialog.setPositiveButton("Войти", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (TextUtils.isEmpty(email.getText().toString())){
                    Snackbar.make(root, "Введите вашу почту", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (pass.getText().toString().length() < 5){
                    Snackbar.make(root, "Введите ваш пароль", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                auth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Intent i = new Intent(new Intent(Auth.this, MainActivity.class));
                        startActivity(i);
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(root,  e.getMessage(), Snackbar.LENGTH_SHORT).show();
                    }
                });
            }
        });
        dialog.show();
    }

    private void showRegisterWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Зарегистрироваться");
        dialog.setMessage("Введите все данные для регистрации");

        LayoutInflater inflater = LayoutInflater.from(this);
        View register_window = inflater.inflate(R.layout.activity_register, null);
        dialog.setView(register_window);

        TextView email = register_window.findViewById(R.id.email);
        TextView pass = register_window.findViewById(R.id.password);
        TextView name = register_window.findViewById(R.id.name);
        TextView phone = register_window.findViewById(R.id.number);

        dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });
        dialog.setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (TextUtils.isEmpty(email.getText().toString())){
                    Snackbar.make(root, "Введите вашу почту", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (pass.getText().toString().replaceAll("[\\s\\d]", "").length() < 5){
                    Snackbar.make(root, "Введите пароль больше 5-ти знаков со спец. символами", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(name.getText().toString())){
                    Snackbar.make(root, "Введите ваше имя", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(phone.getText().toString())){
                    Snackbar.make(root, "Введите ваш телефон", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                String i1 = "", i2 = "", i3 = "" ,i4 = "";
                Random r = new Random();
                i1 = String.valueOf(r.nextInt(9999 - 1000 + 1) + 1000);
                i2 = String.valueOf(r.nextInt(9999 - 1000 + 1) + 1000);
                i3 = String.valueOf(r.nextInt(9999 - 1000 + 1) + 1000);
                i4 = String.valueOf(r.nextInt(9999 - 1000 + 1) + 1000);
                String forcard = (i1 + " " + i2 + " " + i3 + " " + i4);
//                Регистрация пользователя
                auth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                User user = new User();
                                user.setEmail(email.getText().toString());
                                user.setPass(pass.getText().toString());
                                user.setName(name.getText().toString());
                                user.setPhone(phone.getText().toString());
                                user.setCard(forcard);


                                users.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user)
                                        .addOnSuccessListener(avoid -> Snackbar.make(root, "Пользователь добавлен!", Snackbar.LENGTH_SHORT).show()).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Snackbar.make(root, "Ошибка создания пользователя!" + e.getMessage(), Snackbar.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
            }
        });
        dialog.show();
    }
}