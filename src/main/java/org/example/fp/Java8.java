package org.example.fp;

import java.util.function.BiFunction;
import java.util.function.Consumer;

public class Java8 {

    // prior:
    // - verbose
    // - less modern
    // - less FP (more imperative)

    // after:
    // - functional interfaces
    // - lambdas
    // - streams

    /**
     * write a method that takes a string and logs it
     */
    // Function<String, void> === Consumer<String>
    public static void show(String input) {
        System.out.println(input);
    }

    public static int getSelf(int a) {
        return a;
    }

    public static void processAndShow(String s1, String s2) {
        String result = s1.concat(s2); // s1="Berke", s2="Joanna" => "BerkeJoanna"
        show(result);
    }

    /**
     * what if I can:
     * - store Functions in variables / values?
     * - pass Function as argument
     * - return Function from methods
     */

    // more customized way
    // static method
    public static void processAndShow2(
            Consumer<String> showFunction,
            BiFunction<String, String, String> calculator,
            String s1,
            String s2
    ) {
        String result = calculator.apply(s1, s2);
        showFunction.accept(result);
    }

    public static void main(String[] args) {

        Consumer<String> justPrintItLong = new Consumer<String>() {

            @Override // public void accept(String s);
            public void accept(String s) {
                System.out.println(s);
            }
        };

        // Consumer<String> = take a string and do side effect
        Consumer<String> justPrintItMiddle = (String input) -> System.out.println(input);
        Consumer<String> justPrintItShorter = System.out::println; // compiler = expand into (String s) -> System.out.println(s);

        // compiler is also a code
        /**
         *
         */

        // take a string
        // 1. print it once
        // 2. and then print it twice, to uppercase

        Consumer<String> justPrintOnce = System.out::println;

        Consumer<String> toUppercaseAndThenPrintTwice = s -> {
            var upperCase = s.toUpperCase();
            System.out.println(upperCase);
            System.out.println(upperCase);
        };

        // reads like a story
        justPrintOnce.andThen(toUppercaseAndThenPrintTwice).accept("Berke");

        // Consumer - accept
        // Function - apply
        // Supplier - get()
        // Callable - call()
        // Runnable - run()
        // BiFunction<T, T, T> - apply(T t1, T t2);

        // BiFunction<String, String, String> = take two strings and return one string
        BiFunction<String, String, String> concatenate = String::concat;

        processAndShow2(
                justPrintItShorter,
                concatenate,
                "Joanna",
                "Berke"
        );




    }




}
