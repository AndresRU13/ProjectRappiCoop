package com.unipiloto.projectrappicoop.FormProduct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.unipiloto.projectrappicoop.DataBase.ConexionSQLiteHelper;
import com.unipiloto.projectrappicoop.DataBase.Utilidades;
import com.unipiloto.projectrappicoop.Home.Home_vendor;
import com.unipiloto.projectrappicoop.Objects.Products;
import com.unipiloto.projectrappicoop.R;

import java.util.Arrays;
import java.util.stream.Collectors;

public class FormAddProduct extends AppCompatActivity {

    Products products;
    EditText editNombre, editDescripcion, editValor, editLocal, editExpedition;
    ImageView imagen;
    int selectCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_add_product);

        editNombre = (EditText) findViewById(R.id.editName);
        editDescripcion = (EditText) findViewById(R.id.editdescrip);
        editValor = (EditText) findViewById(R.id.editValue);
        editLocal = (EditText) findViewById(R.id.editLocal);
        editExpedition = (EditText) findViewById(R.id.editExpedition);

        Toolbar toolbar = findViewById(R.id.tool);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_Return:
                Intent intent = new Intent(this, Home_vendor.class);
                startActivity(intent);
                return true;
            case R.id.action_delete:
                Intent i = new Intent(this, FormDeleteProduct.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void addProducto(View view) {
        ConexionSQLiteHelper con = new ConexionSQLiteHelper(this, "bd_rappicoop", null, 1);

        SQLiteDatabase db = con.getWritableDatabase();

        Integer valueCategory = new Integer(selectCategory);

        if (validarCampos() == true) {
            ContentValues values = new ContentValues();

            values.put(Utilidades.PRO_NOMBRE, editNombre.getText().toString());
            values.put(Utilidades.PRO_DESCRIPCION, editDescripcion.getText().toString());
            values.put(Utilidades.PRO_VALOR, editValor.getText().toString());
            values.put(Utilidades.PRO_EXPEDICION, editExpedition.getText().toString());
            values.put(Utilidades.PRO_LOCAL, editLocal.getText().toString());
            values.put(Utilidades.PRO_IMAGEN, imagen.getImageAlpha());

            String category = "Bebida";

            if (valueCategory == 0) {
                category = "Comida";
            }

            values.put(Utilidades.PRO_CATEGORIA, category);

            Long res = db.insert(Utilidades.TABLA_PRODUCTO, null, values);
            if (res == -1)
                Toast.makeText(this, "Producto NO Agregado", Toast.LENGTH_LONG).show();
            else {
                Toast.makeText(this, "Producto Agregado satisfactoriamente", Toast.LENGTH_LONG).show();
                editNombre.setText("");
                editDescripcion.setText("");
                editValor.setText("");
                editLocal.setText("");
                editExpedition.setText("");
            }
        }else
            Toast.makeText(this, "Falta diligenciar algunos Campos", Toast.LENGTH_LONG).show();
    }

    private boolean validarCampos() {
        String name = editNombre.getText().toString();
        String description = editDescripcion.getText().toString();
        String value = editValor.getText().toString();
        String local = editLocal.getText().toString();
        String expedition = editExpedition.getText().toString();

        if (name.isEmpty() || description.isEmpty() || value.isEmpty() || local.isEmpty() || expedition.isEmpty())
            return false;
        else
            return true;
    }

    public void radioClickedCategory(View view) {
        boolean check = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radioDrink:
                if (check)
                    selectCategory = 1;
                break;
            case R.id.radioFood:
                if (check)
                    selectCategory = 0;
                break;
        }
    }

    public void ImageSelect(View view) {
        imagen = (ImageView) findViewById(R.id.imagen);
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Seleccione La Aplicacion"), 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            Uri path = data.getData();
            imagen.setImageURI(path);
        }
    }
}