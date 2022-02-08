package tasktwo.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyQueue {

    private final Lock lock = new ReentrantLock();
    private final Condition producingCondition = lock.newCondition();
    private final Condition consumingCondition = lock.newCondition();

    private final String[] array;

    int putIndex, takeIndex;
    int count;
    boolean rdyToProduce;

    public MyQueue(int maxElements) {
        this.rdyToProduce = true;
        this.array = new String[maxElements];
    }

    public void put(String element) throws InterruptedException {

        lock.lock();
        try {

            while (!rdyToProduce) {
                producingCondition.await();
            }
            array[putIndex] = element;
            putIndex++;
            if (putIndex == array.length) {
                putIndex = 0;
            }
            count++;

            if (count == array.length) {
                rdyToProduce = false;
            }

            consumingCondition.signal();
        } finally {
            lock.unlock();
        }

    }

    public String take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                consumingCondition.await();
                rdyToProduce = true;
            }
            String element = array[takeIndex];
            takeIndex++;
            if (takeIndex == array.length) {
                takeIndex = 0;
            }
            --count;
            if (count == array.length / 2) {
                rdyToProduce = true;
            }
            producingCondition.signal();
            return element;
        } finally {
            lock.unlock();
        }
    }
}
