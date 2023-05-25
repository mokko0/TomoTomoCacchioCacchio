public class Customer implements Runnable {
    private int myNumber;

    public Customer() {
        synchronized (Main.class) {
            myNumber = ++Main.customerNumber;
        }
    }

    @Override
    public void run() {
        try {
            if (!Main.availableChairs.tryAcquire(Main.MAX_WAIT_TIME, java.util.concurrent.TimeUnit.MILLISECONDS)) {
                System.out.println("Customer " + myNumber + " gave up waiting for a free chair.");
                return;
            }
            int chairNumber = -1;
            synchronized (Main.chairs) {
                for (int i = 0; i < Main.NUM_CHAIRS; i++) {
                    if (!Main.chairs[i]) {
                        Main.chairs[i] = true;
                        chairNumber = i;
                        break;
                    }
                }
            }
            System.out.println("Customer " + myNumber + " is sitting in chair " + (chairNumber + 1) + " and waiting for their turn.");
            Thread.sleep(Main.random.nextInt(10000));
            System.out.println("Customer " + myNumber + " is having their portrait drawn.");
            Thread.sleep(Main.random.nextInt(10000));
            System.out.println("Customer " + myNumber + "'s portrait is finished.");
            synchronized (Main.chairs) {
                Main.chairs[chairNumber] = false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            Main.availableChairs.release();
        }
    }
}