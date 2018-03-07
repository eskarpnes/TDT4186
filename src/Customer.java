import java.util.Random;

/**
 * This class implements a customer, which is used for holding data and update the statistics
 *
 */
public class Customer {

    private Random random = new Random();
    private int maxOrder = SushiBar.maxOrder;
    private int id;

    /**
     *  Creates a new Customer.
     *  Each customer should be given a unique ID
     */
    public Customer() {
        this.id = SushiBar.customerCounter.get();
        SushiBar.customerCounter.increment();
    }


    /**
     * Here you should implement the functionality for ordering food as described in the assignment.
     */
    public synchronized void order(){
        int orders = randomIntInRange(0, maxOrder);
        int takeout = randomIntInRange(0, orders);
        SushiBar.servedOrders.add(orders-takeout);
        SushiBar.takeawayOrders.add(takeout);
        SushiBar.totalOrders.add(orders);
        SushiBar.write(Thread.currentThread().getName() + ": Customer #" + Integer.toString(this.id) + " is now eating.");
        try {
            Thread.sleep(SushiBar.customerWait);
            SushiBar.write(Thread.currentThread().getName() + ": Customer #" + Integer.toString(this.id) + " is now leaving.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return Should return the customerID
     */
    public int getCustomerID() {
        return this.id;
    }

    private int randomIntInRange(int min, int max) {
        return random.nextInt((max-min) + 1) + min;
    }

    // Add more methods as you see fit
}
