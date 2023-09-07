import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Connect4Game {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private static final char EMPTY = ' ';
    private static final char PLAYER1_SYMBOL = 'X';
    private static final char PLAYER2_SYMBOL = 'O';

    private char[][] board; // 2D array to represent the game board
    private boolean player1Turn; // Keeps track of whose turn it is
    private Random random; // Used for generating random moves for player 2

    public Connect4Game() {
        // Initialize the board and set initial conditions
        board = new char[ROWS][COLUMNS];
        for (int row = 0; row < ROWS; row++) {
            Arrays.fill(board[row], EMPTY); // Fill the board with empty spaces
        }
        player1Turn = true; // Player 1 starts first
        random = new Random(); // Initialize the random number generator
    }

    public void printBoard() {
        // Print the current state of the board to the console
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                System.out.print("|" + board[row][col]); // Print each cell with vertical separator
            }
            System.out.println("|"); // Move to the next row
        }
        for (int col = 0; col < COLUMNS; col++) {
            System.out.print(" " + (col + 1)); // Print column numbers at the bottom
        }
        System.out.println();
    }

    public boolean isColumnFull(int col) {
        // Check if a column is already full (no more moves can be made in that column)
        return board[0][col] != EMPTY; // If the top cell is not empty, the column is full
    }

    public boolean isBoardFull() {
        // Check if the board is completely filled (no more moves can be made)
        for (int col = 0; col < COLUMNS; col++) {
            if (!isColumnFull(col)) {
                return false; // If any column is not full, the board is not full
            }
        }
        return true; // All columns are full, board is full
    }

    public void dropPiece(int col, char symbol) {
        // Drop a piece into a column (make a move)
        for (int row = ROWS - 1; row >= 0; row--) {
            if (board[row][col] == EMPTY) {
                board[row][col] = symbol; // Find the first empty cell from the bottom and place the piece
                break;
            }
        }
    }

    public boolean checkWin(char symbol) {
        // Check if the player with the given symbol has won
        // Check for horizontal wins
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col <= COLUMNS - 4; col++) {
                if (board[row][col] == symbol && board[row][col + 1] == symbol &&
                        board[row][col + 2] == symbol && board[row][col + 3] == symbol) {
                    return true; // Four consecutive pieces in a row
                }
            }
        }

        // Check for vertical wins
        for (int col = 0; col < COLUMNS; col++) {
            for (int row = 0; row <= ROWS - 4; row++) {
                if (board[row][col] == symbol && board[row + 1][col] == symbol &&
                        board[row + 2][col] == symbol && board[row + 3][col] == symbol) {
                    return true; // Four consecutive pieces in a column
                }
            }
        }

        // Check for diagonal wins (from bottom-left to top-right)
        for (int row = 3; row < ROWS; row++) {
            for (int col = 0; col <= COLUMNS - 4; col++) {
                if (board[row][col] == symbol && board[row - 1][col + 1] == symbol &&
                        board[row - 2][col + 2] == symbol && board[row - 3][col + 3] == symbol) {
                    return true; // Four consecutive pieces in a diagonal line
                }
            }
        }

        // Check for diagonal wins (from bottom-right to top-left)
        for (int row = 3; row < ROWS; row++) {
            for (int col = 3; col < COLUMNS; col++) {
                if (board[row][col] == symbol && board[row - 1][col - 1] == symbol &&
                        board[row - 2][col - 2] == symbol && board[row - 3][col - 3] == symbol) {
                    return true; // Four consecutive pieces in a diagonal line
                }
            }
        }

        return false; // No winning condition found
    }

    public boolean play(int col) {
        // Play a move in the specified column
        if (col < 0 || col >= COLUMNS || isColumnFull(col)) {
            return false; // Invalid move (column out of bounds or full)
        }

        char currentPlayerSymbol = player1Turn ? PLAYER1_SYMBOL : PLAYER2_SYMBOL;
        dropPiece(col, currentPlayerSymbol); // Make the move
        player1Turn = !player1Turn; // Switch turns

        return true;
    }

    public static void main(String[] args) {
        // Main game loop
        Connect4Game game = new Connect4Game(); // Initialize the game
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Connect 4!");

        while (true) {
            game.printBoard(); // Display current state of the board

            if (game.isBoardFull()) {
                System.out.println("It's a draw!"); // Game ends in a draw
                break;
            }

            if (game.player1Turn) {
                int col;
                do {
                    System.out.print("Player 1 (X), enter column (1-7): ");
                    while (!scanner.hasNextInt()) {
                        System.out.print("Please enter a valid column (1-7): ");
                        scanner.next(); // Clear invalid input
                    }
                    col = scanner.nextInt() - 1; // Adjust for array index (0-based)
                } while (!game.play(col));

                if (game.checkWin(PLAYER1_SYMBOL)) {
                    game.printBoard();
                    System.out.println("You win!"); // Player 1 wins
                    break;
                }
            } else {
                int randomCol;
                do {
                    randomCol = game.random.nextInt(COLUMNS);
                } while (game.isColumnFull(randomCol));

                System.out.println("Player 2 (O) selected column " + (randomCol + 1));
                game.play(randomCol);
                if (game.checkWin(PLAYER2_SYMBOL)) {
                    game.printBoard();
                    System.out.println("Computer wins, better luck next time!"); // Player 2 (computer) wins
                    break;
                }
            }
        }

        scanner.close(); // Close the scanner
    }
}
