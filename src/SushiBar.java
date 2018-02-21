import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class SushiBar {
    //SushiBar settings
    private static int capacity = 10;
    private static int duration = 3;
    public static int maxOrder = 10;
    public static int customerWait = 3000;
    public static int doorWait = 100;
    public static boolean isOpen = true;

    //Creating log file
    private static File log;
    private static String path = "./";

    //Variables related to statistics
    public static SynchronizedInteger customerCounter;
    public static SynchronizedInteger servedOrders;
    public static SynchronizedInteger takeawayOrders;
    public static SynchronizedInteger totalOrders;
    public static int lastCustomer;

    /**
     * @param args
     */
    public static void main(String[] args) {
        log = new File(path + "log.txt");

        // TODO initialize the bar
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
}
