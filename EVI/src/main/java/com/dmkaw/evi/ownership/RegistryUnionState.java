package com.dmkaw.evi.ownership;

import com.dmkaw.evi.estates.LandLot;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by domek on 2015-07-06.
 */
@Named
@SessionScoped
public class RegistryUnionState implements Serializable {

    List<LandLot> currentLandLotList = new ArrayList<>();

    public List<LandLot> getCurrentLandLotList() {
        return currentLandLotList;
    }

    public void setCurrentLandLotList(List<LandLot> currentLandLotList) {
        this.currentLandLotList = currentLandLotList;
    }
}
