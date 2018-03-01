import java.util.Timer;

/**
 * This class implements the Door component of the sushi bar assignment
 * The Door corresponds to the Producer in the producer/consumer problem
 */
public class Door implements Runnable {

    private final WaitingArea waitingArea;

    /**
     * Creates a new Door. Make sure to save the
     * @param waitingArea   The customer queue waiting for a seat
     */
    public Door(WaitingArea waitingArea) {
        // TODO Implement required functionality
        this.waitingArea = waitingArea;
    }

    /**
     * This method will run when the door thread is created (and started)
     * The method should create customers at random intervals and try to put them in the customerQueue
     */
    @Override
    public void run() {
        // TODO Implement required functionality
        int frequency = SushiBar.doorWait;
        while(SushiBar.isOpen) {
            spawnCustomer();
            try {
                Thread.sleep(frequency);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected void spawnCustomer() {
        Customer customer = new Customer();
        try {
            waitingArea.enter(customer);
        } catch (IllegalStateException e) {
            System.out.println("No more room in the waiting area.");
        }
    }

    // Add more methods as you see fit
}
