package com.unipiloto.projectrappicoop.FormUser;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.unipiloto.projectrappicoop.DataBase.ConexionSQLiteHelper;
import com.unipiloto.projectrappicoop.DataBase.Utilidades;
import com.unipiloto.projectrappicoop.Home.Home_vendor;
import com.unipiloto.projectrappicoop.Objects.Products;
import com.unipiloto.projectrappicoop.Objects.Users;
import com.unipiloto.projectrappicoop.R;

import java.util.ArrayList;

public class FormLogin extends AppCompatActivity {

    EditText editEmail, editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        editEmail = (EditText) findViewById(R.id.logCorreo);
        editPassword = (EditText) findViewById(R.id.logContraseña);

    }

    public void login(View view) {
        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();

        if (validar() == true) {
            boolean checkuser = checkUser(email);
            if (checkuser == false) {
                boolean checkpass = checkPass(password);
                if (checkpass == false) {
                    //boolean chekrol = checkRol(email);

                    Toast.makeText(this, "Bienvenido", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(this, Home_vendor.class);
                    startActivity(i);
                } else
                    Toast.makeText(this, "La Contraseña es INCORRECTA", Toast.LENGTH_LONG).show();
            }else
                Toast.makeText(this, "El usuario NO existe", Toast.LENGTH_LONG).show();
        }else
            Toast.makeText(this, "Falta Digitar tus datos", Toast.LENGTH_LONG).show();
    }

    public void register(View view) {
        Intent i = new Intent(this, FormRegister.class);
        startActivity(i);
    }

    public boolean validar(){
        String email = editEmail.getText().toString();
        String pass = editPassword.getText().toString();

        if (email.isEmpty() || pass.isEmpty())
            return false;
        else
            return true;
    }

    public void show(String title, String message){
        AlertDialog.Builder build = new AlertDialog.Builder(this);
        build.setCancelable(true);
        build.setTitle(title);
        build.setMessage(message);
        build.show();
    }

    //Checks
    public boolean checkUser(String email){
        ConexionSQLiteHelper con = new ConexionSQLiteHelper(this, "bd_rappicoop", null, 1);

        SQLiteDatabase db = con.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_USUARIO + " WHERE " + Utilidades.US_EMAIL + " = ?", new String[] {email});
        if (cursor.getCount() > 0)
            return false;
        else
            return true;
    }

    public boolean checkPass(String password){
        ConexionSQLiteHelper con = new ConexionSQLiteHelper(this, "bd_rappicoop", null, 1);

        SQLiteDatabase db = con.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_USUARIO + " WHERE " + Utilidades.US_PASSWORD+ " = ?", new String[] {password});
        if (cursor.getCount() > 0)
            return false;
        else
            return true;
    }

    private boolean checkRol(String email) {
        ConexionSQLiteHelper con = new ConexionSQLiteHelper(getApplicationContext(), "bd_rappicoop", null, 1);
        SQLiteDatabase db = con.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_USUARIO + " WHERE " + Utilidades.US_EMAIL + " = ?", new String[] {email});

        Users user = new Users();
        while (cursor.moveToFirst()){
            user.setRol(cursor.getString(6));

        }
        if (user.getRol() == "Cliente")
            return false;
        else
            return true;
    }
}