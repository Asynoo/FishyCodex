package com.example.fishycodex;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class AddMenuActivity extends AppCompatActivity {

    FirebaseFirestore database = FirebaseFirestore.getInstance();
    Button AddButton;
    private TextView addCatchBanner;
    DatePickerDialog picker;
    EditText eText;
    String CalenderData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_menu);
        eText=(EditText) findViewById(R.id.dateID);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(AddMenuActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
        addNewCatch();
        goBack();
        getUser();
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

    public void getUser(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();
            String uid = user.getUid();
            Log.d(name, email);
        }

    }

    public void fishDataWrite() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
        Map<String, Object> Fish = new HashMap<>();


        EditText speciesValue = (EditText) findViewById(R.id.speciesID);
        Fish.put("Species", speciesValue.getText().toString());

        EditText locationValue = (EditText) findViewById(R.id.locationID);
        Fish.put("Location", locationValue.getText().toString());

        EditText sizeValue = (EditText) findViewById(R.id.sizeID);
        Fish.put("Size", sizeValue.getText().toString());

        EditText dateValue = (EditText) findViewById(R.id.dateID);
        Fish.put("Date", dateValue.getText().toString());

        database.collection(uid).add(Fish);
    }
}