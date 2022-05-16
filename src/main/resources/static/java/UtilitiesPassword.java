/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class UtilitiesPassword {

    public String encriptarPass(String pass) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(pass.getBytes());
            StringBuffer sb = new StringBuffer();
            
            for (int i = 0; i < array.length; i++) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            
            return sb.toString();
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UtilitiesPassword.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}