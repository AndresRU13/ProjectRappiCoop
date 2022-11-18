package com.unipiloto.projectrappicoop.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.unipiloto.projectrappicoop.FormOrder.FormSearchOrder;
import com.unipiloto.projectrappicoop.R;

public class Home_repartidor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_repartidor);
    }

    public void listaPedidos(View view) {
        Intent intent = new Intent(this, FormSearchOrder.class);
        startActivity(intent);
    }
}