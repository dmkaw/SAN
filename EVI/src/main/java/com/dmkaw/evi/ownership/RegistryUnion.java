package com.dmkaw.evi.ownership;

import com.dmkaw.evi.estates.LandLot;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({@NamedQuery(name = "findRuIdByLandLotLlId", query = "select r.id from RegistryUnion r join r.landLots l"
        + " where l.landLotId = :llId"),
        @NamedQuery(name = "findAllRu", query = "SELECT ru FROM RegistryUnion ru"),
        @NamedQuery(name = "findRuByLandRegister", query = "SELECT ru FROM RegistryUnion ru WHERE ru.lr.landRegisterNumber = :landReg"),
        @NamedQuery(name = "findRuById", query = "SELECT ru FROM RegistryUnion ru WHERE ru.id = :id")})
@Table(name = "REGISTRY_UNIONS")
public class RegistryUnion implements Serializable {

    @Id
    @TableGenerator(name = "TABLE_GENERATOR", table = "SEQUENCE_TABLE", pkColumnName = "SEQ_NAME",
            valueColumnName = "SEQ_COUNT", pkColumnValue = "RU_SEQ")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GENERATOR")
    Long id;

    @LazyCollection(LazyCollectionOption.FALSE)
    @Cascade(value = {CascadeType.MERGE, CascadeType.PERSIST})
    @OneToMany(mappedBy = "landLotRegistryUnion") // This makes each land lot can exist in ONLY one registry union
    List<LandLot> landLots = new ArrayList<>();


    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "individualRegistryUnion") // This makes individual can exist in MORE than one registry union
    List<Individual> owners = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "landregister_id")
    @Cascade(value = {CascadeType.MERGE, CascadeType.PERSIST})
    LandRegister lr;

    public RegistryUnion() {
    }

    public RegistryUnion(String landRegisterNumber) {
        lr = new LandRegister();
        lr.setLandRegisterNumber(landRegisterNumber);
    }

    public Long getId() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegistryUnion that = (RegistryUnion) o;

        if (landLots != null ? !landLots.equals(that.landLots) : that.landLots != null) return false;
        if (owners != null ? !owners.equals(that.owners) : that.owners != null) return false;
        return !(lr != null ? !lr.equals(that.lr) : that.lr != null);

    }

    @Override
    public int hashCode() {
        int result = landLots != null ? landLots.hashCode() : 0;
        result = 31 * result + (owners != null ? owners.hashCode() : 0);
        result = 31 * result + (lr != null ? lr.hashCode() : 0);
        return result;
    }
}
