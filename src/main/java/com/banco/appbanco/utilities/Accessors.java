package com.banco.appbanco.utilities;

import com.banco.appbanco.entities.Tabla;

import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;

public class Accessors {
    PropertyAccessor prevAccessor;

    PropertyAccessor newAccessor;

    public void actualizarObjeto(Tabla prevData, Tabla newData) {
        this.prevAccessor = PropertyAccessorFactory.forBeanPropertyAccess(prevData);
        this.newAccessor = PropertyAccessorFactory.forBeanPropertyAccess(newData);

        for (String campo : prevData.getCampos()) {
            if (newAccessor.getPropertyValue(campo) != null) {
                prevAccessor.setPropertyValue(campo, newAccessor.getPropertyValue(campo));
            }
        }
    }
}
