package com.example.fishycodex;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
    DatePickerDialog picker;
    EditText eText;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_menu);
        eText= findViewById(R.id.dateID);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(v -> {
            final Calendar cldr = Calendar.getInstance();
            int day = cldr.get(Calendar.DAY_OF_MONTH);
            int month = cldr.get(Calendar.MONTH);
            int year = cldr.get(Calendar.YEAR);

            picker = new DatePickerDialog(AddMenuActivity.this,
                    (view, year1, monthOfYear, dayOfMonth) -> eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1), year, month, day);
            picker.show();
        });
        addNewCatch();
        goBack();
        getUser();
        setupSpeciesSpinner();
        setupLocationsSpinner();
        setupSizeSpinner();
    }

    public void goBack(){
        TextView addCatchBanner = findViewById(R.id.addCatchBanner);
        addCatchBanner.setOnClickListener(v -> {
            Intent loadAddPage = new Intent(AddMenuActivity.this, Home.class);
            startActivity(loadAddPage);
        });
    }

    public void addNewCatch() {
        AddButton = findViewById(R.id.addCatchID);
        AddButton.setOnClickListener(v -> {
            fishDataWrite();
            Intent loadAddPage = new Intent(AddMenuActivity.this, Home.class);
            startActivity(loadAddPage);
        });
    }

    public void getUser(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();
            Log.d(name, email);
        }
    }

    public void setupSpeciesSpinner(){
        Spinner spinner = findViewById(R.id.speciesID);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.species_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void setupLocationsSpinner(){
        Spinner spinner = findViewById(R.id.locationID);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.locations_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void setupSizeSpinner(){
        Spinner spinner = findViewById(R.id.sizeID);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sizes_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void fishDataWrite() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        String uid = user.getUid();
        Map<String, Object> Fish = new HashMap<>();

        Spinner speciesValue = findViewById(R.id.speciesID);
        Fish.put("Species", speciesValue.getSelectedItem().toString());

        Spinner locationValue = findViewById(R.id.locationID);
        Fish.put("Location", locationValue.getSelectedItem().toString());

        Spinner sizeValue = findViewById(R.id.sizeID);
        Fish.put("Size", sizeValue.getSelectedItem().toString());

        EditText dateValue = findViewById(R.id.dateID);
        Fish.put("Date", dateValue.getText().toString());

        database.collection(uid).add(Fish);
    }
}