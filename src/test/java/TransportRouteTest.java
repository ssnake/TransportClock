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
    public static void main(String args[])
    {
        junit.textui.TestRunner.run(TransportRouteTest.class);

    }
}
