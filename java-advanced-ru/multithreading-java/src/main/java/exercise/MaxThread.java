package exercise;

import java.util.logging.Logger;

// BEGIN
public class MaxThread extends Thread {
    private static final Logger LOGGER = Logger.getLogger(MinThread.class.getName());
    private final int[] numbers;
    private int max;

    public MaxThread(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        LOGGER.info("Thread " + getName() + " started");
        max = numbers[0];
        for (int number : numbers) {
            if (number > max) {
                max = number;
            }
        }
        LOGGER.info("Thread " + getName() + " finished");
    }

    public int getMax() {
        return max;
    }
}
// END
