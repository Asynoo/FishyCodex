package com.example.fishycodex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class AddMenuActivity extends AppCompatActivity {

    FirebaseFirestore database = FirebaseFirestore.getInstance();
    Button AddButton;
    private TextView addCatchBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_menu);
        addNewCatch();
        goBack();
    }

    public void goBack(){
        addCatchBanner = (TextView) findViewById(R.id.addCatchBanner);
        addCatchBanner.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent loadAddPage = new Intent(AddMenuActivity.this, Home.class);
                startActivity(loadAddPage);
            }
        });
    }

    public void addNewCatch() {
        AddButton = (Button) findViewById(R.id.addCatchID);
        AddButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                fishDataWrite();
                Intent loadAddPage = new Intent(AddMenuActivity.this, Home.class);
                startActivity(loadAddPage);
            }
        });
    }


    public void fishDataWrite() {
        Map<String, Object> Fish = new HashMap<>();

        EditText speciesValue = (EditText) findViewById(R.id.speciesID);
        Fish.put("Species", speciesValue.getText().toString());

        EditText locationValue = (EditText) findViewById(R.id.locationID);
        Fish.put("Location", locationValue.getText().toString());

        EditText sizeValue = (EditText) findViewById(R.id.sizeID);
        Fish.put("Size", sizeValue.getText().toString());

        EditText dateValue = (EditText) findViewById(R.id.dateID);
        Fish.put("Date", dateValue.getText().toString());

        database.collection("Fish").add(Fish);
    }

    /*
    public void speciesMethod() {
        EditText speciesValue = (EditText) findViewById(R.id.speciesID);
        Map<String, Object> Fish = new HashMap<>();
        Fish.put("Species", speciesValue.getText().toString());
        database.collection("Fish").add(Fish);
    }

    public void locationMethod() {
        EditText locationValue = (EditText) findViewById(R.id.locationID);
        Map<String, Object> Fish = new HashMap<>();
        Fish.put("Location", locationValue.getText().toString());
        database.collection("Fish").add(Fish);


    }

    public void sizeMethod() {
        EditText sizeValue = (EditText) findViewById(R.id.sizeID);
        Map<String, Object> Fish = new HashMap<>();
        Fish.put("Size", sizeValue.getText().toString());
        database.collection("Fish").add(Fish);
    }

    public void dateMethod() {
        EditText dateValue = (EditText) findViewById(R.id.dateID);
        Map<String, Object> Fish = new HashMap<>();
        Fish.put("Date", dateValue.getText().toString());
        database.collection("Fish").add(Fish);
    }
    */

}