package Main;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TaskOne {

    public static void main(String[] args) {


        int[][] example = {
                {1, 1, 0, 0},
                {0, 1, 0, 0},
                {1, 1, 0},
                {0, 0, 0, 0, 0},
                {1, 0, 1, 0, 1},
                {0, 0},
                {1, 1, 0, 0, 0, 0, 0, 1},
                {}

        };

        System.out.printf("Islands count for matrix: \n%s\nis:%d",
                Arrays.stream(example)
                        .map(row -> Arrays.stream(row).boxed().map(String::valueOf).collect(Collectors.joining(", ")))
                        .collect(Collectors.joining("\n")),
                getIslandsCount(example)
        );
    }

    private static int getIslandsCount(int[][] arr) {

        int counter = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 1) {
                    arr[i][j] = 0;
                    counter++;
                    checkNeighbourLandToFormOneIsland(arr, i, j + 1);
                    checkNeighbourLandToFormOneIsland(arr, i, j - 1);

                    checkNeighbourLandToFormOneIsland(arr, i + 1, j);
                    checkNeighbourLandToFormOneIsland(arr, i + 1, j + 1);
                    checkNeighbourLandToFormOneIsland(arr, i + 1, j - 1);

                    checkNeighbourLandToFormOneIsland(arr, i - 1, j);
                    checkNeighbourLandToFormOneIsland(arr, i - 1, j + 1);
                    checkNeighbourLandToFormOneIsland(arr, i - 1, j - 1);

                }
            }
        }
        return counter;
    }

    private static void checkNeighbourLandToFormOneIsland(int[][] arr, int i, int j) {

        while (isValidIndex(i, j, arr) && arr[i][j] == 1) {

            arr[i][j] = 0;
            checkNeighbourLandToFormOneIsland(arr, i + 1, j);
            checkNeighbourLandToFormOneIsland(arr, i + 1, j + 1);
            checkNeighbourLandToFormOneIsland(arr, i + 1, j - 1);

            checkNeighbourLandToFormOneIsland(arr, i, j + 1);
            checkNeighbourLandToFormOneIsland(arr, i, j - 1);

            checkNeighbourLandToFormOneIsland(arr, i - 1, j);
            checkNeighbourLandToFormOneIsland(arr, i - 1, j + 1);
            checkNeighbourLandToFormOneIsland(arr, i - 1, j - 1);
        }
    }


    private static boolean isValidIndex(int i, int j, int[][] arr) {
        return i >= 0 && j >= 0 & i <= arr.length - 1 && j <= arr[i].length - 1;
    }
}
