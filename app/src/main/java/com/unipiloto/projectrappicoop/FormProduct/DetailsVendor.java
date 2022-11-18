package com.unipiloto.projectrappicoop.FormProduct;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.unipiloto.projectrappicoop.DataBase.ConexionSQLiteHelper;
import com.unipiloto.projectrappicoop.DataBase.Utilidades;
import com.unipiloto.projectrappicoop.Home.Home_vendor;
import com.unipiloto.projectrappicoop.Objects.Products;
import com.unipiloto.projectrappicoop.R;

public class DetailsVendor extends AppCompatActivity {

    ConexionSQLiteHelper con;
    EditText nombre, descripcion, valor, local, expedicion, categoria;

    Products prod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_vendor);

        con = new ConexionSQLiteHelper(this, "bd_rappicoop", null, 1);

        nombre = findViewById(R.id.viewNombre);
        descripcion = findViewById(R.id.viewDescripcion);
        valor = findViewById(R.id.viewValor);
        local = findViewById(R.id.viewLocal);
        expedicion = findViewById(R.id.viewExpedicion);
        categoria = findViewById(R.id.viewCategoria);

        Bundle objSend = getIntent().getExtras();
        prod = null;

        if (objSend != null){
            prod = (Products) objSend.getSerializable("producto");
            nombre.setText(prod.getNombre().toString());
            descripcion.setText(prod.getDescripcion().toString());
            valor.setText( "$ " + prod.getValor().toString());
            local.setText(prod.getLocal().toString());
            expedicion.setText(prod.getExpedicion().toString());
        }
    }

    public void updateProduct(View view) {
        SQLiteDatabase db = con.getWritableDatabase();

        String[] param = {prod.getNombre().toString()};

        ContentValues values = new ContentValues();
        values.put(Utilidades.PRO_NOMBRE, nombre.getText().toString());
        values.put(Utilidades.PRO_DESCRIPCION, descripcion.getText().toString());
        values.put(Utilidades.PRO_VALOR, valor.getText().toString());
        values.put(Utilidades.PRO_LOCAL, local.getText().toString());
        values.put(Utilidades.PRO_EXPEDICION, expedicion.getText().toString());

        db.update(Utilidades.TABLA_PRODUCTO, values, Utilidades.PRO_NOMBRE + " = ?", param);

        Toast.makeText(this, "Actualizacion Exitosa", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, Home_vendor.class);

        startActivity(intent);
        db.close();
    }

    public void deleteProdruct(View view) {
        SQLiteDatabase db = con.getWritableDatabase();

        String[] param = {nombre.getText().toString()};

        db.delete(Utilidades.TABLA_PRODUCTO, Utilidades.PRO_NOMBRE + " = ?", param);

        Toast.makeText(this, "Producto Eliminado Exitosa", Toast.LENGTH_LONG).show();
        db.close();
        nombre.setText("");
        descripcion.setText("");
        valor.setText("");
        local.setText("");
        expedicion.setText("");
    }
}