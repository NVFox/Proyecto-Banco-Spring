package com.banco.appbanco.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "creditos")
public class Credito extends Tabla {
    @Id
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "DocumentoUsuario")
    private Usuario usuarioDocumento;

    @ManyToOne
    @JoinColumn(name = "CodigoLinea")
    private Linea lineaCodigo;

    @Column(name = "Monto", nullable = false, length = 50)
    private int monto;

    @Column(name = "FechaAprobacion", nullable = false)
    private String fechaAprobacion;

    @Column(name = "Plazo", nullable = false, length = 50)
    private int plazo;

    public Credito() {
        super("creditos", 
        new String[]{"codigo", "usuarioDocumento", "lineaCodigo", "monto", "fechaAprobacion", "plazo"}, 
        new String[]{"text", "number", "text", "text", "date", "number"});
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

    public Linea getLineaCodigo() {
        return lineaCodigo;
    }

    public void setLineaCodigo(Linea lineaCodigo) {
        this.lineaCodigo = lineaCodigo;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(String fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }

}
