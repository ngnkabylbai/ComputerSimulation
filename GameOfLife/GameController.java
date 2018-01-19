import java.util.ArrayList;
import java.lang.Runnable;
import java.io.IOException;

class GameController implements Runnable {
    private Grid grid;
    private static volatile GameController instance;

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
        setGrid(new Grid(size));
    }

    @Override
    public void run() {
        while(true) {
            try {
                getGrid().invalidate();
                getGrid().printGrid();
                Thread.sleep(300);    
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void start() {
        // grid.start();
        this.run();
    }

    void revive(int y, int x) {
        getGrid().revive(x, y);
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