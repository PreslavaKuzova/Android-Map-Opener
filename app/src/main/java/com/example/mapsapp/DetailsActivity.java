package com.example.mapsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetailsActivity extends AppCompatActivity {

    private Button btnContinue;
    private EditText edtAge;
    private EditText edtAddress;
    private EditText edtCity;
    private EditText edtBirthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initViews();
        btnContinue.setEnabled(false);
        setOnTextChangedListeners();

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startResumeScreen();
            }
        });
    }

    private void setOnTextChangedListeners() {
        edtAge.addTextChangedListener(createTextWatcher());
        edtAddress.addTextChangedListener(createTextWatcher());
        edtCity.addTextChangedListener(createTextWatcher());
        edtBirthday.addTextChangedListener(createTextWatcher());
    }

    private void initViews() {
        btnContinue = findViewById(R.id.btn_continue);
        edtAge = findViewById(R.id.edt_age);
        edtAddress = findViewById(R.id.edt_address);
        edtCity = findViewById(R.id.edt_city);
        edtBirthday = findViewById(R.id.edt_date_of_birth);
    }

    private TextWatcher createTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (checkRequiredFields()) {
                    btnContinue.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        };
    }

    private boolean checkRequiredFields() {
        return !edtAge.getText().toString().isEmpty()
                && !edtAddress.getText().toString().isEmpty()
                && !edtCity.getText().toString().isEmpty()
                && !edtBirthday.getText().toString().isEmpty();
    }

    private void startResumeScreen() {
        String name = getIntent().getStringExtra("USER_NAME");
        Intent resumeActIntent = new Intent(this, ResumeActivity.class);
        resumeActIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        resumeActIntent.putExtra("USER_NAME", name);
        resumeActIntent.putExtra("AGE", edtAge.getText().toString());
        resumeActIntent.putExtra("ADDRESS", edtAddress.getText().toString());
        resumeActIntent.putExtra("CITY", edtCity.getText().toString());
        resumeActIntent.putExtra("BIRTHDAY", edtBirthday.getText().toString());
        startActivity(resumeActIntent);
    }
}