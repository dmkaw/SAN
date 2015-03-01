package evi.util;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import evi.estates.CadastralMunicipality;
import evi.estates.LandLot;
import evi.ownership.Institution;
import evi.ownership.LandRegister;
import evi.ownership.Person;
import evi.ownership.RegistryUnion;

@Stateless
public class EviUtilities {

	@PersistenceContext(unitName = "EVI")
	private EntityManager em;
	
	
	public void addLandLot(LandLot lLot){
		Query q = em.createNamedQuery("findLandLotIdByLLId");
		q.setParameter("llId", lLot.getLlId());
		
		
		@SuppressWarnings("unchecked")
		final List<Integer> found = q.getResultList();
		if(found.isEmpty()) em.persist(lLot);
		else {
			// TODO: Make something more in case of adding existing land lot
			System.out.println("Land lot not added - already exist.");
		}
		
	}
	
	public void addLandLot(String name, CadastralMunicipality cm, long area){
		LandLot lLot = new LandLot(name, cm, area);
		addLandLot(lLot);
	}
	
	public void addPerson(String fName, String lName, String pesel){
		Person person = new Person(fName, lName, pesel);
		addPerson(person);
	}
	
	public void addPerson(Person person){
		em.persist(person);
	}
	
	
	public void addInstitution(Institution ins){
		em.persist(ins);
	}
	
	
	public void addRegistryUnion(String llId, LandRegister lr){
		Query q = em.createNamedQuery("findLandLotIdByLLId");
		q.setParameter("llId", llId);
		
		@SuppressWarnings("unchecked")
		final List<Integer> found = q.getResultList();
		
		if(!found.isEmpty()){
			LandLot l1 = em.find(LandLot.class, found.get(0));
			RegistryUnion ru = new RegistryUnion();
			
			l1.setRu(ru);
			ru.getLandLots().add(l1);
			ru.setLr(lr);
			lr.setRu(ru);
			
			em.persist(ru);
			em.persist(lr);
			
			
		}
		else{
			// TODO: Make something more in case of trying to add wrong registry union
			System.out.println("ERROR : Registry union not created (no such land lot).");
		}
	}
	
	public void addPersonToRu(Person person, String llId){
		Query q1 = em.createNamedQuery("findRuIdByLandLotLlId");
		q1.setParameter("llId", llId);
		
		@SuppressWarnings("unchecked")
		final List<Integer> foundRu = q1.getResultList();
		
		if(!foundRu.isEmpty()){
			RegistryUnion ru = em.find(RegistryUnion.class, foundRu.get(0));
			
			Query q2 = em.createNamedQuery("findPersonIdByNameAndPesel");
			q2.setParameter("fName", person.getFirstName());
			q2.setParameter("lName", person.getLastName());
			q2.setParameter("pesel", person.getPesel());
			
			@SuppressWarnings("unchecked")
			final List<Integer> foundPerson = q2.getResultList();
			if(foundPerson.isEmpty()) em.persist(person);
			else person = em.find(Person.class, foundPerson.get(0));
			ru.getOwners().add(person);
			person.getRu().add(ru);
		}
		else{
			// TODO: Make something more in case of trying to person to not existing land lot/registry union
			System.out.println("ERROR : Person was not added to registry union (no registry union to given land lot).");
		}
	}
	
	
}
