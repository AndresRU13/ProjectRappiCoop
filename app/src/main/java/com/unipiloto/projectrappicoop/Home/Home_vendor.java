package com.unipiloto.projectrappicoop.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.unipiloto.projectrappicoop.FormProduct.FormAddProduct;
import com.unipiloto.projectrappicoop.FormProduct.FormDeleteProduct;
import com.unipiloto.projectrappicoop.FormProduct.FormSearchProductVendor;
import com.unipiloto.projectrappicoop.FormUser.FormLogin;
import com.unipiloto.projectrappicoop.R;

public class Home_vendor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_vendor);

        Toolbar toolbar = findViewById(R.id.tool);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_AddProduct:
                Intent intent = new Intent(this, FormAddProduct.class);
                startActivity(intent);
                return true;

            case R.id.action_delete:
                Intent i = new Intent(this, FormDeleteProduct.class);
                startActivity(i);
                return true;

            case R.id.action_Return:
                Intent intent1 = new Intent(this, FormLogin.class);
                startActivity(intent1);
                return true;

            case R.id.action_Search:
                Intent intent2 = new Intent(this, FormSearchProductVendor.class);
                startActivity(intent2);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void searchProdruct(View view) {
        Intent intent = new Intent(this, FormSearchProductVendor.class);
        startActivity(intent);
    }
}