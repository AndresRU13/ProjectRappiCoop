package com.unipiloto.projectrappicoop.Objects;

import java.io.Serializable;

public class Orders implements Serializable {

    private Integer id;
    private String nombreCliente;
    private String list_shop;
    private String valor;
    private String proveedor;
    private String direccion;
    private String fecha;

    public Orders(){

    }

    public Orders(Integer id, String nombreCliente, String list_shop, String valor, String proveedor, String direccion, String fecha) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.list_shop = list_shop;
        this.valor = valor;
        this.proveedor = proveedor;
        this.direccion = direccion;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getList_shop() {
        return list_shop;
    }

    public void setList_shop(String list_shop) {
        this.list_shop = list_shop;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
