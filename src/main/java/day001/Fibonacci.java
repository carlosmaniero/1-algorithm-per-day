package day001;

import java.util.stream.Stream;

public class Fibonacci {
    public static long fibonacci(long n) {
        if (n < 1) {
            return 0;
        }

        long previous = 0;
        long current = 1;

        for (int i = 1; i < n; i++) {
            current = current + previous;
            previous = current - previous;
        }

        return current;
    }

    public static long fibonacciRecursive(long n) {
        if (n <= 1) {
            return n;
        }
        return fibonacciRecursive(n-1) + fibonacciRecursive(n-2);
    }

    public static long fibonacciStream(long n) {
        if (n < 1) {
            return 0;
        }
        return Stream.iterate(FibonacciSequence.initialSequence(), FibonacciSequence::getNext)
                .limit(n).skip(n - 1)
                .mapToLong(FibonacciSequence::getValue)
                .findFirst()
                .getAsLong();
    }

    public static class FibonacciSequence {
        private final long previous;
        private final long current;

        private FibonacciSequence(long previous, long current) {
            this.previous = previous;
            this.current = current;
        }

        public long getValue() {
            return current;
        }

        public FibonacciSequence getNext() {
            return new FibonacciSequence(this.current, this.previous + this.current);
        }

        public static FibonacciSequence initialSequence() {
            return new FibonacciSequence(0, 1);
        }
    }
}
