package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class InterviewQuestions {

    public static void main(String[] args) {
        List<Integer> myList = Arrays.asList(10,15,8,49,25,98,32,98);

        //Given a list of integers, find out all the numbers starting with 1 using Stream functions?
        List<String> startsWith1 = myList.stream().map(number -> number + "").filter(num -> num.startsWith("1")).collect(Collectors.toList());
        System.out.println("startsWith1 "+ startsWith1);
        Set<Integer> hashset = new HashSet<>();

        //How to find duplicate elements in a given integers list in java using Stream functions?
        List<Integer> duplicates = myList.stream().
                filter(n -> !hashset.add(n)).toList();
        System.out.println("duplicates "+ duplicates);

       // Given the list of integers, find the first element of the list using Stream functions?
        myList.stream().findFirst().ifPresent(System.out::println);

        //Given a list of integers, find the total number of elements present in the list using Stream functions?
       System.out.println("Total Count " + myList.stream().count());

       //Given a list of integers, find the maximum value element present in it using Stream functions?
        System.out.println("Max Number " + myList.stream().max(Integer::compare).get());

        //Given a list of integers, find the minimum value element present in it using Stream functions?
        System.out.println("Min Number " + myList.stream().min(Integer::compare).get());
        System.out.println("Min Number option II " + myList.stream().reduce(Integer::min).get());

        //Given a String, find the first non-repeated character in it using Stream functions?
       System.out.println("Non repeat "+ myList.stream().map(e->e+"").distinct().toList());
        //How do you sort a list of objects using Java 8 Comparator?
        List<Employee> employees = Arrays.asList(
                new Employee(1, 30, "John", "HR", Collections.emptyList()),
                new Employee(2, 25, "Jane", "Finance", Collections.emptyList()),
                new Employee(3, 35, "Jack", "IT", Collections.emptyList())
        );
        List<Employee> sortedEmployees = employees.stream()
                .sorted(Comparator.comparing(Employee::getAge)).toList();
        System.out.println("Sorted Employees by Age: " + sortedEmployees);
        //How do you sort in reverse order using Comparator in Java 8?
        List<Employee> reverseSortedEmployees = employees.stream()
                .sorted(Comparator.comparing(Employee::getAge).reversed()).toList();
        System.out.println("Reverse Sorted Employees by Age: " + reverseSortedEmployees);
        //How do you chain multiple comparators (sort by multiple fields)?
        List<Employee> multiSortedEmployees = employees.stream()
                .sorted(Comparator.comparing(Employee::getDept)
                        .thenComparing(Employee::getAge)).toList();
        System.out.println("Multi Sorted Employees by Dept and Age: " + multiSortedEmployees);
        //How do you handle null values while sorting with Comparator?
        List<Employee> employeesWithNulls = Arrays.asList(
                new Employee(1, 30, "John", "HR", Collections.emptyList()),
                new Employee(2, null, "Jane", "Finance", Collections.emptyList()),
                new Employee(3, 35, "Jack", "IT", Collections.emptyList())
        );
        List<Employee> sortedEmployeesWithNulls = employeesWithNulls.stream()
        .sorted(Comparator.comparing(Employee::getAge, Comparator.nullsFirst(Integer::compareTo)))
                .toList();
        System.out.println("Sorted Employees with Nulls First: " + sortedEmployeesWithNulls);

        //How do you sort a list of strings in Java 8?
        List<String> stringList = Arrays.asList("banana", "Apple", "orange", "kiwi", "grape");
        List<String> sortedStringList = stringList.stream()
                .sorted()
                .toList();
        //How do you sort a list of strings ignoring case?
        List<String> sortedStringListIgnoreCase = stringList.stream()
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .toList();
        System.out.println("Sorted Strings: " + sortedStringList);
        System.out.println("Sorted Strings Ignore Case: " + sortedStringListIgnoreCase);
        //How do you sort a map by its Keys using Java 8 streams and Comparator?
        Map<String, Integer> map = new HashMap<>();
        map.put("banana", 1);
        map.put("apple", 3);
        map.put("orange", 2);
        Map<String, Integer> sortedMapByValues = map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, // Merge function for duplicate keys
                        LinkedHashMap::new // Use LinkedHashMap to maintain insertion order
                ));
        System.out.println("Sorted Map by Key: " + sortedMapByValues);
    }



}
