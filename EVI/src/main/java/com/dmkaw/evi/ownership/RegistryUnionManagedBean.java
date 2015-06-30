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
import javax.inject.Named;

/**
 *
 * @author Dominik Kawczyński
 */
@Named
@RequestScoped
public class RegistryUnionManagedBean implements Serializable {
    
    @EJB
    RegistryUnionController ruc;

    private long id;

    private String landRegisterNumber;
    
    private List<RegistryUnion> regUnitList;

    private List<LandLot> availableLandLots;

    private List<LandLot> chosenLandLots;
    
    @PostConstruct
    private void init(){
        availableLandLots = ruc.findLandLotsAvailableToBind();
        regUnitList = ruc.findAllRegistryUnions();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
        ruc.addRegistryUnion(landRegisterNumber, chosenLandLots);
    }
}
