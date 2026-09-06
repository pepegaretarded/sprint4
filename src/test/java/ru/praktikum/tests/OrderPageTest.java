package ru.praktikum.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.praktikum.pages.HomePage;
import ru.praktikum.pages.OrderPage;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class OrderPageTest {

    private WebDriver driver;

    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String rentalPeriod;
    private final String comment;

    public OrderPageTest(
            String name,
            String surname,
            String address,
            String metro,
            String phone,
            String date,
            String rentalPeriod,
            String comment
    ) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getData() {
        return Arrays.asList(new Object[][]{
                {
                        "Иван",
                        "Петров",
                        "улица Ленина, 10",
                        "Сокольники",
                        "79999999999",
                        "29.08.2026",
                        "двое суток",
                        "самокат"
                },
                {
                        "Алексей",
                        "Смирнов",
                        "улица Гагарина, 25",
                        "Лубянка",
                        "78888888888",
                        "30.08.2026",
                        "трое суток",
                        "Позвонить перед доставкой"
                }
        });
    }

    @Test
    public void orderThroughTopButton() {
        openHomePage();

        HomePage homePage = new HomePage(driver);
        homePage.clickTopOrderButton();

        fillOrderForm();

        OrderPage orderPage = new OrderPage(driver);

        orderPage.clickOrderButton();
        orderPage.confirmOrder();

        Assert.assertTrue(
                "Не появилось сообщение об успешном оформлении заказа",
                orderPage.isOrderCreated()
        );
    }

    @Test
    public void orderThroughBottomButton() {
        openHomePage();

        HomePage homePage = new HomePage(driver);
        homePage.clickBottomOrderButton();

        fillOrderForm();

        OrderPage orderPage = new OrderPage(driver);

        orderPage.clickOrderButton();
        orderPage.confirmOrder();

        Assert.assertTrue(
                "Не появилось сообщение об успешном оформлении заказа",
                orderPage.isOrderCreated()
        );
    }

    private void openHomePage() {
        WebDriverManager.firefoxdriver().setup();

        driver = new FirefoxDriver();

        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    private void fillOrderForm() {
        OrderPage orderPage = new OrderPage(driver);

        orderPage.setName(name);
        orderPage.setSurname(surname);
        orderPage.setAddress(address);
        orderPage.selectMetro(metro);
        orderPage.setPhone(phone);

        orderPage.clickNextButton();

        orderPage.setDate(date);
        orderPage.selectRentalPeriod(rentalPeriod);
        orderPage.selectBlackColor();
        orderPage.setComment(comment);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}