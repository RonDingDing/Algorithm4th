import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {

    private int num; // number of nodes
    private Node first;
    private Node last;

    public Deque() {
        this.num = 0;
        this.first = null;
        this.last = null;
    }

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return num;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();

        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.prev = null;

        if (oldfirst == null) {
            last = first;
            first.next = null;
        } else {
            first.next = oldfirst;
            oldfirst.prev = first;

        }
        num++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();

        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;

        if (isEmpty()) {
            first = last;
            last.prev = null;
        } else {
            oldlast.next = last;
            last.prev = oldlast;
        }
        num++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();

        Item it = first.item;
        first = first.next;
        num--;

        if (num == 0) last = null;
        else first.prev = null;

        return it;

    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();

        Item it = last.item;
        last = last.prev;
        num--;

        if (num == 0) first = null;
        else last.next = null;

        return it;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {

            if (!hasNext()) throw new NoSuchElementException();

            Item it = current.item;
            current = current.next;
            return it;
        }
    }

    // test
    public static void main(String[] args) {

    }

}