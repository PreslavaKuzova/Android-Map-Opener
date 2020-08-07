package com.example.mapsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setEnabled(false);

        EditText edtName = findViewById(R.id.edt_name);
        edtName.addTextChangedListener(createTextWatcher());

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDetailsScreen();
            }
        });
    }

    private TextWatcher createTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (checkRequiredFields()) {
                    btnLogin.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        };
    }

    private boolean checkRequiredFields() {
        return !((EditText) findViewById(R.id.edt_name)).getText().toString().isEmpty();

    }

    private void startDetailsScreen() {
        EditText edtName = findViewById(R.id.edt_name);
        Intent detailsActIntent = new Intent(this, DetailsActivity.class);
        detailsActIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        detailsActIntent.putExtra("USER_NAME", edtName.getText().toString());
        startActivity(detailsActIntent);
    }

}