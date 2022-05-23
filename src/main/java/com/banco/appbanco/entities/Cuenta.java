package com.banco.appbanco.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cuentas")
public class Cuenta extends Tabla {
    @Id
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "DocumentoUsuario")
    private Usuario usuarioDocumento;

    @Column(name = "Tipo", nullable = false, length = 50)
    private String tipo;

    @Column(name = "Saldo", nullable = false)
    private int saldo;
    
    public Cuenta() {
        super("cuentas", 
        new String[]{"codigo", "usuarioDocumento", "tipo", "saldo"}, 
        new String[]{"text", "number", "text", "number"});
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Usuario getUsuarioDocumento() {
        return usuarioDocumento;
    }

    public void setUsuarioDocumento(Usuario usuarioDocumento) {
        this.usuarioDocumento = usuarioDocumento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    

}
