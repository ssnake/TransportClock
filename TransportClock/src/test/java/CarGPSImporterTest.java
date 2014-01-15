import com.transportclock.CarGPSImporter;
import com.transportclock.TransportCar;
import com.transportclock.UnitTestHelper;
import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by snake on 11.01.14.
 */
public class CarGPSImporterTest extends TestCase{
    String cars_on_route_json;
    ArrayList<TransportCar> carList;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        cars_on_route_json = UnitTestHelper.resource2String(this, "cars_on_route.json");
        carList = new ArrayList<TransportCar>();
    }

    public void testLoad() {
        CarGPSImporter.load(cars_on_route_json, carList);
        assertEquals(14, carList.size());
        TransportCar car = carList.get(0);
        // {"rows":[{"CarId":"25458","route_id":null,"inzone":"t","CarName":"39-23","SpeedV":"3","color":"green"
        assertEquals("39-23", car.getName());
        assertEquals(25458, car.getId());
        assertEquals(3.0f, car.getSpeed());
        assertTrue(false == car.getAvaible());
        assertTrue(carList.get(1).getAvaible());

    }


    public static void main(String[] args) {
        junit.textui.TestRunner.run(CarGPSImporterTest.class);
    }
}