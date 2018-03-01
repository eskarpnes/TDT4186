import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest {
    public static SynchronizedInteger customerCounter = new SynchronizedInteger(0);
    private Customer customer;

    @Before
    public void setUp() throws Exception {
        this.customer = new Customer();
    }

    @After
    public void tearDown() throws Exception {
        this.customer = null;
    }

    @Test
    public void order() throws Exception {

    }

    @Test
    public void getCustomerID() throws Exception {
        Assert.assertEquals(0, this.customer.getCustomerID());
    }

}