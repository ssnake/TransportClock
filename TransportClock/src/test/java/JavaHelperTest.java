
import com.transportclock.JavaHelper;
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
}
