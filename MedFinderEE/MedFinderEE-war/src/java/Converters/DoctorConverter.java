/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;

import Controlador.ManagedBeanDoctor;
import be.Doctor;
import javax.el.ValueExpression;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author yzavaleta
 */
@FacesConverter("doctorConverter")
public class DoctorConverter implements Converter{
     @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if (context == null) {
            throw new NullPointerException("context");
        }
        if (component == null) {
            throw new NullPointerException("component");
        }
        FacesContext ctx = FacesContext.getCurrentInstance();
        ValueExpression vex = ctx.getApplication().getExpressionFactory().createValueExpression(ctx.getELContext(), "#{managedBeanDoctor}", ManagedBeanDoctor.class);
        ManagedBeanDoctor managedBean = (ManagedBeanDoctor) vex.getValue(ctx.getELContext());
        Doctor objeto;
        try {
            objeto = managedBean.traerObjeto(value);

        } catch (NumberFormatException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Valor Desconocido", "NumberFormatException");
            throw new ConverterException(message);
        }
        if (objeto == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Valor Desconocido", "Object NULL");
            throw new ConverterException(message);
        }
        return objeto;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (context == null) {
            throw new NullPointerException("context");
        }
        if (component == null) {
            throw new NullPointerException("component");
        }
        if (value == null || ((Doctor) value).getPKId()== null) {
            return "";
        }

        return ((Doctor) value).getPKId().toString();
    }
}
