import com.transportclock.*;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snake on 1/11/14.
 */
public class TransportClientSumyTest extends TestCase {
    TransportClient client;
    List<TransportRoute> routeList;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        client = new TransportClientSumy();
        routeList = new ArrayList<TransportRoute>();

    }

    public void testLoadAllCars() {
        List<TransportCar> carList = new ArrayList<TransportCar>();
        client.loadRouteCars(0, carList);
        assert (carList.size() > 0);

    }
    public void testLoadRoute() {
        TransportRoute r = new TransportRoute();
        r.setId(0);
        r.addOtherValue("id_route", "959");
        client.loadRoutePath(r);
        assertTrue(r.size() > 0);


    }
    public void testLoadRouteDetails() {
        TransportRoute r = new TransportRoute();
        r.setId(0);
        client.loadRouteDetails(r);
        assertEquals("959", r.getOther().get("id_route"));

    }
}
