package com.unipiloto.projectrappicoop.FormProduct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.unipiloto.projectrappicoop.DataBase.ConexionSQLiteHelper;
import com.unipiloto.projectrappicoop.DataBase.Utilidades;
import com.unipiloto.projectrappicoop.Home.Home_vendor;
import com.unipiloto.projectrappicoop.R;

public class FormDeleteProduct extends AppCompatActivity {

    EditText idP;
    TextView products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_delete_product);

        idP = (EditText) findViewById(R.id.dIdProduct);
        products = (TextView) findViewById(R.id.products);

        Toolbar toolbar = findViewById(R.id.tool);
        setSupportActionBar(toolbar);
    }

    public void deleteProdruct(View view) {
        String name = idP.getText().toString();
        Integer checkP = deleteProduct(name);
        if (checkP > 0) {
            Toast.makeText(this, "Producto Eliminado", Toast.LENGTH_LONG).show();
            Cursor res = getAllProduct();
            if (res.getCount() == 0) {
                products.setText("No Existen Productos");
                return;
            }
        }else
            Toast.makeText(this, "Producto NO Existe", Toast.LENGTH_LONG).show();
    }

    public Integer deleteProduct(String name){
        ConexionSQLiteHelper con = new ConexionSQLiteHelper(this, "bd_rappicoop", null, 1);

        SQLiteDatabase db = con.getWritableDatabase();

        return db.delete(Utilidades.TABLA_PRODUCTO, " " + Utilidades.PRO_NOMBRE + " = ?", new String[] {name});
    }

    public Cursor getAllProduct(){
        ConexionSQLiteHelper con = new ConexionSQLiteHelper(this, "bd_rappicoop", null, 1);

        SQLiteDatabase db = con.getWritableDatabase();

        Cursor res = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_PRODUCTO, null);
        return res;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_Return:
                Intent intent = new Intent(this, Home_vendor.class);
                startActivity(intent);
                return true;
            case R.id.action_AddProduct:
                Intent i = new Intent(this, FormAddProduct.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}