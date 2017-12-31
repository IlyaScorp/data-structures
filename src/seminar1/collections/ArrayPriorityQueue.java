package Tasks;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class ArrayPriorityQueue<Key extends Comparable<Key>> implements IPriorityQueue<Key> {

    private Key[] heap;
    private Comparator<Key> c;
    private int size = 0;

    public ArrayPriorityQueue() {
        /* TODO: implement it — O(n) */

    }

    public ArrayPriorityQueue(Key[] arr) {
        /* TODO: implement it — O(n) */
        heap = arr;
        size = heap.length;
        for (int i = size - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    public ArrayPriorityQueue(Key[] arr, Comparator<Key> comparator) {
        /* TODO: implement it — O(n) */
        heap = arr;
        size = heap.length;
        this.c = comparator;
        for (int i = size - 1; i >= 0; i--) {
            siftDown(i);
        }
    }



    public ArrayPriorityQueue(Comparator<Key> comparator) {
        /* TODO: implement it — O(n) */
        this.c = comparator;
    }

    @Override
    public void add(Key key) {
        /* TODO: implement it — O(log n) */
        if (heap.length <= size) {
            grow();
        }
        heap[size] = key;
        siftUp(size++);
    }

    @Override
    public Key peek() {
        /**
         * TODO: implement it — O(1)
         * Посмотреть на минимальный элемент
         */
        return heap[0];
    }

    @Override
    public Key extractMin() {
        /**
         * TODO: implement it — O(log n)
         * Достать минимальный элемент
         *  и перестроить кучу
         */
        Key tmp = heap[0];
        heap[0] = heap[--size];
        siftDown(0);
        if (heap.length > 4*size && size != 0){
            shrink();
        }
        return tmp;

    }

    public int length(){
        return heap.length;
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

    private void siftUp(int Idx) {
        /**
         * TODO: implement it — O(log n)
         * Просеивание вверх —
         *  подъём элемента больше родителей
         */
        if (Idx == 0){
            return;
        }
        if (Idx % 2 == 0){
            if (greater((Idx - 2)/2,Idx)){
                swap(Idx,(Idx - 2)/2);
                siftUp((Idx - 2)/2);
            }
        }else{
            if (greater((Idx - 1)/2,Idx)){
                swap(Idx,(Idx - 1)/2);
                siftUp((Idx - 1)/2);
            }
        }
    }

    private void siftDown(int Idx) {
        /**
         * TODO: implement it — O(log n)
         * Просеивание вниз
         *  спуск элемента меньше детей
         */
        int parentOne  = Idx*2 + 2;
        int parentTwo  = Idx*2 + 1;
        if (parentOne < size && greater( parentTwo,parentOne)) {
            if (greater(Idx, parentOne)){
                swap(Idx,parentOne);
                siftDown(parentOne);
            }

        }else if (parentTwo < size && greater(Idx, parentTwo)){
            swap(Idx,parentTwo);
            siftDown(parentTwo);
        }
    }

    private void grow() {
        /**
         * TODO: implement it
         * Если массив заполнился,
         * то увеличить его размер в полтора раз
         */
        changeCapacity(heap.length/2 + heap.length);
    }

    private void shrink() {
        /**
         * TODO: implement it
         * Если количество элементов в четыре раза меньше,
         * то уменьшить его размер в два раза
         */
        changeCapacity(heap.length/2);
    }

    private boolean greater(int i, int j) {
        return c == null
                ? heap[i].compareTo(heap[j]) > 0
                : c.compare(heap[i], heap[j]) > 0
                ;
    }

    @Override
    public Iterator<Key> iterator() {
        /* TODO: implement it */
        return null;
    }

    private void changeCapacity(int newCapacity) {

        heap = Arrays.copyOf(heap,newCapacity);
    }

    private void swap(int one, int two){
        Key tmp = heap[one];
        heap[one] = heap[two];
        heap[two] = tmp;
    }
}
