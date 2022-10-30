package com.example.babystore.CatFire.listener;

import com.example.babystore.CatFire.Model.CartModel;
import com.example.babystore.CatFire.Model.Model;

import java.util.List;

public interface LoadListenerCart {
    void onCartSuccess(List<CartModel> cartModelList);
    void onCartFailed(String message);
}
