import java.util.ArrayList;
import java.lang.String;

 class Grid {
    
    private int size;
    private Cell[][] grid;
    private int generation = 0;
    private int multi = 3; 

    Grid (int size) {
        if(size <= 10)
            this.size = 10;        
        this.size = size;
        initializeGrid(size);
    }

    private void initializeGrid(int size) {
        this.grid = new Cell[size][multi*size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < multi*size; x++) {
                this.grid[y][x] = new Cell(y, x);
            }
        }
    }

    void revive(int x, int y) {
        grid[y][x].revive();
    }

    void clearList() {
        grid = new Cell[size][multi*size];
    }

    void killCell(int x, int y) {
        if(x < 0 || y < 0 || x >= multi*size || y >= size)
            return;
        grid[y][x] = null;
    }

    boolean isCellALive(int x, int y) {
        if(x < 0 || y < 0 || x >= multi*size || y >= size)
            return false;
        
        Cell checkCell = grid[y][x];
        return checkCell.isAlive();
    }

    void printGrid() {
        String result = "Generation:"+(generation++)+" Grid size:"
            +size+"/"+multi*size+"\n";
        for(int y = 0; y < size; y++) {
            for(int x = 0; x < multi*size; x++) {
                if(isCellALive(x, y)) {
                    result += '@';
                } else {
                    result += '-';
                }
            }
            result += '\n';
        }
        System.out.println(result);
    }

    @Override
    public String toString() {
        String result = "Grid:\n";
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < multi*size; j++) {
                Cell currentCell = grid[i][j];
                if(currentCell != null) {
                    result += "\n"+currentCell.toString();
                }
            }
        }
        return result;
    }
}