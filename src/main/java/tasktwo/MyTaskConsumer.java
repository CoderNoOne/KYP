package tasktwo;

import tasktwo.queue.MyQueue;

import java.math.BigDecimal;

public class MyTaskConsumer implements Runnable {

    private final MyQueue queue;
    private final int threadNo;

    public MyTaskConsumer(MyQueue queue, int threadNo) {
        this.queue = queue;
        this.threadNo = threadNo;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Consumed:" + calculateValue(queue.take()) + " by: " + threadNo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private BigDecimal calculateValue(String chars) {
        return null;
    }
}
