package deque;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void equalsTest(){
        ArrayDeque<Integer> a = new ArrayDeque<>();
        //a.printDeque();
        ArrayDeque<Integer> b = new ArrayDeque<>();
        ArrayDeque<Integer> c = new ArrayDeque<>();
        ArrayDeque<String> d = new ArrayDeque<>();

        a.addFirst(1);
        a.addLast(2);
        a.addLast(3);
        a.addFirst(1);
        a.addLast(2);
        a.addLast(3);
        a.addFirst(1);
        a.addLast(2);
        a.addLast(3);
        a.addFirst(1);
        a.addLast(2);
        a.addLast(3);

        b.addFirst(1);
        b.addLast(2);
        b.addLast(3);
        b.addFirst(1);
        b.addLast(2);
        b.addLast(3);
        b.addFirst(1);
        b.addLast(2);
        b.addLast(3);
        b.addFirst(1);
        b.addLast(2);
        b.addLast(3);

        c.addFirst(1000);
        c.addLast(2);
        c.addLast(3);
        c.addFirst(1);
        c.addLast(2);
        c.addLast(3);
        c.addFirst(1);
        c.addLast(2);
        c.addLast(3);
        c.addFirst(1);
        c.addLast(2);
        c.addLast(3);

        d.addLast("");
        d.addLast("");
        d.addLast("");
        d.addLast("");
        d.addLast("");
        d.addLast("");
        d.addLast("");
        d.addLast("");
        d.addLast("");
        d.addLast("");
        d.addLast("");
        d.addLast("");

        assertTrue(a.equals(b));
        assertFalse(a.equals(c));
        assertFalse(a.equals(d));
    }

    @Test
    public void getTest(){
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addFirst(10);
        a.addFirst(20);
        a.addLast(100);
        a.addLast(101);
        a.addFirst(90);

        //90 20 10 100 101
        assertTrue(a.get(0) == 90);
        assertTrue(a.get(2) == 10);
        assertTrue(a.get(4) == 101);
    }

    @Test
    public void removeAndSizeTest(){
        ArrayDeque<Integer> a = new ArrayDeque<>();
        assertTrue(a.size() == 0);
        assertTrue(a.isEmpty());
        a.addFirst(10);
        a.addFirst(20);
        a.addLast(100);
        a.addLast(101);
        a.addFirst(90);
        a.removeFirst();
        a.removeLast();
        ArrayDeque<Integer> b = new ArrayDeque<>();
        b.addFirst(10);
        b.addFirst(20);
        b.addLast(100);
        a.printDeque();
        b.printDeque();
        assertTrue(a.equals(b));
    }
    @Test
    public void resizingTest(){
        ArrayDeque<Integer> a = new ArrayDeque<>();
        for (int i = 0; i < 100; ++i){
            a.addLast(1);
            a.addFirst(1);
        }
        assertTrue(a.size() == 200);

        for (int i = 0; i < 50; ++i) {
            a.removeLast();
            a.removeFirst();
        }
        assertTrue(a.size() == 100);
    }

    @Test
    public void resizingTest2(){
        ArrayDeque<Integer> a = new ArrayDeque<>();
        for (int i = 0; i < 100; ++i){
            a.addLast(1);
            a.addFirst(1);
        }
        assertTrue(a.size() == 200);

        for (int i = 0; i < 50; ++i) {
            a.removeLast();
            a.removeLast();
        }
        assertTrue(a.size() == 100);
    }

    @Test
    public void resizingTest3(){
        ArrayDeque<Integer> a = new ArrayDeque<>();
        for (int i = 0; i < 100; ++i){
            a.addLast(1);
            a.addFirst(1);
        }
        assertTrue(a.size() == 200);

        for (int i = 0; i < 150; ++i) {
            a.removeFirst();
        }
        assertTrue(a.size() == 50);
    }

    @Test
    public void fillThenEmpty(){
        ArrayDeque<Integer> a = new ArrayDeque<>();
        for (int i = 0; i < 100; ++i){
            a.addLast(1);
            a.addFirst(1);
        }

        for (int i = 0; i < 99; ++i) {
            a.removeLast();
            a.removeLast();
        }
        assertTrue(a.size() == 2);
        assertTrue(a.removeFirst() == 1);
        assertTrue(a.removeLast() == 1);
        assertTrue(a.size() == 0);
    }

    @Test
    public void removeFromEmpty() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        try {
            a.removeFirst();
        }
        catch( IndexOutOfBoundsException e) {
            System.out.print(a.size());
        }
    }

    @Test
    public void iteratorTest() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        for (int i = 0; i < 100; ++i){
            a.addLast(1);
            a.addFirst(1);
        }
        int sum = 0;
        for (Integer x : a) {
            sum += x;
        }
        assertEquals(200, sum);
    }
}
