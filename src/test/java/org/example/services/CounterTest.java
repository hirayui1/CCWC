package org.example.services;

import org.example.model.Command;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CounterTest {

    //

    @Test
    public void testMatchAndCalculateBytes(){
        Counter counter = new Counter();
        Command bytes = Command.Bytes;
        String input = "a"; //  65
        long expected = 1;
        long actual = counter.matchAndCalculate(bytes, input);

        assertEquals(expected, actual);
    }
    @Test
    public void testMatchAndCalculateLines(){
        Counter counter = new Counter();
        Command lines = Command.Lines;
        String s1 = "berke nika";
        String s2 = "joanna joanna";
        String s3 = "magda";

        String input1 = String.join("\n", s1, s2, s3);
        String input2 = "berke nika\njoanna joanna\nmagda";

        long expected = 3;
        long actual = counter.matchAndCalculate(lines, input1);
        System.out.println(input1);
        System.out.println(input2);

        assertEquals(expected, actual);
    }

}