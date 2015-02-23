package kemod.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kemod.galaxy.GalaxyUtilities;
import kemod.ownership.Individual;
import kemod.ownership.Institution;
import kemod.ownership.Person;
import kemod.planet.Planet;

@WebServlet("/servlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	GalaxyUtilities gu;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		
		try(PrintWriter out = response.getWriter()){
			out.println("<h1>Welcome!</h1>");
		}
		
		Person viole = new Person("Viole", "Feetable"); 
		Person fiona = new Person("Fiona", "Heellicker");
		
		gu.addPerson("Jack", "Handsome");
		gu.addPerson(viole);
		gu.addPerson(fiona);
		
		
		Planet p1 = new Planet();
		Planet p2 = new Planet();
		Planet p3 = new Planet("Hexor");
		Planet p4 = new Planet("Zypher");
		Planet p5 = new Planet("Pluto");
		Planet p6 = new Planet("Haxana");
		p1.setName("Pixon");
		p2.setName("Titanium");
		
		gu.addPlanet(p1);
		gu.addPlanet(p2);
		gu.addPlanet(p3);
		gu.addPlanet(p4);
		gu.addPlanet(p5);
		gu.addPlanet(p6);
		
		
		Institution ins = new Institution("IPO - Intergalaxy Planet Owners");
		
		gu.addInstitution(ins);
		
		
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
		
	}

}
