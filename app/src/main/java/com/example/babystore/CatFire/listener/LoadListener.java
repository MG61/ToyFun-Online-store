package com.example.babystore.CatFire.listener;

import com.example.babystore.CatFire.Model.Model;

import java.util.List;

public interface LoadListener {
    void onLoadSuccess(List<Model> ModelList);
    void onLoadFailed(String message);
}
