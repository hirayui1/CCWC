package org.example.fp;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class RealFpExample {

    // new addition in Java 17
    // same as class Person with constructor

    // this is a blueprint
    // instance is a living object
    record Person(String name, int age, String email) {
        boolean isAdult() { return age >= 18; }
    }

    static Person randomPerson(String name, int age, String email) {
        return new Person(
                name,
                age,
                email
        );
    }

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();

        people.add(randomPerson("Random guy", 17, "random guys' email"));
        people.add(randomPerson("Berke", 22, "Berke's email"));
        people.add(randomPerson("Magda", 45, "magda's email"));
        people.add(randomPerson("Fanuel", 25, "fanuel's email"));

        // your task is to:
        // collect people's emails and print them in uppercase who are adults
        // rules:
        // - at least 18
        // - make email uppercase
        // - print them

        // without FP

        // imperative
        for (Person person : people) {
            if (person.age >= 18) {
                String emailInUppercase = person.email.toUpperCase();
                System.out.println(emailInUppercase);
            }
        }

        // with FP
        // intro to streams and function applied to streams

        // Function<Person, boolean>
        // :: means turning method into function
        Predicate<Person> adultsLonger = person -> person.age >= 18;
        Predicate<Person> adultsNicer  = Person::isAdult; // turns into this "person -> person.age >= 18"
        // val adults: Person => Boolean = _.age >= 18

        Function<Person, String> theirEmailsToUppercase = (Person person) -> person.email.toUpperCase();

        // declarative programming - value / expression oriented / FP.
        people.stream()
                .filter(adultsNicer)
                .map(theirEmailsToUppercase)
                .forEach(System.out::println);

    }
}
