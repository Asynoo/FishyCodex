package com.example.fishycodex;

import androidx.annotation.NonNull;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ShowFish extends ListActivity {

    FirebaseFirestore database = FirebaseFirestore.getInstance();
    ArrayList<String> listItems=new ArrayList<String>();
    ArrayAdapter<String> adapter;

    private Button reloadButton;
    private TextView yourFishyCodexBanner;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_show_fish);
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        setListAdapter(adapter);
        readDatabase();
        loadAddMenu();
        goBack();

    }

    public void readDatabase(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        database.collection(uid).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        listItems.add("\n" + "Species: " + document.getString("Species") + "\n" + "Location: " + document.getString("Location") + "\n" + "Size: " + document.getString("Size") + "\n" + "Date: " + document.getString("Date") + "\n");
                    }
                }
            }
        });
    }

    public void loadAddMenu(){
        reloadButton = findViewById(R.id.reloadListView);
        reloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.notifyDataSetChanged();
                readDatabase();
            }
        });
    }

    public void goBack(){
        yourFishyCodexBanner = findViewById(R.id.yourFishyCodexBanner);
        yourFishyCodexBanner.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent loadAddPage = new Intent(ShowFish.this, Home.class);
                startActivity(loadAddPage);
            }
        });
    }

}