package ru.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By[] questions = new By[8];
    private final By[] answers = new By[8];

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

        for (int i = 0; i < questions.length; i++) {
            questions[i] = By.id("accordion__heading-" + i);
            answers[i] = By.id("accordion__panel-" + i);
        }
    }

    public void clickQuestion(int questionNumber) {
        clickQuestion(questions[questionNumber]);
    }

    public String getAnswerText(int questionNumber) {
        return getAnswerText(answers[questionNumber]);
    }

    // Верхняя кнопка «Заказать»
    public void clickTopOrderButton() {
        clickOrderButton(topOrderButton);
    }

    // Нижняя кнопка «Заказать»
    public void clickBottomOrderButton() {
        clickOrderButton(bottomOrderButton);
    }

    private void clickQuestion(By question) {
        clickWithJavaScript(question);
    }

    private void clickOrderButton(By buttonLocator) {
        clickWithJavaScript(buttonLocator);
    }

    private void clickWithJavaScript(By locator) {
        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(locator)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'}); arguments[0].click();",
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
