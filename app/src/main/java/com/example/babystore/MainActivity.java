package com.example.babystore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.babystore.categories.Categories1;
import com.example.babystore.categories.Categories2;
import com.example.babystore.dowbase.DataBaseHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{//View.OnClickListener - это для метода кнопки категорий
    ImageView backbtn;
    public CardView categories1, categories2, categories3, categories4, categories5, categories6, categories7, categories8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.bottomNavigationView);//Навигация
        NavController navController = Navigation.findNavController(this, R.id.fragment);//Навигация
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.home1, R.id.catalog1, R.id.basket1, R.id.profile).build();//Навигация
        NavigationUI.setupWithNavController(navView, navController);//Навигация
        DataBaseHelper myDbHelper = new DataBaseHelper(this);
        myDbHelper = new DataBaseHelper(this);

        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }

        try {
            myDbHelper.openDataBase();
        }catch(SQLException sqle){
            throw sqle;
        }
    }


        @Override
        public void onClick(View v) {
            Intent i;
            switch (v.getId()) {
                case R.id.categor1:
                    i = new Intent(this, Categories1.class);
                    startActivity(i);
                    break;
                case R.id.categor2:
                    i = new Intent(this, Categories2.class);
                    startActivity(i);
                    break;
            }
        }
};
