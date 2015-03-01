package evi.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import evi.estates.Building;
import evi.estates.BuildingType;
import evi.estates.CadastralMunicipality;
import evi.estates.LandLot;
import evi.ownership.Institution;
import evi.ownership.LandRegister;
import evi.ownership.Person;
import evi.util.EviUtilities;

@WebServlet("/servlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	EviUtilities eu;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		
		try(PrintWriter out = response.getWriter()){
			out.println("<h1>Welcome!</h1>");
			out.println("We will run some tests.");
		}
				
		Person viole = new Person("Viole", "Nowak", "81021181425");
		eu.addPerson(viole);
		
		Person fiona = new Person("Fiona", "Surnamsky", "63060796321");
		eu.addPerson(fiona);
		
		eu.addPerson("Jack", "Handsome", "75060623541");
		eu.addPerson("Pete", "Black", "75060623542");
		eu.addPerson("Bob", "Noname", "75060623543");
		
		Institution ins = new Institution("BLLO - Big Land Lot Owners", "123456789");
		eu.addInstitution(ins);
		
		LandLot p1 = new LandLot();
		p1.setLlid("15/8");
		p1.setCm(CadastralMunicipality.MŚCISZEWICE);
		p1.setArea(3861);
		p1.getBuildings().add(new Building(BuildingType.RESIDENTAL, 101.53f));
		p1.getBuildings().add(new Building(BuildingType.RETAIL, 50.12f));
		
		LandLot p2 = new LandLot();
		p2.setLlid("37/6");
		p2.setCm(CadastralMunicipality.SUCHA);
		p2.setArea(1012);
		p2.getBuildings().add(new Building(BuildingType.HEALTHCARE, 2542.06f));
		
		LandLot p3 = new LandLot("106/2", CadastralMunicipality.BUKOWA);
		p3.getBuildings().add(new Building(BuildingType.INDUSTRIAL, 1008.00f));
		p3.getBuildings().add(new Building(BuildingType.INDUSTRIAL, 534.12f));
		
		LandLot p4 = new LandLot("15", CadastralMunicipality.KŁODNO);
		LandLot p5 = new LandLot("13/1", CadastralMunicipality.KŁODNO);
		LandLot p6 = new LandLot("13/2", CadastralMunicipality.PODJAZY);
				
		eu.addLandLot(p1);
		eu.addLandLot(p2);
		eu.addLandLot(p3);
		eu.addLandLot(p4);
		eu.addLandLot(p5);
		eu.addLandLot(p6);
		eu.addLandLot(p6); // Test on duplicate LandLot
				
		eu.addRegistryUnion("15/8", new LandRegister("LD1M/00001234/1"));
		eu.addRegistryUnion("13/1", new LandRegister("LD1M/00001235/3"));
		eu.addRegistryUnion("156/10", new LandRegister("LD1M/00001236/3"));
		eu.addRegistryUnion("13/2", new LandRegister("LD1M/00001238/3"));
		
		eu.addPersonToRu(fiona, "15/8");
		eu.addPersonToRu(viole, "13/2");
		eu.addPersonToRu(fiona, "500/10");
		eu.addPersonToRu(new Person("Anna", "Testova", "71120506321"), "15/8");
		
	}

}
