package io.xmalec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day4 {
    public static void main(String[] args) {
        String fileName = "/test4.txt";
        fileName = "/day4.txt";

        try (var reader = new BufferedReader(new InputStreamReader(Day3.class.getResourceAsStream(fileName)))) {
            String line;
            List<String> gridStrings = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                gridStrings.add(line);
            }

            int h = gridStrings.size();
            int w = gridStrings.getFirst().length();

            char[][] grid = new char[h][w];
            for (int i = 0; i < h; i++) {
                grid[i] = gridStrings.get(i).toCharArray();
            }

            int sum = 0;
            var current = getForks(grid);
            while (!current.isEmpty()) {
                sum += current.size();
                current.forEach(pair -> {
                    grid[pair.left()][pair.right()] = '.';
                });
                current = getForks(grid);
            }
            System.out.println("Sum: " + sum);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Pair<Integer, Integer>> getForks(char[][] grid) {
        List<Pair<Integer, Integer>> res = new ArrayList<>();
        int h = grid.length;
        int w = grid[0].length;

        int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                char c = grid[row][col];
                if (c != '@') continue;

                int rolls = 0;
                for (int k = 0; k < 8; k++) {
                    int nRow = row + dy[k];
                    int nCol = col + dx[k];
                    if (nRow < 0 || nRow >= h || nCol < 0 || nCol >= w) {
                        continue;
                    }
                    if (grid[nRow][nCol] == '@') {
                        rolls++;
                    }
                }
                if (rolls < 4) {
                    res.add(new Pair<>(row, col));
                }
            }
        }

        return res;
    }
}
