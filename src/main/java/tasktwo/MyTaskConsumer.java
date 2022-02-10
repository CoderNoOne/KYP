package tasktwo;

import tasktwo.queue.MyQueue;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MyTaskConsumer implements Runnable {

    Logger logger = Logger.getLogger(MyTaskConsumer.class.getName());

    private final MyQueue queue;
    private final int threadNo;
    private final TaskCalculator taskCalculator = new TaskCalculator();

    public MyTaskConsumer(MyQueue queue, int threadNo) {
        this.queue = queue;
        this.threadNo = threadNo;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String task = queue.take();
                System.out.printf("Consumer: %d calculated value for task: %s is: %s at time: %d%n", threadNo, task, taskCalculator.calculateValue(task), System.currentTimeMillis());
            } catch (InterruptedException e) {
                logger.log(Level.WARNING, e.getMessage(), e);
            }
        }
    }
}
