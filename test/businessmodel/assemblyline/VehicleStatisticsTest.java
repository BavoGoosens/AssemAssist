package businessmodel.assemblyline;

import businessmodel.assemblyline.AssemblyLine;
import businessmodel.assemblyline.AssemblyLineAFactory;
import businessmodel.assemblyline.AssemblyLineScheduler;
import org.junit.Before;

import static org.junit.Assert.*;

public class VehicleStatisticsTest {

    @Before
    public void setUp() throws Exception {
        AssemblyLine aLine = new AssemblyLineAFactory().createAssemblyLine();
        AssemblyLine bLine = new AssemblyLineBFactory().createAssemblyLine();
        AssemblyLine cLine = new AssemblyLineCFactory().createAssemblyLine();
        AssemblyLineScheduler aLineScheduler = aLine.getAssemblyLineScheduler();

    }
}