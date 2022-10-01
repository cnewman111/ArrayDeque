package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private T[] arr;
    private static final int STARTING_CAPACITY = 8;
    private static final int USAGE_RATIO = 4;
    private static final int EXPANSION_FACTOR = 2;
    private static final int CONTRACTION_FACTOR = 2;
    private int length;
    private int first;
    private int last;

    private int rIndex(int originalIndex) {
        return (originalIndex + this.arr.length) % this.arr.length;
    }


    private void reSize(int newCap) {
        T[] newArr = (T[]) new Object[newCap];
        int j = 0;
        for (int i = first; i < first + length; ++i) {
            newArr[j] = this.arr[rIndex(i)];
            ++j;
        }
        first = 0;
        last = j - 1;
        this.arr = newArr;
    }
    private void addToEmptyList(T item) {
        this.arr[0] = item;
        first = 0;
        last = 0;
        length = 1;
    }

    public ArrayDeque() {
        this.arr = (T[]) new Object[STARTING_CAPACITY];
        length = 0;
    }
    @Override
    public void addFirst(T item) {
        if (rIndex(first - 1) == rIndex(last)) {
            reSize(this.arr.length * this.EXPANSION_FACTOR);
        }
        if (length == 0) {
            addToEmptyList(item);
            return;
        }
        this.arr[rIndex(first - 1)] = item;
        first = rIndex(first - 1);
        ++length;
    }
    @Override
    public void addLast(T item) {
        if (rIndex(first) == rIndex(last + 1)) {
            reSize(this.arr.length * this.EXPANSION_FACTOR);
        }
        if (length == 0) {
            addToEmptyList(item);
            return;
        }
        this.arr[rIndex(last + 1)] = item;
        last = rIndex(last + 1);
        ++length;
    }
    @Override
    public int size() {
        return length;
    }
    @Override
    public void printDeque() {
        for (int i = first; i < first + length; ++i) {
            System.out.println(this.arr[rIndex(i)]);
        }
    }
    @Override
    public T removeFirst() {
        if (length == 0) {
            return null;
        }
        if ((this.arr.length > this.STARTING_CAPACITY) && (this.arr.length / this.USAGE_RATIO > length)) {
            reSize(this.arr.length / this.CONTRACTION_FACTOR);
        }
        T ret = arr[rIndex(first)];
        arr[rIndex(first)] = null;
        first = rIndex(first + 1);
        --length;
        return ret;
    }
    @Override
    public T removeLast() {
        if (length == 0) {
            return null;
        }
        if ((this.arr.length > this.STARTING_CAPACITY) && (this.arr.length / this.USAGE_RATIO > length)) {
            reSize(this.arr.length / this.CONTRACTION_FACTOR);
        }
        T ret = arr[rIndex(last)];
        arr[rIndex(last)] = null;
        last = rIndex(last - 1);
        --length;
        return ret;
    }
    @Override
    public T get(int index) {
        if (index < 0 || index >= this.length) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        return this.arr[rIndex(first + index)];
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Deque<?> other && other.size() == this.length) {
            for (int i = 0; i < this.length; ++i) {
                if (!get(rIndex(i)).equals(other.get(rIndex(i)))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int index;
        public ArrayDequeIterator() {
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
        return new ArrayDequeIterator();
    }
}

