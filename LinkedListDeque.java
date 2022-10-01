package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {
    private class Node {
        private T data;
        private Node next;
        private Node last;
        Node(T data, Node next, Node last) {
            this.data = data;
            this.next = next;
            this.last = last;
        }
    }
    private Node sentinel;
    private int length;
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.last = sentinel;
    }
    @Override
    public void addFirst(T item) {
        sentinel.next = new Node(item, sentinel.next, sentinel);
        sentinel.next.next.last = sentinel.next;
        ++length;
    }
    @Override
    public void addLast(T item) {
        sentinel.last.next = new Node(item, sentinel, sentinel.last);
        sentinel.last = sentinel.last.next;
        ++length;
    }
    @Override
    public int size() {
        return length;
    }
    @Override
    public void printDeque() {
        Node curr = sentinel.next;
        while (curr != sentinel) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }
    @Override
    public T removeFirst() {
        if (sentinel.next != sentinel) {
            T val = sentinel.next.data;
            sentinel.next.data = null;
            sentinel.next = sentinel.next.next;
            sentinel.next.last = sentinel;
            --length;
            return val;
        }
        return null;
    }
    @Override
    public T removeLast() {
        if (sentinel.last != sentinel) {
            T val = sentinel.last.data;
            sentinel.last.data = null;
            sentinel.last = sentinel.last.last;
            sentinel.last.next = sentinel;
            --length;
            return val;
        }
        return null;
    }
    @Override
    public T get(int index) {
        Node curr = sentinel.next;
        while (index > 0) {
            if (curr == sentinel) {
                throw new IndexOutOfBoundsException();
            }
            --index;
            curr = curr.next;
        }
        return curr.data;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Deque<?> otherLLD && otherLLD.size() == this.length) {
            for (int i = 0; i < this.length; ++i) {
                if (!this.get(i).equals(otherLLD.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private T getRecursiveHelper(int index, Node n) {
        if (n == sentinel) {
            return null;
        } else if (index == 0) {
            return n.data;
        }
        return getRecursiveHelper(index - 1, n.next);
    }
    public T getRecursive(int index) {
        return getRecursiveHelper(index, sentinel.next);
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private int index;
        public LinkedListDequeIterator() {
            index = 0;
        }
        public boolean hasNext() {
            return index != length;
        }
        public T next() {
            T ret = get(index);
            ++index;
            return ret;
        }
    }
    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }
}
