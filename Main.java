import java.util.concurrent.Semaphore;
import java.util.Random;

public class Main {
    public static final int MAX_WAIT_TIME = 10000; // maximum wait time in milliseconds
    public static final int NUM_CHAIRS = 4;
    public static final Semaphore availableChairs = new Semaphore(NUM_CHAIRS, true);
    public static final Random random = new Random();
    public static final boolean[] chairs = new boolean[NUM_CHAIRS];
    public static int customerNumber = 0;

    public static void main(String[] args) {
        while (true) {
            new Thread(new Customer()).start();
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}