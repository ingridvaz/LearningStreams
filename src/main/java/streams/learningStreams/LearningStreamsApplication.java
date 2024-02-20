package streams.learningStreams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
public class LearningStreamsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearningStreamsApplication.class, args);

        List<Person> people = getPeople();

        //Declarative approach
        //Filter
        List<Person> females = people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .collect(Collectors.toList());

        //Sort
        List<Person> sortedAge = people.stream()
                .sorted(Comparator.comparing(Person::getAge))
                .collect(Collectors.toList());
        //sortedAge.forEach(System.out::println);

        //Max
        people.stream()
                .max(Comparator.comparing(Person::getAge))
                .ifPresent(person -> {
                    System.out.println(person);
                });

        //Min
        people.stream()
                .min(Comparator.comparing(Person::getAge))
                .ifPresent(person -> {
                    System.out.println(person);
                });

        //Group

        Map<Gender, List<Person>> groupbyGender = people.stream()
                .collect(Collectors.groupingBy(Person::getGender));
        groupbyGender.forEach((gender, people1) -> {
            System.out.println(gender);
            people1.forEach(System.out::println);
        });
    }
    private static List<Person> getPeople() {
        return List.of(
                new Person("Ana", 32, Gender.FEMALE),
                new Person("Andre", 43, Gender.MALE),
                new Person("Ingrid", 33, Gender.FEMALE),
                new Person("Paulo", 37, Gender.MALE)
        );
    }
}
