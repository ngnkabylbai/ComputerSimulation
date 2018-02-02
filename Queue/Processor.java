import java.lang.InterruptedException;
import java.util.LinkedList;
import java.util.Queue;


public class Processor extends Thread {

    private String name;
    private int cpu; 
    private RequestPool requestPool;

    public Processor(String name, int cpu, RequestPool requestPool) {
        this.name = name;
        this.cpu = cpu;
        this.requestPool = requestPool;
        this.requestPool.setServingRate(cpu); // setting serving rate of the pool
    }

    public synchronized void run() {
        System.out.println(name+" started...");
        try {
            while(true) {
                while(requestPool.isEmpty()) {
                    // System.out.println("Processor waiting...");
                    Thread.sleep(200);
                }
                
                Request servingRequest = (Request) requestPool.peek(); // get top request from queue

                System.out.println(name + ". Serving...:" + servingRequest.toString()); // log

                int servingTime = servingRequest.getWeight() / cpu * 1000;
                Thread.sleep(servingTime);  // service the request

                requestPool.remove();
                System.out.println(name + ": DONE : " + servingRequest.toString());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getCpu() {
        return this.cpu;
    }
}