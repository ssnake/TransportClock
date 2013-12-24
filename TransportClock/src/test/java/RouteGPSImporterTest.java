import com.transportclock.RouteGPSImporter;
import com.transportclock.TransportRoute;
import junit.framework.TestCase;

/**
 * Created by snake on 17.12.13.
 */
public class RouteGPSImporterTest extends TestCase {
    public void testLoad()
    {
        final String s = "[{\"id_route\":\"824\",\"lat\":\"34.7408\",\"lng\":\"50.94468\",\"direction\":\"t\"},{\"id_route\":\"824\",\"lat\":\"34.75007\",\"lng\":\"50.94359\",\"direction\":\"t\"},"+
                "{\"id_route\":\"824\",\"lat\":\"34.75457\",\"lng\":\"50.94297\",\"direction\":\"f\"},{\"id_route\":\"824\",\"lat\":\"34.75494\",\"lng\":\"50.94291\",\"direction\":\"f\"}]";
        TransportRoute r = RouteGPSImporter.load(s, Boolean.TRUE);
        assertEquals(2, r.size());
        assertEquals(34.7408f, r.get(0).getLat(), 1.0f);
        assertEquals(50.94468f, r.get(0).getLng(), 1.0f);
        assertEquals(34.75007f, r.get(1).getLat(), 1.0f);
        assertEquals(50.94359f, r.get(1).getLng(), 1.0f);
        TransportRoute r2 = RouteGPSImporter.load(s, Boolean.FALSE);
        assertEquals(2, r2.size());
        assertEquals(34.7545f, r2.get(0).getLat(), 1.0f);
        assertEquals(50.94297f, r2.get(0).getLng(), 1.0f);
        assertEquals(34.75494f, r2.get(1).getLat(), 1.0f);
        assertEquals(50.94291f, r2.get(1).getLng(), 1.0f);

    }
    public static void main(String args[])
    {
        junit.textui.TestRunner.run(RouteGPSImporterTest.class);

    }
}
