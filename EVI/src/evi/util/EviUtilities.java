package evi.util;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import evi.estates.CadastralMunicipality;
import evi.estates.LandLot;
import evi.ownership.Individual;
import evi.ownership.Institution;
import evi.ownership.Person;
import evi.ownership.RegistryUnion;

@Stateless
public class EviUtilities {

	@PersistenceContext(unitName = "EVI")
	private EntityManager em;
	
	
	public void addLandLot(LandLot lLot){
		Query q = em.createNamedQuery("findLandLotIdByLLId");
		q.setParameter("llid", lLot.getLlid());
		
		@SuppressWarnings("unchecked")
		final List<LandLot> lLots = q.getResultList();
		if(lLots.isEmpty()) em.persist(lLot);
		// TODO: Make something in case of adding existing land lot
		System.out.println("Land lot not added - already exist.");
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
	
	
	public void addRegistryUnion(String llid, Individual owner){
		RegistryUnion ru = new RegistryUnion();

		Query q = em.createNamedQuery("findLandLotIdByLLId");
		q.setParameter("llid", llid);
		
		@SuppressWarnings("unchecked")
		final List<Integer> lLots = q.getResultList();
		
		if(!lLots.isEmpty()){
			LandLot l1 = em.find(LandLot.class, lLots.get(0));
			l1.setRu(ru);
			ru.getLandLots().add(l1);
			em.persist(ru);
			em.merge(l1);
		}
		else System.out.println("ERROR : Registry union not created.");
	}
	
	
}
