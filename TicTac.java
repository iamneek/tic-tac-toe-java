import java.util.Scanner;

// Todo 1: Give user an option to select symbol.
// Todo 2: Give player an option to chose 2v2 or versus with computer.
// Todo 3: Implement versus with computer.

public class TicTac {
    public static void main(String[] args) {
        char[][] board = new char[3][3];
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                board[row][column] = ' ';
            }
        }

        Scanner scanner = new Scanner(System.in);

        boolean gameOver = false;
        char player = 'X';
        char winner = 'Z';

        while (!gameOver) {
            printBoard(board);
            System.out.print("\nPlayer "+ player + "'s turn (row column): ");
            int userRow = scanner.nextInt() - 1;
            int userColumn = scanner.nextInt() - 1;
            try {
                if (board[userRow][userColumn] != ' ') {
                    System.out.println("Invalid Move!");
                    continue;
                }
                board[userRow][userColumn] = player;
            } catch (Exception e) {
                System.out.println("\nInsert a valid value within the range.\nTo put your symbol use the row and column to help you.\nE.g. 2 2 puts the character in the 2nd column of 2nd row i.e. at the middle.\n");
                continue;
            }

            gameOver = checkWin(board, player) || isBoardFull(board);
            if (checkWin(board, player)) {
                winner = player;
            } else if (isBoardFull(board)) {
                System.out.println("\nGame Over! It's a draw.");
                break;
            }
            player = player == 'X' ? 'O': 'X';
        }
        printBoard(board);
        System.out.println("\nGame Over! Player " + winner + " won.");
        scanner.close();
    }

    public static void printBoard(char[][] board) {
        System.out.println("");
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                if (board[row].length - column != 1) {
                    System.out.print(board[row][column] + "  |  ");
                }
                else{
                    System.out.print(board[row][column]);

                }
            }
            System.out.println("");
            if (board.length - row != 1) {
                System.out.println("-  -  -  -  -  ");
            }
        }
    }

    public static boolean isBoardFull(char[][] board) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }


    public static boolean checkWin(char[][] board, char player) {

        // check for row matches
        for (int row = 0; row < board.length; row++) {
            if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
                return true;
            }
        }

        // check for column matches
        for (int column = 0; column < board.length; column++) {
            if (board[0][column] == player && board[1][column] == player && board[2][column] == player){
                return true;
            }
        }

        // check for diagonal matches
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }

        if (board[2][0] == player && board[1][1] == player && board[0][2] == player) {
            return true;
        }

        return false;
    }
}
