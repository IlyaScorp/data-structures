package Tasks;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedDeque<Item> implements IDeque<Item> {

    private Node<Item> head;
    private Node<Item> tail;
    private int size;

    @Override
    public void pushFront(Item item) {
        /* TODO: implement it */
        //head - - - - >  tail//
        Node<Item> l = head;
        Node<Item> newItem = new Node<Item>(item, null, null);
        head = newItem;
        if (l == null){
            tail = newItem;
        }else{
            l.prev = newItem;
            head.next = l;
            head.prev = tail;
            tail.next = head;
        }
        size++;
    }

    @Override
    public void pushBack(Item item) {
        /* TODO: implement it */

        Node<Item> l = tail;
        Node<Item> newItem = new Node<Item>(item, null,null);
        tail = newItem;
        if (l == null){
            head = newItem;
        }else {
            l.next = newItem;
            tail.next = head;
            head.prev = tail;
            tail.prev = l;
        }
        size++;
    }

    @Override
    public Item popFront() {
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
    public Item popBack() {
        /* TODO: implement it */
        Item temp;
        if (size == 0){
            throw  new NoSuchElementException();
        }else {
            temp = tail.item;
            tail = tail.prev;
            size--;
            return temp;
        }

    }

    @Override
    public boolean isEmpty() {
        /* TODO: implement it */
        return size == 0;
    }

    @Override
    public int size() {
        /* TODO: implement it */
        return size;
    }

    @Override
    public Iterator<Item> iterator() {
        /* TODO: implement it */
        return new LinkedDequeIterator();
    }
    private class LinkedDequeIterator implements Iterator<Item>{

        private Node<Item> nodeIter = tail;
        private int sizeIter = size;
        @Override
        public boolean hasNext() {
            return sizeIter != 0;
        }

        @Override
        public Item next() {

                Item tmp = nodeIter.item;
                nodeIter = nodeIter.prev;
                sizeIter--;
            return tmp;
        }
    }

    private static class Node<Item> {
        Item item;
        Node<Item> next;
        Node<Item> prev;

        public Node(Item item) {
            this.item = item;
        }

        public Node(Item item,Node<Item> prev, Node<Item> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
}
