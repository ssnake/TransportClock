import com.transportclock.RoutePoint;
import com.transportclock.TransportRoute;
import junit.framework.TestCase;
import org.junit.*;


/**
 * Created by snake on 10.12.13.
 */
public class TransportRouteTest extends TestCase {
    TransportRoute r;

    @Before
    public void setUp() throws Exception {

        r = new TransportRoute();

    }
    public void testAddPoint()
    {
        RoutePoint p = r.add(1.0f, 1.0f);
        assertEquals(1, r.size());
        Boolean b= p.equals(new RoutePoint(1.0f, 1.0f));
        assertTrue(b);

    }

    public void testRouteLength()
    {
        r.add(50.885655f,34.873989f);
        r.add(50.875419f,34.903772f);
        assertEquals(2389.0f, r.getLength(), 100.0f);

    }
    public void testRouteLength2()
    {
        r.add(50.464498f,30.518303f);
        r.add(50.896104f,34.813957f);
        //30 km inaccuracy!!!
        assertEquals(334208.0f, r.getLength(), 30000.0f);

    }
    public void testRouteLength3()
    {
        r.add(36.12f, -86.67f);
        r.add(33.94f, -118.40f);
        assertEquals(2887260.0f, r.getLength(), 1000.0f);

    }
    public void testPointToJSON()
    {
        assertEquals("{\"lng\":22.44,\"lat\":11.23}", new RoutePoint(11.23f, 22.44f).toJSON());
    }
    public void testGetDistance()
    {
        r.add(1.0f, 1.0f);
        r.add(2.0f, 1.0f);
        r.add(3.0f, 1.0f);
        r.add(4.0f, 1.0f);
        Float dist = r.getDistance(new RoutePoint(1.6f,0.0f), new RoutePoint(10.0f,0.0f));
        assertFalse(r.getLength().compareTo(dist) == 0 );
        dist = r.getDistance(new RoutePoint(-0.5f,0.0f), new RoutePoint(10.0f,0.0f));
        assertEquals(r.getLength(), dist);



    }
    public void testRouteToJSON()
    {
        TransportRoute r = new TransportRoute();
        r.add(1.1f, 2.3f);
        r.add(2.2f, 3.3f);
        assertEquals("[\"{\\\"lng\\\":2.3,\\\"lat\\\":1.1}\",\"{\\\"lng\\\":3.3,\\\"lat\\\":2.2}\"]", r.toJSON());
    }
    public static void main(String args[])
    {
        junit.textui.TestRunner.run(TransportRouteTest.class);

    }
}
