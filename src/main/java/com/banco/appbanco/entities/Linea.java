package com.banco.appbanco.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Lineas")
public class Linea extends Tabla {
    @Id
    private String codigo;

    @Column(name = "Nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "MontoMaximo", nullable = false)
    private int montoMaximo;

    @Column(name = "PlazoMaximo", nullable = false)
    private int plazoMaximo;

    public Linea() {
        super("lineas", 
        new String[]{"codigo", "nombre", "montoMaximo", "plazoMaximo"}, 
        new String[]{"text", "text", "number", "number"});
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMontoMaximo() {
        return montoMaximo;
    }

    public void setMontoMaximo(int montoMaximo) {
        this.montoMaximo = montoMaximo;
    }

    public int getPlazoMaximo() {
        return plazoMaximo;
    }

    public void setPlazoMaximo(int plazoMaximo) {
        this.plazoMaximo = plazoMaximo;
    }

    
}
