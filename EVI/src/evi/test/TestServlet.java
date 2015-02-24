package evi.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import evi.estates.CadastralMunicipality;
import evi.estates.LandLot;
import evi.ownership.Institution;
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
		}
		
		Person viole = new Person("Viole", "Feetable", "81021181425");
		Person fiona = new Person("Fiona", "Heellicker", "63060796321");
		
		eu.addPerson("Jack", "Handsome", "75060623541");
		eu.addPerson(viole);
		eu.addPerson(fiona);
		
		
		LandLot p1 = new LandLot();
		LandLot p2 = new LandLot();
		LandLot p3 = new LandLot("106/2", CadastralMunicipality.BUKOWA);
		LandLot p4 = new LandLot("15", CadastralMunicipality.KŁODNO);
		LandLot p5 = new LandLot("13/1", CadastralMunicipality.KŁODNO);
		LandLot p6 = new LandLot("13/2", CadastralMunicipality.PODJAZY);
		p1.setLlid("15/8");
		p1.setCm(CadastralMunicipality.MŚCISZEWICE);
		p2.setLlid("37/6");
		p2.setCm(CadastralMunicipality.SUCHA);
		p2.setArea(87);
		
		
		eu.addLandLot(p1);
		eu.addLandLot(p2);
		eu.addLandLot(p3);
		eu.addLandLot(p4);
		eu.addLandLot(p5);
		eu.addLandLot(p6);
		eu.addLandLot(p6);
		
		
		
		Institution ins = new Institution("IPO - Intergalaxy Land Lot Owners", "123456789");
		
		eu.addInstitution(ins);
		
		
		eu.addRegistryUnion("15/8", fiona);
		eu.addRegistryUnion("156/10", fiona);
		
		/*
		
		List<Individual> owners = new ArrayList<Individual>();
		owners.add(viole);
		
		List<Planet> planets = new ArrayList<Planet>();
		planets.add(p1);
		planets.add(p2);
		
		gu.addRegistryUnion(p2, fiona);
		
		List<Individual> owners2 = new ArrayList<Individual>();
		owners2.add(ins);
		
		List<Planet> planets2 = new ArrayList<Planet>();
		planets2.add(p3);
		planets2.add(p4);
		
		//gu.addRegistryUnion(planets2, owners2);
		
		List<Individual> owners3 = new ArrayList<Individual>();
		owners3.add(fiona);
		
		List<Planet> planets3 = new ArrayList<Planet>();
//		planets3.add(p1);
//		planets3.add(p4);
		planets3.add(p5);
		planets3.add(p6);
		
		//gu.addRegistryUnion(planets3, owners3);
		
		*/
		
	}

}
