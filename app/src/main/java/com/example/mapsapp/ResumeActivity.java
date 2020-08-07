package com.example.mapsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResumeActivity extends AppCompatActivity {

    private String name;
    private String age;
    private String address;
    private String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);

        getIntentValues();

        TextView txtInformation = findViewById(R.id.txt_information);
        txtInformation.setText(name + ", " + age + "\n" + address + ", " + city);

        Button btnShowAddress = findViewById(R.id.btn_show_address);
        btnShowAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMap();
            }
        });
    }

    private void openMap() {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + address + ", "+ city);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }

    private void getIntentValues() {
        name = getIntent().getStringExtra("USER_NAME");
        age = getIntent().getStringExtra("AGE");
        address = getIntent().getStringExtra("ADDRESS");
        city = getIntent().getStringExtra("CITY");
    }
}