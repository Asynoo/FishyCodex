package com.example.fishycodex;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class MainActivity extends ListActivity {

    FirebaseFirestore database = FirebaseFirestore.getInstance();
    Button Addbutton;
    ArrayList<String> listItems=new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        setListAdapter(adapter);
        loadAddMenu();
        readDatabase();
    }

    public void readDatabase(){
        database.collection("Fish").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        //listItems.add(document.getId() + document.getData());
                        listItems.add(String.valueOf(document.getData()));
                        Log.d(TAG, document.getId() + document.getData());
                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });
    }

    public void loadAddMenu(){
        Addbutton = (Button) findViewById(R.id.openNewFishView);
        Addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadAddPage = new Intent(MainActivity.this,AddMenuActivity.class);
                startActivity(loadAddPage);}});
    }

    public void reloadListView(View v){
        adapter.notifyDataSetChanged();
        readDatabase();
    }
}