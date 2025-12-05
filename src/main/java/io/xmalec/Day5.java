package io.xmalec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day5 {
    public static void main(String[] args) {
        String fileName = "/test5.txt";
        fileName = "/day5.txt";

        try (var reader = new BufferedReader(new InputStreamReader(Day5.class.getResourceAsStream(fileName)))) {
            String line;

            List<long[]> ranges = new ArrayList<>();
            while (!(line = reader.readLine()).isEmpty()) {
                addRange(ranges, line);
            }

            ranges = mergeRanges(ranges);
            long result = 0;
            for (long[] range : ranges) {
                result += range[1] - range[0] + 1;
            }
            System.out.println(result);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addRange(List<long[]> ranges, String line) {
        String[] nums = line.split("-");
        long left = Long.parseLong(nums[0].trim());
        long right = Long.parseLong(nums[1].trim());
        ranges.add(new long[]{left, right});
    }

    private static List<long[]> mergeRanges(List<long[]> ranges) {
        if (ranges.isEmpty()) return ranges;
        ranges.sort(Comparator.comparingLong(a -> a[0]));
        List<long[]> merged = new ArrayList<>();
        long[] current = ranges.getFirst().clone();
        for (int i = 1; i < ranges.size(); i++) {
            long[] next = ranges.get(i);
            if (next[0] <= current[1] + 1) {
                current[1] = Math.max(current[1], next[1]);
            } else {
                merged.add(current);
                current = next.clone();
            }
        }
        merged.add(current);
        return merged;
    }
}
