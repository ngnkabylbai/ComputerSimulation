import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Coordinator {

    private ArrayList<RequestPool> requestPools;
    private PrintWriter writer;
    private int time = 0;
    private char separator = ',';

    public Coordinator() {
        requestPools = new ArrayList<>();
       
    }

    public Coordinator(ArrayList<RequestPool> requestPools) {
        this.requestPools = requestPools;
    }
    
    public void addPool(RequestPool newRequestPool) {
        requestPools.add(newRequestPool);
    }
    
    public void initWriter() {
        try {
            writer = new PrintWriter("output.csv", "UTF-8");
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        writer.print("time");
        for(int i = 0; i < requestPools.size(); i++) {
            writer.print(separator+""+i);
        }
    }

    public void addRequest(Request newRequest) {
        writer.print('\n');
        writer.print((time++));
        for(int i = 0; i < requestPools.size(); i++) {
            RequestPool current = requestPools.get(i);
            int currentWeight = current.getQueueWeight();
            int currentRate = current.getServingRate();
            
            writer.print(separator+""+(currentWeight/currentRate)); // TODO: weight * cpu

            // System.out.println("REQUEST. POOL: " + i + "_weight_"+currentWeight +"_rate_"+currentRate+"___" + currentWeight / currentRate);
        }

        writer.print(separator+""+(requestPools.get(0).getQueueWeight()*500)+separator+
        (requestPools.get(1).getQueueWeight()*1000)+separator+(requestPools.get(2).getQueueWeight()*1600));
        

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
        // System.out.println("COORDINATOR: ADD REQUEST TO ::::::::::::::: " + index);
        requestPools.get(index).addRequest(newRequest);

        System.out.println("__________________"+RequestCreator.requestCount+"__________________");
        if(RequestCreator.requestCount == 0) {
            writer.close();
            System.out.println("WRITER DONE");
        }
    }
}