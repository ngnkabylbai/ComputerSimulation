
import java.lang.Runnable;

class Cell implements Runnable {

    private int x = -1;
    private int y = -1;

    private Cell top;
    private Cell bottom;
    private Cell right;
    private Cell left;

    private boolean isAlive = false;

    Cell (int x, int y) {
        this.x = x;
        this.y = y;
    }

    Cell (int x, int y, Cell top, Cell bottom, Cell right, Cell left) {
        this.x = x;
        this.y = y;
        this.top = top;
        this.bottom = bottom;
        this.right = right;
        this.left = left;
    }

    @Override
    public void run() {
        while (true) {
            WhatToDoEnum todo = GameController.getInstance().whatToDo(this);
            
            switch (todo) {
                case Live: continue;
                case Die: ;
                case Revive: ;  
            }
        }
    }

    boolean isAlive() {
        return isAlive;
    }

    void revive() {
        this.isAlive = true;
    }

    void setLeft (Cell left) {
        this.left = left;
    }

    void setRight (Cell right) {
        this.right = right;
    }

    void setTop (Cell top) {
        this.top = top;
    }

    void setBottom (Cell bottom) {
        this.bottom = bottom;
    }

    Cell getLeft () {
        return left;
    }
    
    Cell getRight () {
        return right;
    }
    
    Cell getTop () {
        return top;
    }
    
    Cell getBottom () {
        return bottom;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Cell {x="+x+", y="+y+"}";
    } 
}