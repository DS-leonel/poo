package com.tienda.models.seguridad;

public class Usuario {
    private int id;
    private String nombreUsuario;
    private String contrasena;
    private String rol;

    public Usuario() {}

    public Usuario(int id, String nombreUsuario, String contrasena, String rol) {
        setId(id);
        setNombreUsuario(nombreUsuario);
        setContrasena(contrasena);
        setRol(rol);
    }

    public int getId() { return id; }

    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID debe ser > 0");
        this.id = id;
    }

    public String getNombreUsuario() { return nombreUsuario; }

    public void setNombreUsuario(String nombreUsuario) {
        if (nombreUsuario == null || nombreUsuario.trim().isEmpty())
            throw new IllegalArgumentException("Nombre de usuario no puede estar vacío");
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() { return contrasena; }

    public void setContrasena(String contrasena) {
        if (contrasena == null || contrasena.length() < 6)
            throw new IllegalArgumentException("Contraseña debe tener al menos 6 caracteres");
        this.contrasena = contrasena;
    }

    public String getRol() { return rol; }

    public void setRol(String rol) {
        if (rol == null || rol.trim().isEmpty())
            throw new IllegalArgumentException("Rol no puede estar vacío");
        this.rol = rol;
    }
}
