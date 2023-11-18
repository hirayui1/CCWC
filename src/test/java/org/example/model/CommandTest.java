package org.example.model;

import org.junit.jupiter.api.Test;


import java.util.Optional;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {

    @Test
    // test if "-c" is parsed to Command.Bytes
    public void testParsingToBytes() {
        // write test code

        // what we should be expecting
        Optional<Command> expected = Optional.of(Command.Bytes);

        // what is real result after calling production code
        Optional<Command> actual = Command.parseToCommand("-c");

        assertEquals(expected, actual);
    }

    @Test
    // test if "-l" is parsed to Command.Lines
    public void testParsingToLines() {
        // write test code
        Optional<Command> expected = Optional.of(Command.Lines);
        Optional<Command> actual = Command.parseToCommand("-l");

        assertEquals(expected, actual);
    }

    @Test
    // test if "-l" is parsed to Command.Lines
    public void testParsingIncorrectCommand() {
        // write test code
        Optional<Command> expected = Optional.empty();
        Optional<Command> actual = Command.parseToCommand("-awindaiwdiwaiudn");

        assertEquals(expected, actual);
    }

}