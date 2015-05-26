package com.dmkaw.evi.ownership;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name="INSTITUTIONS")
@NamedQuery(name="findInstitutionIdByInstitutionNumber", query="select i.id from Institution i where i.institutionNumber = :iNum")
@PrimaryKeyJoinColumn(name="individuals_id")
public class Institution extends Individual{

	@Column(nullable = false, length = 30)
	String name;
	
	@Column(nullable = false, length = 9)
	String institutionNumber;
	
	@Column(length = 100)
	String address;
	
	public Institution(){}
	
	public Institution(String name, String iNumber){
		this.name = name;
		if (iNumber.length() != 9) throw new IllegalArgumentException("Institution Number have to be 9 digits");
		this.institutionNumber = iNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getInstitutionNumber() {
		return institutionNumber;
	}

	public void setInstitutionNumber(String institutionNumber) {
		if (institutionNumber.length() != 9) throw new IllegalArgumentException("Institution Number have to be 9 digits");
		this.institutionNumber = institutionNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime
				* result
				+ ((institutionNumber == null) ? 0 : institutionNumber
						.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Institution))
			return false;
		Institution other = (Institution) obj;
		if (institutionNumber == null) {
			if (other.institutionNumber != null)
				return false;
		} else if (!institutionNumber.equals(other.institutionNumber))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
