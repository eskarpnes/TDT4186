import java.util.Timer;

/**
 * This class implements the Door component of the sushi bar assignment
 * The Door corresponds to the Producer in the producer/consumer problem
 */
public class Door implements Runnable {

    private final WaitingArea waitingArea;
    private final int frequency;

    /**
     * Creates a new Door. Make sure to save the
     * @param waitingArea   The customer queue waiting for a seat
     */
    public Door(WaitingArea waitingArea) {
        this.waitingArea = waitingArea;
        this.frequency = SushiBar.doorWait;
    }

    /**
     * This method will run when the door thread is created (and started)
     * The method should create customers at random intervals and try to put them in the customerQueue
     */
    @Override
    public void run() {
        while(SushiBar.isOpen) {
            spawnCustomer();
            try {
                Thread.sleep((int)(this.frequency*Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        SushiBar.write("***** NO MORE CUSTOMERS - THE SHOP IS CLOSED NOW. *****");
        waitingArea.close();
    }

    private void spawnCustomer() {
        Customer customer = new Customer();
        try {
            waitingArea.enter(customer);
        } catch (IllegalStateException e) {
            //Waiting area full, ignore customer
        }
    }
}
