package com.dmkaw.evi.ownership;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name="PERSONS")
@PrimaryKeyJoinColumn(name="individuals_id")
@NamedQueries({ @NamedQuery(name="findPersonIdByNameAndPesel",
                            query="SELECT p.id FROM Person p WHERE p.firstName = :fName AND"
                            + " p.lastName  = :lName AND"
                            + " p.pesel     = :pesel"),
                @NamedQuery(name="findPersonByPesel", query="SELECT p FROM Person p WHERE p.pesel = :pesel"),
                @NamedQuery(name="findPersonById", query ="SELECT p FROM Person p WHERE p.id = :id"),
                @NamedQuery(name="findAllPersons", query="SELECT p FROM Person p")})


public class Person extends Individual{


	@Column(nullable = false, length = 20)
	String firstName;
	
	@Column(nullable = false, length = 30)
	String lastName;

	@Column(unique = true, length = 11)
	String pesel;
	
	public Person(){}
	
	public Person(String firstName, String lastName, String pesel) {
		this.firstName = firstName;
		this.lastName = lastName;
		if (pesel.length() != 11) throw new IllegalArgumentException("Pesel have to be 11 digits");
		this.pesel = pesel;
	}
        
        public Person(String firstName, String lastName, String pesel, List<RegistryUnion> registruUnions) {
            this(firstName, lastName, pesel);
            this.individualRegistryUnion = registruUnions;
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
		if (pesel.length() != 11) throw new IllegalArgumentException("Pesel have to be 11 digits");
		this.pesel = pesel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((pesel == null) ? 0 : pesel.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Person))
			return false;
		Person other = (Person) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (pesel == null) {
			if (other.pesel != null)
				return false;
		} else if (!pesel.equals(other.pesel))
			return false;
		return true;
	}

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
	
	
	
}
