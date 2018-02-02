
import java.util.Random;


public class RequestCreator extends Thread {

    // private RequestPool requestPool;
    private Coordinator coordinator;
    private Random random;

    public RequestCreator(Coordinator coordinator) {
        // this.requestPool = requestPool;
        this.coordinator = coordinator;
        this.random = new Random();
    }

    public void run () {
        System.out.println("Request creator started...");
        int counter = 5;
        try {
            while(true) {
                Request newRequest = new Request(random.nextInt(9000)+1000);
                // System.out.println("RequestCreator: Adding new request:" + newRequest.toString());                
                coordinator.addRequest(newRequest);
                System.out.println("RequestCreator: Added new request:" + newRequest.toString());
                
                int waitTime = random.nextInt(1000)+1000;
                System.out.println("RequestCreator: waiting " + waitTime +"..."); 
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}