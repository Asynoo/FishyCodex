package com.example.fishycodex;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        loadAddMenu();
        loadFishMenu();
    }

    public void loadAddMenu(){
        Button loadAdd = findViewById(R.id.openNewFishView);
        loadAdd.setOnClickListener(v -> {
            Intent loadAddPage = new Intent(Home.this,AddMenuActivity.class);
            startActivity(loadAddPage);});
    }

    public void loadFishMenu(){
        Button loadFish = findViewById(R.id.openFishView);
        loadFish.setOnClickListener(v -> {
            Intent loadAddPage = new Intent(Home.this,ShowFish.class);
            startActivity(loadAddPage);});
    }
}