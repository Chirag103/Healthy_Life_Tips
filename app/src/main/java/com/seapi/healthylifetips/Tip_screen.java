package com.seapi.healthylifetips;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
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

public class Tip_screen extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;

    ImageView tip_Image;
    TextView tip_Data;
    Button back_BT,next_BT;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,mainReference,childReference,tipRef;
    public int tipPositon=0,tipCounter=0;
    public String tipstr="";
    public String tipDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_screen);

        intAdFun();

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("TipsData");

        setCategory_fun();

        tip_Data=findViewById(R.id.tip_data);
        tip_Image=findViewById(R.id.tip_imsge);
        back_BT=findViewById(R.id.back_bt);
        next_BT=findViewById(R.id.next_bt);

        mainReference=databaseReference.child(tipstr);
        childReference=mainReference.child("tip_url");
        tipRef=mainReference.child("tip");


        back_BT.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {

                                           if(tipCounter>1)
                                           {
                                               String s;
                                               tipCounter=tipCounter - 1;
                                               tipPositon=tipPositon-1;
                                               s=String.valueOf(tipPositon);
                                               tipstr="tip"+s;
                                               mainReference=databaseReference.child(tipstr);
                                               childReference=mainReference.child("tip_url");
                                               tipRef=mainReference.child("tip");
                                               if(tipCounter==5)
                                               {
                                                   showIntAd();
                                               }
                                               onStart();
                                           }

                                       }
                                   }
        );

        next_BT.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           if(tipCounter<10)
                                           {
                                               String s;
                                               tipCounter=tipCounter + 1;
                                               tipPositon=tipPositon+1;
                                               s=String.valueOf(tipPositon);
                                               tipstr="tip"+s;
                                               mainReference=databaseReference.child(tipstr);
                                               childReference=mainReference.child("tip_url");
                                               tipRef=mainReference.child("tip");
                                               if(tipCounter==5)
                                               {
                                                   showIntAd();
                                               }
                                               onStart();
                                           }

                                       }
                                   }
        );
    }

    public void intAdFun() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-2997250392892049/8402152288");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

public void showIntAd()
{
    if (mInterstitialAd.isLoaded()) {
        mInterstitialAd.show();
       // Toast.makeText(Tip_screen.this,"Welcome....",Toast.LENGTH_SHORT).show();
    }
}


    @Override
    protected void onStart() {
        super.onStart();
        childReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String srt=dataSnapshot.getValue(String.class);
                Picasso.get().load(srt).into(tip_Image);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        tipRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String s1=dataSnapshot.getValue().toString();
                tip_Data.setText(s1);
                tipDB=s1;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void setCategory_fun()
    {
        Intent intent=getIntent();
        String cat_pos =intent.getStringExtra("bt_postion");



        if(cat_pos.equals("1"))
        { tipstr="tip101"; tipPositon=101;}
        else if(cat_pos.equals("2"))
        { tipstr="tip201"; tipPositon=201;}
        else if(cat_pos.equals("3"))
        { tipstr="tip601"; tipPositon=601;}
        else if(cat_pos.equals("4"))
        { tipstr="tip701"; tipPositon=701;}
        else if(cat_pos.equals("5"))
        { tipstr="tip801"; tipPositon=801;}
        else if(cat_pos.equals("6"))
        { tipstr="tip901"; tipPositon=901;}
        else if(cat_pos.equals("7"))
        { tipstr="tip1001"; tipPositon=1001;}
        else if(cat_pos.equals("8"))
        { tipstr="tip1101"; tipPositon=1101;}
        else if(cat_pos.equals("9"))
        { tipstr="tip1201"; tipPositon=1201;}
        tipCounter=1;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.colon_menu, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share_menu:
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,tipDB + "\n\n Healthy Life Tips in (Hindi)\n https://play.google.com/store/apps/details?id=com.seapi.healthylifetips");
                startActivity(Intent.createChooser(intent,"Via share"));
                return true;
            case R.id.dis_menu:
                startActivity(new Intent(Tip_screen.this, Disclaimer.class));
                finish();
                return true;
            case R.id.abtus_menu:
                startActivity(new Intent(Tip_screen.this, AboutUS.class));
                finish();
                return true;

            default: return super.onOptionsItemSelected(item);
        }

    }
}
