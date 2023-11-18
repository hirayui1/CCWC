package org.example.services;

import org.example.model.Command;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Counter {

    /**
     * client where we use Counter
     * var c = new Counter();
     * c.matchAndCalculate(... , ...) <- belongs to running instance (object)
     *
     * /if it was static
     * Counter.matchAndCalculate <- belongs to class
     *
     * / Scanner class
     *
     * Scanner scan = new Scanner
     * scan.readLine() <- method on instance of class
     *
     * Scanner.readLine() <- static method on class
     *
     */

    private static Pattern ptn = java.util.regex.Pattern.compile("\\s+");

    // input can be: "-c", "-l", all else should
    public void calculateAndDisplay(
            Command cmd,
            String content,
            String filePath
    ) {
        System.out.println(filePath + " " + matchAndCalculate(cmd, content));

        // unit testing

      /*  if (cmd.equals(Command.Bytes)){
            var byteLength = content.getBytes(StandardCharsets.UTF_8).length;
            System.out.println(filePath + " " + byteLength);
        } else if (cmd.equals(Command.Lines)){
            var length = content.split("\n").length;
            System.out.println(filePath + " " + length);
        } else if (cmd.equals(Command.Words)) {
            // F[F[String]].flatMap => F[String]
            // List<List<String>>.flatMap => List<String> / map(...).flatten
          var words =  content
                  .lines()
                  .flatMap((String line) -> ptn.splitAsStream(line)) // split on Stream<String>
                  .filter((String word) -> !word.isEmpty()).count(); // keep non empty strings
            System.out.println(filePath + " " + words);
        } else if (cmd.equals(Command.Characters)) {
            var characters = Arrays.stream(content.split("")).map(String::trim).count();
            // var characters = content.split("").filter(word -> !word.isEmpty()).length(); why can't I do this?
            System.out.println(filePath + " " + characters);
        } else
            System.out.println(filePath + " " + "");// make byteLength, length, words and characters be printed out as a default option if the user does not enter a command.

       */
    }

    // method defined on object
    // package-default makes testing easier
    public long matchAndCalculate(Command cmd, String content) {
        return switch (cmd) {
            case Bytes -> content.getBytes(StandardCharsets.UTF_8).length;
            case Lines -> content.split("\n").length;
            case Words -> content.lines()
                        .flatMap((String line) -> ptn.splitAsStream(line)) // split on Stream<String>
                        .filter((String word) -> !word.isEmpty()).count(); // keep non empty strings
            case Characters -> Arrays.stream(content.split("")).map(String::trim).count();

        };
    }
}
