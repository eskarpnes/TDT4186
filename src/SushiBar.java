import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class SushiBar {

    //SushiBar settings
    private static int waitingAreaCapacity = 20;
    private static int waitressCount = 10;
    private static int duration = 3;
    public static int maxOrder = 10;
    public static int waitressWait = 50; // Used to calculate the time the waitress spends before taking the order
    public static int customerWait = 150; // Used to calculate the time the customer uses eating
    public static int doorWait = 10; // Used to calculate the interval at which the door tries to create a customer
    public static boolean isOpen = true;

    //Creating log file
    private static File log;
    private static String path = "./";

    //Variables related to statistics
    public static SynchronizedInteger customerCounter = new SynchronizedInteger(0); //Must be initiated for testing.
    public static SynchronizedInteger servedOrders;
    public static SynchronizedInteger takeawayOrders;
    public static SynchronizedInteger totalOrders;


    public static void main(String[] args) {
        log = new File(path + "log.txt");

        //Initializing shared variables for counting number of orders
        customerCounter = new SynchronizedInteger(0);
        totalOrders = new SynchronizedInteger(0);
        servedOrders = new SynchronizedInteger(0);
        takeawayOrders = new SynchronizedInteger(0);

        // TODO initialize the bar and start the different threads
        ArrayList<Thread> threads = new ArrayList<>();
        SushiBar sushiBar = new SushiBar();
        WaitingArea waitingArea = new WaitingArea(sushiBar.waitingAreaCapacity);
        Door door = new Door(waitingArea);
        threads.add(new Thread(door, "Door"));
        for (int i = 0; i < sushiBar.waitressCount; i++) {
            Waitress waitress = new Waitress(waitingArea);
            threads.add(new Thread(waitress, "waitress" + Integer.toString(i+1)));
        }
        Clock clock = new Clock(sushiBar.duration);
        for (Thread thread : threads) {
            thread.start();
        }
    }

    //Writes actions in the log file and console
    public static void write(String str) {
        try {
            FileWriter fw = new FileWriter(log.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(Clock.getTime() + ", " + str + "\n");
            bw.close();
            System.out.println(Clock.getTime() + ", " + str);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void endLoggingSequence() {
        SushiBar.write("***** NO MORE CUSTOMERS - THE SHOP IS CLOSED NOW. *****");
        SushiBar.write("Stats:");
        SushiBar.write("Total number of orders: " + Integer.toString(SushiBar.totalOrders.get()));
        SushiBar.write("Total number of takeaways: " + Integer.toString(SushiBar.takeawayOrders.get()));
        SushiBar.write("Total number of orders eaten at the bar: " + Integer.toString(SushiBar.servedOrders.get()));
    }
}
