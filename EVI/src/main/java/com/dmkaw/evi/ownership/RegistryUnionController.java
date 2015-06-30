/*
 * EVI - Land Lot Register
 */
package com.dmkaw.evi.ownership;

import com.dmkaw.evi.estates.LandLot;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Dominik Kawczyński
 */
@Stateless
public class RegistryUnionController {

    @PersistenceContext(unitName = "EVI")
    private EntityManager em;

    public List<RegistryUnion> findAllRegistryUnions() {
        Query query = em.createNamedQuery("findAllRu");
        return query.getResultList();
    }

    public List<LandLot> findLandLotsAvailableToBind() {
        Query query = em.createNamedQuery("findLandLotWithoutRegistryUnion");
        return query.getResultList();
    }

    public RegistryUnion findRegUnitByLandRegister(String param) {
        Query query = em.createNamedQuery("findRuByLandRegister");
        query.setParameter("landReg", param);
        final List<RegistryUnion> result = query.getResultList();
        return result.get(0);
    }

    public boolean addRegistryUnion(String landRegisterNumber, List<LandLot> landLotList){
        Query query = em.createNamedQuery("findRuByLandRegister");
        query.setParameter("landReg", landRegisterNumber);
        List<RegistryUnion> found = query.getResultList();
        if (!found.isEmpty()) {
            return false;
        }
        RegistryUnion registryUnion = new RegistryUnion(landRegisterNumber);
        registryUnion.setLandLots(landLotList);
        em.persist(registryUnion);
        return true;
    }

}
