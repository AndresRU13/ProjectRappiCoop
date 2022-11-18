package com.unipiloto.projectrappicoop.FormOrder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.unipiloto.projectrappicoop.Adaptadores.AdaptadorCarroCompras;
import com.unipiloto.projectrappicoop.DataBase.ConexionSQLiteHelper;
import com.unipiloto.projectrappicoop.DataBase.Utilidades;
import com.unipiloto.projectrappicoop.Home.Home_vendor;
import com.unipiloto.projectrappicoop.Objects.Products;
import com.unipiloto.projectrappicoop.R;

import java.util.List;

public class FormRegisterOrder extends AppCompatActivity {

    List<Products> listaCompra;
    AdaptadorCarroCompras adaptador;
    EditText editNameClient, editLocation, editProvider, editDate, editProdructs, editValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_register_order);

        listaCompra = (List<Products>) getIntent().getSerializableExtra("compra");

        editNameClient = findViewById(R.id.editNameClient);
        editLocation = findViewById(R.id.editLocation);
        editProvider = findViewById(R.id.editProvider);
        editDate = findViewById(R.id.editFecha);
        editProdructs = findViewById(R.id.viewProducts);
        editValue = findViewById(R.id.viewValue);

        adaptador = new AdaptadorCarroCompras(this, listaCompra, editValue, editProdructs, editProvider);

    }

    public void addOrder(View view) {
        ConexionSQLiteHelper con = new ConexionSQLiteHelper(this, "bd_rappicoop", null, 1);

        SQLiteDatabase db = con.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilidades.ORD_USUARIO, editNameClient.getText().toString());
        values.put(Utilidades.ORD_DIRECCION, editLocation.getText().toString());
        values.put(Utilidades.ORD_COMPRA, editProdructs.getText().toString());
        values.put(Utilidades.ORD_VALOR, editValue.getText().toString());
        values.put(Utilidades.ORD_PROVEEDOR, editProvider.getText().toString());
        values.put(Utilidades.ORD_FECHA_ENTREGA, editDate.getText().toString());

        long res = db.insert(Utilidades.TABLA_ORDEN, null, values);
        if (res == -1)
            Toast.makeText(this, "Pedido NO Registrado", Toast.LENGTH_LONG).show();
        else {
            Toast.makeText(this, "Pedido Resgistrado con EXITO", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, Home_vendor.class);
            startActivity(i);
        }
    }
}

