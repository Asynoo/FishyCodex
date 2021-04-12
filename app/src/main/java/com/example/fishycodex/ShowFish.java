package com.example.fishycodex;

import androidx.annotation.NonNull;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
        database.collection("Fish").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        listItems.add(String.valueOf("\n"+"Species: "+document.getString("Species"))+"\n"+String.valueOf("Location: "+document.getString("Location"))+"\n"+String.valueOf("Size: "+document.getString("Size"))+"\n"+String.valueOf("Date: "+document.getString("Date")+"\n"));
                    }
                }
            }
        });
    }

    public void loadAddMenu(){
        reloadButton = (Button) findViewById(R.id.reloadListView);
        reloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.notifyDataSetChanged();
                readDatabase();
            }
        });
    }

    public void goBack(){
        yourFishyCodexBanner = (TextView) findViewById(R.id.yourFishyCodexBanner);
        yourFishyCodexBanner.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent loadAddPage = new Intent(ShowFish.this, Home.class);
                startActivity(loadAddPage);
            }
        });
    }

}