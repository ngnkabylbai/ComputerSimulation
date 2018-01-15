
import java.util.ArrayList;

class GameOfLife {

    public static void main(String[] args) {

        GameController controller = GameController.getInstance();
        controller.initializeGrid(30);
        controller.revive(0,0);
        controller.revive(1,0);
        controller.revive(0,1);

        controller.run();
        // System.out.println(controller.toString());
    }

}