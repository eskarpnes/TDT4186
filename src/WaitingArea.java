import java.util.LinkedList;

/**
 * This class implements a waiting area used as the bounded buffer, in the producer/consumer problem.
 */
public class WaitingArea {

    private int capacity;
    private LinkedList<Customer> customerQueue;

    /**
     * Creates a new waiting area.
     * areaSize decides how many people can be waiting at the same time (how large the shared buffer is)
     *
     * @param size The maximum number of Customers that can be waiting.
     */
    public WaitingArea(int size) {
        // TODO Implement required functionality
        this.capacity = size;
        this.customerQueue = new LinkedList<>();
    }

    /**
     * This method should put the customer into the waitingArea
     *
     * @param customer A customer created by Door, trying to enter the waiting area
     */
    public synchronized void enter(Customer customer) {
        // TODO Implement required functionality
        if (this.customerQueue.size() < capacity){
            this.customerQueue.add(customer);
        } else {
            System.out.println("A customer tried to enter a full waiting area.");
            throw new IllegalStateException("Waiting area full.");
        }
    }

    /**
     * @return The customer that is first in line.
     */
    public synchronized Customer next() {
        // TODO Implement required functionality
        if (!this.customerQueue.isEmpty()) {
            return this.customerQueue.pop();
        } else {
            System.out.println("No more customers in waiting area.");
            return null; //Might find a way to not have to return null #cleancode
        }
    }

    public int getQueueLength() {
        return this.customerQueue.size();
    }

    // Add more methods as you see fit
}
