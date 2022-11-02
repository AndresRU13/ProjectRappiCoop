package com.unipiloto.projectrappicoop.FormProduct;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.unipiloto.projectrappicoop.DataBase.ConexionSQLiteHelper;
import com.unipiloto.projectrappicoop.DataBase.Utilidades;
import com.unipiloto.projectrappicoop.Objects.Products;
import com.unipiloto.projectrappicoop.R;

public class Details extends AppCompatActivity {

    EditText nombre, descripcion, valor, local, expedicion, categoria;
    FloatingActionButton actualizar;

    public static final String EXTRA = "productId";

    Products products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        nombre = findViewById(R.id.viewNombre);
        descripcion = findViewById(R.id.viewDescripcion);
        valor = findViewById(R.id.viewValor);
        local = findViewById(R.id.viewLocal);
        expedicion = findViewById(R.id.viewExpedicion);
        categoria = findViewById(R.id.viewCategoria);

        Bundle objSend = getIntent().getExtras();
        Products prod = null;

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