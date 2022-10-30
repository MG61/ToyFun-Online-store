package com.example.babystore.CatFire;

        import android.content.Intent;
        import android.os.Bundle;
        import android.widget.FrameLayout;
        import android.widget.ImageView;
        import android.widget.RelativeLayout;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.GridLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import com.example.babystore.CatFire.CartActivity.CartActivity;
        import com.example.babystore.CatFire.Model.CartModel;
        import com.example.babystore.CatFire.Model.Model;
        import com.example.babystore.CatFire.adapter.MyToyAdapter;
        import com.example.babystore.CatFire.eventbus.MyUpdateCartEvent;
        import com.example.babystore.CatFire.listener.LoadListener;
        import com.example.babystore.CatFire.listener.LoadListenerCart;
        import com.example.babystore.CatFire.utils.SpaceItemDecoration;
        import com.example.babystore.R;
        import com.google.android.material.snackbar.Snackbar;

        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;
        import com.nex3z.notificationbadge.NotificationBadge;
        import org.greenrobot.eventbus.EventBus;
        import org.greenrobot.eventbus.Subscribe;
        import org.greenrobot.eventbus.ThreadMode;

        import java.util.ArrayList;
        import java.util.List;

        import butterknife.BindView;
        import butterknife.ButterKnife;

public class SoftCatFire extends AppCompatActivity implements LoadListener, LoadListenerCart {
    @BindView(R.id.recycler_all)
    RecyclerView recycler_all;
    @BindView(R.id.mainLayout)
    RelativeLayout mainLayout;
    @BindView(R.id.badge)
    NotificationBadge badge;
    @BindView(R.id.btncart)
    FrameLayout btnCart;
    @BindView(R.id.btnback)
    ImageView btnBack;

    LoadListener loadListener;
    LoadListenerCart loadListenerCart;

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
        countCartItem();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_soft_cat_fire);
        init();
        loadFromFirebase();
        countCartItem();
    }

    private void loadFromFirebase() {
        List<Model> Models = new ArrayList<>();
        FirebaseDatabase.getInstance()
                .getReference("Soft")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            for(DataSnapshot Snapshot:snapshot.getChildren())
                            {
                                Model model = Snapshot.getValue(Model.class);
                                model.setKey(Snapshot.getKey());
                                Models.add(model);
                            }
                            loadListener.onLoadSuccess(Models);
                        }
                        else{
                            loadListener.onLoadFailed("Товары не найдены!");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        loadListener.onLoadFailed(error.getMessage());
                    }
                });
    }

    private void init() {
        ButterKnife.bind(this);

        loadListener = this;
        loadListenerCart = this;

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recycler_all.setLayoutManager(gridLayoutManager);
        recycler_all.addItemDecoration(new SpaceItemDecoration());
        btnBack.setOnClickListener(v -> finish());
        btnCart.setOnClickListener(v -> startActivity(new Intent(this, CartActivity.class)));
    }

    @Override
    public void onLoadSuccess(List<Model> ModelList) {
        MyToyAdapter adapter = new MyToyAdapter(this, ModelList, loadListenerCart);
        recycler_all.setAdapter(adapter);
    }

    @Override
    public void onLoadFailed(String message) {
        Snackbar.make(mainLayout, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onCartSuccess(List<CartModel> cartModelList) {
        int cartSum = 0;
        for(CartModel cartModel: cartModelList)
            cartSum += cartModel.getQuantity();
        badge.setNumber(cartSum);
    }

    @Override
    public void onCartFailed(String message) {
        Snackbar.make(mainLayout, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        countCartItem();
    }

    private void countCartItem() {
        List<CartModel> cartModels = new ArrayList<>();
        FirebaseDatabase
                .getInstance().getReference("Корзина")
                .child("UNIQUE_USER_ID")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot cartSnapshot:snapshot.getChildren())
                        {
                            CartModel cartModel = cartSnapshot.getValue(CartModel.class);
                            cartModel.setKey(cartSnapshot.getKey());
                            cartModels.add(cartModel);
                        }
                        loadListenerCart.onCartSuccess(cartModels);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        loadListenerCart.onCartFailed(error.getMessage());
                    }
                });
    }
}