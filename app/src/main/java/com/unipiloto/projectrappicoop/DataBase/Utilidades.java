package com.unipiloto.projectrappicoop.DataBase;

public class Utilidades {

    public static final String TABLA_USUARIO = "usuario";

    public static final String US_ID = "ID";
    public static final String US_NOMBRE = "NAME";
    public static final String US_EDAD = "AGE";
    public static final String US_EMAIL = "EMAIL";
    public static final String US_PASSWORD = "PASSWORD";
    public static final String US_GENDER = "GENDER";
    public static final String US_ROL = "ROL";

    public static final String TABLA_PRODUCTO = "producto";

    public static final String PRO_ID = "ID";
    public static final String PRO_NOMBRE = "NAME";
    public static final String PRO_DESCRIPCION = "DESCRIPTION";
    public static final String PRO_VALOR = "VALUE";
    public static final String PRO_EXPEDICION = "EXPEDITION";
    public static final String PRO_LOCAL = "LOCAL";
    public static final String PRO_CATEGORIA = "CATEGORY";
    public static final String PRO_IMAGEN = "IMAGE_ID";

    public static final String TABLA_ORDEN = "pedido";

    public static final String ORD_ID = "ID";
    public static final String ORD_USUARIO = "NAME_USER";
    public static final String ORD_VALOR = "VALUE";
    public static final String ORD_PAGO = "WAY_TO_PAY";
    public static final String ORD_PROVEEDOR = "PROVIDER_NAME";
    public static final String ORD_DIRECCION = "LOCATION";
    public static final String ORD_FECHA = "DATE";

    public static final String CREAR_TABLA_USUARIO = "CREATE TABLE " + TABLA_USUARIO +
            " (" + US_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + US_NOMBRE + " TEXT, " + US_EDAD +" TEXT, " +
            US_EMAIL + " TEXT, " + US_PASSWORD + " TEXT, " + US_GENDER + " INTEGER, " + US_ROL +" INTEGER)";
    public static final String CREAR_TABLA_PRODUCTO = "CREATE TABLE " +TABLA_PRODUCTO +
            " (" + PRO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PRO_NOMBRE + " TEXT, " + PRO_DESCRIPCION + " TEXT, " +
            PRO_VALOR +" TEXT, " + PRO_EXPEDICION + " TEXT, " + PRO_LOCAL + " TEXT, " +
            PRO_CATEGORIA + " INTEGER, " + PRO_IMAGEN + " INTEGER)";
    public static final String CREAR_TABLA_ORDEN = "CREATE TABLE " + TABLA_ORDEN +
            " (" + ORD_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " + ORD_USUARIO + " TEXT, " +
            ORD_VALOR + " TEXT, " + ORD_PAGO + " INTEGER, " + ORD_PROVEEDOR +" TEXT, " +
            ORD_DIRECCION + " TEXT, " + ORD_FECHA + " TEXT)";

}
