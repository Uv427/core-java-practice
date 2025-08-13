package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Address {
    private String streetName;
    private Integer streetNumber;
    private String postalCode;
}
