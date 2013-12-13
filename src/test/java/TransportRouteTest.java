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
        assert(r.getLength().compareTo(2389.0f)==0);

    }
    public void testRouteLength2()
    {
        r.add(50.464498f,30.518303f);
        r.add(50.896104f,34.813957f);
        assert(r.getLength().compareTo(334208.0f)==0);
       // assertEquals(334208.0f, r.getLength());

    }
    public void testRouteLength3()
    {
        r.add(36.12f, -86.67f);
        r.add(33.94f, -118.40f);
        assert(r.getLength().compareTo(2887.259950607110f) == 0);

    }
    public static void main(String args[])
    {
        junit.textui.TestRunner.run(TransportRouteTest.class);

    }
}
