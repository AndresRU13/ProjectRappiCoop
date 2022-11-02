package com.unipiloto.projectrappicoop.Objects;

import java.io.Serializable;

public class Products implements Serializable {

    private Integer id;
    private String nombre;
    private String descripcion;
    private String valor;
    private String local;
    private String expedicion;
    private String categoria;
    private int imageResource;

    public Products(){

    }

    public Products(Integer id, String nombre, String descripcion, String valor, String local, String expedicion, String categoria, int imageResource) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.valor = valor;
        this.local = local;
        this.expedicion = expedicion;
        this.categoria = categoria;
        this.imageResource = imageResource;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getExpedicion() {
        return expedicion;
    }

    public void setExpedicion(String expedicion) {
        this.expedicion = expedicion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}
