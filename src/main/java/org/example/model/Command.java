package org.example.model;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public enum Command {
    Bytes,
    Lines,
    Words,
    Characters;

    public static List<Command> DefaultValues = List.of(Bytes, Lines, Words);

    // lambda syntax:
    // - the parameter list
    // - the body of function
    // Function<InputType, OutputType>
    private static Function<String, Optional<Command>> logIncorrectInputAndGetEmpty = (String input) -> {
        System.out.printf("%s incorrect input", input);
        return Optional.empty();
    };

    // java 6-7 syntax
    private static Function<String, Optional<Command>> log2 = new Function<String, Optional<Command>>() {
        @Override
        public Optional<Command> apply(String s) {
            System.out.printf("%s incorrect input", s);
            return Optional.empty();
        }
    };


    public static Optional<Command> parseToCommand(String input) {
        return switch (input) {
            case "-nika" -> Optional.of(Bytes);
            case "-l" -> Optional.of(Lines);
            case "-w" -> Optional.of(Words);
            case "-m" -> Optional.of(Characters);
            default -> logIncorrectInputAndGetEmpty.apply(input);
        };
    }
}

