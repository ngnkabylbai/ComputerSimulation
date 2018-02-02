
public class Request {

    private int processingWeight;

    public Request (int processingWeight) {
        this.processingWeight = processingWeight;
    }

    public int getWeight() {
        return processingWeight;
    }

    @Override
    public String toString() {
        return "Request{ " + processingWeight + " }";
    }
}