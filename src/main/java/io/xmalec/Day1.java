package io.xmalec;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day1 {
    public static void main(String[] args) {
        List<String> rotations;
        try (var reader = Files.newBufferedReader(Path.of("main/resources/day1.txt"))) {
            rotations = reader.lines()
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read input: " + e.getMessage(), e);
        }

        long part1 = solvePart1(rotations);
        long part2 = solvePart2(rotations);

        System.out.println(part1);
        System.out.println(part2);
    }

    private static long solvePart1(List<String> rotations) {
        int current = 50;
        long zeros = 0;
        for (String line : rotations) {
            char dir = line.charAt(0);
            int dist = Integer.parseInt(line.substring(1));

            int delta = dir == 'L' ? -dist : dist;
            current = Math.floorMod(current + delta, 100);
            if (current == 0) {
                zeros++;
            }
        }
        return zeros;
    }

    private static long solvePart2(List<String> rotations) {
        int current = 50;
        long zeros = 0;
        for (String line : rotations) {
            char dir = line.charAt(0);
            int dist = Integer.parseInt(line.substring(1));

            if (dir == 'R') {
                zeros += countZeroClicksRight(current, dist);
                current = Math.floorMod(current + dist, 100);
            } else {
                zeros += countZeroClicksLeft(current, dist);
                current = Math.floorMod(current - dist, 100);
            }
        }
        return zeros;
    }

    private static long countZeroClicksRight(int current, int n) {
        if (n <= 0) return 0;
        int k0 = (100 - (current % 100)) % 100;
        if (k0 == 0) k0 = 100;
        if (n < k0) return 0;
        return 1L + (n - k0) / 100;
    }

    private static long countZeroClicksLeft(int current, int n) {
        if (n <= 0) return 0;
        int k0 = current % 100;
        if (k0 == 0) k0 = 100;
        if (n < k0) return 0;
        return 1L + (n - k0) / 100;
    }
}
