package com.unipiloto.projectrappicoop.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.unipiloto.projectrappicoop.FormOrder.FormCreateOrder;
import com.unipiloto.projectrappicoop.FormProduct.FormSearchProductClient;
import com.unipiloto.projectrappicoop.FormProduct.FormSearchProductVendor;
import com.unipiloto.projectrappicoop.R;

public class Home_client extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_client);
    }

    public void searchProdruct(View view) {
        Intent intent = new Intent(this, FormSearchProductClient.class);
        startActivity(intent);
    }
}