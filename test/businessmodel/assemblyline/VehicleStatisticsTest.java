package businessmodel.assemblyline;
import java.util.ArrayList;
import org.junit.Before;

import businessmodel.Catalog;
import businessmodel.OrderManager;
import businessmodel.category.VehicleModel;
import static org.junit.Assert.*;

public class VehicleStatisticsTest {

    @Before
    public void setUp() throws Exception {
        OrderManager om = new OrderManager();
        Catalog catalog = new Catalog();
        ArrayList<VehicleModel> models = catalog.getAvailaleModelsClone();
        for (VehicleModel model: models) {
        }
    }
}