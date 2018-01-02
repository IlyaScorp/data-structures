package seminar1.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStack<Item> implements IStack<Item> {

    private Node<Item> head;
    private Node<Item> last;
    private int size;

    @Override
    public void push(Item item) {
        /* TODO: implement it */
        Node<Item> newNode = new Node<>(item, null);

        if (head == null){
            head = newNode;
        }else{
            Node<Item> current = head;
            head = newNode;
            head.next = current;
        }
        size++;
    }

    @Override
    public Item pop() {
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
        return new LinkedStackIterator();
    }

    private class LinkedStackIterator implements Iterator<Item> {
        private int count = size;
        private Node<Item> IterItem = head;

        @Override
        public boolean hasNext() {
            /* TODO: implement it */
            return count != 0;
        }

        @Override
        public Item next() {
            /* TODO: implement it */
            if (IterItem.item != null){
                Item tmp = IterItem.item;
                IterItem = IterItem.next;
                return tmp;
            }else {
                 throw new NoSuchElementException();
            }
        }

    }

    private static class Node<Item> {
        Item item;
        Node<Item> next;

        public Node(Item item, Node<Item> next) {
            this.item = item;
            this.next = next;
        }
    }


}
