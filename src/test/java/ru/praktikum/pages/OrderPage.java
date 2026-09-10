package ru.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Первая страница заказа
    private final By nameField = By.xpath("//input[@placeholder='* Имя']");
    private final By surnameField = By.xpath("//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroField = By.xpath("//input[@placeholder='* Станция метро']");
    private final By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath("//button[normalize-space(.)='Далее']");

    // Вторая страница заказа
    private final By dateField = By.xpath(
            "//input[@placeholder='* Когда привезти самокат']"
    );

    private final By rentalPeriodField = By.xpath(
            "//div[contains(@class,'Dropdown-placeholder') and normalize-space(.)='* Срок аренды']"
    );

    private final By commentField = By.xpath(
            "//input[@placeholder='Комментарий для курьера'] | " +
                    "//textarea[@placeholder='Комментарий для курьера']"
    );

    // Cookie
    private final By cookieButton = By.id("rcc-confirm-button");

    // Подтверждение заказа
    private final By confirmButton = By.xpath(
            "//button[normalize-space(.)='Да']"
    );

    // Сообщение об успешном заказе
    private final By successMessage = By.xpath(
            "//*[contains(text(),'Заказ оформлен') or contains(text(),'Номер заказа')]"
    );

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        closeCookieBanner();
    }

    // Закрытие cookie-баннера
    private void closeCookieBanner() {
        try {
            WebElement button = new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.elementToBeClickable(cookieButton));

            button.click();

        } catch (Exception ignored) {
        }
    }

    // Имя
    public void setName(String name) {
        wait.until(
                ExpectedConditions.elementToBeClickable(nameField)
        ).sendKeys(name);
    }

    // Фамилия
    public void setSurname(String surname) {
        wait.until(
                ExpectedConditions.elementToBeClickable(surnameField)
        ).sendKeys(surname);
    }

    // Адрес
    public void setAddress(String address) {
        wait.until(
                ExpectedConditions.elementToBeClickable(addressField)
        ).sendKeys(address);
    }

    // Станция метро
    public void selectMetro(String metroStation) {

        WebElement metroInput = wait.until(
                ExpectedConditions.elementToBeClickable(metroField)
        );

        metroInput.click();
        metroInput.sendKeys(metroStation);

        By metroOption = By.xpath(
                "//div[contains(@class,'select-search__select')]" +
                        "//button[contains(@class,'select-search__option')]" +
                        "[normalize-space(.)=" + xpathText(metroStation) + "]"
        );

        WebElement option = wait.until(
                ExpectedConditions.elementToBeClickable(metroOption)
        );

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        option
                );

        option.click();
    }

    // Телефон
    public void setPhone(String phone) {
        wait.until(
                ExpectedConditions.elementToBeClickable(phoneField)
        ).sendKeys(phone);
    }

    // Далее
    public void clickNextButton() {

        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(nextButton)
        );

        button.click();
    }

    // Дата доставки
    public void setDate(String date) {

        WebElement input = wait.until(
                ExpectedConditions.elementToBeClickable(dateField)
        );

        input.click();
        input.clear();
        input.sendKeys(date);
        input.sendKeys(Keys.ENTER);

        wait.until(
                ExpectedConditions.attributeToBe(
                        dateField,
                        "value",
                        date
                )
        );
    }

    // Срок аренды
    public void selectRentalPeriod(String rentalPeriod) {

        WebElement field = wait.until(
                ExpectedConditions.elementToBeClickable(rentalPeriodField)
        );

        field.click();

        By rentalOption = By.xpath(
                "//div[contains(@class,'Dropdown-option')]" +
                        "[normalize-space(.)=" + xpathText(rentalPeriod) + "]"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(rentalOption)
        ).click();
    }

    // Чёрный цвет
    public void selectBlackColor() {

        By blackColorLabel = By.xpath(
                "//label[contains(.,'чёрный') or contains(.,'черный')]"
        );

        WebElement label = wait.until(
                ExpectedConditions.elementToBeClickable(blackColorLabel)
        );

        label.click();
    }

    // Комментарий
    public void setComment(String comment) {

        wait.until(
                ExpectedConditions.elementToBeClickable(commentField)
        ).sendKeys(comment);
    }

    // Кнопка Заказать
    public void clickOrderButton() {

        By orderButton = By.xpath(
                "(//button[normalize-space(.)='Заказать'])[last()]"
        );

        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(orderButton)
        );

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        button
                );

        button.click();
    }

    // Кнопка Да в окне подтверждения
    public void confirmOrder() {

        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(confirmButton)
        );

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", button);
    }

    // Проверка успешного оформления
    public boolean isOrderCreated() {

        try {
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(successMessage)
            );

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    // Формирование текста для XPath
    private String xpathText(String text) {

        if (!text.contains("'")) {
            return "'" + text + "'";
        }

        if (!text.contains("\"")) {
            return "\"" + text + "\"";
        }

        String[] parts = text.split("'");

        StringBuilder result = new StringBuilder("concat(");

        for (int i = 0; i < parts.length; i++) {

            if (i > 0) {
                result.append(", \"'\", ");
            }

            result.append("'").append(parts[i]).append("'");
        }

        result.append(")");

        return result.toString();
    }
}
