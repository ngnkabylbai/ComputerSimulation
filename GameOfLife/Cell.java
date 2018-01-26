import java.lang.Thread;

class Cell extends Thread {

    private int x = -1;
    private int y = -1;

    private boolean isCellAlive = false;
    private WhatToDoEnum whatToDo = WhatToDoEnum.DIE;
    private GameController controller;

    private Grid grid;

    Cell (int x, int y, Grid grid) {
        this.x = x;
        this.y = y;
        this.grid = grid;
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public synchronized final void run() {

        while(true) {
            try {
                controller.ready();
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            whatToDo = grid.obtainWhatToDo(this);
            controller.ready();

            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                
            invalidate();
        }

    }
    
    private void invalidate () {
        if(isCellAlive && whatToDo == WhatToDoEnum.DIE) {
            isCellAlive = false;
        } else if(!isCellAlive && whatToDo == WhatToDoEnum.REVIVE) {
            isCellAlive = true;
        }
    }

    void setWhatToDo(WhatToDoEnum whatToDo) { this.whatToDo = whatToDo; }

    boolean isCellAlive() { return isCellAlive; }

    void revive() { this.isCellAlive = true; }

    int getX() { return x; }

    int getY() { return y; }

    @Override
    public String toString() { return "Cell {x="+x+", y="+y+"}"; } 
}