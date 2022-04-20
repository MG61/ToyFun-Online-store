package com.example.babystore;

import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.babystore.Sqlcat.Allcategor;
import com.example.babystore.Sqlcat.DobSql;
import com.example.babystore.categories.Allcategor1;
import com.example.babystore.categories.Categories1;
import com.example.babystore.categories.Categories2;
import com.example.babystore.categories.Categories3;
import com.example.babystore.categories.Categories4;
import com.example.babystore.categories.Categories5;
import com.example.babystore.categories.Categories6;
import com.example.babystore.categories.Categories7;
import com.example.babystore.categories.Categories8;

public class Catalog1 extends Fragment implements View.OnClickListener{

    CardView categories1, categories2, categories3, categories4, categories5 ,categories6 ,categories7 ,categories8, allcategories, allcategories1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalog1, container, false);

        categories1 = view.findViewById(R.id.categor1);//Обьявление элемента категории
        categories2 = view.findViewById(R.id.categor2);//Обьявление элемента категории
        categories3 = view.findViewById(R.id.categor3);//Обьявление элемента категории
        categories4 = view.findViewById(R.id.categor4);//Обьявление элемента категории
        categories5 = view.findViewById(R.id.categor5);//Обьявление элемента категории
        categories6 = view.findViewById(R.id.categor6);//Обьявление элемента категории
        categories7 = view.findViewById(R.id.categor7);//Обьявление элемента категории
        categories8 = view.findViewById(R.id.categor8);//Обьявление элемента категории
        allcategories = view.findViewById(R.id.allcategor);
        categories1.setOnClickListener(this);//Обьявление кнопки элемента категории
        categories2.setOnClickListener(this);//Обьявление кнопки элемента категории
        categories3.setOnClickListener(this);//Обьявление кнопки элемента категории
        categories4.setOnClickListener(this);//Обьявление кнопки элемента категории
        categories5.setOnClickListener(this);//Обьявление кнопки элемента категории
        categories6.setOnClickListener(this);//Обьявление кнопки элемента категории
        categories7.setOnClickListener(this);//Обьявление кнопки элемента категории
        categories8.setOnClickListener(this);//Обьявление кнопки элемента категории
        allcategories.setOnClickListener(this);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.searchinrecycler, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView =(SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.categor1:
                i = new Intent(getActivity(), Categories1.class);
                startActivity(i);
                break;
            case R.id.categor2:
                i = new Intent(getActivity(), Categories2.class);
                startActivity(i);
                break;
            case R.id.categor3:
                i = new Intent(getActivity(), Categories3.class);
                startActivity(i);
                break;
            case R.id.categor4:
                i = new Intent(getActivity(), Categories4.class);
                startActivity(i);
                break;
            case R.id.categor5:
                i = new Intent(getActivity(), Categories5.class);
                startActivity(i);
                break;
            case R.id.categor6:
                i = new Intent(getActivity(), Categories6.class);
                startActivity(i);
                break;
            case R.id.categor7:
                i = new Intent(getActivity(), Categories7.class);
                startActivity(i);
                break;
            case R.id.categor8:
                i = new Intent(getActivity(), Categories8.class);
                startActivity(i);
                break;
            case R.id.allcategor:
                i = new Intent(getActivity(), Allcategor1.class);
                startActivity(i);
                break;
        }
    }

}