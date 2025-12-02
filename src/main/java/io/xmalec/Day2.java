package io.xmalec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Day2 {
    public static void main(String[] args) {
        try (var reader = new BufferedReader(new InputStreamReader(Day2.class.getResourceAsStream("/day2.txt")))) {
            String line = reader.readLine();
            List<String> ids = Arrays.asList(line.split(","));
            long result = 0;

            for (String pair : ids ) {
                List<String> parts = Arrays.asList(pair.split("-"));
                long first = Long.parseLong(parts.get(0));
                long second = Long.parseLong(parts.get(1));
                for (long i = first; i <= second; i++) {
                    if (invalid2(i)) {
                        System.out.println("invalid: " + i);
                        result += i;
                    }
                }
            }
            System.out.println(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean invalid(long value) {
        int length = getDigits(value);
        if (length % 2 == 1) return false;

        long exp = (long) Math.pow(10, (double) length / 2);
        long left = value / exp;
        long right = value % exp;
        return left == right;
    }

    private static int getDigits(long value) {
        int res = 0;
        while (value > 0) {
            value /= 10;
            res++;
        }
        return res;
    }

    private static boolean invalid2(long value) {
        int length = getDigits(value);

        for (int i = 1; i <= length / 2; i++) {
            if (length % i != 0) continue;
            if (isSame(value, i)) return true;
        }

        return false;
    }

    private static boolean isSame(long value, int length) {
        long exp = (long) Math.pow(10, (double) length);
        long pattern  = value % exp;

        while (value > 0) {
            long current = value % exp;
            if (current != pattern) return false;
            value /= exp;
        }

        return true;
    }
}
