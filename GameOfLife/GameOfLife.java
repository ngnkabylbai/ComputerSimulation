
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

class GameOfLife {

    private static int size = 30;

    public static void main(String[] args) {

        GameController controller = GameController.getInstance();
        controller.initializeGrid(size);
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("input/input.txt"));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();            
            
            while(line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

            initializeController(controller, sb.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        controller.start();
    }

    private static void initializeController(GameController controller, String input) {
        int row = 0;
        int column = -1;
        for(int i = 0; i < input.length(); i++) {
            column++;
            char currentChar = input.charAt(i);
            if( currentChar == '\n') {
                row++;
                column = -1;
            } else if(currentChar == '@') {
                controller.revive(row, column);
            }
        }        
    }

}