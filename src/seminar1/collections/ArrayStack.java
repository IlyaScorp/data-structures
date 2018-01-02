package seminar1.collections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayStack<Item> implements IStack<Item> {

    private static final int DEFAULT_CAPACITY = 4;

    private Item[] elementData;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public ArrayStack() {
        this.elementData = (Item[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void push(Item item) {
        /* TODO: implement it */
        if (elementData.length > size){
            elementData[size++] = item;
        }else{
            grow();
            elementData[size++] = item;
        }

    }


    public Item peek(){
        return elementData[size - 1];
    }


    @Override
    public Item pop() {
        /* TODO: implement it */
        if (elementData.length > 4 * size){
            shrink();
        }

        Item temp = elementData[--size];
        if (temp == null){
            throw  new NoSuchElementException();
        }else {
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


    @SuppressWarnings("unchecked")
    private void grow() {
        /**
         * TODO: implement it
         * Если массив заполнился,
         * то увеличить его размер в полтора раз
         */
        changeCapacity(elementData.length/2 + elementData.length);

    }

    private void shrink() {
        /**
         * TODO: implement it
         * Если количество элементов в четыре раза меньше,
         * то уменьшить его размер в два раза
         */
        changeCapacity(elementData.length/2);
    }

    private void changeCapacity(int newCapacity) {
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayStackIterator();
    }

    private class ArrayStackIterator implements Iterator<Item> {

        private int currentPosition = size;

        @Override
        public boolean hasNext() {
            return currentPosition != 0;
        }

        @Override
        public Item next() {
            return elementData[--currentPosition];
        }

    }

}
