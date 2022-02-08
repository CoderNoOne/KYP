package tasktwo;

import tasktwo.queue.MyQueue;

public class MyTaskProducer implements Runnable {

    private final String TASK_PATTERN = "([+-]?[1-9][0-9]*([*/+-][1-9][0-9]*|([*-+]0))*)";
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
                String randomString = createRandomString();
                queue.put(randomString);
                System.out.println("produced: " + randomString + "  by: " + threadNo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String createRandomString() {
        return "";
    }


    private boolean isStringValid(String string) {
        return string.matches(TASK_PATTERN);
    }

}

