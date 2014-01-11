
import com.transportclock.JavaHelper;
import com.transportclock.RoutePoint;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snake on 1/2/14.
 */
public class JavaHelperTest extends TestCase {
    public void testListContainsInt()
    {
        List<Integer> l = new ArrayList<Integer>();
        l.add(2);
        l.add(5);
        l.add(-1);
        assertEquals(true, JavaHelper.listContainsInt(l, 2));
    }
    public void testGetAngle() {
        Float angle = 0.0f;
        angle = JavaHelper.getAngle(new RoutePoint(0.0f, 0.0f), new RoutePoint(1.0f, 1.0f));
        assertEquals(45.0f, angle, 1.0f );
        angle = JavaHelper.getAngle(new RoutePoint(0.0f, 0.0f), new RoutePoint(-1.0f, -1.0f));
        assertEquals(180.0f+45.0f, angle, 1.0f );
        angle = JavaHelper.getAngle(new RoutePoint(0.0f, 0.0f), new RoutePoint(-1.0f, 1.0f));
        assertEquals(90.0f+45.0f, angle, 1.0f );
        angle = JavaHelper.getAngle(new RoutePoint(0.0f, 0.0f), new RoutePoint(1.0f, -1.0f));
        assertEquals(270.0f+45.0f, angle, 1.0f );
    }
}
