public class AnalyzeColourGrid {

    static final int hight = 10;
    static final int width = 10; // width

    static int count;

    static final int visitedNode[][] = new int [hight][width];
    static final int result[][] = new int [hight][width];

    static boolean isValidCheck(int x, int y, String key, String input[][]) {
        if (x < hight && y < width &&
                x >= 0 && y >= 0) {
            if (visitedNode[x][y] == 0 &&
                    input[x][y] == key) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    static void printContinuousColorBlock(int occurance){
        System.out.println ("the largest continuous color block occurance:" + occurance );

        for (int i = 0; i < hight; i++)
        {
            for (int j = 0; j < width; j++)
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
            for (int j = 0; j < width; j++)
                visitedNode[i][j] = 0;
    }

    static void resetGraphSearchResult(String key, String input[][])  {
        for (int i = 0; i < hight; i++)
        {
            for (int j = 0; j < width; j++)
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
        count++;

        int xAxixMove[] = { 0, 0, 1, -1 };
        int yAxixMove[] = { 1, -1, 0, 0 };

        for (int u = 0; u < 4; u++)
            if ((isValidCheck(i + yAxixMove[u],
                    j + xAxixMove[u], x, input)) == true)
                searchGraph(x, y, i + yAxixMove[u],
                        j + xAxixMove[u], input);
    }

    public static String findLargestColourBlock(String input[][]){

        int currentMax = Integer.MIN_VALUE;

        for (int i = 0; i < hight; i++) {
            for (int j = 0; j < width; j++) {
                resetVisitedNode();
                count = 0;

                if (j + 1 < width) {
                    searchGraph(input[i][j], input[i][j + 1],
                            i, j, input);
                }

                if (count >= currentMax)  {
                    currentMax = count;
                    resetGraphSearchResult(input[i][j], input);
                }
                resetVisitedNode();
                count = 0;

                if (i + 1 < hight) {
                    searchGraph(input[i][j],
                            input[i + 1][j], i, j, input);
                }

                if (count >= currentMax){
                    currentMax = count;
                    resetGraphSearchResult(input[i][j], input);
                }
            }
        }

        printContinuousColorBlock(currentMax);

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
