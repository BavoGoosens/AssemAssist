package businessmodel.assemblyline;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AssemblyLineFactoryTest {
	
	AssemblyLine aLine;
	AssemblyLine bLine;
	AssemblyLine cLine;

	@Before
	public void setUp() throws Exception {
		AssemblyLineFactory aFactory = new AssemblyLineAFactory();
		aLine = aFactory.createAssemblyLine();
		bLine = new AssemblyLineBFactory().createAssemblyLine();
		cLine = new AssemblyLineCFactory().createAssemblyLine();
	}

	@Test
	public void testALine() {
		assertEquals(aLine.getNumberOfWorkPosts(), 3);
		assertEquals(aLine.getWorkPosts().get(0).getName(), "Body Work Post");
		assertEquals(aLine.getWorkPosts().get(1).getName(), "Drivetrain Work Post");
		assertEquals(aLine.getWorkPosts().get(2).getName(), "Accessories Work Post");
	}
	
	@Test
	public void testBLine() {
		assertEquals(aLine.getNumberOfWorkPosts(), 3);
		assertEquals(aLine.getWorkPosts().get(0).getName(), "Body Work Post");
		assertEquals(aLine.getWorkPosts().get(1).getName(), "Drivetrain Work Post");
		assertEquals(aLine.getWorkPosts().get(2).getName(), "Accessories Work Post");
	}

}
