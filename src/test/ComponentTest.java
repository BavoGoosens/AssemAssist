package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import component.Body;

public class ComponentTest {
	
	private Body body;
	
	@Before
	public void setUp() throws Exception {
		body = new Body("sedan",1000);
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
