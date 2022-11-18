package com.unipiloto.projectrappicoop.FormProduct;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.unipiloto.projectrappicoop.DataBase.ConexionSQLiteHelper;
import com.unipiloto.projectrappicoop.DataBase.Utilidades;
import com.unipiloto.projectrappicoop.FormOrder.FormCreateOrder;
import com.unipiloto.projectrappicoop.Objects.Products;
import com.unipiloto.projectrappicoop.R;

import java.util.ArrayList;

public class DetailsClient extends AppCompatActivity {

    ConexionSQLiteHelper con;
    EditText nombre, descripcion, valor, local, expedicion, categoria;

    Products prod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_client);

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
            categoria.setText(prod.getCategoria().toString());
        }
    }
}