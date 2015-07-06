/*
 * EVI - Land Lot Register
 */
package com.dmkaw.evi.ownership;

import com.dmkaw.evi.estates.LandLot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Dominik Kawczyński
 */
@Named
@RequestScoped
public class RegistryUnionManagedBean implements Serializable {
    
    @EJB
    RegistryUnionController registryUnionController;

    @Inject
    RegistryUnionState registryUnionState;

    private Long id;

    private String landRegisterNumber;
    
    private List<RegistryUnion> regUnitList;

    private List<LandLot> availableLandLots;

    private List<LandLot> chosenLandLots;
    
    @PostConstruct
    private void init(){
        regUnitList = registryUnionController.findAllRegistryUnions();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLandRegisterNumber() {
        return landRegisterNumber;
    }

    public void setLandRegisterNumber(String landRegisterNumber) {
        this.landRegisterNumber = landRegisterNumber;
    }

    public List<RegistryUnion> getRegUnitList() {
        return regUnitList;
    }

    public void setRegUnitList(List<RegistryUnion> regUnitList) {
        this.regUnitList = regUnitList;
    }

    public List<LandLot> getAvailableLandLots() {
        availableLandLots = registryUnionController.findLandLotsAvailableToBind();
        availableLandLots.addAll(registryUnionState.getCurrentLandLotList());
        return availableLandLots;
    }

    public void setAvailableLandLots(List<LandLot> availableLandLots) {
        this.availableLandLots = availableLandLots;
    }

    public List<LandLot> getChosenLandLots() {
        return chosenLandLots;
    }

    public void setChosenLandLots(List<LandLot> chosenLandLots) {
        this.chosenLandLots = chosenLandLots;
    }

    public List<RegistryUnion> select(String query){
        List<RegistryUnion> result = new ArrayList<>();
        for(RegistryUnion ru : regUnitList){
            if(ru.lr.getLandRegisterNumber().contains(query)){
                result.add(ru);
            }
        }
        return result;
    }

    public void addEditRegistryUnion(){
        if(id == null || id == 0){
            if (registryUnionController.addRegistryUnion(landRegisterNumber, chosenLandLots)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "Registry union succesfuly added."));
                reset();
            }
            else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info:", "Registry union with this number already exist."));
            }
        }
        else {
            if (registryUnionController.editRegistryUnion(id, landRegisterNumber, chosenLandLots)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "Registry union succesfuly updated."));
                reset();
            }
            else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info:", "Registry union with this number already exist."));
            }
        }
    }

    public String prepareNewRegistryUnion() {
        registryUnionState.setCurrentLandLotList(new ArrayList<>());
        return "/registryunion/registryunion";
    }

    public String preapreForEdit(Long id){
        RegistryUnion registryUnion = registryUnionController.findRegUnitById(id);
        registryUnionState.setCurrentLandLotList(registryUnion.getLandLots());
        this.id = registryUnion.getId();
        this.landRegisterNumber = registryUnion.getLr().toString();
        return "registryunion";
    }

    private void reset() {
        availableLandLots = registryUnionController.findLandLotsAvailableToBind();
        registryUnionState.setCurrentLandLotList(new ArrayList<>());
        landRegisterNumber = "";
    }
}
