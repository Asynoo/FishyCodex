package com.example.fishycodex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    private Button loadAdd, loadFish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        loadAddMenu();
        loadFishMenu();
    }

    public void loadAddMenu(){
        loadAdd = (Button) findViewById(R.id.openNewFishView);
        loadAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadAddPage = new Intent(Home.this,AddMenuActivity.class);
                startActivity(loadAddPage);}});
    }

    public void loadFishMenu(){
        loadFish = (Button) findViewById(R.id.openFishView);
        loadFish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadAddPage = new Intent(Home.this,ShowFish.class);
                startActivity(loadAddPage);}});
    }
}