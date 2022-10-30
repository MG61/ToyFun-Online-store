package com.example.babystore.CatFire.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.babystore.CatFire.Model.CartModel;
import com.example.babystore.CatFire.Model.Model;
import com.example.babystore.CatFire.eventbus.MyUpdateCartEvent;
import com.example.babystore.CatFire.listener.IRecyclerViewClickListener;
import com.example.babystore.CatFire.listener.LoadListener;
import com.example.babystore.CatFire.listener.LoadListenerCart;
import com.example.babystore.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyToyAdapter extends RecyclerView.Adapter<MyToyAdapter.MyToyViewHolder> {

    private Context context;
    private List<Model> ModelList;
    private LoadListenerCart iCartLoadListener;

    public MyToyAdapter(Context context, List<Model> modelList, LoadListenerCart iCartLoadListener) {
        this.context = context;
        ModelList = modelList;
        this.iCartLoadListener = iCartLoadListener;
    }

    @NonNull
    @Override
    public MyToyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyToyViewHolder(LayoutInflater.from(context)
        .inflate(R.layout.itemfromfirebasenewbase, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyToyViewHolder holder, int position) {
        Glide.with(context)
                .load(ModelList.get(position).getImage())
                .into(holder.imageView);
        holder.txtPrice.setText(new StringBuilder("₽ ").append(ModelList.get(position).getPrice()));
        holder.txtName.setText(new StringBuilder().append(ModelList.get(position).getName()));

        holder.setListener((view, adapterPosition) -> {
            addToCart(ModelList.get(position));
        });
    }

    private void addToCart(Model model) {
        DatabaseReference userCart = FirebaseDatabase
                .getInstance()
                .getReference("Cart")
                .child("UNIQUE_USER_ID");

        userCart.child(model.getKey())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            CartModel cartModel = snapshot.getValue(CartModel.class);
                            cartModel.setQuantity(cartModel.getQuantity()+1);
                            Map<String, Object> updateData = new HashMap<>();
                            updateData.put("Количество", cartModel.getQuantity()+1);
                            updateData.put("Всего: ", cartModel.getQuantity()*Float.parseFloat(cartModel.getPrice()));

                            userCart.child(model.getKey())
                                    .updateChildren(updateData)
                                    .addOnSuccessListener(aVoid -> {
                                        iCartLoadListener.onCartFailed("Товар добавлен в корзину!");
                                    })
                            .addOnFailureListener(e -> iCartLoadListener.onCartFailed(e.getMessage()));
                        }
                        else
                        {
                            CartModel cartModel = new CartModel();
                            cartModel.setName(model.getName());
                            cartModel.setImage(model.getImage());
                            cartModel.setKey(model.getKey());
                            cartModel.setPrice(model.getPrice());
                            cartModel.setQuantity(1);
                            cartModel.setTotalPrice(Float.parseFloat(model.getPrice()));

                            userCart.child(model.getKey())
                                    .setValue(cartModel)
                                    .addOnSuccessListener(aVoid -> {
                                        iCartLoadListener.onCartFailed("Товар добавлен в корзину!");
                                    })
                                    .addOnFailureListener(e -> iCartLoadListener.onCartFailed(e.getMessage()));
                        }

                        EventBus.getDefault().postSticky(new MyUpdateCartEvent());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        iCartLoadListener.onCartFailed(error.getMessage());
                    }
                });
    }

    @Override
    public int getItemCount() {
        return ModelList.size();
    }

    public class MyToyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.img1)
        ImageView imageView;
        @BindView(R.id.txtName)
        TextView txtName;
        @BindView(R.id.txtPrice)
        TextView txtPrice;

        IRecyclerViewClickListener listener;

        public void setListener(IRecyclerViewClickListener listener) {
            this.listener = listener;
        }

        private Unbinder unbinder;

        public MyToyViewHolder(@NonNull View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onRecyclerClick(v, getAdapterPosition());
        }
    }
}
