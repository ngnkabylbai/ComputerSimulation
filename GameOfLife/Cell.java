import java.lang.Thread;

class Cell extends Thread {

    private int x = -1;
    private int y = -1;

    private boolean isCellAlive = false;
    private WhatToDoEnum whatToDo = WhatToDoEnum.DIE;

    private Grid grid;

    Cell (int x, int y, Grid grid) {
        this.x = x;
        this.y = y;
        this.grid = grid;
    }

    @Override
    public void start() {

        while(true) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            whatToDo = grid.obtainWhatToDo(this);
            invalidate();

            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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