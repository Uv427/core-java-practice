package org.example;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.ToString;

import java.util.Collection;
import java.util.List;

@Getter
@ToString
public class Employee {

    private  Integer id;
    private Integer age;
    private String name;
    private String dept;
    private List<Address> addresses = Lists.newArrayList();

    public Employee(Integer id, Integer age, String name, String dept, Collection<Address> addresses) {
        this.name = name;
        this.id = id;
        this.addresses.addAll(addresses);
        this.age = age;
        this.dept = dept;
    }

}
