package com.dmkaw.evi.util;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.dmkaw.evi.estates.CadastralMunicipality;
import com.dmkaw.evi.estates.LandLot;
import com.dmkaw.evi.ownership.*;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Stateless
public class EviUtilities {

    @PersistenceContext(unitName = "EVI")
    private EntityManager em;

    //TODO: Sprawdzać działkę nie tylko po numerze ale jeszcze po obrębie
    public boolean addLandLot(LandLot lLot) {
        Query q = em.createNamedQuery("findLandLotIdByLLId");
        q.setParameter("llId", lLot.getLandLotId());

        @SuppressWarnings("unchecked")
        final List<Integer> found = q.getResultList();
        if (found.isEmpty()) {
            em.persist(lLot);
            return true;
        } else {
            // TODO: Make something more in case of adding existing land lot
            System.out.println(">>> Land lot " + lLot + " not added - already exist.");
            try {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error:", "Given land lot already exixt"));
            } catch (Exception e) {

            }
            return false;
        }
    }

    public boolean addLandLot(java.lang.String name, CadastralMunicipality cm, double area, RegistryUnion ru) {
        LandLot lLot = new LandLot(name, cm, area, ru);
        return addLandLot(lLot);
    }

    public void addPerson(java.lang.String fName, java.lang.String lName, java.lang.String pesel) {
        Person person = new Person(fName, lName, pesel);
        addPerson(person);
    }

    public void addPerson(Person person) {
        em.persist(person);
    }

    public void addInstitution(Institution ins) {
        em.persist(ins);
    }

    public void addRegistryUnion(java.lang.String llId, LandRegister lr) {
        Query q = em.createNamedQuery("findLandLotIdByLLId");
        q.setParameter("llId", llId);

        @SuppressWarnings("unchecked")
        final List<Integer> found = q.getResultList();

        if (!found.isEmpty()) {
            LandLot l1 = em.find(LandLot.class, found.get(0));
            RegistryUnion ru = new RegistryUnion();

            l1.setLandLotRegistryUnion(ru);
            ru.getLandLots().add(l1);
            ru.setLr(lr);
            lr.setRu(ru);

            em.persist(ru);
            em.persist(lr);

        } else {
            // TODO: Make something more in case of trying to add wrong registry union
            System.out.println(">>> ERROR : Registry union not created (no such land lot " + llId + ").");
        }
    }

    public void addPersonToRu(Person person, java.lang.String llId) {
        Query q1 = em.createNamedQuery("findRuIdByLandLotLlId");
        q1.setParameter("llId", llId);

        @SuppressWarnings("unchecked")
        final List<Integer> foundRu = q1.getResultList();

        if (!foundRu.isEmpty()) {
            RegistryUnion ru = em.find(RegistryUnion.class, foundRu.get(0));

            Query q2 = em.createNamedQuery("findPersonIdByNameAndPesel");
            q2.setParameter("fName", person.getFirstName());
            q2.setParameter("lName", person.getLastName());
            q2.setParameter("pesel", person.getPesel());

            @SuppressWarnings("unchecked")
            final List<Integer> foundPerson = q2.getResultList();
            if (foundPerson.isEmpty()) {
                em.persist(person);
            } else {
                person = em.find(Person.class, foundPerson.get(0));
            }
            ru.getOwners().add(person);
            person.getIndividualRegistryUnion().add(ru);
        } else {
            // TODO: Make something more in case of trying to person to not existing land lot/registry union
            System.out.println(">>> ERROR : Person was not added to registry union (no registry union to given land lot - " + llId + ").");
        }
    }

    public LandLot getLandLotbyId(Long id) {
        Query query = em.createNamedQuery("findLandLotById");
        query.setParameter("id", id);
        final List<LandLot> foundLandLot = query.getResultList();
        if (foundLandLot.isEmpty()) {
            throw new RuntimeException();
        }
        LandLot landLot = foundLandLot.get(0);
        return landLot;
    }

    public boolean editLandLot(Long id, java.lang.String llId, CadastralMunicipality cm, Double area, RegistryUnion ru) {
        Query query = em.createNamedQuery("findLandLotById");
        query.setParameter("id", id);
        LandLot landLot;
        final List<LandLot> foundLandLot = query.getResultList();
        if (foundLandLot.isEmpty()) {
            //TODO: Zrobić to odrobine ładniej
            throw new RuntimeException();
        } else {
            landLot = foundLandLot.get(0);
        }
        landLot.setLlid(llId);
        landLot.setCadastralMunicipality(cm);
        landLot.setArea(area);
        landLot.setLandLotRegistryUnion(ru);
        em.merge(landLot);
        return true;
    }

}
