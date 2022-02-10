package tasktwo;

import tasktwo.queue.MyQueue;

import java.util.Random;
import java.util.stream.IntStream;

public class MyTaskProducer implements Runnable {

    private final String TASK_PATTERN = "([1-9][0-9]*([*/+-][1-9][0-9]*|([*-+]0))*)";
    private final char[] CHARS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '*', '/', '-', '+'};
    private final MyQueue queue;
    private final int threadNo;

    public MyTaskProducer(MyQueue queue, int threadNo) {
        this.queue = queue;
        this.threadNo = threadNo;
    }

    @Override
    public void run() {

        while (true) {
            try {
                String task = createTask();
                queue.put(task);
                System.out.printf("Producer: %d produced task: %s at time: %d%n", threadNo, task, System.currentTimeMillis());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String createTask() {
        int charsNo = new Random().nextInt(100) + 1;
        StringBuilder sb = new StringBuilder();

        do {
            sb.setLength(0);
            IntStream.range(0, charsNo)
                    .forEach(index -> sb.append(CHARS[new Random().nextInt(CHARS.length)]));
        } while (!isTaskValid(sb.toString()));

        return sb.toString();
    }


    private boolean isTaskValid(String string) {
        return string.matches(TASK_PATTERN);
    }

}

