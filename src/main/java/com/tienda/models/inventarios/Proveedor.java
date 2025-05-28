package com.tienda.models.inventarios;

public class Proveedor {
    private int id;
    private String nombre;
    private String contacto;
    private String telefono;
    private String direccion;

    public Proveedor() {}

    public Proveedor(int id, String nombre, String contacto, String telefono, String direccion) {
        setId(id);
        setNombre(nombre);
        setContacto(contacto);
        setTelefono(telefono);
        setDireccion(direccion);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID del proveedor debe ser > 0");
        }
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del proveedor no puede estar vacío");
        }
        this.nombre = nombre;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        if (contacto == null) {
            this.contacto = "";
        } else {
            this.contacto = contacto.trim();
        }
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("El teléfono del proveedor no puede estar vacío");
        }
        this.telefono = telefono.trim();
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        if (direccion == null) {
            this.direccion = "";
        } else {
            this.direccion = direccion.trim();
        }
    }



}