/*
 * EVI - Land Lot Register
 */
package com.dmkaw.evi.ownership;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Dominik Kawczyński
 */
@FacesConverter("registryUnionConverter")
public class RegistryUnionConverter implements Converter {

    @EJB
    RegistryUnionController ru;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return ru.findRegUnitByLandRegister(value);
            } catch (Exception e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Conversion Error", "Registry union not found."));
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return ((RegistryUnion) value).lr.getLandRegisterNumber();
        } else {
            return null;
        }
    }

}
