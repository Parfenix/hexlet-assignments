package exercise;

import java.util.logging.Logger;


// BEGIN
public class MinThread extends Thread {
    private static final Logger LOGGER = Logger.getLogger(MinThread.class.getName());
    private final int[] numbers;
    private int min;

    public MinThread(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        LOGGER.info("Thread " + getName() + " started");
        min = numbers[0];
        for (int number : numbers) {
            if (number < min) {
                min = number;
            }
        }
        LOGGER.info("Thread " + getName() + " finished");
    }

    public int getMin() {
        return min;
    }
}
// END
