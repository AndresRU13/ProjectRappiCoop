package com.unipiloto.projectrappicoop.FormUser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.unipiloto.projectrappicoop.DataBase.ConexionSQLiteHelper;
import com.unipiloto.projectrappicoop.DataBase.Utilidades;
import com.unipiloto.projectrappicoop.Home.Home_vendor;
import com.unipiloto.projectrappicoop.R;

public class FormRegister extends AppCompatActivity {

    EditText editName, editAge, editEmail, editPassword, editRepass;
    int selectGender, selectRol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_register);

        ConexionSQLiteHelper con = new ConexionSQLiteHelper(this, "bd_rappicoop", null, 1);

        editName = (EditText) findViewById(R.id.editFullNombre);
        editAge = (EditText) findViewById(R.id.editAge);
        editEmail = (EditText) findViewById(R.id.editCorreo);
        editPassword = (EditText) findViewById(R.id.editContraseña);
        editRepass = (EditText) findViewById(R.id.editConfirmacionContraseña);
    }

    public void register(View view) {
        ConexionSQLiteHelper con = new ConexionSQLiteHelper(this, "bd_rappicoop", null, 1);

        SQLiteDatabase db = con.getWritableDatabase();

        Integer valueGender = new Integer(selectGender);
        Integer valueRol = new Integer(selectRol);

        if (validar() == true) {
            if (validarC() == true) {

                ContentValues values = new ContentValues();
                values.put(Utilidades.US_NOMBRE, editName.getText().toString());
                values.put(Utilidades.US_EDAD, editAge.getText().toString());
                values.put(Utilidades.US_EMAIL, editEmail.getText().toString());
                values.put(Utilidades.US_PASSWORD, editPassword.getText().toString());

                String gender = "Hombre";
                String rol = "Cliente";

                if (valueGender == 0) {
                    gender = "mujer";
                }
                if (valueRol == 0) {
                    rol = "Vendedor";
                } else if (valueRol == 2) {
                    rol = "Repartidor";
                }

                values.put(Utilidades.US_GENDER, gender);
                values.put(Utilidades.US_ROL, rol);

                if (validarE() == true) {
                    if (checkUser(editEmail.getText().toString()) == true) {
                        long res = db.insert(Utilidades.TABLA_USUARIO, null, values);
                        if (res == -1)
                            Toast.makeText(this, "Datos NO registrados", Toast.LENGTH_LONG).show();
                        else {
                            Toast.makeText(this, "Datos Registrados con EXITO", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(this, Home_vendor.class);
                            startActivity(i);
                        }
                    } else
                        Toast.makeText(this, "El Correo YA existe", Toast.LENGTH_LONG).show();
                } else if (validarE() == false && rol == "Vendedor" || rol == "Repartidor")
                    Toast.makeText(this, "NO eres mayor de edad", Toast.LENGTH_LONG).show();
            } else
                Toast.makeText(this, "Los campos de la CONTRASEÑA NO coinciden", Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(this, "Faltan completar alguno campos", Toast.LENGTH_LONG).show();
    }

    public void back(View view) {
        Intent i = new Intent(this, FormLogin.class);
        startActivity(i);
    }

    public boolean validar(){
        String name = editName.getText().toString();
        String age = editAge.getText().toString();
        String email = editEmail.getText().toString();
        String pass = editPassword.getText().toString();
        String repass = editRepass.getText().toString();
        if (name.isEmpty() || age.isEmpty() || email.isEmpty() || pass.isEmpty() || repass.isEmpty())
            return false;
        else
            return true;
    }

    public boolean validarC(){
        String pass = editPassword.getText().toString();
        String repass = editRepass.getText().toString();
        if (pass.equals(repass))
            return true;
        else
            return false;
    }

    public boolean validarE(){
        String age = editAge.getText().toString();
        int ageI = Integer.parseInt(age);
        if (ageI >= 18)
            return true;
        else
            return false;
    }
    public void radioClickedGender(View view) {
        boolean check = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.radioMale:
                if (check)
                    selectGender = 1;
                break;
            case R.id.radioFemale:
                if (check)
                    selectGender = 0;
                break;
        }
    }

    public void radioClickedRol(View view) {
        boolean check = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.radioClient:
                if (check)
                    selectRol = 1;
                break;
            case R.id.radioVendor:
                if (check)
                    selectRol = 0;
                break;
            case R.id.radioRepart:
                if (check)
                    selectRol = 2;
                break;
        }
    }

    //CHECKEOS

    public boolean checkUser(String email){
        ConexionSQLiteHelper con = new ConexionSQLiteHelper(this, "bd_rappicoop", null, 1);

        SQLiteDatabase db = con.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_USUARIO + " WHERE " + Utilidades.US_EMAIL + " = ?", new String[] {email});
        if (cursor.getCount() > 0)
            return false;
        else
            return true;
    }
}