/*
 * EVI - Land Lot Register
 */
package com.dmkaw.evi.estates;

import com.dmkaw.evi.util.EviUtilities;
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
public class LandLotListManagedBean {

    List<LandLot> landLotList;

    @EJB
    LandLotController landLotController;

    @PostConstruct
    public void init() {
        landLotList = landLotController.getLandLots();
    }

    public List<LandLot> getLandLotList() {
        return landLotList;
    }
}
