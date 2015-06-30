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
public class RegistryUnionListManagedBean {
    
    List<RegistryUnion> registryUnionList;
    
    @EJB
    RegistryUnionController ruc;

    @PostConstruct
    public void init(){
        registryUnionList = ruc.findAllRegistryUnions();
    }
    
    
    public List<RegistryUnion> getRegistryUnionList() {
        return registryUnionList;
    }

    public void setRegistryUnionList(List<RegistryUnion> registryUnionList) {
        this.registryUnionList = registryUnionList;
    }
    
}
