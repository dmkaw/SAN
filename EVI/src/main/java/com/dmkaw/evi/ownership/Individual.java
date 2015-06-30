package com.dmkaw.evi.ownership;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "INDIVIDUALS")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Individual {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	long id;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="registryunion_id")
	List<RegistryUnion> individualRegistryUnion = new ArrayList<>();

	public List<RegistryUnion> getIndividualRegistryUnion() {
		return individualRegistryUnion;
	}

	public void setIndividualRegistryUnion(List<RegistryUnion> ru) {
		this.individualRegistryUnion = ru;
	}

	public long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + id);
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
		Individual other = (Individual) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
