package businessmodel.assemblyline;

import businessmodel.MainScheduler;
import businessmodel.OrderManager;
import businessmodel.assemblyline.AssemblyLine;
import businessmodel.assemblyline.AssemblyLineAFactory;
import businessmodel.assemblyline.AssemblyLineScheduler;
import org.junit.Before;

import static org.junit.Assert.*;

public class VehicleStatisticsTest {

    @Before
    public void setUp() throws Exception {
        AssemblyLine aLine = new AssemblyLineAFactory().createAssemblyLine(new MainScheduler(new OrderManager()));
        AssemblyLine bLine = new AssemblyLineBFactory().createAssemblyLine(new MainScheduler(new OrderManager()));
        AssemblyLine cLine = new AssemblyLineCFactory().createAssemblyLine(new MainScheduler(new OrderManager()));
        AssemblyLineScheduler aLineScheduler = aLine.getAssemblyLineScheduler();

    }
}