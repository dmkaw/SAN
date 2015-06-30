package com.dmkaw.evi.ownership;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.dmkaw.evi.estates.LandLot;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({@NamedQuery(name = "findRuIdByLandLotLlId", query = "select r.id from RegistryUnion r join r.landLots l"
        + " where l.landLotId = :llId"),
        @NamedQuery(name = "findAllRu", query = "SELECT ru FROM RegistryUnion ru"),
        @NamedQuery(name = "findRuByLandRegister", query = "SELECT ru FROM RegistryUnion ru WHERE ru.lr.landRegisterNumber = :landReg")})
@Table(name = "REGISTRY_UNIONS")
public class RegistryUnion implements Serializable {

    @Id
    @TableGenerator(name = "TABLE_GENERATOR", table = "SEQUENCE_TABLE", pkColumnName = "SEQ_NAME",
            valueColumnName = "SEQ_COUNT", pkColumnValue = "RU_SEQ")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GENERATOR")
    int id;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "landLotRegistryUnion") // This makes each land lot can exist in ONLY one registry union
    List<LandLot> landLots = new ArrayList<>();


    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "individualRegistryUnion") // This makes individual can exist in MORE than one registry union
    List<Individual> owners = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "landregister_id")
    @Cascade(CascadeType.PERSIST)
    LandRegister lr;

    public RegistryUnion() {
    }

    public RegistryUnion(String landRegisterNumber) {
        lr = new LandRegister();
        lr.setLandRegisterNumber(landRegisterNumber);
    }

    public int getId() {
        return id;
    }

    public List<LandLot> getLandLots() {
        return landLots;
    }

    public void setLandLots(List<LandLot> landLots) {
        this.landLots = landLots;
    }

    public List<Individual> getOwners() {
        return owners;
    }

    public void setOwners(List<Individual> owners) {
        this.owners = owners;
    }

    public LandRegister getLr() {
        return lr;
    }

    public void setLr(LandRegister lr) {
        this.lr = lr;
    }

    @Override
    public java.lang.String toString() {
        return "" + id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RegistryUnion other = (RegistryUnion) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }


}
