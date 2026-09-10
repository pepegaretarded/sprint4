package ru.praktikum.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.BaseTest;
import ru.praktikum.pages.HomePage;
import ru.praktikum.pages.OrderPage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class OrderPageTest extends BaseTest {

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

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

    @Parameterized.Parameters(name = "Тестовые данные: {0} {1}")
    public static Collection<Object[]> getData() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);

        return Arrays.asList(new Object[][]{
                {
                        "Иван",
                        "Петров",
                        "улица Ленина, 10",
                        "Сокольники",
                        "79999999999",
                        tomorrow.format(DATE_FORMATTER),
                        "двое суток",
                        "самокат"
                },
                {
                        "Алексей",
                        "Смирнов",
                        "улица Гагарина, 25",
                        "Лубянка",
                        "78888888888",
                        tomorrow.plusDays(1).format(DATE_FORMATTER),
                        "трое суток",
                        "Позвонить перед доставкой"
                }
        });
    }

    @Test
    public void orderThroughTopButton() {
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

}
