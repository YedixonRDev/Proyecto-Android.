package com.example.appfuncionandroid.utilidades;

import org.w3c.dom.Text;

public class Utilidades {

    //Constantes campos tabla usuario

    public static final String TABLA_USUARIO="usuario";
    public static final String CAMPO_ID="id";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_TELEFONO="telefono";



    public static final String CREAR_TABLA_USUARIO = "CREATE TABLE " +
            ""+TABLA_USUARIO+ "("+CAMPO_ID+" " +
            "INTEGER, "+CAMPO_NOMBRE+" TEXT, "+CAMPO_TELEFONO+" TEXT)";
}