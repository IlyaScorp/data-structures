package Tasks;

import java.util.Arrays;
import java.util.Iterator;

public class CyclicArrayDeque<Item> implements IDeque<Item> {

    private static final int DEFAULT_CAPACITY = 10;

    private Item[] elementData;

    private int start = 0;
    private int end = 1;

    private int size = 0;
    @SuppressWarnings("unchecked")
    public CyclicArrayDeque() {
        elementData = (Item[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void pushFront(Item item) {
        //int newIndex = start == 0 ? elementData.length - 1 : start - 1;
        elementData[start] = item;
        start = start == 0 ? elementData.length - 1 : start - 1;
        size++;
        if (size() == elementData.length) {
            grow();
        }
    }

    @Override
    public void pushBack(Item item) {
        elementData[end] = item;
        end = (end+1)%elementData.length;
        size++;
        if (size() == elementData.length) {
            grow();
        }
    }

    @Override
    public Item popFront() {
        Item temp;
        if (isEmpty()) {
            temp = null;
        } else {
            temp = elementData[start];
            elementData[start] = null;
            start = (start+1)%elementData.length;
            size--;
            if (size()*4 <= elementData.length) {
                shrink();
            }
        }
        return temp;
    }

    @Override
    public Item popBack() {
        Item temp;
        if (isEmpty()) {
            temp = null;
        } else {
            temp = elementData[end];
            elementData[end] = null;
            end = (end-1)%elementData.length;
            size--;
            if (size()*4 <= elementData.length) {
                shrink();
            }
        }
        return temp;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public int size() {
        return size;
    }


    @SuppressWarnings("unchecked")
    private void grow() {

        Item[] tempData = (Item[]) new Object[size()];

        Iterator iterator = iterator();
        for (int i=0; i<size(); i++) {
            tempData[i] = (Item) iterator.next();
        }
        Arrays.fill(elementData, null);

        start = 0;
        end = tempData.length;

        Arrays.copyOf(tempData, (int)(tempData.length*1.5));
    }

    @SuppressWarnings("unchecked")
    private void shrink() {
        Item[] tempData = (Item[]) new Object[size()];

        Iterator iterator = iterator();
        for (int i=0; i<size(); i++) {
            tempData[i] = (Item) iterator.next();
        }
        Arrays.fill(elementData, null);

        start = 0;
        end = tempData.length;

        Arrays.copyOf(tempData, tempData.length/2);
    }


    @Override
    public Iterator<Item> iterator() {
        return new CyclicArrayDequeIterator();
    }

    private class CyclicArrayDequeIterator implements Iterator<Item> {

        private int currentPosition = start+1;
        private int iter = 0;

        @Override
        public boolean hasNext() {
            return iter != size();
        }

        @Override
        public Item next() {
            iter++;
            return elementData[(currentPosition++)%elementData.length];
        }

    }
}