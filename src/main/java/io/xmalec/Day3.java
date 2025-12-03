package io.xmalec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day3 {
    public static void main(String[] args) {
        String fileName = "/test3.txt";
        fileName = "/day3.txt";
        try (var reader = new BufferedReader(new InputStreamReader(Day3.class.getResourceAsStream(fileName)))) {
            String line;
            long sum = 0;
            while ((line = reader.readLine()) != null) {
                sum += largestJoltage(line, 12);
            }
            System.out.println("Sum: " + sum);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static long largestJoltage(String line, int requiredLen) {
        int n = line.length();
        int remove = n - requiredLen;
        List<Integer> stack = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            int d = line.charAt(i) - '0';

            while (!stack.isEmpty()
                    && remove > 0
                    && stack.getLast() < d
                    && (stack.size() - 1 + (n - i)) >= requiredLen) {
                stack.removeLast();
                remove--;
            }

            if (stack.size() < requiredLen) {
                stack.add(d);
            } else {
                remove--;
            }
        }

        while (stack.size() > requiredLen) {
            stack.removeLast();
        }

        long result = 0;
        for (int d : stack) {
            result = result * 10 + d;
        }
        return result;
    }

}