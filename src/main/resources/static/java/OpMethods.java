/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.DatosDAO;

/**
 *
 * @author usuario
 */
public class OpMethods {

    public boolean realizarOperacion(String saldo, String[] valoresAnteriores) {
        ArrayList<String[]> valoresTotales = new ArrayList<>();

        valoresTotales.add(new String[]{"CodOp", "CodCuenta", "Operacion", "CodCuentaTransf",
            "OpDinero", "SalAnterior", "SalActual", "Fecha"});

        Integer saldoActual = Integer.parseInt(saldo);
        Integer dineroOperacion = Integer.parseInt(valoresAnteriores[4]);
        String tipo = valoresAnteriores[2];
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaActual = dtf.format(LocalDateTime.now());

        if (saldoActual >= dineroOperacion) {
            Integer saldoNuevo = saldoActual - dineroOperacion;
            
            if (tipo.matches("(?i)consignacion")) {
                saldoNuevo = saldoActual + dineroOperacion;
            }
            
            valoresTotales.add(new String[]{valoresAnteriores[0], valoresAnteriores[1],
                    valoresAnteriores[2], valoresAnteriores[3], valoresAnteriores[4],
                saldoActual.toString(), saldoNuevo.toString(), fechaActual});
            
            return efectuarConsulta(valoresTotales.get(1));
        }

        JOptionPane.showMessageDialog(null, "No tiene saldo disponible para realizar esta operación");
        return false;
    }
    
    public static boolean efectuarConsulta(String[] valores) {
        DatosDAO dao = new DatosDAO();
        String tipo = valores[2];
        
        if (dao.insertarDato("operaciones", valores)) {
            
            if (dao.actualizarDato("cuentas", new String[]{"CodCuenta", "Saldo"}, new String[]{valores[1], valores[6]})) {
                
                if (tipo.matches("(?i)transferencia")) {
                    
                    Integer saldoOtroUsuario = Integer.parseInt(dao.comprobarDatosPorId("cuentas", new String[]{"CodCuenta", "saldo"}, valores[3])[0]);
                    Integer saldoTotal = saldoOtroUsuario + Integer.parseInt(valores[4]);
                    
                    return dao.actualizarDato("cuentas", new String[]{"CodCuenta", "Saldo"}, new String[]{valores[3], saldoTotal.toString()});
                }
                return true;
            }
            
            JOptionPane.showMessageDialog(null, "Error actualizando saldo");
            return false;
        }
        
        JOptionPane.showMessageDialog(null, "Error insertando en operaciones");
        return false;
    }
}
