import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;
    private int num; // number of elements on queue

    public RandomizedQueue() {
        queue = (Item[]) new Object[2];
        num = 0;
    }

    public boolean isEmpty() {
        return num == 0;
    }

    public int size() {
        return num;
    }

    private void resize(int capacity) {
        assert capacity >= num;
        Item[] temp = (Item[]) new Object[capacity];

        for (int i = 0; i < num; i++) {
            temp[i] = queue[i];
        }

        queue = temp;
    }

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (num == queue.length) resize(2 * queue.length);

        queue[num++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();

        int new_num = StdRandom.uniform(num);
        Item item = queue[new_num];

        if (new_num != num - 1) queue[new_num] = queue[num - 1];
        queue[num - 1] = null;
        num--;

        if (num > 0 && num == queue.length / 4) resize(queue.length / 2);
        return item;
    }


    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();

        int new_num = StdRandom.uniform(num);
        return queue[new_num];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private Item[] randomDeque; // independent iterator
        private int current;

        public RandomizedQueueIterator() {

            randomDeque = (Item[]) new Object[num];
            current = 0;

            for (int k = 0; k < num; k++) {
                randomDeque[k] = queue[k];
            }

            StdRandom.shuffle(randomDeque);
        }

        public boolean hasNext() {
            return current < num;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {

            if (!hasNext()) throw new NoSuchElementException();

            Item item = randomDeque[current];
            current++;
            return item;
        }
    }

}