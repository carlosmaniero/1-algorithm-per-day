# Fibonacci

I solved this problems in three different ways. The classical the recursive and using the Stream API.

As you all know, the fibonacci sequence starts with 0 and 1, where the next value should be the sum of the previous one.

    0 1 1 2 3 5 8 ...

## The classical way

```java
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
}
```

To solve the problem in the classical way, you need to variables: one for the `previous` value and another one for the `current` value.

I just started with the first values of the sequence `0` and `1`. As the current value is `1`, a guard clause is needed, otherwise the `n=0` would be `1` not `0`.

The `for` loop starts with `i=1` because the first fibonacci number is already defined in the `current` variable. So basically what we need to do at the `for` block is to define the new `current` and `previous` value, where the `previous` should be the `current` and `current` should be the `current + previous`.

To avoid to have a temporary variable you can just define the current with `current + previous` and after that define
the previous as `current - previous`. Because when `current` is `3` and `previous` is `2` then `current=3+2` and `previous=5-2`. 

## The recursive way

Fibonacci is a recursive sequence. To solve `fibonacci(n)` you just need to sum `fibonacci(n-1) + fibonacci(n-2)`.
That said, let's see the code:

```java
public class Fibonacci {
    public static long fibonacciRecursive(long n) {
        if (n <= 1) {
            return n;
        }
        return fibonacciRecursive(n-1) + fibonacciRecursive(n-2);
    }
}
```

### How is it evaluated?

![Fibonacci Recurcive](./Fibonacci%20Recursive.svg)

The purple number is the `if` clause. That it means that always that zero or one is given for the fibonacci function, 
it will return the same number. This default values are called `base cases` that prevents the function to run forever.

## Fibonacci using Stream API

Personally, the classical way has some kind of mutations that makes the code hard to understand. Even when we just have
two variables changing. The recursive way is the one who best describes what fibonacci is in terms of code, but it is
really slow. As you can see above, to discovery `fib(5)` we need to discovery `fib(4)` and `fib(3)` and to
discovery `fib(4)` we need to discovery `fib(3)` again. So `fib(3)` is processed twice.

To solve this problem using streams, I first started creating a representation to our fibonacci pairs.

```java
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
```

So `fib(1)` is equals to `FibonacciSequence.initialSequence().getValue())`, `fib(2)` is equals to 
`FibonacciSequence.initialSequence().getNext().getValue())`. So let's see how our function looks like:

```java
public class Fibonacci {
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
}
```

As the classic way our sequence starts with `1`, it means that we also need the guard clause for zero. The 
`Stream.iterate` will get the initial value and will `call` the given function at the second argument. That means that
for each iteration it will call the `.getNext()` and store the returned value.