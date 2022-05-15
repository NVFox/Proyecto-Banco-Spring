package com.banco.appbanco.entities;

public class Tabla {
    private String nombreTabla;

    private String[] campos;

    private String[] tipos;

    public Tabla(String nombreTabla, String[] campos, String[] tipos) {
        this.nombreTabla = nombreTabla;
        this.campos = campos;
        this.tipos = tipos;
    }

    public String getNombreTabla() {
        return this.nombreTabla;
    }

    public void setNombreTabla(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }

    public String[] getCampos() {
        return campos;
    }

    public void setCampos(String[] campos) {
        this.campos = campos;
    }

    public String[] getTipos() {
        return tipos;
    }

    public void setTipos(String[] tipos) {
        this.tipos = tipos;
    }
}
