package com.example.babystore;

import static android.os.Build.VERSION.SDK_INT;

import android.content.Intent;
import android.graphics.ImageDecoder;
import android.graphics.drawable.AnimatedImageDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.babystore.Auth.Auth;
import com.example.babystore.Auth.User;
import com.example.babystore.Svyaz.Svyaz11;
import com.example.babystore.Svyaz.Svyaz12;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.io.IOException;



public class Profile extends Fragment {

    ImageView videoPlayer;
    Button exit;
    TextView name1, nameprofilefirebase, telprofilefirebase;
    ImageView photoprofile;
    RelativeLayout svyaz1, svyaz2;

    public static String PACKAGE_NAME;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        name1 = (TextView) view.findViewById(R.id.name);
        nameprofilefirebase = (TextView) view.findViewById(R.id.nameprofilefirebase);
        telprofilefirebase = (TextView) view.findViewById(R.id.telprofilefirebase);
        photoprofile = (ImageView) view.findViewById(R.id.photoprofile);
        exit = (Button) view.findViewById(R.id.exit);
        svyaz1 = view.findViewById(R.id.svyaz1);
        svyaz2 = view.findViewById(R.id.svyaz2);
        checkname();
        checktel();
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(new Intent(getActivity(), Auth.class));
                startActivity(i);
            }
        });

        svyaz1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Svyaz11.class);
                startActivity(intent);
            }
        });

        svyaz2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Svyaz12.class);
                startActivity(intent);
            }
        });

        photoprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoPlayer = view.findViewById(R.id.videoPlayer);
                Glide.with(Profile.this)
                        .asGif()
                        .load(R.raw.znew1918)
                        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                        .listener(new RequestListener<GifDrawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable @org.jetbrains.annotations.Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                                resource.setLoopCount(1);
                                return false;
                            }
                        })
                        .into(videoPlayer);

                videoPlayer.setVisibility(View.VISIBLE);
            }
        });
        return view;
    }


    public void checkname() {
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usuariosRef = rootRef.child("Пользователи");
        usuariosRef.child(uid).child("name").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                nameprofilefirebase.setText(String.valueOf(task.getResult().getValue()));
            }
        });
    }

    public void checktel() {
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usuariosRef = rootRef.child("Пользователи");
        usuariosRef.child(uid).child("phone").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                telprofilefirebase.setText(String.valueOf(task.getResult().getValue()));
            }
        });
    }
}