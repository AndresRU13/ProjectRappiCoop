package com.unipiloto.projectrappicoop.Objects;

public class Users {

    private Integer id;
    private String nombre;
    private String edad;
    private String email;
    private String password;
    private String genero;
    private String rol;

    public Users(Integer id, String nombre, String edad, String email, String password, String genero, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.email = email;
        this.password = password;
        this.genero = genero;
        this.rol = rol;
    }

    public Users() {

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

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
