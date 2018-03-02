

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world");   
        
        Coordinator coordinator = new Coordinator();
        RequestCreator requestCreator1 = new RequestCreator(coordinator);
        
        RequestPool pool1 = new RequestPool("Pool1");
        coordinator.addPool(pool1);
        RequestPool pool2 = new RequestPool("Poo2");
        coordinator.addPool(pool2);
        RequestPool pool3 = new RequestPool("Pool3");
        coordinator.addPool(pool3);
        
        coordinator.initWriter();

        Processor p1 = new Processor("Processor1", 500, pool1);
        requestCreator1.start();
        p1.start();

        Processor p2 = new Processor("Processor2", 1000, pool2);
        p2.start();

        Processor p3 = new Processor("Processor3", 1600, pool3);
        p3.start();
    }

}
    