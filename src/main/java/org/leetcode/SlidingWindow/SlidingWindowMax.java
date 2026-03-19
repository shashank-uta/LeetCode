package org.leetcode.SlidingWindow;

import java.util.Arrays;
import java.util.PriorityQueue;

public class SlidingWindowMax {


    public static void main(String[] args) {

//        int[] numbers = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] numbers = {9,10,9,-7,-4,-8,2,-6};
        int k = 5;

        int[] result = slidingWindowWithMaxNumber(numbers, k);

        System.out.println("Sliding Window Of Maximum Numbers : " + Arrays.toString(result));
    }

    private static int[] slidingWindowWithMaxNumber(int[] numbers, int k) {


        if (numbers.length == 1 || k == 1) {
            return numbers;
        }

        int[] maxNumbersArray = new int[numbers.length - k + 1];

        // custom comparator to create a max-heap
        // pq.peek() 👉 index of maximum element in current window because of max heap
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (numbers[b] - numbers[a]));

        for (int i = 0; i < numbers.length; i++) {
            // Add current index
            // Insert into priority queue: O(log n)
            pq.add(i);

            // Remove indices that are outside the current window
            // Current window range is: [i - k + 1  →  i]
            //
            // Any index <= (i - k) is outside the window (i.e., left of window start)
            // Example:
            // i = 5, k = 3 → window = [3, 4, 5]
            // So index <= 2 must be removed
            while (!pq.isEmpty() && pq.peek() < i - k + 1) {
                pq.poll();
            }

            // Start recording answer once first full window is formed
            if (i >= k - 1) {
                maxNumbersArray[i - k + 1] = numbers[pq.peek()];
            }
        }

        return maxNumbersArray;
    }
}
