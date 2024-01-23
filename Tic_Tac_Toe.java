package CodtechSolutions;

import java.util.Random;
import java.util.Scanner;

public class Tic_Tac_Toe {

	private static char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    private static char currentPlayer;
    private static boolean isGameOver;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println("Choose your opponent: 1 for Human, 2 for Computer");
        int opponentChoice = scanner.nextInt();

        if (opponentChoice == 1) {
            playAgainstHuman();
        } else if (opponentChoice == 2) {
            playAgainstComputer();
        } else {
            System.out.println("Invalid choice. Exiting the game.");
        }

        scanner.close();
    }

    private static void playAgainstHuman() {
        currentPlayer = 'X';

        while (!isGameOver) {
            printBoard();
            makeMove();
            switchPlayer();
            checkGameStatus();
        }
    }

    private static void playAgainstComputer() {
        currentPlayer = 'X';

        while (!isGameOver) {
            if (currentPlayer == 'X') {
                printBoard();
                makeMove();
            } else {
                makeComputerMove();
            }

            switchPlayer();
            checkGameStatus();
        }
    }

    private static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private static void makeMove() {
        Scanner scanner = new Scanner(System.in);
        int row, col;

        do {
            System.out.println("Player " + currentPlayer + ", enter your move (row and column, e.g., 1 2): ");
            row = scanner.nextInt() - 1;
            col = scanner.nextInt() - 1;
        } while (!isValidMove(row, col));

        board[row][col] = currentPlayer;
    }

    private static void makeComputerMove() {
        Random random = new Random();
        int row, col;

        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (!isValidMove(row, col));

        System.out.println("Computer chooses: " + (row + 1) + " " + (col + 1));
        board[row][col] = currentPlayer;
    }

    private static boolean isValidMove(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
            return true;
        } else {
            System.out.println("Invalid move. Try again.");
            return false;
        }
    }

    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private static void checkGameStatus() {
        if (checkForWin()) {
            printBoard();
            System.out.println("Player " + currentPlayer + " wins!");
            isGameOver = true;
        } else if (checkForDraw()) {
            printBoard();
            System.out.println("It's a draw!");
            isGameOver = true;
        }
    }

    private static boolean checkForWin() {
        for (int i = 0; i < 3; i++) {
            // Check rows and columns
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) ||
                    (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) {
                return true;
            }
        }

        // Check diagonals
        return (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
                (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer);
    }

    private static boolean checkForDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
