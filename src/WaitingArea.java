import java.util.LinkedList;

/**
 * This class implements a waiting area used as the bounded buffer, in the producer/consumer problem.
 */
public class WaitingArea {

    int size;
    LinkedList<Customer> queue;

    /**
     * Creates a new waiting area.
     * areaSize decides how many people can be waiting at the same time (how large the shared buffer is)
     *
     * @param size The maximum number of Customers that can be waiting.
     */
    public WaitingArea(int size) {
        // TODO Implement required functionality
        this.size = size;
        this.queue = new LinkedList<>();
    }

    /**
     * This method should put the customer into the waitingArea
     *
     * @param customer A customer created by Door, trying to enter the waiting area
     */
    public synchronized void enter(Customer customer) {
        // TODO Implement required functionality
        if (this.queue.size() < size){
            this.queue.add(customer);
        } else {
            System.out.println("A customer tried to enter a full waiting area.");
        }
    }

    /**
     * @return The customer that is first in line.
     */
    public synchronized Customer next() {
        // TODO Implement required functionality
        if (!this.queue.isEmpty()) {
            return this.queue.pop();
        } else {
            System.out.println("No more customers in waiting area.");
            return null; //Might find a way to not have to return null #cleancode
        }
    }

    // Add more methods as you see fit
}
