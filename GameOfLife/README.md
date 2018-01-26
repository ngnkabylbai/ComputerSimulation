# Game Of Life
> Java 9.0.1

The game is done on Java by multithreading with using a monitor. The project consists of five classes:
* GameOfLife
* GameController
* Grid
* Cell
* WhatToDoEnum


### GameOfLife.java
> NOTE: Starter class

The class has `main(String[] args)` method. As the starter class it reads initial state of a grid from a [input file](https://github.com/ngnkabylbai/computer_simulation/blob/master/GameOfLife/input/input.txt) and initializes the game.

### GameController
> NOTE: Thread Monitor

The run all threads and monitors them to use resources in a right order.
```java
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
```

### Grid

Holds state of the grid, prints it and gives the GameController access to its state. Tells requsted cells what to do in a next generation:
```java
 public synchronized WhatToDoEnum obtainWhatToDo(Cell cell) {
        
        WhatToDoEnum todo;
        int x = cell.getX();
        int y = cell.getY();

        boolean isCellALive = isCellALive(x, y);
        int aliveNeighbours = 0;
        
        aliveNeighbours += isCellALive(x+1, y) ? 1 : 0; // right
        aliveNeighbours += isCellALive(x, y+1) ? 1 : 0; // bottom
        aliveNeighbours += isCellALive(x-1, y) ? 1 : 0; // left
        aliveNeighbours += isCellALive(x, y-1) ? 1 : 0; // bottom
        aliveNeighbours += isCellALive(x+1, y+1) ? 1 : 0; // right-bottom
        aliveNeighbours += isCellALive(x-1, y-1) ? 1 : 0; // left-up
        aliveNeighbours += isCellALive(x+1, y-1) ? 1 : 0; // right-up
        aliveNeighbours += isCellALive(x-1, y+1) ? 1 : 0; // right-bottom
        
        if(!isCellALive && aliveNeighbours == 3) {
            todo = WhatToDoEnum.REVIVE;
        } else if(isCellALive && (aliveNeighbours < 2 || aliveNeighbours > 3)) {
            todo = WhatToDoEnum.DIE;
        } else if(isCellALive) {
            todo = WhatToDoEnum.LIVE;
        } else {
            todo = WhatToDoEnum.DIE;
        }
        return todo;
    }
```

### Cell

Gets permission from the Grid. According to its state might printed on grid or not in the next generation.

```java
 private void invalidate () {
        if(isCellAlive && whatToDo == WhatToDoEnum.DIE) {
            isCellAlive = false;
        } else if(!isCellAlive && whatToDo == WhatToDoEnum.REVIVE) {
            isCellAlive = true;
        }
    }
```

### WhatToDoEnum

> NOTE: Enumeration class

Enumeration class for Cell's state.

```java
enum WhatToDoEnum {
    LIVE,
    DIE,
    REVIVE
}
```

![generation 0](https://github.com/ngnkabylbai/computer_simulation/blob/master/GameOfLife/screens/0.PNG)
![generation 37](https://github.com/ngnkabylbai/computer_simulation/blob/master/GameOfLife/screens/37.PNG)
![generation 45](https://github.com/ngnkabylbai/computer_simulation/blob/master/GameOfLife/screens/45.PNG)