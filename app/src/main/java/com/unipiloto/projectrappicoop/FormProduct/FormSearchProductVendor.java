package com.unipiloto.projectrappicoop.FormProduct;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.unipiloto.projectrappicoop.DataBase.ConexionSQLiteHelper;
import com.unipiloto.projectrappicoop.DataBase.Utilidades;
import com.unipiloto.projectrappicoop.Objects.Products;
import com.unipiloto.projectrappicoop.R;

import java.util.ArrayList;

public class FormSearchProductVendor extends AppCompatActivity implements SearchView.OnQueryTextListener {

    ArrayList<String> listaInformacion;
    ArrayList<Products> listaProductos;
    ListView listarProductos;
    SearchView txtBuscar;
    TextView name, value, expedition;

    FloatingActionButton agregar_Producto;

    ConexionSQLiteHelper con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_search_product_vendor);

        con = new ConexionSQLiteHelper(getApplicationContext(), "bd_rappicoop", null, 1);

        txtBuscar = findViewById(R.id.buscar);
        listarProductos = findViewById(R.id.listViewProducts);
        name = findViewById(R.id.name);
        value = findViewById(R.id.value);
        expedition = findViewById(R.id.expedition);

        agregar_Producto = (FloatingActionButton) findViewById(R.id.agregar);

        MostrarProductos();

        ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                listaInformacion);
        listarProductos.setAdapter(adapter);

        listarProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Products prod = listaProductos.get(i);
                Intent intent = new Intent(FormSearchProductVendor.this, Details.class);

                Bundle bundle = new Bundle();

                bundle.putSerializable("producto", prod);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        agregar_Producto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newProduct();
            }
        });

        txtBuscar.setOnQueryTextListener(this);
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
        ObtenerLista();
    }

    private void ObtenerLista() {
        listaInformacion = new ArrayList<String>();

        for (int i = 0; i < listaProductos.size(); i++){
            listaInformacion.add(listaProductos.get(i).getNombre() + "\n" +
                    listaProductos.get(i).getValor() + " - " + listaProductos.get(i).getLocal());
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    private void newProduct() {
        Intent intent = new Intent(this, FormAddProduct.class);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        SQLiteDatabase db = con.getReadableDatabase();
        String[] param = {s};
        String[] field = {Utilidades.PRO_NOMBRE, Utilidades.PRO_VALOR, Utilidades.PRO_EXPEDICION};

        try {
            Cursor cursor = db.query(Utilidades.TABLA_PRODUCTO, field, Utilidades.PRO_NOMBRE + " = ?", param,  null, null, null);
            cursor.moveToFirst();

            name.setText(cursor.getString(0));
            value.setText("$ " + cursor.getString(1));
            expedition.setText(cursor.getString(2));
            cursor.close();
        }catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Producto NO Existe", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

}