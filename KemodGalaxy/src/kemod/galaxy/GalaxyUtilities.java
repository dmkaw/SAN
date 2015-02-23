package kemod.galaxy;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import kemod.ownership.Individual;
import kemod.ownership.Institution;
import kemod.ownership.Person;
import kemod.ownership.RegistryUnion;
import kemod.planet.Planet;

@Stateless
public class GalaxyUtilities {

	@PersistenceContext(unitName = "KemodGalaxy")
	private EntityManager em;
	
	
	public void addPlanet(Planet planet){
		em.persist(planet);
	}
	
	public void addPerson(String fName, String lName){
		Person person = new Person(fName, lName);
		em.persist(person);
	}
	
	public void addPerson(Person person){
		em.persist(person);
	}
	
	public void addInstitution(Institution ins){
		em.persist(ins);
	}
	
	
	public void addRegistryUnion(Planet planet, Individual owner){
		RegistryUnion ru = new RegistryUnion();

		List<Planet> lPlanets = new ArrayList<Planet>();
		
		Planet p1 = (Planet)em.find(Planet.class, 22);
		Planet p2 = (Planet)em.find(Planet.class, 23);
		
		lPlanets.add(p1);
		lPlanets.add(p2);
		
		p1.setRu(ru);
		p2.setRu(ru);
		
		ru.setPlanet(lPlanets);
		
//		List<Individual> lOwners = new ArrayList<Individual>();
//		lOwners.add((Individual)em.find(Individual.class, 12));
//		ru.setOwner(lOwners);
		em.persist(ru);
		em.merge(p1);
		em.merge(p2);
	}
	
	
}
