import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Connect4Game {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private static final char EMPTY = ' ';
    private static final char PLAYER1_SYMBOL = 'X';
    private static final char PLAYER2_SYMBOL = 'O';

    private char[][] board;
    private boolean player1Turn;
    private Random random;

    public Connect4Game() {
        board = new char[ROWS][COLUMNS];
        for (int row = 0; row < ROWS; row++) {
            Arrays.fill(board[row], EMPTY);
        }
        player1Turn = true;
        random = new Random();
    }

    public void printBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                System.out.print("|" + board[row][col]);
            }
            System.out.println("|");
        }
        for (int col = 0; col < COLUMNS; col++) {
            System.out.print(" " + (col + 1));
        }
        System.out.println();
    }

    public boolean isColumnFull(int col) {
        return board[0][col] != EMPTY;
    }

    public boolean isBoardFull() {
        for (int col = 0; col < COLUMNS; col++) {
            if (!isColumnFull(col)) {
                return false;
            }
        }
        return true;
    }

    public void dropPiece(int col, char symbol) {
        for (int row = ROWS - 1; row >= 0; row--) {
            if (board[row][col] == EMPTY) {
                board[row][col] = symbol;
                break;
            }
        }
    }

    public boolean checkWin(char symbol) {
        // Check for horizontal wins
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col <= COLUMNS - 4; col++) {
                if (board[row][col] == symbol && board[row][col + 1] == symbol &&
                        board[row][col + 2] == symbol && board[row][col + 3] == symbol) {
                    return true;
                }
            }
        }

        // Check for vertical wins
        for (int col = 0; col < COLUMNS; col++) {
            for (int row = 0; row <= ROWS - 4; row++) {
                if (board[row][col] == symbol && board[row + 1][col] == symbol &&
                        board[row + 2][col] == symbol && board[row + 3][col] == symbol) {
                    return true;
                }
            }
        }

        // Check for diagonal wins (from bottom-left to top-right)
        for (int row = 3; row < ROWS; row++) {
            for (int col = 0; col <= COLUMNS - 4; col++) {
                if (board[row][col] == symbol && board[row - 1][col + 1] == symbol &&
                        board[row - 2][col + 2] == symbol && board[row - 3][col + 3] == symbol) {
                    return true;
                }
            }
        }

        // Check for diagonal wins (from bottom-right to top-left)
        for (int row = 3; row < ROWS; row++) {
            for (int col = 3; col < COLUMNS; col++) {
                if (board[row][col] == symbol && board[row - 1][col - 1] == symbol &&
                        board[row - 2][col - 2] == symbol && board[row - 3][col - 3] == symbol) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean play(int col) {
        if (col < 0 || col >= COLUMNS || isColumnFull(col)) {
            return false;
        }

        char currentPlayerSymbol = player1Turn ? PLAYER1_SYMBOL : PLAYER2_SYMBOL;
        dropPiece(col, currentPlayerSymbol);
        player1Turn = !player1Turn;

        return true;
    }

    public static void main(String[] args) {
        Connect4Game game = new Connect4Game();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Connect 4!");

        while (true) {
            game.printBoard();

            if (game.isBoardFull()) {
                System.out.println("It's a draw!");
                break;
            }

            if (game.player1Turn) {
                int col;
                do {
                    System.out.print("Player 1 (X), enter column (1-7): ");
                    while (!scanner.hasNextInt()) {
                        System.out.print("Please enter a valid column (1-7): ");
                        scanner.next();
                    }
                    col = scanner.nextInt() - 1;
                } while (!game.play(col));

                if (game.checkWin(PLAYER1_SYMBOL)) {
                    game.printBoard();
                    System.out.println("You win!");
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
                    System.out.println("Computer wins, better lick next time!");
                    break;
                }
            }
        }

        scanner.close();
    }
}
