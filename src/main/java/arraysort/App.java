package arraysort;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class App {
    public static void main(String[] args) {
        int size = 1500;
        int[] numbers = new int[size];
        Random random = new Random();

        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
        long start = System.nanoTime();
        Arrays.sort(numbers); // single thread
        System.out.println("serial sorting took  = " + (System.nanoTime() - start));

        IntStream.range(0,size).forEach(i -> numbers[i] = random.nextInt());
        start = System.nanoTime();
        Arrays.parallelSort(numbers); //자원에 따라 다르지만 대부분 빠름
        System.out.println("parallel sorting took  " + (System.nanoTime() - start));
    }
}
