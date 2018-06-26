import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int N;

    private class Node<Item> {
        public Node<Item> next;
        public Node<Item> prev;
        public Item item;

        public Node(Item id) {
            item = id;
            next = null;
        }
    }

    public Deque() {
        N = 0;
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException();
        }
        Node<Item> node = new Node<>(item);
        node.next = first;
        if (first == null) {
            last = node;
        } else {
            first.prev = node;
        }
        first = node;
        N++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException();
        }
        Node<Item> node = new Node<>(item);
        if (last == null) {
            first = node;
            last = node;
        } else {
            node.prev = last;
            last.next = node;
            last = node;
        }
        N++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item info = first.item;
        if (first.next == null) {
            first = null;
            last = null;
        } else {
            first.next.prev = null;
            first = first.next;
        }
        N--;
        return info;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = last.item;
        if (last.prev != null) {
            Node<Item> x = last;
            last = last.prev;
            last.next = null;
            x.prev = null;
        } else {
            last = null;
            first = null;
        }
        N--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new deqiterator<Item>(first);
    }

    private class deqiterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public deqiterator(Node<Item> x) {
            current = x;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item x = current.item;
            current = current.next;
            return x;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Deque<Integer> d = new Deque<>();
        d.addFirst(0);
        d.removeFirst();
        d.addFirst(2);
        d.removeFirst();
        StdOut.println(d.isEmpty());

    }
}
