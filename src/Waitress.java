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
        this.waitingArea = waitingArea;
    }

    /**
     * This is the code that will run when a new thread is
     * created for this instance
     */
    @Override
    public void run() {
        while (!waitingArea.isStopServing()) {
            synchronized (waitingArea) {
                try {
                    waitingArea.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (!waitingArea.isStopServing()) {
                Customer customer = waitingArea.next();
                SushiBar.write(Thread.currentThread().getName() + ": Customer #" + Integer.toString(customer.getCustomerID()) + " is now fetched.");
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
