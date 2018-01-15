
import java.util.ArrayList;
import java.lang.Runnable;

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
                printGrid();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized WhatToDoEnum whatToDo(Cell cell) {
        WhatToDoEnum todo = WhatToDoEnum.Live;
        


        return todo;
    }

    void revive(int x, int y) {
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