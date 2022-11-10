package com.unipiloto.projectrappicoop.FormOrder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.unipiloto.projectrappicoop.DataBase.ConexionSQLiteHelper;
import com.unipiloto.projectrappicoop.DataBase.Utilidades;
import com.unipiloto.projectrappicoop.Objects.Products;
import com.unipiloto.projectrappicoop.R;

import java.util.ArrayList;

public class FormCreateOrder extends AppCompatActivity {

    ArrayList<String> listaInformacion;
    ArrayList<Products> listaProductos;
    ListView listarProductos;
    TextView total;

    ConexionSQLiteHelper con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_create_order);

        con = new ConexionSQLiteHelper(getApplicationContext(), "bd_rappicoop", null, 1);

        total = findViewById(R.id.total);

        Toolbar toolbar = findViewById(R.id.tool);
        setSupportActionBar(toolbar);

        MostrarProductos();

        listarProductos = findViewById(R.id.listProducts);
        ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                listaInformacion);
        listarProductos.setAdapter(adapter);

        listarProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int tot = 0;

                tot += Integer.parseInt(listaInformacion.get(1));

                total.setText(tot);
            }
        });
    }

    public void addOrder(View view) {
    }

    private void MostrarProductos() {
        SQLiteDatabase db = con.getReadableDatabase();

        Products products = null;
        listaProductos = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_PRODUCTO, null);

        while (cursor.moveToNext()){
            products = new Products();
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
}