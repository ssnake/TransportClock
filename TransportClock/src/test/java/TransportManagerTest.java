import com.transportclock.TransportManager;
import com.transportclock.TransportRoute;
import junit.framework.TestCase;
import org.junit.Before;


/**
 * Created by snake on 13.12.13.
 */
public class TransportManagerTest extends TestCase {
    TransportManager tm;
    @Before
    public void setUp() throws Exception {

        tm = new TransportManager();

    }
    public void testS1()
    {

    }
    public static void main(String args[])
    {
        junit.textui.TestRunner.run(TransportManagerTest.class);

    }
}
