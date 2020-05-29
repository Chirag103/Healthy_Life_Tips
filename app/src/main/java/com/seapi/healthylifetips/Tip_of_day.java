package com.seapi.healthylifetips;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Tip_of_day extends AppCompatActivity {

    ImageView tip_Image;
    TextView tip_Data;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,mainReference,childReference,tipRef;
    public String tipDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_of_day);

        tip_Data=findViewById(R.id.tip_data);
        tip_Image=findViewById(R.id.tip_imsge);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("TipsData");

        mainReference=databaseReference.child("tip301");
        childReference=mainReference.child("tip_url");
        tipRef=mainReference.child("tip");

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
                intent.putExtra(Intent.EXTRA_TEXT,tipDB + "\n Healthy Life Tips in (Hindi)\n https://play.google.com/store/apps/details?id=com.seapi.healthylifetips");
                startActivity(Intent.createChooser(intent,"Via share"));
                return true;
            case R.id.dis_menu:
                startActivity(new Intent(Tip_of_day.this, Disclaimer.class));
                finish();
                return true;
            case R.id.abtus_menu:
                startActivity(new Intent(Tip_of_day.this, AboutUS.class));
                finish();
                return true;

            default: return super.onOptionsItemSelected(item);
        }

    }
}
