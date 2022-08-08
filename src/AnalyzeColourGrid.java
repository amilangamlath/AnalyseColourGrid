public class AnalyzeColourGrid {

    static final int hight = 10;
    static final int m = 10; // width

    static int COUNT;

    static final int visitedNode[][] = new int [hight][m];
    static final int result[][] = new int [hight][m];

    static boolean is_valid(int x, int y, String key, String input[][]) {
        if (x < hight && y < m &&
                x >= 0 && y >= 0) {
            if (visitedNode[x][y] == 0 &&
                    input[x][y] == key) {
                return true;
            }
            else {
                return false;
            }
        }
        else
            return false;
    }

    static void printContinuousColorBlock(int res){
        System.out.println ("the largest continuous color block occurance:" + res );

        for (int i = 0; i < hight; i++)
        {
            for (int j = 0; j < m; j++)
            {
                if (result[i][j] != 0)
                    System.out.print("* ");
                else
                    System.out.print("_ ");
            }
            System.out.println();
        }
    }

    static void resetVisitedNode() {
        for (int i = 0; i < hight; i++)
            for (int j = 0; j < m; j++)
                visitedNode[i][j] = 0;
    }

    static void resetGraphSearchResult(String key, String input[][])  {
        for (int i = 0; i < hight; i++)
        {
            for (int j = 0; j < m; j++)
            {
                if (visitedNode[i][j] == 1 &&
                        input[i][j] == key)
                    result[i][j] = visitedNode[i][j];
                else
                    result[i][j] = 0;
            }
        }
    }

    static void searchGraph(String x, String y, int i, int j, String input[][]) {
        if (x != y)
            return;

        visitedNode[i][j] = 1;
        COUNT++;

        int x_move[] = { 0, 0, 1, -1 };
        int y_move[] = { 1, -1, 0, 0 };

        for (int u = 0; u < 4; u++)
            if ((is_valid(i + y_move[u],
                    j + x_move[u], x, input)) == true)
                searchGraph(x, y, i + y_move[u],
                        j + x_move[u], input);
    }

    public static String findLargestColourBlock(String input[][]){

        int current_max = Integer.MIN_VALUE;

        for (int i = 0; i < hight; i++) {
            for (int j = 0; j < m; j++) {
                resetVisitedNode();
                COUNT = 0;

                if (j + 1 < m) {
                    searchGraph(input[i][j], input[i][j + 1],
                            i, j, input);
                }

                if (COUNT >= current_max)  {
                    current_max = COUNT;
                    resetGraphSearchResult(input[i][j], input);
                }
                resetVisitedNode();
                COUNT = 0;

                if (i + 1 < hight) {
                    searchGraph(input[i][j],
                            input[i + 1][j], i, j, input);
                }

                if (COUNT >= current_max){
                    current_max = COUNT;
                    resetGraphSearchResult(input[i][j], input);
                }
            }
        }

        printContinuousColorBlock(current_max);

        return null;
    }

    public static void main(String[] args) {

        String colorGrid[][] = {{"Blue", "Blue", "Silver", "Blue", "Blue", "Red", "Red", "Blue", "Red", "Blue"},
                {"Blue", "Red", "Red", "Red", "Silver", "Red", "Red", "Red", "Silver", "Blue"},
                {"Silver", "Silver", "Silver", "Silver", "Red", "Silver", "Red", "Silver", "Silver", "Silver"},
                {"Silver", "Silver", "Silver", "Blue", "Silver", "Blue", "Blue", "Blue", "Red", "Silver"},
                {"Blue", "Blue", "Blue", "Red", "Silver", "Red", "Blue", "Red", "Silver", "Red"},
                {"Blue", "Red", "Red", "Red", "Silver", "Red", "Red", "Red", "Silver", "Blue"},
                {"Silver", "Silver", "Silver", "Silver", "Red", "Silver", "Red", "Silver", "Silver", "Silver"},
                {"Silver", "Silver", "Silver", "Blue", "Silver", "Blue", "Blue", "Blue", "Red", "Silver"},
                {"Blue", "Blue", "Blue", "Red", "Silver", "Red", "Blue", "Red", "Silver", "Red"},
                {"Blue", "Blue", "Blue", "Red", "Silver", "Red", "Blue", "Red", "Silver", "Red"}};

        findLargestColourBlock(colorGrid);
    }
}
