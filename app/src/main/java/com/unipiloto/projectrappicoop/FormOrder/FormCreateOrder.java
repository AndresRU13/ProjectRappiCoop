package com.unipiloto.projectrappicoop.FormOrder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

import com.unipiloto.projectrappicoop.R;

public class FormCreateOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_create_order);

        Toolbar toolbar = findViewById(R.id.tool);
        setSupportActionBar(toolbar);
    }

    public void addOrder(View view) {
    }
}