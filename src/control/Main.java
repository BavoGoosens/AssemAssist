package control;

import java.util.ArrayList;

import businessmodel.CarModel;
import businessmodel.CarModelSpecification;
import component.Airco;
import component.Body;
import component.Color;
import component.Component;
import component.Engine;
import component.Gearbox;
import component.Seats;
import component.Wheels;


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
		Controller controller = new Controller();
		controller.run();
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
		// aanmaak van carmodels
		
		// aanmaak van actions
		
		// aanmaak van assembly tasks 
		
		// aanmaak van workstations 
		
		// aanmaak van assembly line
		
		// aanmaak van production scheduler
		
		// aanmaak van ordermanager
		
		// aanmaak users
		
		// aanmaak usermanagement 
		
		// aanmaak car manufacturing company
		
		
	}
}
