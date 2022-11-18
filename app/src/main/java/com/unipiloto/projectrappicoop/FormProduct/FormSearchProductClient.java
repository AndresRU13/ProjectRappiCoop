package com.unipiloto.projectrappicoop.FormProduct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;

import com.unipiloto.projectrappicoop.Adaptadores.AdaptadorProductos;
import com.unipiloto.projectrappicoop.DataBase.ConexionSQLiteHelper;
import com.unipiloto.projectrappicoop.DataBase.Utilidades;
import com.unipiloto.projectrappicoop.Objects.Products;
import com.unipiloto.projectrappicoop.R;

import java.util.ArrayList;
import java.util.List;

public class FormSearchProductClient extends AppCompatActivity {

    Button btnVerCarro;
    RecyclerView rvListaProductos;
    AdaptadorProductos adaptador;

    List<Products> listaProductos;
    List<Products> carroCompras = new ArrayList<>();

    ConexionSQLiteHelper con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_search_product_client);
        con = new ConexionSQLiteHelper(getApplicationContext(), "bd_rappicoop", null, 1);

        btnVerCarro = findViewById(R.id.verOrden);
        rvListaProductos = findViewById(R.id.rvListaProductos);
        rvListaProductos.setLayoutManager(new GridLayoutManager(this, 1));

        MostrarProductos();

        adaptador = new AdaptadorProductos(this, btnVerCarro, listaProductos, carroCompras);
        rvListaProductos.setAdapter(adaptador);
    }

    private void MostrarProductos() {
        SQLiteDatabase db = con.getReadableDatabase();

        Products products = null;
        listaProductos = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_PRODUCTO, null);

        while (cursor.moveToNext()){
            products = new Products();
            products.setId(cursor.getInt(0));
            products.setNombre(cursor.getString(1));
            products.setDescripcion(cursor.getString(2));
            products.setValor(cursor.getString(3));
            products.setLocal(cursor.getString(4));
            products.setExpedicion(cursor.getString(5));
            products.setCategoria(cursor.getString(6));

            listaProductos.add(products);
        }
    }

}