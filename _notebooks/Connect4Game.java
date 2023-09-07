import java.util.Scanner;

public class Connect4Game {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // create a 2D array to represent the game grid
        char[][] grid = new char[6][7];

        // create the array with empty spaces
        for (int row = 0; row < grid.length; row++){
            for (int col = 0; col < grid[0].length; col++){
                grid[row][col] = ' ';
            }
        }

        int turn = 1;
        char player = 'X';
        boolean winner = false;        

        // play a turn
        while (winner == false && turn <= 42){
            boolean validPlay;
            int play;
            do {
                display(grid);

                System.out.print("Player " + player + ", choose a column: ");
                play = in.nextInt();

                // make sure the player's move is valid
                validPlay = validate(play,grid);

            }while (validPlay == false);

            // drop the icon into the selected column
            for (int row = grid.length-1; row >= 0; row--){
                if(grid[row][play] == ' '){
                    grid[row][play] = player;
                    break;
                }
            }

            // determine if there is a winner
            winner = isWinner(player,grid);

            // swap players each turn
            if (player == 'X'){
                player = 'O';
            } else{
                player = 'X';
            }

            turn++;            
        }
        // display the updated grid
        display(grid);

        if (winner){
            if (player=='O'){
                System.out.println("Player O won");
            } else{
                System.out.println("Player X won");
            }
        } else{
            System.out.println("Tie game");
        }

    }

    // display the current state of the game grid
    public static void display(char[][] grid){
        System.out.println(" 0 1 2 3 4 5 6");
        System.out.println("---------------");
        for (int row = 0; row < grid.length; row++){
            System.out.print("|");
            for (int col = 0; col < grid[0].length; col++){
                System.out.print(grid[row][col]);
                System.out.print("|");
            }
            System.out.println();
            System.out.println("---------------");
        }
        System.out.println(" 0 1 2 3 4 5 6");
        System.out.println();
    }

    // validate the player's move
    public static boolean validate(int column, char[][] grid){
        // check if the column is valid
        if (column < 0 || column >= grid[0].length){
            return false;
        }

        // check if the column is full
        if (grid[0][column] != ' '){
            return false;
        }

        return true;
    }

    // check if a player has won
    public static boolean isWinner(char player, char[][] grid){
        // check for 4 in a row horizontally
        for(int row = 0; row < grid.length; row++){
            for (int col = 0; col < grid[0].length - 3; col++){
                if (grid[row][col] == player &&
                    grid[row][col+1] == player &&
                    grid[row][col+2] == player &&
                    grid[row][col+3] == player){
                    return true;
                }
            }           
        }

        // check for 4 in a row vertically
        for(int row = 0; row < grid.length - 3; row++){
            for(int col = 0; col < grid[0].length; col++){
                if (grid[row][col] == player &&
                    grid[row+1][col] == player &&
                    grid[row+2][col] == player &&
                    grid[row+3][col] == player){
                    return true;
                }
            }
        }

        // check for 4 in a row diagonally (up right)
        for(int row = 3; row < grid.length; row++){
            for(int col = 0; col < grid[0].length - 3; col++){
                if (grid[row][col] == player &&
                    grid[row-1][col+1] == player &&
                    grid[row-2][col+2] == player &&
                    grid[row-3][col+3] == player){
                    return true;
                }
            }
        }

        // Check for 4 in a row diagonally (up left)
        for(int row = 0; row < grid.length - 3; row++){
            for(int col = 0; col < grid[0].length - 3; col++){
                if (grid[row][col] == player &&
                    grid[row+1][col+1] == player &&
                    grid[row+2][col+2] == player &&
                    grid[row+3][col+3] == player){
                    return true;
                }
            }
        }

        return false;
    }
}
