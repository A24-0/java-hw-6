import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Ann", 18));
        students.add(new Student("Sam", 19));
        students.add(new Student("Mary", 22));
        students.add(new Student("Boris", 17));

        List<Student> sortedByName = sortByName(students);
        System.out.println("Sorted by name:");
        for (Student student : sortedByName) {
            System.out.println(student.getName());
        }

        List<Student> sortedByAge = sortByAge(students);
        System.out.println("\nSorted by age:");
        for (Student student : sortedByAge) {
            System.out.println(student.getName() + " - " + student.getAge());
        }

        var g = new StudentGroup(new ArrayList<>());
        g.addStudent(new Student("Ann", 18));
        g.addStudent(new Student("Sam", 19));
        g.addStudent(new Student("Mary", 22));
        g.addStudent(new Student("Boris", 17));
        var names = g.overNames();
        var it1 = names.iterator();
        var it2 = names.iterator();
        while (it1.hasNext()) {
            System.out.println(it1.next().equals(it2.next()));
        }
        System.out.println(!it2.hasNext());
        for (var name : names) {
            System.out.println(name);
        }
    }

    public static List<Student> sortByName(List<Student> students) {
        students.sort(Comparator.comparing(Student::getName));
        return students;
    }

    public static List<Student> sortByAge(List<Student> students) {
        students.sort(Comparator.comparingInt(Student::getAge));
        return students;
    }
}

final class StudentGroup {
    public StudentGroup(List<Student> students) {
        this.students = students;
    }

    void addStudent(Student s) {
        this.students.add(s);
    }

    Iterable<String> overNames() {
        return new Iterable<String>() {
            @Override
            public Iterator<String> iterator() {
                return new Iterator<String>() {
                    private final Iterator<Student> iterator = students.iterator();

                    @Override
                    public boolean hasNext() {
                        return iterator.hasNext();
                    }

                    @Override
                    public String next() {
                        return iterator.next().getName();
                    }
                };
            }
        };
    }

    private final List<Student> students;
}