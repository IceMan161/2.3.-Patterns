package ru.netology.delivery.test;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;
import ru.netology.delivery.data.RegistrationInfo;

import java.time.Duration;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.*;


public class CardDeliveryTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        Configuration.headless = true;
        open("http://localhost:9999");
    }

    @Test
    void shouldSendForm() {

        String planningDate = DataGenerator.generateDate(10);

        RegistrationInfo info = DataGenerator
                .Registration.generateInfo("ru");

        $x("//input[@type='text']").val(info.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(SHIFT, HOME), BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $x("//input[@name='name']").val(info.getName());
        $x("//input[@name='phone']").val(info.getPhone());
        $x("//label[@data-test-id='agreement']").click();
        $(withText("Запланировать")).click();

        $x("//*[contains(text(),'Успешно!')]").should(appear, Duration.ofSeconds(5));
        $(".notification__content").shouldHave(Condition.text("Встреча успешно запланирована на " + planningDate), Duration.ofSeconds(5));

    }

    @Test
    void shouldSendFormRescheduling() {

        String planningDate = DataGenerator.generateDate(5);
        String planingDateSecond = DataGenerator.generateDate(10);

        RegistrationInfo info = DataGenerator
                .Registration.generateInfo("ru");

        $x("//input[@type='text']").val(info.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(SHIFT, HOME), BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $x("//input[@name='name']").val(info.getName());
        $x("//input[@name='phone']").val(info.getPhone());
        $x("//label[@data-test-id='agreement']").click();
        $(withText("Запланировать")).click();

        $x("//*[contains(text(),'Успешно!')]").should(appear, Duration.ofSeconds(5));
        $(".notification__content").shouldHave(Condition.text("Встреча успешно запланирована на " + planningDate), Duration.ofSeconds(5));

        $("[data-test-id='date'] input").sendKeys(Keys.chord(SHIFT, HOME), BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planingDateSecond);
        $(withText("Запланировать")).click();

        $x("//*[contains(text(),'Необходимо подтверждение')]").should(visible, Duration.ofSeconds(5));
        $(withText("Перепланировать")).click();

        $x("//*[contains(text(),'Успешно!')]").should(appear, Duration.ofSeconds(5));
        $(".notification__content").shouldHave(Condition.text("Встреча успешно запланирована на " + planingDateSecond), Duration.ofSeconds(5));

    }
}
