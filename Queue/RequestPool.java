import java.util.LinkedList;
import java.util.Queue;

public class RequestPool {

    private Queue<Request> queue;
    private int servingRate = -1;
    private String name;

    public RequestPool(String name) {
        queue = new LinkedList<Request>();
        this.name = name;
    }
    
    public void setServingRate(int servingRate) {
        this.servingRate = servingRate;
    } 

    public int getServingRate() {
        return this.servingRate;
    }

    public void addRequest(Request newRequest) {
        queue.add(newRequest);
        // System.out.println(name + " : weight-UP:" + getQueueWeight());
    }

    public Request peek() {
        return (Request) queue.peek();
    }

    public void remove() {
        queue.remove();
        // System.out.println("Pool: weight-DOWN:" + getQueueWeight());
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int getQueueWeight() {
        int weight = 0;
        for(Request r : queue) {
            weight += r.getWeight();
        }
        return weight;
    }
}