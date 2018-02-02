import java.util.ArrayList;

public class Coordinator {

    private ArrayList<RequestPool> requestPools;

    public Coordinator() {
        requestPools = new ArrayList<>();
    }

    public Coordinator(ArrayList<RequestPool> requestPools) {
        this.requestPools = requestPools;
    }
    
    public void addPool(RequestPool newRequestPool) {
        requestPools.add(newRequestPool);
    }
    
    public void addRequest(Request newRequest) {
        for(int i = 0; i < requestPools.size(); i++) {
            RequestPool current = requestPools.get(i);
            int currentWeight = current.getQueueWeight();
            int currentRate = current.getServingRate();
            
            System.out.println("REQUEST. POOL: " + i + "_weight_"+currentWeight +"_rate_"+currentRate+"___" + currentWeight / currentRate);
        }

        int index = 0;
        int weight = requestPools.get(0).getQueueWeight();
        int rate = requestPools.get(0).getServingRate();

        for(int i = 1; i < requestPools.size(); i++) {
            RequestPool current = requestPools.get(i);
            int currentWeight = current.getQueueWeight();
            int currentRate = current.getServingRate();
            
            if(weight / rate > currentWeight / currentRate) {
                index = i;
                weight = currentWeight;
                rate = currentRate;
            }
        }
        System.out.println("COORDINATOR: ADD REQUEST TO ::::::::::::::: " + index);
        requestPools.get(index).addRequest(newRequest);
    }
}