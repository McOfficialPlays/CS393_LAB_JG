package lab14;

import java.util.Random;

public class SumOfSquaresTiming {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java SumOfSquaresTiming <n>");
            return;
        }

        int n = Integer.parseInt(args[0]);
        int[] arr = new int[n];
        Random rand = new Random(42);

        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(100) + 1; // 1 to 100
        }

        // Non-threaded
        long startTime1 = System.nanoTime();
        long sum1 = sumSquaresSingleThread(arr);
        long endTime1 = System.nanoTime();
        long duration1 = endTime1 - startTime1;

        // Threaded
        int mid = n / 2;
        SumWorker w1 = new SumWorker(arr, 0, mid);
        SumWorker w2 = new SumWorker(arr, mid, n);

        long startTime2 = System.nanoTime();
        w1.start();
        w2.start();

        try {
            w1.join();
            w2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long sum2 = w1.getPartialSum() + w2.getPartialSum();
        long endTime2 = System.nanoTime();
        long duration2 = endTime2 - startTime2;

        System.out.println("n = " + n);
        System.out.println("Single-threaded sum = " + sum1);
        System.out.println("Single-threaded time = " + duration1 + " ns");

        System.out.println("Two-threaded sum = " + sum2);
        System.out.println("Two-threaded time = " + duration2 + " ns");
    }

    public static long sumSquaresSingleThread(int[] arr) {
        long sum = 0;
        for (int value : arr) {
            sum += (long) value * value;
        }
        return sum;
    }
}

class SumWorker extends Thread {
    private int[] arr;
    private int start;
    private int end;
    private long partialSum = 0;

    public SumWorker(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            partialSum += (long) arr[i] * arr[i];
        }
    }

    public long getPartialSum() {
        return partialSum;
    }
}