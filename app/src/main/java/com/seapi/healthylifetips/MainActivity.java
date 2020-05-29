package com.seapi.healthylifetips;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.app.NotificationChannel;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button tipofthedayBt, wight_loss_Bt, hair_fall_Bt, wight_gain_Bt, beauty_Bt, teeth_Bt, digestion_Bt, yoga_Bt,stress_relieve_bt;
    String i = "0";

    public AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        tipofthedayBt = findViewById(R.id.tipoftheday_bt);
        wight_loss_Bt = findViewById(R.id.wight_loss_bt);
        hair_fall_Bt = findViewById(R.id.hair_fall_bt);
        wight_gain_Bt = findViewById(R.id.wight_gain_bt);
        beauty_Bt = findViewById(R.id.beauty_bt);
        teeth_Bt = findViewById(R.id.teeth_bt);
        digestion_Bt = findViewById(R.id.digestion_bt);
        yoga_Bt = findViewById(R.id.yoga_bt);
        stress_relieve_bt= findViewById(R.id.stress_relief_bt);



        tipofthedayBt.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View view) {
                                                 startActivity(new Intent(MainActivity.this, Tip_of_day.class));
                                             }
                                         }
        );


        wight_loss_Bt.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View view) {
                                                 Intent intent = new Intent(MainActivity.this, Tip_screen.class);
                                                 i = "1";
                                                 intent.putExtra("bt_postion", i);
                                                 startActivity(intent);
                                             }
                                         }
        );
        hair_fall_Bt.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Intent intent = new Intent(MainActivity.this, Tip_screen.class);
                                                i = "2";
                                                intent.putExtra("bt_postion", i);
                                                startActivity(intent);
                                            }
                                        }
        );
        wight_gain_Bt.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View view) {
                                                 Intent intent = new Intent(MainActivity.this, Tip_screen.class);
                                                 i = "3";
                                                 intent.putExtra("bt_postion", i);
                                                 startActivity(intent);
                                             }
                                         }
        );
        beauty_Bt.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             Intent intent = new Intent(MainActivity.this, Tip_screen.class);
                                             i = "4";
                                             intent.putExtra("bt_postion", i);
                                             startActivity(intent);
                                         }
                                     }
        );
        teeth_Bt.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent intent = new Intent(MainActivity.this, Tip_screen.class);
                                            i = "5";
                                            intent.putExtra("bt_postion", i);
                                            startActivity(intent);
                                        }
                                    }
        );
        digestion_Bt.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Intent intent = new Intent(MainActivity.this, Tip_screen.class);
                                                i = "6";
                                                intent.putExtra("bt_postion", i);
                                                startActivity(intent);
                                            }
                                        }
        );
        yoga_Bt.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           Intent intent = new Intent(MainActivity.this, Tip_screen.class);
                                           i = "7";
                                           intent.putExtra("bt_postion", i);
                                           startActivity(intent);
                                       }
                                   }
        );
        stress_relieve_bt.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View view) {
                                                     Intent intent = new Intent(MainActivity.this, Tip_screen.class);
                                                     i = "8";
                                                     intent.putExtra("bt_postion", i);
                                                     startActivity(intent);
                                                 }
                                             }
        );



        banAdFun();
    }


    public void banAdFun()
    {
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-2997250392892049/4514192716");

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.banadView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
}

