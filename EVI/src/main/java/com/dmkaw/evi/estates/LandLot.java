package com.dmkaw.evi.estates;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.dmkaw.evi.ownership.RegistryUnion;
import org.hibernate.annotations.*;

import javax.persistence.NamedQueries;

/**
 * Entity implementation class for Entity: LandLot
 *
 */
@Entity
@Table(name = "LAND_LOTS")
@NamedQueries({
        @NamedQuery(name="findLandLotIdByLLId", query="select l.id from LandLot l where l.landLotId = :llId"),
        @NamedQuery(name="findLandLotByLLId", query="select l from LandLot l where l.landLotId = :llId"),
        @NamedQuery(name="findAllLandLots", query="select l from LandLot l"),
        @NamedQuery(name="findLandLotById", query="select l from LandLot l where l.id = :id"),
        @NamedQuery(name="findLandLotWithoutRegistryUnion", query="select l from LandLot l where l.landLotRegistryUnion = null")
        })
         
public class LandLot implements Serializable {

	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long id;
	
	@Column(unique=true, nullable=false, length=12)
	private String landLotId;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private CadastralMunicipality cadastralMunicipality;
	
	//Area in square meters
	double area;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="registryunion_id")
	private RegistryUnion landLotRegistryUnion;
	
	@OneToMany(fetch=FetchType.LAZY, cascade={CascadeType.ALL})
	@JoinColumn(name="landlot_id")
	List<Building> buildings = new ArrayList<>();
	
	public LandLot() {
	}
	
	public LandLot(String name, CadastralMunicipality cadastralMunicipality) {
		this.landLotId = name;
		this.cadastralMunicipality = cadastralMunicipality;
	}
	
	public LandLot(String name, CadastralMunicipality cadastralMunicipality, double area) {
		this(name, cadastralMunicipality);
		this.area = area;
	}

        public LandLot(String name, CadastralMunicipality cadastralMunicipality, double area, RegistryUnion landLotRegistryUnion) {
		this(name, cadastralMunicipality, area);
                this.landLotRegistryUnion = landLotRegistryUnion;
	}
	
	public long getId() {
		return id;
	}

	public String getLandLotId() {
		return landLotId;
	}

	public void setLlid(String llId) {
		this.landLotId = llId;
	}

	public double getArea() {
		return area;
	}

	public CadastralMunicipality getCadastralMunicipality() {
		return cadastralMunicipality;
	}

	public void setCadastralMunicipality(CadastralMunicipality cm) {
		this.cadastralMunicipality = cm;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public RegistryUnion getLandLotRegistryUnion() {
		return landLotRegistryUnion;
	}

	public void setLandLotRegistryUnion(RegistryUnion ru) {
		this.landLotRegistryUnion = ru;
	}
	
	public List<Building> getBuildings() {
		return buildings;
	}
	

	public void setBuildings(List<Building> buildings) {
		this.buildings = buildings;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cadastralMunicipality == null) ? 0 : cadastralMunicipality.hashCode());
		result = prime * result + ((landLotId == null) ? 0 : landLotId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LandLot other = (LandLot) obj;
		if (cadastralMunicipality != other.cadastralMunicipality)
			return false;
		if (landLotId == null) {
			if (other.landLotId != null)
				return false;
		} else if (!landLotId.equals(other.landLotId))
			return false;
		return true;
	}

	private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return landLotId + " : " + cadastralMunicipality;
    }

}
