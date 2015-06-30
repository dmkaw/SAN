/*
 * EVI - Land Lot Register
 */
package com.dmkaw.evi.estates;

import com.dmkaw.evi.ownership.RegistryUnion;
import com.dmkaw.evi.util.EviUtilities;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * @author Dominik Kawczyński
 */
@Named
@RequestScoped
public class LandLotManagedBean implements Serializable {

    private Long id;
    private String landLotId;
    private CadastralMunicipality cadastralMunicipality;
    private Double area;
    private RegistryUnion registryUnion;

    @EJB
    EviUtilities eu;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLandLotId() {
        return landLotId;
    }

    public void setLandLotId(String landLotId) {
        this.landLotId = landLotId;
    }

    public CadastralMunicipality getCadastralMunicipality() {
        return cadastralMunicipality;
    }

    public void setCadastralMunicipality(CadastralMunicipality cadastralMunicipality) {
        this.cadastralMunicipality = cadastralMunicipality;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    //TODO: Zrobić jedną metode która albo robi persist albo merge
    //TODO: Poprawić te ify
    public void addLandLot() {
        if (id == null || id == 0) {
            if (eu.addLandLot(landLotId, cadastralMunicipality, area, registryUnion)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "Land lot succesfuly added."));
            }
        } else {
            if (eu.editLandLot(id, landLotId, cadastralMunicipality, area, registryUnion)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "Land lot succesfuly updated."));
            }
        }
    }

    public String preapreForEdit(Long id) {
        LandLot l = eu.getLandLotbyId(id);
        this.id = l.getId();
        this.landLotId = l.getLandLotId();
        this.cadastralMunicipality = l.getCadastralMunicipality();
        this.area = l.getArea();
        this.registryUnion = l.getLandLotRegistryUnion();
        return "landlot";
    }

    public CadastralMunicipality[] getCmList() {
        return CadastralMunicipality.values();
    }

    public RegistryUnion getRegistryUnion() {
        return registryUnion;
    }

    public void setRegistryUnion(RegistryUnion registryUnion) {
        this.registryUnion = registryUnion;
    }

}
