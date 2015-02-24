package evi.ownership;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name="persons")
@PrimaryKeyJoinColumn(name="individuals_id")
@NamedQuery(name="findPersonIdByName", query="select p.id from Person p where p.firstName = :fName and p.lastName = :lName")
public class Person extends Individual{


	@Column(nullable = false)
	String firstName;
	
	@Column(nullable = false)
	String lastName;

	@Column(unique = true)
	String pesel;
	
	public Person(){}
	
	public Person(String firstName, String lastName, String pesel) {
		this.firstName = firstName;
		this.lastName = lastName;
		if (pesel.length() != 11) throw new IllegalArgumentException("Pesel have to be 11 digits");
		this.pesel = pesel;
	}

	public int getId() {
		return id;
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
	
}
