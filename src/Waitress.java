/**
 * This class implements the consumer part of the producer/consumer problem.
 * One waitress instance corresponds to one consumer.
 */
public class Waitress implements Runnable {

    private final WaitingArea waitingArea;

    /**
     * Creates a new waitress. Make sure to save the parameter in the class
     *
     * @param waitingArea The waiting area for customers
     */
    public Waitress(WaitingArea waitingArea) {
        // TODO Implement required functionality
        this.waitingArea = waitingArea;
    }

    /**
     * This is the code that will run when a new thread is
     * created for this instance
     */
    @Override
    public void run() {
        // TODO Implement required functionality
        while (SushiBar.isOpen) {
            if (!this.waitingArea.isQueueEmpty()){
                Customer customer = waitingArea.next();
                if (customer == null) {
                    continue;
                }
                SushiBar.write("Customer #" + Integer.toString(customer.getCustomerID()) + " is now fetched.");
                try {
                    Thread.sleep(SushiBar.waitressWait);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                customer.order();
            }
        }
    }


}
