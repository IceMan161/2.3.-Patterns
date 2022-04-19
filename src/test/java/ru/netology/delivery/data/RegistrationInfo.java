package ru.netology.delivery.data;

import lombok.Data;

import java.time.LocalDate;

@Data

public class RegistrationInfo {

    private final String city;
    private final LocalDate date;
    private final String name;
    private final String phone;

}
