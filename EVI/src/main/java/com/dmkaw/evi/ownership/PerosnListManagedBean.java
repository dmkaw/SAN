/*
 * EVI - Land Lot Register
 */
package com.dmkaw.evi.ownership;

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
public class PerosnListManagedBean {
    
    List<Person> personList;

    @EJB
    PersonContoller pc;
    
    @PostConstruct
    public void init(){
        personList = pc.findAllPersons();
    }
    
    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
    
}
