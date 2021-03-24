package com.example.fishycodex;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    //FirebaseDatabase database = FirebaseDatabase.getInstance("https://fishy-codex-default-rtdb.europe-west1.firebasedatabase.app/");
    ListView fishView;
    Button Addbutton;
    //EditText GetValue;
    String[] ListElements = new String[] {

    };
/*
    public void database(){
        DatabaseReference speciesFish = database.getReference("Species");
        speciesFish.setValue("Rainbow Trout");

        DatabaseReference locationFish = database.getReference("Location");
        locationFish.setValue("Henne Put and Take");

        DatabaseReference sizeFish = database.getReference("Size");
        sizeFish.setValue("3kg");

        DatabaseReference dateFish = database.getReference("Date");
        dateFish.setValue("15.3.2021");


        locationFish.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
    */

    public void init(){
        fishView = (ListView) findViewById(R.id.fishListView);

        final List< String > ListElementsArrayList = new ArrayList < String >
                (Arrays.asList(ListElements));


        final ArrayAdapter < String > adapter = new ArrayAdapter < String >
                (MainActivity.this, android.R.layout.simple_list_item_1,
                        ListElementsArrayList);
        Addbutton = (Button) findViewById(R.id.openNewFishView);
        Addbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent loadAddPage = new Intent(MainActivity.this,AddMenuActivity.class);
                startActivity(loadAddPage);
            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
}

/*
        listview = (ListView) findViewById(R.id.listView1);
        Addbutton = (Button) findViewById(R.id.button1);
        GetValue = (EditText) findViewById(R.id.editText1);

        final List< String > ListElementsArrayList = new ArrayList < String >
                (Arrays.asList(ListElements));


        final ArrayAdapter < String > adapter = new ArrayAdapter < String >
                (MainActivity.this, android.R.layout.simple_list_item_1,
                        ListElementsArrayList);

        listview.setAdapter(adapter);

        Addbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //DatabaseReference speciesFish = database.getReference("Species");
                //ListElementsArrayList.add(GetValue.getText().toString());
                //speciesFish.setValue(GetValue.getText().toString());
                //adapter.notifyDataSetChanged();

            }
        });
 */