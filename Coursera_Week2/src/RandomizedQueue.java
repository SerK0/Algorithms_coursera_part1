import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int N;

    public RandomizedQueue() {
        N = 0;
        queue = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void resize(int n) {
        Item[] temp = (Item[]) new Object[n];
        for (int i = 0; i < N; i++) {
            temp[i] = queue[i];
        }
        queue = temp;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException();
        }
        if (N == queue.length) {
            resize(2 * queue.length);
        }
        queue[N++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniform(N);
        Item swap = queue[index];
        queue[index] = queue[--N];
        queue[N] = null;
        if (N < queue.length / 4) {
            resize(queue.length / 2);
        }
        return swap;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniform(N);
        return queue[index];
    }

    public Iterator<Item> iterator() {
        return new RandomQueueIterator<Item>(N, queue);
    }

    private class RandomQueueIterator<Item> implements Iterator<Item> {
        private int size;
        final Item[] q;

        public RandomQueueIterator(int n, Item[] queue) {
            size = n;
            q = queue;
        }

        public boolean hasNext() {
            return size>0;
        }


        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            int index = StdRandom.uniform(size);
            Item x = q[index];
            q[index] = q[--size];
            return x;
        }


        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        for (Integer i : q) {
            StdOut.println(i);
        }
    }
}
