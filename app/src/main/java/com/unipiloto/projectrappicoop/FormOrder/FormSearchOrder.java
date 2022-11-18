package com.unipiloto.projectrappicoop.FormOrder;

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

import com.unipiloto.projectrappicoop.DataBase.ConexionSQLiteHelper;
import com.unipiloto.projectrappicoop.DataBase.Utilidades;
import com.unipiloto.projectrappicoop.Objects.Orders;
import com.unipiloto.projectrappicoop.R;

import java.util.ArrayList;

public class FormSearchOrder extends AppCompatActivity implements SearchView.OnQueryTextListener {

    ArrayList<String> listaInformacion;
    ArrayList<Orders> listaOrden;
    ListView listarOrden;
    SearchView txtBuscar;
    TextView name, nameClient, value, location;

    ConexionSQLiteHelper con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_search_order);
        con = new ConexionSQLiteHelper(getApplicationContext(), "bd_rappicoop", null, 1);

        txtBuscar = findViewById(R.id.buscar);
        listarOrden = findViewById(R.id.listViewProducts);
        name = findViewById(R.id.nombre);
        nameClient = findViewById(R.id.nombreCliente);
        value = findViewById(R.id.valor);
        location = findViewById(R.id.direccion);

        MostrarOrden();

        ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                listaInformacion);
        listarOrden.setAdapter(adapter);

        listarOrden.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Orders ord = listaOrden.get(i);
                Intent intent = new Intent(FormSearchOrder.this, DetailsOrder.class);

                Bundle bundle = new Bundle();

                bundle.putSerializable("orden", ord);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        txtBuscar.setOnQueryTextListener(this);
    }

    private void MostrarOrden() {
        SQLiteDatabase db = con.getReadableDatabase();

        Orders orders = null;
        listaOrden = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_ORDEN, null);

        while (cursor.moveToNext()){
            orders = new Orders();
            orders.setId(cursor.getInt(0));
            orders.setNombreCliente(cursor.getString(1));
            orders.setList_shop(cursor.getString(2));
            orders.setValor(cursor.getString(3));
            orders.setProveedor(cursor.getString(4));
            orders.setDireccion(cursor.getString(5));
            orders.setFecha(cursor.getString(6));

            listaOrden.add(orders);
        }
        ObtenerLista();
    }

    private void ObtenerLista() {
        listaInformacion = new ArrayList<String>();

        for (int i = 0; i < listaOrden.size(); i++){
            listaInformacion.add(listaOrden.get(i).getProveedor() + "\n" +
                    listaOrden.get(i).getNombreCliente() + " - " + listaOrden.get(i).getValor() + " - " + listaOrden.get(i).getDireccion());
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        SQLiteDatabase db = con.getReadableDatabase();
        String[] param = {s};
        String[] field = {Utilidades.ORD_PROVEEDOR, Utilidades.ORD_USUARIO, Utilidades.ORD_VALOR, Utilidades.ORD_DIRECCION};

        try {
            Cursor cursor = db.query(Utilidades.TABLA_ORDEN, field, Utilidades.ORD_PROVEEDOR + " = ?", param,  null, null, null);
            cursor.moveToFirst();

            name.setText(cursor.getString(0));
            nameClient.setText(cursor.getString(1));
            value.setText("$ " + cursor.getString(2));
            location.setText(cursor.getString(3));
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