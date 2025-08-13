package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamAPI {

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Employee> EMPLOYEES = Lists.newArrayList(
                new Employee(1,20,"Kai", "HR", Lists.newArrayList(new Address("Aylesbury",80,"L7A"))),
                new Employee(2,21,"Eric", "Manager", Lists.newArrayList(new Address("Queen",205,"L6W"))),
                new Employee(3,22,"Saajan", "HR", Lists.newArrayList(new Address("Daven",24,"L6S"),new Address("DavenPort",240,"L6S"))),
                new Employee(4,23,"Kevin", "Architect", Lists.newArrayList(new Address("Maven",16,"L6B"))),
                new Employee(5,24,"Amanda", "Manager", Lists.newArrayList(new Address("Java",90,"X7W")))
        );

        try {
            String jsonString = objectMapper.writeValueAsString(groupByDept(EMPLOYEES));
            System.out.println("groupByDept "+ jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        try {
            String jsonString = objectMapper.writeValueAsString(groupByDeptAndCounting(EMPLOYEES));
            System.out.println("groupByDept and counting "+ jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        try {
            String jsonString = objectMapper.writeValueAsString(FilterByPostalCode(EMPLOYEES));
            System.out.println("FilterByPostalCode "+ jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            String jsonString = objectMapper.writeValueAsString(FilterByPostalCodeAndGroupByDept(EMPLOYEES));
            System.out.println("FilterByPostalCodeAndGroupByDept "+ jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        try {
            String jsonString = objectMapper.writeValueAsString(flatMap(EMPLOYEES));
            System.out.println("======== flatMap ======");
            System.out.println("flatMap "+ jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            String jsonString = objectMapper.writeValueAsString(flatMapWithGroupBy(EMPLOYEES));
            System.out.println("======== flatMapWithGroupBy ======");
            System.out.println("flatMapWithGroupBy "+ jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


    }

    // Grouping
    private static Map<String, List<Employee>> groupByDept(List<Employee> employees){

        return employees.stream().collect(Collectors.groupingBy(Employee::getDept));

    }
    // Grouping and counting
    private static Map<String, Long> groupByDeptAndCounting(List<Employee> employees){

        return employees.stream().collect(Collectors.groupingBy(Employee::getDept,Collectors.counting()));

    }

    private  static List<Employee> FilterByPostalCode(List<Employee> employees){

        return employees.stream()
                .filter(emp-> emp.getAddresses().stream().
                        anyMatch(address -> address.getPostalCode().startsWith("L6"))).collect(Collectors.toList());
    }
    private  static Map<String, List<Employee>> FilterByPostalCodeAndGroupByDept(List<Employee> employees){

        return employees.stream()
                .filter(emp-> emp.getAddresses().stream().
                        anyMatch(address -> address.getPostalCode().startsWith("L6"))).collect(Collectors.groupingBy(Employee::getDept));
    }

    private  static List<Address> flatMap(List<Employee> employees){

        return employees.stream().flatMap(employee -> employee.getAddresses().stream()).collect(Collectors.toList());
    }

    private  static Map<String, List<Address>> flatMapWithGroupBy(List<Employee> employees){

        return employees.stream().flatMap(employee -> employee.getAddresses().stream()).collect(Collectors.groupingBy(Address::getPostalCode));
    }


}
