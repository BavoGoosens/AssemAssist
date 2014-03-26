package control;

import java.util.ArrayList;

import org.joda.time.DateTime;

import businessmodel.*;
import component.*;



/**
 * @author Team 10
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		setup();
	}
	
	private static void setup(){
		// aanmaak van componenten
		// alle soorten bodies
		Body body1 = new Body("sedan",50);
		Body body2 = new Body("break",50);
		
		// alle mogelijke kleuren
		Color color1 = new Color("red",50);
		Color color2 = new Color("blue",50);
		Color color3 = new Color("white",50);
		Color color4 = new Color("black",50);	
		
		// alle mogelijke engine types 
		Engine engine1 = new Engine("standard 2l 4 cilinders",50);
		Engine engine2 = new Engine("performance 2.5l 6 cilinders",50);
		
		// alle mogelijke gearboxes 
		Gearbox gearbox1 = new Gearbox("6 speed manual",1000);
		Gearbox gearbox2 = new Gearbox("5 speed automatic",1000);
		
		// alle mogelijke soorten stoelen
		Seats seats1 = new Seats("leather black",1000);
		Seats seats2 = new Seats("leather white",1000);
		Seats seats3 = new Seats("vinyl grey",1000);
		
		// alle mogelijke climate control systemen
		Airco airco1 = new Airco("manual climate control",1000);
		Airco airco2 = new Airco("automatic climate control",1000);
		
		// alle mogelijke wiel types
		Wheels wheels1 = new Wheels("comfort",1000);
		Wheels wheels2 = new Wheels("sports",1000);
		
		// aanmaak van carmodelspecifications
		
		// Audi A1
		ArrayList<Component> audiA1 = new ArrayList<Component>();
		audiA1.add(body1);
		audiA1.add(color1);
		audiA1.add(color2);
		audiA1.add(color3);
		audiA1.add(color4);
		audiA1.add(airco1);
		audiA1.add(engine1);
		audiA1.add(gearbox1);
		audiA1.add(seats1);
		audiA1.add(seats2);
		audiA1.add(wheels1);
		CarModelSpecification cmsA1 = new CarModelSpecification(audiA1);
		
		// Audi A8
		ArrayList<Component> audiA8 = new ArrayList<Component>();
		audiA8.add(body2);
		audiA8.add(color2);
		audiA8.add(color3);
		audiA8.add(airco2);
		audiA8.add(engine2);
		audiA8.add(gearbox2);
		audiA8.add(seats1);
		audiA8.add(seats2);
		audiA8.add(wheels2);
		audiA8.add(wheels1);
		CarModelSpecification cmsA8 = new CarModelSpecification(audiA8);
		
		// Audi R8
		ArrayList<Component> audiR8 = new ArrayList<Component>();
		audiR8.add(body1);
		audiR8.add(body2);
		audiR8.add(color1);
		audiR8.add(color4);
		audiR8.add(airco1);
		audiR8.add(engine2);
		audiR8.add(engine1);
		audiR8.add(gearbox1);
		audiR8.add(seats1);
		audiR8.add(seats2);
		audiR8.add(wheels2);
		audiR8.add(wheels1);
		CarModelSpecification cmsR8 = new CarModelSpecification(audiR8);

		// aanmaak van carmodels
		CarModel audi_A1 = new CarModel("Audi A1",cmsA1);
		CarModel audi_A8 = new CarModel("Audi A8",cmsA8);
		CarModel audi_R8 = new CarModel("Audi R8",cmsR8);
		ArrayList<CarModel> carmodels = new ArrayList<CarModel>();
		carmodels.add(audi_A1);
		carmodels.add(audi_A8);
		carmodels.add(audi_R8);
		
		// aanmaak van actions
		Action action1 = new Action("Paint car");
		Action action2 = new Action("Assembly Car Body");
		Action action3 = new Action("Insert engine");
		Action action4 = new Action("Insert gearbox");
		Action action5 = new Action("Install seats");
		Action action6 = new Action("Install Airco");
		Action action7 = new Action("Mount Wheels");
		
		ArrayList<Action> actions1 = new ArrayList<Action>();
		actions1.add(action1);	
		ArrayList<Action> actions2 = new ArrayList<Action>();
		actions2.add(action2);
		ArrayList<Action> actions3 = new ArrayList<Action>();
		actions3.add(action3);
		ArrayList<Action> actions4 = new ArrayList<Action>();
		actions4.add(action4);
		ArrayList<Action> actions5 = new ArrayList<Action>();
		actions5.add(action5);
		ArrayList<Action> actions6 = new ArrayList<Action>();
		actions6.add(action6);
		ArrayList<Action> actions7 = new ArrayList<Action>();
		actions7.add(action7);
		
		// aanmaak van assembly tasks 
		
		AssemblyTask assem1 = new AssemblyTask("Paint car",actions1);
		AssemblyTask assem2 = new AssemblyTask("Assembly Car Body",actions2);
		AssemblyTask assem3 = new AssemblyTask("Insert engine",actions3);
		AssemblyTask assem4 = new AssemblyTask("Insert gearbox",actions4);
		AssemblyTask assem5 = new AssemblyTask("Install seats",actions5);
		AssemblyTask assem6 = new AssemblyTask("Install Airco",actions6);
		AssemblyTask assem7 = new AssemblyTask("Mount Wheels",actions7);
		
		ArrayList<AssemblyTask> tasks1 = new ArrayList<AssemblyTask>();
		ArrayList<AssemblyTask> tasks2 = new ArrayList<AssemblyTask>();
		ArrayList<AssemblyTask> tasks3 = new ArrayList<AssemblyTask>();
	
		tasks1.add(assem1);
		tasks1.add(assem2);
		tasks2.add(assem3);
		tasks2.add(assem4);
		tasks3.add(assem5);
		tasks3.add(assem6);
		tasks3.add(assem7);
		
		// aanmaak van workstations 
		
		WorkPost wp1 = new WorkPost("car body post",tasks1);
		WorkPost wp2 = new WorkPost("drive train post",tasks2);
		WorkPost wp3 = new WorkPost("accessories post",tasks3);
		ArrayList<WorkPost> workposts = new ArrayList<WorkPost>();
		workposts.add(wp1);
		workposts.add(wp2);
		workposts.add(wp3);
		
		// aanmaak van assembly line
		AssemblyLine assemblyline = new AssemblyLine(workposts);
		
		// aanmaak van ordermanager
		OrderManager ordermanager = new OrderManager(carmodels);
		
		// aanmaak van production scheduler
		
		DateTime start = new DateTime().withHourOfDay(6).withMinuteOfHour(0).withSecondOfMinute(0);
		ProductionScheduler prod1 = new ProductionScheduler(ordermanager,start,assemblyline);
		ordermanager.setProductionScheduler(prod1);
		
		// aanmaak users
		GarageHolder garageholder = new GarageHolder("jan","Willens","Jan2014","cc12");
		Mechanic mechanic = new Mechanic("Peter","Winkens","Peter2014","cc12");
		Manager manager = new Manager("Fred","Janssen","Fred2014","cc12");
		
		// aanmaak usermanagement 
		
		// UserManagment fixen.
		UserManagement usermanagement = new UserManagement();
		
		// aanmaak car manufacturing company
		// Controller moet hier aangemaakt worden?
		CarManufacturingCompany cmc = new CarManufacturingCompany(ordermanager,usermanagement);
	}
}
