package businessmodel;

import java.util.ArrayList;

import component.Airco;
import component.Body;
import component.Color;
import component.Engine;
import component.Gearbox;
import component.Seats;
import component.Wheels;

/**
 * A class that represents an inventory for a factory. Here we hold all the different component.
 * 
 * @author SWOP team 10 2014
 *
 */
public class Inventory {

	/**
	 * A list that holds all the bodies of the inventory.
	 */
	public ArrayList <Body> bodytypes = new ArrayList<Body>();
	
	/**
	 * A list that holds all the engines of the inventory,
	 */
	public ArrayList <Engine> enginetypes = new ArrayList<Engine>();
	
	/**
	 * A list that holds all the aircos of the inventory.
	 */
	public ArrayList <Airco> aircotypes = new ArrayList<Airco>();

	/**
	 * A list that holds all the colors of the inventory.
	 */
	public ArrayList <Color> colortypes = new ArrayList<Color>();

	/**
	 * A list that holds all the gear boxes of the inventory.
	 */
	public ArrayList <Gearbox> gearboxtypes = new ArrayList<Gearbox>();

	/**
	 * A list that holds all the seats types of the inventory.
	 */
	public ArrayList <Seats> seattypes = new ArrayList<Seats>();

	/**
	 * A list that holds all the wheel types of the inventory.
	 */
	public ArrayList <Wheels> wheeltypes = new ArrayList<Wheels>();

	/**
	 * A Constructor that creates a new inventory list.
	 */
	public Inventory() {
		Body body1 = new Body("sedan",50);
		Body body2 = new Body("break",50);
		this.bodytypes.add(body1);
		this.bodytypes.add(body2);
		
		Color color1 = new Color("red",50);
		Color color2 = new Color("blue",50);
		Color color3 = new Color("white",50);
		Color color4 = new Color("black",50);
		this.colortypes.add(color1);
		this.colortypes.add(color2);
		this.colortypes.add(color3);
		this.colortypes.add(color4);		
		
		Engine engine1 = new Engine("standard 2l 4 cilinders",50);
		Engine engine2 = new Engine("performance 2.5l 6 cilinders",50);
		this.enginetypes.add(engine1);
		this.enginetypes.add(engine2);
		
		Gearbox gearbox1 = new Gearbox("6 speed manual",1000);
		Gearbox gearbox2 = new Gearbox("5 speed automatic",1000);
		this.gearboxtypes.add(gearbox1);
		this.gearboxtypes.add(gearbox2);
		
		Seats seats1 = new Seats("leather black",1000);
		Seats seats2 = new Seats("leather white",1000);
		Seats seats3 = new Seats("vinyl grey",1000);
		this.seattypes.add(seats1);
		this.seattypes.add(seats2);
		this.seattypes.add(seats3);

		Airco airco1 = new Airco("manual climate control",1000);
		Airco airco2 = new Airco("automatic climate control",1000);
		this.aircotypes.add(airco1);
		this.aircotypes.add(airco2);

		
		Wheels wheels1 = new Wheels("comfort",1000);
		Wheels wheels2 = new Wheels("sports",1000);
		this.wheeltypes.add(wheels1);
		this.wheeltypes.add(wheels2);	
	}
}
