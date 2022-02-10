package tasktwo;

import tasktwo.queue.MyQueue;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskTwo {


    public static void main(String[] args) throws FileNotFoundException {

        PrintStream fileOut = new PrintStream("./logs.txt");
        System.setOut(fileOut);

        MyQueue myQueue = new MyQueue(100);
        ExecutorService producerExecutorService = Executors.newFixedThreadPool(4);
        ExecutorService consumerExecutorService = Executors.newFixedThreadPool(2);

        producerExecutorService.submit(new MyTaskProducer(myQueue, 1));
        producerExecutorService.submit(new MyTaskProducer(myQueue, 2));
        producerExecutorService.submit(new MyTaskProducer(myQueue, 3));
        producerExecutorService.submit(new MyTaskProducer(myQueue, 4));

        consumerExecutorService.submit(new MyTaskConsumer(myQueue, 1));
        consumerExecutorService.submit(new MyTaskConsumer(myQueue, 2));

        producerExecutorService.shutdown();
        consumerExecutorService.shutdown();

    }
}
