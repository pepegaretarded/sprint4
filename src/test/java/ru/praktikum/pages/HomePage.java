package ru.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By firstQuestion = By.id("accordion__heading-0");
    private final By firstAnswer = By.id("accordion__panel-0");

    private final By secondQuestion = By.id("accordion__heading-1");
    private final By secondAnswer = By.id("accordion__panel-1");

    private final By thirdQuestion = By.id("accordion__heading-2");
    private final By thirdAnswer = By.id("accordion__panel-2");

    private final By fourthQuestion = By.id("accordion__heading-3");
    private final By fourthAnswer = By.id("accordion__panel-3");

    private final By fifthQuestion = By.id("accordion__heading-4");
    private final By fifthAnswer = By.id("accordion__panel-4");

    private final By sixthQuestion = By.id("accordion__heading-5");
    private final By sixthAnswer = By.id("accordion__panel-5");

    private final By seventhQuestion = By.id("accordion__heading-6");
    private final By seventhAnswer = By.id("accordion__panel-6");

    private final By eighthQuestion = By.id("accordion__heading-7");
    private final By eighthAnswer = By.id("accordion__panel-7");

    // Верхняя кнопка «Заказать»
    private final By topOrderButton = By.xpath(
            "(//button[normalize-space(.)='Заказать'])[1]"
    );

    // Нижняя кнопка «Заказать»
    private final By bottomOrderButton = By.xpath(
            "(//button[normalize-space(.)='Заказать'])[last()]"
    );

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickFirstQuestion() {
        clickQuestion(firstQuestion);
    }

    public String getFirstAnswerText() {
        return getAnswerText(firstAnswer);
    }

    public void clickSecondQuestion() {
        clickQuestion(secondQuestion);
    }

    public String getSecondAnswerText() {
        return getAnswerText(secondAnswer);
    }

    public void clickThirdQuestion() {
        clickQuestion(thirdQuestion);
    }

    public String getThirdAnswerText() {
        return getAnswerText(thirdAnswer);
    }

    public void clickFourthQuestion() {
        clickQuestion(fourthQuestion);
    }

    public String getFourthAnswerText() {
        return getAnswerText(fourthAnswer);
    }

    public void clickFifthQuestion() {
        clickQuestion(fifthQuestion);
    }

    public String getFifthAnswerText() {
        return getAnswerText(fifthAnswer);
    }

    public void clickSixthQuestion() {
        clickQuestion(sixthQuestion);
    }

    public String getSixthAnswerText() {
        return getAnswerText(sixthAnswer);
    }

    public void clickSeventhQuestion() {
        clickQuestion(seventhQuestion);
    }

    public String getSeventhAnswerText() {
        return getAnswerText(seventhAnswer);
    }

    public void clickEighthQuestion() {
        clickQuestion(eighthQuestion);
    }

    public String getEighthAnswerText() {
        return getAnswerText(eighthAnswer);
    }

    // Верхняя кнопка «Заказать»
    public void clickTopOrderButton() {
        WebElement button = driver.findElement(topOrderButton);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                button
        );
    }

    // Нижняя кнопка «Заказать»
    public void clickBottomOrderButton() {
        WebElement button = driver.findElement(bottomOrderButton);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                button
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                button
        );
    }

    // Клик по вопросу FAQ
    private void clickQuestion(By question) {
        WebElement element = driver.findElement(question);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                element
        );
    }

    // Получение текста ответа с ожиданием его появления
    private String getAnswerText(By answer) {
        return wait.until(driver -> {
            String text = driver.findElement(answer).getText();

            if (text != null && !text.trim().isEmpty()) {
                return text;
            }

            return null;
        });
    }
}