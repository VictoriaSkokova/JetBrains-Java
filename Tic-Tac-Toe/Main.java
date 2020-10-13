package tictactoe;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scan = new Scanner(System.in);
        char[][] ticTacToe = new char[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ticTacToe[i][j] = '_';
            }
        }

        printTable(ticTacToe);

        Map<String, Integer[]> coordinates = new HashMap<String, Integer[]>();

        coordinates.put("1, 3", new Integer[] {0, 0});
        coordinates.put("2, 3", new Integer[] {0, 1});
        coordinates.put("3, 3", new Integer[] {0, 2});
        coordinates.put("1, 2", new Integer[] {1, 0});
        coordinates.put("2, 2", new Integer[] {1, 1});
        coordinates.put("3, 2", new Integer[] {1, 2});
        coordinates.put("1, 1", new Integer[] {2, 0});
        coordinates.put("2, 1", new Integer[] {2, 1});
        coordinates.put("3, 1", new Integer[] {2, 2});


        char currentChar = 'X';

        while (true) {
            int iCoordinate = 0;
            int jCoordinate = 0;
            while (iCoordinate < 1 || iCoordinate > 3 || jCoordinate < 1 || jCoordinate > 3) {
                System.out.print("Enter the coordinates: ");
                try {
                    iCoordinate = scan.nextInt();
                    jCoordinate = scan.nextInt();

                    if (iCoordinate < 1 || iCoordinate > 3 || jCoordinate < 1 || jCoordinate > 3) {
                        System.out.println("Coordinates should be from 1 to 3!");
                    } else {
                        if (ticTacToe[coordinates.get(iCoordinate + ", " + jCoordinate)[0]][coordinates.get(iCoordinate + ", " + jCoordinate)[1]] != '_') {
                            System.out.println("This cell is occupied! Choose another one!");
                            iCoordinate = 0;
                            jCoordinate = 0;
                        } else {
                            ticTacToe[coordinates.get(iCoordinate + ", " + jCoordinate)[0]][coordinates.get(iCoordinate + ", " + jCoordinate)[1]] = currentChar;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("You should enter numbers!");
                }
            }

            printTable(ticTacToe);
            currentChar = currentChar == 'X' ? 'O' : 'X';

            String checked = checkRows(ticTacToe);
            if (checked.equals("No winners")) {
                checked = new String(ticTacToe[0]) + new String(ticTacToe[01]) + new String(ticTacToe[2]);
                if (!checked.contains("_")) {
                    System.out.println("Draw");
                    break;
                }
            } else {
                System.out.println(checked);
                break;
            }
        }

    }

    public static void printTable(char[][] ticTacToe) {
        System.out.println("---------");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    System.out.print("| " + ticTacToe[i][j] + " ");
                } else {
                    System.out.print(ticTacToe[i][j] + " ");
                }
            }
            System.out.print("|\n");
        }
        System.out.println("---------");
    }

    public static boolean checkForImpossible(String line) {
        boolean isImpossible = false;
        int countX = 0;
        int countO = 0;

        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == 'X') {
                countX++;
            }
            if (line.charAt(i) == 'O') {
                countO++;
            }
        }

        if (countO > countX + 1 || countX > countO + 1) {
            isImpossible = true;
        }

        return isImpossible;
    }

    public static String checkRows (char[][] ticTacToe) {
        boolean xWins = false;
        boolean oWins = false;

        char winningChar = ' ';

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (ticTacToe[i][j] != '_') {
                    if (winningChar == ' ') {
                        winningChar = ticTacToe[i][j];
                    } else {
                        if (winningChar != ticTacToe[i][j]) {
                            winningChar = ' ';
                            break;
                        }
                    }
                } else {
                    winningChar = ' ';
                    break;
                }
            }
            if (winningChar == 'X') {
                xWins = true;
            }
            if (winningChar == 'O') {
                oWins = true;
            }
            winningChar = ' ';
        }

        if (xWins && !oWins) {
            return "X wins";
        }
        if (!xWins && oWins) {
            return "O wins";
        }
        if (xWins && oWins) {
            return "Impossible";
        }
        if (!xWins && !oWins) {
            String checkCols = checkColumns(ticTacToe);
            if (checkCols.equals("No winners")) {
                String checkDials = checkDiagonals(ticTacToe);
                if (checkDials.equals("No winners")) {
                    return "No winners";
                } else {
                    return checkDials;
                }
            } else {
              return checkCols;
            }
        }
        return "";
    }

    public static String checkColumns(char[][] ticTacToe) {
        boolean xWins = false;
        boolean oWins = false;

        char winningChar = ' ';

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (ticTacToe[i][j] != '_') {
                    if (winningChar == ' ') {
                        winningChar = ticTacToe[i][j];
                    } else {
                        if (winningChar != ticTacToe[i][j]) {
                            winningChar = ' ';
                            break;
                        }
                    }
                } else {
                    winningChar = ' ';
                    break;
                }
            }
            if (winningChar == 'X') {
                xWins = true;
            }
            if (winningChar == 'O') {
                oWins = true;
            }
            winningChar = ' ';
        }

        if (xWins && !oWins) {
            return "X wins";
        }
        if (!xWins && oWins) {
            return "O wins";
        }
        if (xWins && oWins) {
            return "Impossible";
        }
        if (!xWins && !oWins) {
            return "No winners";
        }
        return "";
    }

    public static String checkDiagonals(char[][] ticTacToe) {
        char winningChar;

        if (ticTacToe[0][0] == '_') {
            if (ticTacToe[0][2] == '_') {
                return "No winners";
            } else {
                winningChar = ticTacToe[0][2];
                if (ticTacToe[1][1] == winningChar && ticTacToe[2][0] == winningChar) {
                    return winningChar == 'X' ? "X wins" : "O wins";
                } else {
                    return "No winners";
                }
            }
        } else {
            winningChar = ticTacToe[0][0];
            if (ticTacToe[1][1] == winningChar && ticTacToe[2][2] == winningChar) {
                return winningChar == 'X' ? "X wins" : "O wins";
            } else {
                if (ticTacToe[0][2] == '_') {
                    return "No winners";
                } else {
                    winningChar = ticTacToe[0][2];
                    if (ticTacToe[1][1] == winningChar && ticTacToe[2][0] == winningChar) {
                        return winningChar == 'X' ? "X wins" : "O wins";
                    } else {
                        return "No winners";
                    }
                }
            }
        }
    }
}
