package com.unipiloto.projectrappicoop.FormOrder;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.unipiloto.projectrappicoop.DataBase.ConexionSQLiteHelper;
import com.unipiloto.projectrappicoop.Objects.Orders;
import com.unipiloto.projectrappicoop.R;
import com.unipiloto.projectrappicoop.Services.ClientService;
import com.unipiloto.projectrappicoop.Services.VendorService;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class DetailsOrder extends AppCompatActivity {

    ConexionSQLiteHelper con;
    EditText nombre, direccion, proveedor, fecha, prodructos, valor;
    Button inicio, entrega;
    double latitud = 0.0, longitud = 0.0;
    private FusedLocationProviderClient fusedLocationProviderClient;

    Orders ord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_order);
        con = new ConexionSQLiteHelper(this, "bd_rappicoop", null, 1);

        inicio = findViewById(R.id.inicio);
        entrega = findViewById(R.id.entrega);
        nombre = findViewById(R.id.viewNameClient);
        direccion = findViewById(R.id.viewLocation);
        valor = findViewById(R.id.viewValue);
        proveedor = findViewById(R.id.viewProvider);
        fecha = findViewById(R.id.viewDate);
        prodructos = findViewById(R.id.viewProducts);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        Bundle objSend = getIntent().getExtras();
        ord = null;

        if (objSend != null) {
            ord = (Orders) objSend.getSerializable("orden");
            nombre.setText(ord.getNombreCliente().toString());
            direccion.setText(ord.getDireccion().toString());
            valor.setText(ord.getValor().toString());
            proveedor.setText(ord.getProveedor().toString());
            fecha.setText(ord.getFecha().toString());
            prodructos.setText(ord.getList_shop().toString());
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.S)
    public void acceptOrder(View view) {
        getLocation();
        if (inicio.getText().toString() == "ACEPTAR ORDEN") {

            inicio.setText("DETENER");
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("https")
                    .authority("www.google.com")
                    .appendPath("maps")
                    .appendPath("dir")
                    .appendPath("")
                    .appendQueryParameter("api", "1")
                    .appendQueryParameter("origin", latitud + "," + longitud)
                    .appendQueryParameter("destination", direccion.getText().toString());
            String url = builder.build().toString();

            Log.d("Direccion", url);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }else {
            inicio.setText("ACEPTAR ORDEN");
        }
    }

    public void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(
                new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location location = task.getResult();
                        if (location != null) {
                            Geocoder geocoder = new Geocoder(DetailsOrder.this, Locale.getDefault());
                            try {
                                List<Address> addresses = geocoder.getFromLocation(
                                        location.getLatitude(), location.getLongitude(),1);

                                latitud = (double) addresses.get(0).getLatitude();
                                longitud = (double) addresses.get(0).getLongitude();

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            ActivityCompat.requestPermissions(DetailsOrder.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                        }
                    }
                }
        );
    }

    public void entrgado(View view) {
        Intent intent = new Intent(this, VendorService.class);
        intent.putExtra(VendorService.EXTRA_MESSAGE,getResources().getString(R.string.respondeV));
        startService(intent);
        Intent intent1 = new Intent(this, ClientService.class);
        intent1.putExtra(ClientService.EXTRA_MESSAGE,getResources().getString(R.string.respondeC));
        startService(intent1);
    }
}