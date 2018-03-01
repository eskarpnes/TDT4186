import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WaitingAreaTest {

    WaitingArea waitingArea;
    private Customer customer;

    @Before
    public void setUp() throws Exception {
        int capacity = 3;
        this.customer = new Customer();
        this.waitingArea = new WaitingArea(capacity);
    }

    @After
    public void tearDown() throws Exception {
        this.waitingArea = null;
    }

    @Test
    public void enter() throws Exception {
        try {
            waitingArea.enter(this.customer);
        } catch (IllegalStateException e) {
            fail("Could not add customer to waiting area.");
        }
    }

    @Test
    public void enterWhenFull() throws Exception {
        try {
            waitingArea.enter(this.customer);
            waitingArea.enter(this.customer);
            waitingArea.enter(this.customer);
            waitingArea.enter(this.customer);
            fail("Waiting area is full. It should not accept more customers.");
        } catch (IllegalStateException e) {
            //Expected
        }
    }

    @Test
    public void next() throws Exception {
        try {
            waitingArea.enter(this.customer);
        } catch (IllegalStateException e) {
            fail("Customer could not enter waiting area.");
        }
        Customer customerFromWaitingArea = waitingArea.next();
        Assert.assertEquals(this.customer, customerFromWaitingArea);
    }

    @Test
    public void nextWhenEmpty() throws Exception {
        Customer customerFromWaitingArea = waitingArea.next();
        Assert.assertNull(customerFromWaitingArea);
    }

}