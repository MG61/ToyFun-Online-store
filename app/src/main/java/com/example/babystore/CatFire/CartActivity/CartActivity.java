package com.example.babystore.CatFire.CartActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.babystore.CatFire.Model.CartModel;
import com.example.babystore.CatFire.adapter.MyCartAdapter;
import com.example.babystore.CatFire.eventbus.MyUpdateCartEvent;
import com.example.babystore.CatFire.listener.LoadListenerCart;
import com.example.babystore.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nex3z.notificationbadge.NotificationBadge;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartActivity extends AppCompatActivity implements LoadListenerCart {

    @BindView(R.id.recycler_cart)
    RecyclerView recycler_cart;
    @BindView(R.id.mainLayout)
    RelativeLayout mainLayout;
    @BindView(R.id.btnback)
    ImageView btnBack;
    @BindView(R.id.txtTotal)
    TextView txtTotal;
    @BindView(R.id.buyalltovar)
    ImageView buyalltovar;


    LoadListenerCart cartLoadListener;

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        if(EventBus.getDefault().hasSubscriberForEvent(MyUpdateCartEvent.class))
            EventBus.getDefault().removeStickyEvent(MyUpdateCartEvent.class);
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onUpdateCart(MyUpdateCartEvent event)
    {
        loadCartFromFirebase();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_cart);

        init();
        loadCartFromFirebase();
    }
    public int prov = 0;


    private void loadCartFromFirebase() {

        List<CartModel> cartModels = new ArrayList<>();
        FirebaseDatabase.getInstance()
                .getReference("Cart")
                .child("UNIQUE_USER_ID")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            for(DataSnapshot cartSnapshot:snapshot.getChildren()){
                                CartModel cartModel = cartSnapshot.getValue(CartModel.class);
                                cartModel.setKey(cartSnapshot.getKey());
                                cartModels.add(cartModel);
                            }
                            prov = 1;
                            cartLoadListener.onCartSuccess(cartModels);
                        }
                        else{
                            cartLoadListener.onCartFailed("Корзина пуста!");
                            if(prov == 1) {
                                finish();
                                startActivity(getIntent());
                                overridePendingTransition(0, 0);
                                prov = 0;
                            }

                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        cartLoadListener.onCartFailed(error.getMessage());
                    }
                });
    }

    private void deletefromFirebase() {
        DatabaseReference Cart = FirebaseDatabase.getInstance()
                .getReference("Cart");
                Cart.removeValue();
        finish();
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }

    private void init(){
        ButterKnife.bind(this);

        cartLoadListener = this;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler_cart.setLayoutManager(layoutManager);
        recycler_cart.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));

        btnBack.setOnClickListener(v -> finish());
        buyalltovar.setOnClickListener(v -> {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle("Оплата покупок")
                    .setMessage("Хотите оплатить весь товар?")
                    .setNegativeButton("Отмена", (dialog1, which) -> dialog1.dismiss())
                    .setPositiveButton("Да", (dialog12, which) -> {
                    deletefromFirebase();
                  dialog12.dismiss();
                    }).create();
            dialog.show();
        });
    }

    @Override
    public void onCartSuccess(List<CartModel> cartModelList) {
        double sum = 0;
        for (CartModel cartModel: cartModelList){
            sum += cartModel.getTotalPrice();
        }
        txtTotal.setText(new StringBuilder("₽ ").append(sum));
        MyCartAdapter adapter = new MyCartAdapter(this, cartModelList);
        recycler_cart.setAdapter(adapter);
    }

    @Override
    public void onCartFailed(String message) {
        Snackbar.make(mainLayout, message, Snackbar.LENGTH_SHORT).show();
    }
}