
import java.util.ArrayList;
import java.lang.Thread;

class GameController extends Thread {
    private Grid grid;
    private static volatile GameController instance;
    private int aliveCount = 0;
    private int aliveCounter = 0;

    public static GameController getInstance() {
        GameController localInstance  = instance;
        if(localInstance == null) {
            synchronized (GameController.class) {
                localInstance = instance;
                if(localInstance == null) {
                    instance = localInstance = new GameController();
                }
            }
        }
        return localInstance;
    }
    
    void initializeGrid(int size) {
        if(size < 10) {
            System.out.println("Illegal size. Minimum size is 10");
            return;
        }
        setGrid(new Grid(size, this));
    }

    @Override
    public synchronized void start() {
        grid.begin();
        while(true) {
            try {
                aliveCount = grid.getAliveCount();
                if(aliveCount == 0) {
                    Thread.currentThread().interrupt();
                    return;
                }
                grid.wakeUpAllAliveCells();
                wait();

                grid.printGrid();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void ready() {
        aliveCounter++;
        if(aliveCount == aliveCounter) {
            aliveCounter = 0;
            this.notify();
        }
    }

    void revive(int y, int x) {
        getGrid().revive(x, y);
    }

    private void printGrid() {
        getGrid().printGrid();
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid newGrid) {
        this.grid = newGrid;
    }

    @Override
    public String toString() {
        return "GameController { " + grid.toString() + " }";
    }
}