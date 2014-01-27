import com.transportclock.CarGPSImporter;
import com.transportclock.JSONURLReader;
import com.transportclock.TransportCar;
import junit.framework.TestCase;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by snake on 1/11/14.
 */
public class JSONURLReaderTest extends TestCase {
    public void testMeria() throws IOException {
        JSONObject json = JSONURLReader.read2JO("http://gps.meria.sumy.ua/mash.php?act=cars&id=0");
        List<TransportCar> list = new ArrayList<TransportCar>();
        CarGPSImporter.load(json.toString(), list);
        assert (list.size() > 0);

    }

    public void testParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("a", "b");
        params.put("c", "d");
        assertEquals("c=d&a=b", JSONURLReader.encodeParams(params));
    }
}
