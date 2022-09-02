import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        System.out.println("Amount of underage people: " +
                persons.stream().filter((underage) -> underage.getAge() < 18).count() + "\n");

        List<String> conscription =
        persons.stream()
                .filter(conscripts -> conscripts.getAge() > 17)
                .filter(conscripts -> conscripts.getAge() < 28)
                .filter(conscripts -> conscripts.getSex()== Sex.MAN)
                .map(Person::getFamily)
//                .limit(10)
                .collect(Collectors.toList());
        System.out.println("List of conscripts:");
        conscription.forEach(System.out::println);

        System.out.println();

        List<Person> work =
        persons.stream()
                .filter(workers -> workers.getAge() > 17)
                .filter(workers -> workers.getEducation() == Education.HIGHER)
                .filter(workers -> workers.getAge() < 60 && workers.getSex() == Sex.WOMAN
                        || workers.getAge() < 65 && workers.getSex() == Sex.MAN)
                .sorted(Comparator.comparing(Person::getFamily))
//                .limit(10)
                .collect(Collectors.toList());
        System.out.println("List of workers:");
        work.forEach(System.out::println);
    }
}