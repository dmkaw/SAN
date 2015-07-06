package com.dmkaw.evi.estates;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Dominik on 2015-06-24.
 */
@Stateless
public class LandLotController {

    @PersistenceContext(unitName = "EVI")
    private EntityManager em;

    public List<LandLot> getLandLots() {
        Query query = em.createNamedQuery("findAllLandLots");
        return query.getResultList();
    }

    public LandLot getLandLotbyId(String landLotId) {
        Query query = em.createNamedQuery("findLandLotByLLId");
        query.setParameter("llId", landLotId);
        final List<LandLot> result = query.getResultList();
        return result.get(0);
    }
}
