public class Main {

    public static final int SIZE = 10;
    public static final char EMPTY = '-';
    public static final char CACTUS = '*';

    public static void main(String[] args) {

        char[][] field = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field[i][j] = EMPTY;
            }
        }
        field[0][0] = 'Щ';
        field[5][9] = CACTUS;
        field[6][9] = CACTUS;
        field[7][8] = CACTUS;
        field[7][7] = CACTUS;
        field[3][6] = CACTUS;
        field[6][6] = CACTUS;
        field[7][6] = CACTUS;
        field[8][6] = CACTUS;
        field[2][5] = CACTUS;
        field[5][5] = CACTUS;
        field[6][4] = CACTUS;
        field[1][3] = CACTUS;
        field[3][2] = CACTUS;
        field[5][2] = CACTUS;
        field[4][1] = CACTUS;
        field[6][1] = CACTUS;
        field[7][1] = CACTUS;
        field[3][0] = CACTUS;
        field[4][0] = CACTUS;

        findPath(field, 8, 3);
    }

    public static char whereFrom(char[][] field, int x, int y, char[][] memory) {
        if (memory[x][y] != '?') {
            return memory[x][y];
        }
        if (x > 0) {
            int leftX = x - 1;
            int leftY = y;
            if (leftX == 0 && leftY == 0) {
                memory[x][y] = 'L';
                return 'L';
            }
            if (field[leftX][leftY] != CACTUS) {
                if (whereFrom(field, leftX, leftY, memory) != 'N') {
                    memory[x][y] = 'L';
                    return 'L';
                }
            }
        }
        if (y > 0) {
            int upX = x;
            int upY = y - 1;
            if (upX == 0 && upY == 0) {
                memory[x][y] = 'U';
                return 'U';
            }
            if (field[upX][upY] != CACTUS) {
                if (whereFrom(field, upX, upY, memory) != 'N') {
                    memory[x][y] = 'U';
                    return 'U';
                }
            }
        }
        memory[x][y] = 'N';
        return 'N';
    }

    public static void findPath(char[][] field, int x0, int y0) {
        char[][] memory = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                memory[i][j] = '?';
            }
        }
        boolean[][] path = new boolean[SIZE][SIZE];
        int x = x0;
        int y = y0;
        while (x != 0 || y != 0) {
            char direction = whereFrom(field, x, y, memory);
            if (direction == 'N') {
                System.out.println("Нет такого пути :(");
            } else if (direction == 'U') {
                path[x][y] = true;
                y -= 1;
            } else if (direction == 'L') {
                path[x][y] = true;
                x -= 1;
            }
        }
        for (y = 0; y < SIZE; y++) {
            for (x = 0; x < SIZE; x++) {
                if (x == x0 && y == y0) {
                    System.out.print('Ч' + " ");
                } else if (path[x][y]) {
                    System.out.print('x' + " ");
                } else {
                    System.out.print(field[x][y] + " ");
                }
            }
            System.out.println();
        }
    }
}
