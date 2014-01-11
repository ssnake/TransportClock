import com.transportclock.TransportCar;
import com.transportclock.TransportClient;
import com.transportclock.TransportClientSumy;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snake on 1/11/14.
 */
public class TransPortClientSumyTest extends TestCase {
    TransportClient client;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        client = new TransportClientSumy();

    }

    public void testLoadAllCars() {
        List<TransportCar> carList = new ArrayList<TransportCar>();
        client.loadAllCars(0, carList);
        assert (carList.size() > 0);

    }
}
