import java.util.Scanner;

public class Main {
    public static final short RED = 1;
    public static final short YELLOW = 2;

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        short[][] board = new short[7][6];
        short turn = RED;
        short winner = 0;


        System.out.println("Welcome to Connect 4");

        while (winner == 0) {
            render(board);
            if (play(turn, (short) (keyboard.nextInt() - 1), board)) {
                if (checkWin(board)) {
                    winner = turn;
                }

                turn = switch (turn) {
                    case RED -> YELLOW;
                    case YELLOW -> RED;
                    default -> turn;
                };
            }
        }
    }

    public static boolean play(short turn, short x, short[][] board) {
        if (x < 0 || x > 6)
            return false;

        short y = 0;
        short max_height = (short) board[x].length;

        while (y < max_height && board[x][y] != 0) {
            y += 1;
        }
        if (y == max_height)
            return false;


        board[x][y] = turn;

        return true;
    }

    public static boolean checkWin (short[][] board) {
        return false;
    }

    public static void render(short[][] board) {
        int height = board[0].length;
        System.out.println("\033c,_____________,");
        for (int y = height - 1 ; y >= 0; y--){
            for (int x = 0; x < board.length; x++) {
                System.out.print('|' + render_token(board[x][y]));
            }
            System.out.println('|');
        }
        System.out.println("'-+-+-+-+-+-+-'");
        System.out.println(" 1 2 3 4 5 6 7 ");
        System.out.print("Enter a row $ ");
    }

    public static String render_token(short color) {
        String reset = "\033[0m";
        String yellow = "\033[93m";
        String red = "\033[91m";

        char icon = '\uea71';

        return switch (color) {
            case RED -> red + icon + reset;
            case YELLOW -> yellow + icon + reset;
            default -> " ";
        };
    }
}