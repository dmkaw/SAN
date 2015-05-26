package com.dmkaw.evi.ownership;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
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
	int id;
	
	@ManyToMany
	@JoinColumn(name="registryunion_id")
	List<RegistryUnion> ru = new ArrayList<RegistryUnion>();

	public List<RegistryUnion> getRu() {
		return ru;
	}

	public void setRu(List<RegistryUnion> ru) {
		this.ru = ru;
	}

	public int getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
