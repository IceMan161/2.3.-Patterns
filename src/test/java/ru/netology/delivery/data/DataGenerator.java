package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

import java.util.Locale;

@UtilityClass
public class DataGenerator {

    public static class Registration {
        public static RegistrationInfo generateInfo(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return new RegistrationInfo(faker.address().cityName(),
                    //                   LocalDate.now().plusDays(7),
                    faker.name().fullName(),
                    faker.phoneNumber().phoneNumber());
        }

    }
}
