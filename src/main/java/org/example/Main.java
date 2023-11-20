package org.example;

import org.example.model.Command;
import org.example.services.Counter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collectors;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    static void process(Consumer<Integer> cd) {
        cd.accept(5);
    }

    public static void main(String[] args) {

        // if the args.length >= 2 (follow same approach)
        // else it has 1 arg which is only filename
        if (args.length > 1) {

            String command = args[0];
            String filePath = args[1];

            try {
                String content = Files.readString(Paths.get(filePath));

                var maybeCommand = Command.parseToCommand(command); // Optional.of(Default)

                var counter = new Counter();

                if (maybeCommand.isPresent()) { // true
                    var cmd = maybeCommand.get();
                    counter.calculateAndDisplay(cmd, content, filePath);
                } else{
                    System.out.println(command + " does not exist");
                }
                // read file in filePath
            } catch(IOException ioe){
                ioe.printStackTrace();
                // System.out.println(ioe.getMessage());
            }
        } else {
            //
            Counter counter = new Counter();
            var cmdInput = args[0];
            var maybeCommand = Command.parseToCommand(cmdInput);
            // either

            if (maybeCommand.isPresent()) { // true
                Scanner scan = new Scanner(System.in);
                var sbuilder = new StringBuilder();
                while(scan.hasNext()){
                    sbuilder.append(scan.nextLine());
                }
                scan.close();
                var content = sbuilder.toString();
                var cmd = maybeCommand.get();
                counter.calculateAndDisplay(cmd, content, "");
            } else{
                String filepath = args[0];
                try {
                    String content = Files.readString(Paths.get(filepath));
                    var commands = Command.DefaultValues;
                    var numbers = commands.stream()
                            .map(cmd -> counter.matchAndCalculate(cmd, content)) // Command -> Long
                            .toList();
                    // map = general function from A => B | String => Integer = (String s) -> s.toInteger();
                    String numbersAsString = numbers.stream() // turn list to stream
                            .map(Objects::toString) // long -> String
                            .reduce("", (s1, s2) -> String.join("|", s1, s2)) // join two strings into one delimited by "|"
                            .substring(1); // drop first character
                    String outputResult = String.join("|", numbersAsString, filepath); // join two strings
                    System.out.println(outputResult); // show in format number1|number2|number3|filename.txt
                } catch(IOException ioe) {
                    ioe.printStackTrace();
                }
            }


        }
        // count bytes
        // print bytes and file name
    }
}