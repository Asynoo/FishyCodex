package com.example.fishycodex;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class ShowFish extends ListActivity {

    FirebaseFirestore database = FirebaseFirestore.getInstance();
    ArrayList<String> listItems= new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_show_fish);
        adapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
        setListAdapter(adapter);
        readDatabase();
        loadAddMenu();
        goBack();

    }

    public void readDatabase(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user != null ? user.getUid() : null;
        assert uid != null;
        database.collection(uid).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                listItems.clear();
                for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                    listItems.add("\n" + "Species: " + document.getString("Species") + "\n" + "Location: " + document.getString("Location") + "\n" + "Size: " + document.getString("Size") + "\n" + "Date: " + document.getString("Date") + "\n");
                }
            }
        });
    }

    public void loadAddMenu(){
        Button reloadButton = findViewById(R.id.reloadListView);
        reloadButton.setOnClickListener(v -> {
            adapter.notifyDataSetChanged();
            readDatabase();
        });
    }

    public void goBack(){
        TextView yourFishyCodexBanner = findViewById(R.id.yourFishyCodexBanner);
        yourFishyCodexBanner.setOnClickListener(v -> {
            Intent loadAddPage = new Intent(ShowFish.this, Home.class);
            startActivity(loadAddPage);
        });
    }

}