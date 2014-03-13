package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import component.Airco;
import component.Body;
import component.Color;
import component.Engine;
import component.Gearbox;
import component.Seats;
import component.Wheels;
import businessmodel.CarModel;
import businessmodel.CarModelSpecification;

public class CarModelTest {
	
	private CarModel audiA6;
	private ArrayList<Body> bodies;
	private ArrayList<Color> colors;
	private ArrayList<Engine> engines;
	private ArrayList<Airco> aircos;
	private ArrayList<Gearbox> gearboxes;
	private ArrayList<Wheels> wheelss;
	private ArrayList<Seats> seatss;
	private Body body;	private Body body2;
	private Color color; private Color color2;
	private Engine engine; private Engine engine2;
	private Gearbox gearbox; private Gearbox gearbox2;
	private Seats seats; private Seats seats2;
	private Airco airco; private Airco airco2;
	private Wheels wheels; private Wheels wheels2;
	private CarModelSpecification cms;

	@Before
	public void setUp() throws Exception {
		body = new Body("sedan",1000);
		color = new Color("red",1000);
		engine = new Engine("standard 2l 4 cilinders",1000);
		gearbox = new Gearbox("6 speed manual",1000);
		seats = new Seats("leather black",1000);
		airco = new Airco("manual",1000);
		wheels = new Wheels("comfort",1000);
		body2 = new Body("sedan",1000);
		color2 = new Color("red",1000);
		engine2 = new Engine("standard 2l 4 cilinders",1000);
		gearbox2 = new Gearbox("6 speed manual",1000);
		seats2 = new Seats("leather black",1000);
		airco2 = new Airco("manual",1000);
		wheels2 = new Wheels("comfort",1000);
		bodies = new ArrayList<Body>();
		colors = new ArrayList<Color>();
		engines = new ArrayList<Engine>();
		gearboxes = new ArrayList<Gearbox>();
		seatss = new ArrayList<Seats>();
		aircos = new ArrayList<Airco>();
		wheelss = new ArrayList<Wheels>();
		bodies.add(body2);
		bodies.add(body);
		colors.add(color);
		colors.add(color2);
		engines.add(engine);
		engines.add(engine2);
		gearboxes.add(gearbox);
		gearboxes.add(gearbox2);
		seatss.add(seats);
		seatss.add(seats2);
		aircos.add(airco);
		aircos.add(airco2);
		wheelss.add(wheels);
		wheelss.add(wheels2);
		cms = new CarModelSpecification(bodies,colors,engines,gearboxes,seatss,aircos,wheelss);
		audiA6 = new CarModel("Audi A6",cms);
	}

	@Test
	public void test() {
		assertEquals("Audi A6",this.audiA6.getCarmodel());
		assertEquals(this.cms,audiA6.getCarModelSpecification());
		assertEquals(audiA6.toString(),"carmodel= Audi A6");
	}

}
