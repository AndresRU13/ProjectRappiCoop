package com.unipiloto.projectrappicoop;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.unipiloto.projectrappicoop.DataBase.ConexionSQLiteHelper;
import com.unipiloto.projectrappicoop.DataBase.Utilidades;
import com.unipiloto.projectrappicoop.Objects.Orders;
import com.unipiloto.projectrappicoop.Objects.Products;

import java.util.ArrayList;
import java.util.List;

public class Reports extends AppCompatActivity {


    EditText nombreEmprendedor, nombreUsuario, ventasEmprendedor, comprasCliente, comprasClienteEmprendedor;

    ConexionSQLiteHelper con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        con = new ConexionSQLiteHelper(getApplicationContext(), "bd_rappicoop", null, 1);

        nombreEmprendedor = findViewById(R.id.nombreEmprendedor);
        nombreUsuario = findViewById(R.id.nombreUsuario);
        ventasEmprendedor = findViewById(R.id.ventasEmprendedor);
        comprasCliente = findViewById(R.id.comprasCliente);
        comprasClienteEmprendedor = findViewById(R.id.comprasClienteEmprendedor);
    }

    public void ventas(View view){
        SQLiteDatabase db = con.getReadableDatabase();
        String[] param = {nombreEmprendedor.getText().toString()};
        String[] field = {Utilidades.ORD_VALOR};

        try {
            Cursor cursor = db.query(Utilidades.TABLA_ORDEN, field, Utilidades.ORD_PROVEEDOR + " = ?", param,  null, null, null);
            while (cursor.moveToNext()) {
               ventasEmprendedor.setText(cursor.getString(0));
            }
        }catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Emprendedor NO Existe", Toast.LENGTH_LONG).show();
        }
    }

    public void compras(View view){
        SQLiteDatabase db = con.getReadableDatabase();
        String[] param = {nombreUsuario.getText().toString()};
        String[] field = {Utilidades.ORD_VALOR};

        try {
            Cursor cursor = db.query(Utilidades.TABLA_ORDEN, field, Utilidades.ORD_USUARIO + " = ?", param,  null, null, null);
            while (cursor.moveToNext()) {
                comprasCliente.setText(cursor.getString(0));
            }
        }catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Usuario NO Existe", Toast.LENGTH_LONG).show();
        }
    }

    public void ClienteEmprendedor(View view) {
        SQLiteDatabase db = con.getReadableDatabase();
        String[] param = {nombreEmprendedor.getText().toString(), nombreUsuario.getText().toString()};
        String[] field = {Utilidades.ORD_VALOR};

        try {
            Cursor cursor = db.query(Utilidades.TABLA_ORDEN, field, Utilidades.ORD_PROVEEDOR + "," + Utilidades.ORD_USUARIO + " = ?", param,  null, null, null);
            while (cursor.moveToNext()) {
                comprasClienteEmprendedor.setText(cursor.getString(0));
            }
        }catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Datos NO Existe", Toast.LENGTH_LONG).show();
        }
    }
}