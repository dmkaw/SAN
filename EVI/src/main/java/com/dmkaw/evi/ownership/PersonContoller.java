/*
 * EVI - Land Lot Register
 */
package com.dmkaw.evi.ownership;

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
public class PersonContoller {

    @PersistenceContext(unitName = "EVI")
    private EntityManager em;

    public List<Person> findAllPersons() {
        Query query = em.createNamedQuery("findAllPersons");
        return query.getResultList();
    }

    public boolean editPerson(Long id, String firstName, String lastName, String pesel, List<RegistryUnion> registryUnions) {
        Query query = em.createNamedQuery("findPersonById");
        query.setParameter("id", id);
        List<Person> found = query.getResultList();
        if (found.isEmpty()) {
            return false;
        }
        Person person = found.get(0);
        person.firstName = firstName;
        person.lastName = lastName;
        person.pesel = pesel;
        person.individualRegistryUnion = registryUnions;
        em.merge(person);
        return true;
    }

    public boolean addPerson(String firstName, String lastName, String pesel, List<RegistryUnion> registryUnions) {
        Person p = new Person(firstName, lastName, pesel, registryUnions);
        Query query = em.createNamedQuery("findPersonByPesel");
        query.setParameter("pesel", pesel);
        List<Person> found = query.getResultList();
        if (!found.isEmpty()) {
            return false;
        }
        em.persist(p);
        return true;
    }
    
    public Person findPersonById(Long id) {
        Query query = em.createNamedQuery("findPersonById");
        query.setParameter("id", id);
        List<Person> found = query.getResultList();
        if(found.isEmpty()){
            throw new IllegalArgumentException();
        }
        return found.get(0);
    }
}
