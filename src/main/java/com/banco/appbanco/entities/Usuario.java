package com.banco.appbanco.entities;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario extends Tabla {
    @Id
    private long documento;

    @Column(name = "Nombre", nullable = false, length = 30)
    private String nombre;
    
    @Column(name = "Clave", nullable = false, length = 255)
    private String clave;

    @Column(name = "Rol", nullable = false, length = 30)
    private String rol;

    @Column(name = "Estado", nullable = false, length = 50)
    private String estado;

    @Column(name = "Imagen", nullable = false, length = 255)
    private String imagen;

    @ManyToOne
    @JoinColumn(name = "DocumentoCliente")
    private Cliente clienteDocumento;

    public Usuario() {
        super("usuarios", 
        new String[]{"documento", "nombre", "rol", "estado", "imagen", "clienteDocumento"}, 
        new String[]{"number", "text", "text", "text", "text", "number"});
    }

    public long getDocumento() {
        return documento;
    }

    public void setDocumento(long documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Cliente getClienteDocumento() {
        return clienteDocumento;
    }

    public void setClienteDocumento(Cliente clienteDocumento) {
        this.clienteDocumento = clienteDocumento;
    }

}
