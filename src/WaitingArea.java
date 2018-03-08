import java.util.LinkedList;

/**
 * This class implements a waiting area used as the bounded buffer, in the producer/consumer problem.
 */
public class WaitingArea {

    private int capacity;
    protected final LinkedList<Customer> customerQueue;
    private boolean stopServing;

    /**
     * Creates a new waiting area.
     * areaSize decides how many people can be waiting at the same time (how large the shared buffer is)
     *
     * @param size The maximum number of Customers that can be waiting.
     */
    public WaitingArea(int size) {
        this.capacity = size;
        this.customerQueue = new LinkedList<>();
        this.stopServing = false;
    }

    /**
     * This method should put the customer into the waitingArea
     *
     * @param customer A customer created by Door, trying to enter the waiting area
     */
    public synchronized void enter(Customer customer) {
        if (this.customerQueue.size() < capacity) {
            this.customerQueue.add(customer);
            SushiBar.write(Thread.currentThread().getName() + ": Customer #" + Integer.toString(customer.getCustomerID()) + " is now waiting.");
            notify();
        } else {
            throw new IllegalStateException("Waiting area full.");
        }
    }

    /**
     * @return The customer that is first in line.
     */
    public synchronized Customer next() {
        Customer customer = null;
        if (!customerQueue.isEmpty()) {
            customer = this.customerQueue.removeFirst();
        }
        return customer;
    }

    public void close() {
        while(this.customerQueue.size() > 0) {
            synchronized (this) {
                notify();
            }
        }
        this.stopServing = true;
        synchronized (this) {
            notifyAll();
        }
    }

    public synchronized void checkForMore() {
        if (!customerQueue.isEmpty()) {
            notify();
        }
    }

    protected boolean isStopServing() {
        return this.stopServing;
    }
}
