package com.example.fishycodex;

import androidx.appcompat.app.AppCompatActivity;
import com.example.fishycodex.MainActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.os.Bundle;

public class AddMenuActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://fishy-codex-default-rtdb.europe-west1.firebasedatabase.app/");


    Button AddButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_menu);
        addNewCatch();
    }

    public void addNewCatch(){
        AddButton = (Button) findViewById(R.id.addCatchID);
        AddButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                fishDataWrite();
                Intent loadAddPage = new Intent(AddMenuActivity.this,MainActivity.class);
                startActivity(loadAddPage);
            }
        });
    }

    public void fishDataWrite(){
        speciesMethod();
        locationMethod();
        sizeMethod();
        dateMethod();
    }

    private void speciesMethod(){
        EditText speciesValue = (EditText)findViewById(R.id.speciesID);
        DatabaseReference speciesFish = database.getReference("Species");
        speciesFish.setValue(speciesValue.getText().toString());
    }

    private void locationMethod(){
        EditText locationValue = (EditText)findViewById(R.id.locationID);
        DatabaseReference locationFish = database.getReference("Location");
        locationFish.setValue(locationValue.getText().toString());
    }

    private void sizeMethod(){
        EditText sizeValue = (EditText)findViewById(R.id.sizeID);
        DatabaseReference sizeFish = database.getReference("Size");
        sizeFish.setValue(sizeValue.getText().toString());
    }

    private void dateMethod(){
        EditText dateValue = (EditText)findViewById(R.id.dateID);
        DatabaseReference dateFish = database.getReference("Date");
        dateFish.setValue(dateValue.getText().toString());
    }

}