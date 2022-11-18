package com.unipiloto.projectrappicoop.FormOrder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.unipiloto.projectrappicoop.Adaptadores.AdaptadorCarroCompras;
import com.unipiloto.projectrappicoop.DataBase.ConexionSQLiteHelper;
import com.unipiloto.projectrappicoop.Objects.Products;
import com.unipiloto.projectrappicoop.R;

import java.io.Serializable;
import java.util.List;

public class FormCreateOrder extends AppCompatActivity {

    List<Products> carroCompras;

    AdaptadorCarroCompras adaptador;

    RecyclerView rvListaCarro;
    TextView tvTotal;

    ConexionSQLiteHelper con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_create_order);

        con = new ConexionSQLiteHelper(getApplicationContext(), "bd_rappicoop", null, 1);

        carroCompras = (List<Products>) getIntent().getSerializableExtra("CarroCompras");

        rvListaCarro = findViewById(R.id.rvListaCarro);
        rvListaCarro.setLayoutManager(new GridLayoutManager(this, 1));
        tvTotal = findViewById(R.id.total);

        adaptador = new AdaptadorCarroCompras(this, carroCompras, tvTotal);
        rvListaCarro.setAdapter(adaptador);
    }

    public void next(View view) {
        Intent intent = new Intent(this, FormRegisterOrder.class);
        intent.putExtra("compra", (Serializable) carroCompras);
        startActivity(intent);
    }
}