package Tasks;

import java.util.Arrays;
import java.util.Iterator;

public class CyclicArrayQueue<Item> implements IQueue<Item> {

    private static final int DEFAULT_CAPACITY = 10;

    private Item[] elementData;

    private int start = 0;
    private int end = 0;

    @SuppressWarnings("unchecked")
    public CyclicArrayQueue() {
        elementData = (Item[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void enqueue(Item item) {
        elementData[end] = item;
        end = (end+1)%elementData.length;
        if ((end - start) == elementData.length) {
            grow();
        }
    }

    @Override
    public Item dequeue() {
        Item temp;
        if (isEmpty()) {
            temp = null;
        } else {
            temp = elementData[start];
            elementData[start] = null;
            start = (start+1)%elementData.length;
            if ((end - start)*4 <= elementData.length) {
                shrink();
            }
        }
        return temp;
    }

    @Override
    public boolean isEmpty() {
        return (end - start) == 0;
    }

    @Override
    public int size() {
        return (end - start);
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
        return new CyclicArrayQueueIterator();
    }


    private class CyclicArrayQueueIterator implements Iterator<Item> {

        private int currentPosition = start;

        @Override
        public boolean hasNext() {
            return currentPosition != end;
        }

        @Override
        public Item next() {
            return elementData[currentPosition++];
        }

    }

    private int getIndex(int end) {
        return end % elementData.length;
    }
}
