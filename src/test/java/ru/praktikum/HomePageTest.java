package ru.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.praktikum.pages.HomePage;

public class HomePageTest {

    private WebDriver driver;

    @Test
    public void checkFirstQuestionAnswer() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        HomePage homePage = new HomePage(driver);

        homePage.clickFirstQuestion();

        String actualAnswer = homePage.getFirstAnswerText();

        Assert.assertEquals(
                "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
                actualAnswer
        );
    }

    @Test
    public void checkSecondQuestionAnswer() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        HomePage homePage = new HomePage(driver);

        homePage.clickSecondQuestion();

        String actualAnswer = homePage.getSecondAnswerText();

        Assert.assertEquals(
                "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
                actualAnswer
        );
    }

    @Test
    public void checkThirdQuestionAnswer() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        HomePage homePage = new HomePage(driver);

        homePage.clickThirdQuestion();

        String actualAnswer = homePage.getThirdAnswerText();

        Assert.assertEquals(
                "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
                actualAnswer
        );
    }

    @Test
    public void checkFourthQuestionAnswer() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        HomePage homePage = new HomePage(driver);

        homePage.clickFourthQuestion();

        String actualAnswer = homePage.getFourthAnswerText();

        Assert.assertEquals(
                "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
                actualAnswer
        );
    }

    @Test
    public void checkFifthQuestionAnswer() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        HomePage homePage = new HomePage(driver);

        homePage.clickFifthQuestion();

        String actualAnswer = homePage.getFifthAnswerText();

        Assert.assertEquals(
                "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
                actualAnswer
        );
    }

    @Test
    public void checkSixthQuestionAnswer() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        HomePage homePage = new HomePage(driver);

        homePage.clickSixthQuestion();

        String actualAnswer = homePage.getSixthAnswerText();

        Assert.assertEquals(
                "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
                actualAnswer
        );
    }

    @Test
    public void checkSeventhQuestionAnswer() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        HomePage homePage = new HomePage(driver);

        homePage.clickSeventhQuestion();

        String actualAnswer = homePage.getSeventhAnswerText();

        Assert.assertEquals(
                "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
                actualAnswer
        );
    }

    @Test
    public void checkEighthQuestionAnswer() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        HomePage homePage = new HomePage(driver);

        homePage.clickEighthQuestion();

        String actualAnswer = homePage.getEighthAnswerText();

        Assert.assertEquals(
                "Да, обязательно. Всем самокатов! И Москве, и Московской области.",
                actualAnswer
        );
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
