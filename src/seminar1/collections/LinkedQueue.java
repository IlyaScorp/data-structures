package Tasks;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<Item> implements IQueue<Item> {

    // -> [tail -> .. -> .. -> head] ->
    private Node<Item> head;
    private Node<Item> tail;
    private int size;

    @Override
    public void enqueue(Item item) {
/* TODO: implement it */

        Node<Item> l = tail;
        Node<Item> newItem = new Node<Item>(item, null);
        tail = newItem;
        if (l == null){
            head = newItem;
        }else {
            l.next = newItem;
        }
        size++;
    }

    @Override
    public Item dequeue() {
/* TODO: implement it */

        if (size == 0){
            throw  new NoSuchElementException();
        }else {
            Item temp = head.item;
            head = head.next;
            size--;
            return temp;
        }
    }



    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedQueueIterator();
    }

    private class LinkedQueueIterator implements Iterator<Item> {
         private int count = size;
         private Node<Item> IterItem = head;
        @Override
        public boolean hasNext() {
            return count != 0;
        }

        @Override
        public Item next() {
            Item tmp = IterItem.item;
            IterItem = IterItem.next;
            count--;
            return tmp;

        }

    }

    private static class Node<Item> {
        Item item;
        Node<Item> next;

        public Node(Item item) {
            this.item = item;
        }

        public Node(Item item, Node<Item> next) {
            this.item = item;
            this.next = next;
        }
    }

}
