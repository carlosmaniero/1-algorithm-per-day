package week001;

import org.junit.Test;

import static week001.Fibonacci.fibonacci;
import static week001.Fibonacci.fibonacciRecursive;
import static week001.Fibonacci.fibonacciStream;
import static org.assertj.core.api.Assertions.assertThat;

public class FibonacciTest {
    @Test
    public void returns0WhenAskingForTheZeroFibonacciNumber() {
        assertThat(fibonacci(0)).isEqualTo(0);
    }

    @Test
    public void returns1WhenAskingForTheFirstFibonacciNumber() {
        assertThat(fibonacci(1)).isEqualTo(1);
    }

    @Test
    public void returnsTheExpectedFibonacciNumbers() {
        assertThat(fibonacci(2)).isEqualTo(1);
        assertThat(fibonacci(3)).isEqualTo(2);
        assertThat(fibonacci(4)).isEqualTo(3);
        assertThat(fibonacci(5)).isEqualTo(5);
    }

    @Test
    public void recursive_returns0WhenAskingForTheZeroFibonacciNumber() {
        assertThat(fibonacciRecursive(0)).isEqualTo(0);
    }

    @Test
    public void recursive_returns1WhenAskingForTheFirstFibonacciNumber() {
        assertThat(fibonacciRecursive(1)).isEqualTo(1);
    }

    @Test
    public void recursive_returnsTheExpectedFibonacciNumbers() {
        assertThat(fibonacciRecursive(2)).isEqualTo(1);
        assertThat(fibonacciRecursive(3)).isEqualTo(2);
        assertThat(fibonacciRecursive(4)).isEqualTo(3);
        assertThat(fibonacciRecursive(5)).isEqualTo(5);
    }

    @Test
    public void stream_returns0WhenAskingForTheZeroFibonacciNumber() {
        assertThat(fibonacciStream(0)).isEqualTo(0);
    }

    @Test
    public void stream_returns1WhenAskingForTheFirstFibonacciNumber() {
        assertThat(fibonacciStream(1)).isEqualTo(1);
    }

    @Test
    public void stream_returnsTheExpectedFibonacciNumbers() {
        assertThat(fibonacciStream(2)).isEqualTo(1);
        assertThat(fibonacciStream(3)).isEqualTo(2);
        assertThat(fibonacciStream(4)).isEqualTo(3);
        assertThat(fibonacciStream(5)).isEqualTo(5);
    }
}