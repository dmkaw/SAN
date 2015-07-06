/*
 * EVI - Land Lot Register
 */
package com.dmkaw.evi.ownership;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Dominik Kawczyński
 */
@Named
@RequestScoped
public class PersonManagedBean {

    private Long id;
    private String firstName;
    private String lastName;
    private String pesel;
    private List<RegistryUnion> registryUnions;

    @EJB
    PersonContoller pc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public List<RegistryUnion> getRegistryUnions() {
        return registryUnions;
    }

    public void setRegistryUnions(List<RegistryUnion> registryUnions) {
        this.registryUnions = registryUnions;
    }

    public void addEditPerson() {
        if (registryUnions != null) {
            Set<RegistryUnion> removeDuplicates = new HashSet<>(registryUnions);
            registryUnions.clear();
            registryUnions.addAll(removeDuplicates);
        }
        try {
            if(id == null || id == 0){
                if(pc.addPerson(firstName, lastName, pesel, registryUnions)){
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "Person succesfuly added."));
                }
                else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "Person with given PESEL already exist."));
                }
            }
            else {
                if(pc.editPerson(id, firstName, lastName, pesel, registryUnions)){
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "Person data succesfuly edited."));
                }
                else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "Person not found."));
                }
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "Person not saved, check given data."));
        }

    }
    
    public String preapreForEdit(Long id) {
        Person p = pc.findPersonById(id);
        this.id = p.getId();
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        this.pesel = p.getPesel();
        this.registryUnions = p.getIndividualRegistryUnion();
        return "person";
    }
}
