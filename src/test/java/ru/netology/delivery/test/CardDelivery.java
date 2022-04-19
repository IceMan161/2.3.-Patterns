package ru.netology.delivery.test;


import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.delivery.data.DataGenerator;
import ru.netology.delivery.data.RegistrationInfo;

import static com.codeborne.selenide.Selenide.open;


public class CardDelivery {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSendForm() {

        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "800x600";
        //       Configuration.headless = true;

        RegistrationInfo info = DataGenerator
                .Registration.generateInfo("ru");


    }
}
