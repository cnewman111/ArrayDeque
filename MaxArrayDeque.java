package deque;
import java.util.*;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comp;
    public MaxArrayDeque(Comparator<T> c) {
        comp = c;
    }
    public T max() {
        if (size() < 1) {
            return null;
        }
        T largest = get(0);
        for (T x : this) {
            if (comp.compare(x, largest) > 0) {
                largest = x;
            }
        }
        return largest;
    }

    public T max(Comparator<T> c) {
        if (size() < 1) {
            return null;
        }
        T largest = get(0);
        for (T x : this) {
            if (c.compare(x, largest) > 0) {
                largest = x;
            }
        }
        return largest;
    }

}
