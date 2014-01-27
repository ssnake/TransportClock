import com.transportclock.RouteGPSImporter;
import com.transportclock.TransportRoute;
import com.transportclock.TransportRouteList;
import com.transportclock.UnitTestHelper;
import junit.framework.TestCase;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snake on 17.12.13.
 */
public class RouteGPSImporterTest extends TestCase {
    String allRoutes;
    String allNames;
    String route959;
    List<TransportRoute> listRoute;



    @Override
    protected void setUp() throws Exception {
        super.setUp();

        allRoutes = UnitTestHelper.resource2String(this, "all_routes.json");
        allNames = UnitTestHelper.resource2String(this, "route_names.json");
        route959 = UnitTestHelper.resource2String(this, "959_route.json");
        listRoute = new ArrayList<TransportRoute>();

    }





    public void testLoadAllNames() {
        RouteGPSImporter.loadRouteNames(allNames, listRoute);
        TransportRoute r = TransportRouteList.findByID(0, listRoute);
        assertEquals("01 Роменська - Гамалiя", r.getName());
        assertEquals("01", r.getNumber());


    }
    public void testLoadRoute() {
        JSONArray ja = new JSONArray(route959);
        TransportRoute r =new TransportRoute();
        RouteGPSImporter.loadRoutePoints(ja, r);
        assertTrue(r.size() > 0);
    }
    public static void main(String args[])
    {
        junit.textui.TestRunner.run(RouteGPSImporterTest.class);

    }
}
