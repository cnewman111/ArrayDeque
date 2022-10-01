package deque;
import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

public class MaxArrayDequeTest {
    @Test
    public void intTest(){
        class maximum implements Comparator<Integer> {
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        }

        MaxArrayDeque<Integer> a = new MaxArrayDeque<Integer>(new maximum());
        for (int i = 0; i < 10; ++i) {
            a.addLast(i);
            a.addFirst(i);
        }
        assert(a.max() == 9);
    }

}
