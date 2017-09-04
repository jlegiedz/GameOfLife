import java.util.Scanner;

/**
 * Created by asia on 03/09/17.
 */
public class GameOfLife {
    final String LIVE = "<3";
    final String DEAD = "..";
    boolean[][] playground;
    int rows;
    int columns;

    public GameOfLife(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.playground = new boolean[rows][columns];
    }

    public void showPlayground() {
        for (boolean row[] : playground) {
            for (boolean elem : row) {
                if (elem == true) {
                    System.out.print(LIVE);
                } else {
                    System.out.print(DEAD);
                }
            }
            System.out.println();
        }
    }

    public void createingFirstGeneration() {
        playground[0][0] = true;
        playground[0][1] = true;
        playground[1][0] = true;
        playground[4][3] = true;
        playground[7][3] = true;
        playground[4][4] = true;
        playground[7][4] = true;
        playground[4][5] = true;
        playground[7][5] = true;
        playground[4][6] = true;
        playground[7][6] = true;
        playground[5][3] = true;
        playground[6][6] = true;
        playground[6][3] = true;
        playground[5][6] = true;
    }


    public int checkNeighbor(int x, int y) {
        int neighbor = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            if (i < 0 || i > rows - 1) continue;
            for (int j = y - 1; j <= y + 1; j++) {
                if ((j < 0 || j > columns - 1) || (i == x && j == y)) continue;
                if (playground[i][j] == true) {
                    neighbor++;
                }
            }
        }
        return neighbor;
    }

    public boolean liveOrDead(int x, int y){
        int neighbor = checkNeighbor(x,y);
        boolean state = false;
        if(playground[x][y]) {
            if (playground[x][y] == true && (neighbor >= 2 && neighbor <= 3)) state = true;
            if (playground[x][y] == true && (neighbor <= 1 || neighbor > 3)) state = false;
        }
        else{
            if (playground[x][y] == false && neighbor == 3) state = true;
            else{
                state = false;
            }
        }
        return state;
    }

    public void newGeneration(){
        boolean[][] newGeneration = new boolean[rows][columns];
     //    fill in the elements of newGeneration with elements of previous playground modified with liveOrDead method
        for (int i = 0; i <rows ; i++) {
            for (int j = 0; j < columns ; j++) {
            newGeneration[i][j] = liveOrDead(i,j);
            }
        }
        playground = newGeneration;
    }

    public static void main(String[] args) {
        GameOfLife game = new GameOfLife(10, 10);
        game.createingFirstGeneration();
        game.showPlayground();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press enter to display next generation:");
        while(scanner.nextLine().equals("")){
            System.out.println("Next generation:");
            game.newGeneration();
            game.showPlayground();
        }

    }


}
    
