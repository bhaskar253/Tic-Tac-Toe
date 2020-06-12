package tictactoe;

import java.util.Scanner;

public class Main {
    private static void drawDashes() {
        System.out.println("---------");
    }

    private static void drawBoard(String board) {
        drawDashes();
        for (int i = 0; i < board.length(); i += 3) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board.charAt(i + j) + " ");
            }
            System.out.println("|");
        }
        drawDashes();
    }

    private static int[] getCounts(String board) {
        int xCount = 0;
        int oCount = 0;

        for (int i = 0; i < board.length(); i++) {
            switch(board.charAt(i)) {
                case 'X': xCount++; break;
                case 'O': oCount++; break;
                default:
            }
        }

        return new int[] {xCount, oCount};
    }

    private static boolean[] getWinners(String board) {
        boolean xWins = false;
        boolean oWins = false;

        for (int i = 0; i < board.length(); i += 3) {
            if (board.charAt(i) == board.charAt(i + 1)
                && board.charAt(i) == board.charAt(i + 2)) {
                switch (board.charAt(i)) {
                    case 'X': xWins = true; break;
                    case 'O': oWins = true; break;
                    default:
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            if (board.charAt(i) == board.charAt(i + 3)
                && board.charAt(i) == board.charAt(i + 6)) {
                switch (board.charAt(i)) {
                    case 'X': xWins = true; break;
                    case 'O': oWins = true; break;
                    default:
                }
            }
        }

        if (board.charAt(0) == board.charAt(4)
            && board.charAt(0) == board.charAt(8)) {
            switch (board.charAt(0)) {
                case 'X': xWins = true; break;
                case 'O': oWins = true; break;
                default:
            }
        }

        if (board.charAt(2) == board.charAt(4)
            && board.charAt(2) == board.charAt(6)) {
            switch (board.charAt(2)) {
                case 'X': xWins = true; break;
                case 'O': oWins = true; break;
                default:
            }
        }

        return new boolean[] {xWins, oWins};
    }

    private static String getCurrentState(String board) {
        int[] counts = getCounts(board);
        boolean[] winners = getWinners(board);

        if (winners[0] && winners[1] || Math.abs(counts[0] - counts[1]) > 1) {
           return "Impossible";
        } else if (winners[0]) {
            return "X wins";
        } else if (winners[1]) {
            return "O wins";
        } else if (board.contains(" ")) {
            return "Game not finished";
        }

        return "Draw";
    }

    public static void main(String[] args) {
        // write your code here
        var sc = new Scanner(System.in);
        var board = "         ";
        drawBoard(board);
        char player = 'X';
        while (getCurrentState(board).equals("Game not finished")) {
            boolean validInput = false;
            while (!validInput) {
                try {
                    System.out.println("Enter the coordinates: ");
                    var x = sc.nextInt();
                    var y = sc.nextInt();
                    var idx = x + 8 - 3 * y;
                    if (idx < 0 || idx > 8) {
                        System.out.println("Coordinates should be from 1 to 3!");
                    } else {
                        if (board.charAt(idx) == ' ') {
                            board = board.substring(0, idx) + player + board.substring(idx + 1);
                            validInput = true;
                            player = player == 'X' ? 'O' : 'X';
                        } else {
                            System.out.println("This cell is occupied! Choose another one!");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("You should enter numbers!");
                }
            }
            drawBoard(board);
        }

        System.out.println(getCurrentState(board));

        sc.close();
    }
}
