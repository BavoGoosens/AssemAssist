package businessmodel.assemblyline;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WorkPostFactoryTest {
	
	WorkPost bodyPost;
	WorkPost drivetrainPost;
	WorkPost accessoriesPost;
	WorkPost cargoPost;
	WorkPost certificationPost;

	@Before
	public void setUp() throws Exception {
		AssemblyLine line = new AssemblyLineCFactory().createAssemblyLine();
		bodyPost = new BodyWorkPostFactory().createWorkPost(line);
		drivetrainPost = new DrivetrainWorkPostFactory().createWorkPost(line);
		accessoriesPost = new AccessoriesWorkPostFactory().createWorkPost(line);
		cargoPost = new CargoWorkPostFactory().createWorkPost(line);
		certificationPost = new CertificationWorkPostFactory().createWorkPost(line);
	}

	@Test
	public void testBodyPost() {
		assertEquals(bodyPost.getName(), "Body Work Post");
		assertEquals(bodyPost.getResponsibleTasksClone().get(0).getName(), "Assembly Vehicle Body");
		assertEquals(bodyPost.getResponsibleTasksClone().get(1).getName(), "Paint Vehicle");
	}
	
	@Test
	public void testDrivetrainPost() {
		assertEquals(drivetrainPost.getName(), "Drivetrain Work Post");
		assertEquals(drivetrainPost.getResponsibleTasksClone().get(0).getName(), "Insert Engine");
		assertEquals(drivetrainPost.getResponsibleTasksClone().get(1).getName(), "Insert Gearbox");
	}
	
	@Test
	public void testAccessoriesPost() {
		assertEquals(accessoriesPost.getName(), "Accessories Work Post");
		assertEquals(accessoriesPost.getResponsibleTasksClone().get(0).getName(), "Install Seats");
		assertEquals(accessoriesPost.getResponsibleTasksClone().get(1).getName(), "Install Airco");
		assertEquals(accessoriesPost.getResponsibleTasksClone().get(2).getName(), "Mount Wheels");
		assertEquals(accessoriesPost.getResponsibleTasksClone().get(3).getName(), "Install Spoiler");
	}
	
	@Test
	public void testCargoPost() {
		assertEquals(cargoPost.getName(), "Cargo Work Post");
		assertEquals(cargoPost.getResponsibleTasksClone().get(0).getName(), "Install Tool Storage");
		assertEquals(cargoPost.getResponsibleTasksClone().get(1).getName(), "Add Cargo Protection");

	}
	
	@Test
	public void testCertificationPost() {
		assertEquals(certificationPost.getName(), "Certification Work Post");
		assertEquals(certificationPost.getResponsibleTasksClone().get(0).getName(), "Certify Maximum Cargo Load");
	}

}
