
import java.lang.Runnable;

class Cell implements Runnable {

    private int x = -1;
    private int y = -1;

    private boolean isAlive = false;
    private WhatToDoEnum whatToDo = WhatToDoEnum.DIE;

    Cell (int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void run() {
        // while (true) {
            // WhatToDoEnum todo = GameController.getInstance().whatToDo(this);
            
            // switch (todo) {
                // case LIVE: continue;
                // case DIE: ;
                // case REVIVE: ;  
            // }
        // }
    }
    
    void invalidate () {
        if(isAlive && whatToDo == WhatToDoEnum.DIE) {
            isAlive = false;
        } else if(!isAlive && whatToDo == WhatToDoEnum.REVIVE) {
            isAlive = true;
        }
    }

    void setWhatToDo(WhatToDoEnum whatToDo) { this.whatToDo = whatToDo; }

    boolean isAlive() { return isAlive; }

    void revive() { this.isAlive = true; }

    int getX() { return x; }

    int getY() { return y; }

    @Override
    public String toString() { return "Cell {x="+x+", y="+y+"}"; } 
}