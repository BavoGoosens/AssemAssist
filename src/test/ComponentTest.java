package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import businessmodel.category.*;


public class ComponentTest {
	
	private Body body;
	
	@Before
	public void setUp() throws Exception {
		body = new Body();
	}

	@Test
	public void test() {
		assertEquals("sedan",body.getName());
		assertEquals(1000,body.getPrice(),0);
		assertEquals(body.toString(),"name= sedan, price= "+ body.getPrice());

		try {body = new Body(null,1000);}
		catch (IllegalArgumentException e) {}

		try {body = new Body("sedan",-1);}
		catch (IllegalArgumentException e) {}

		try {body = new Body("sedan",1000);}
		catch (IllegalArgumentException e) {}
	}

}
