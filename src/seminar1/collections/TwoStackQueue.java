package seminar1.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TwoStackQueue<Item> implements IQueue<Item> {

    private IStack<Item> stack1;
    private IStack<Item> stack2;

    public TwoStackQueue() {
        /* TODO: implement it */
        stack1 = new ArrayStack<>();
        stack2 = new ArrayStack<>();
//        stack1 = new LinkedStack<>();
//        stack2 = new LinkedStack<>();
    }

    @Override
    public void enqueue(Item item) {
        /* TODO: implement it */
        stack1.push(item);
    }

    @Override
    public Item dequeue() {
        /* TODO: implement it */
        if (stack2.isEmpty()) {
            if (stack1.isEmpty()){ throw new NoSuchElementException();} else { getItem();}
        }
       return stack2.pop();
    }

    @Override
    public boolean isEmpty() {
        /* TODO: implement it */
        return (stack1.isEmpty() && stack2.isEmpty());
    }

    @Override
    public int size() {
        /* TODO: implement it */
        return (stack2.size() + stack1.size());
    }

    @Override
    public Iterator<Item> iterator() {
        /* TODO: implement it (optional) */
        return null;
    }
    private void getItem(){

            while (!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
    }

}
