package au.com.ncs.tests;


import au.com.ncs.model.Form;
import au.com.ncs.model.Planet;
import au.com.ncs.model.PlanetPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class seleniumTests {

    public WebDriver driver = getWebDriver();

    @BeforeEach
    public void setup() {

        driver.get("https://d18u5zoaatmpxx.cloudfront.net/#/");
        driver.manage().window().maximize();

    }


    @Test
    public void enterKeysIntoForenameTest() {

        driver.findElement(By.id("forename")).sendKeys("1234");
    }

    @Test
    public void clickSubmitForenameTest() {

        driver.findElement(By.id("submit")).click();
    }

    @Test
    public void checkWebPlaygroundHeaderTest() {

        WebElement header = driver.findElement(By.tagName("h1"));
        assertEquals("Web Playground", header.getText());
    }

    @Test
    public void clickMeDownTest() {
        //Arrange

        //Act
        WebElement buttonElement = driver.findElement(By.cssSelector("div > a.anibtn"));
        buttonElement.click();

        //Assert
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.textToBe(By.cssSelector("div > a.transition-y"), "CLICK ME UP!"));

    }

    @Test
    public void ClickMeUpTest() {

        //Arrange
        WebElement buttonElement = driver.findElement(By.cssSelector("div > a.anibtn"));
        buttonElement.click();
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.textToBe(By.cssSelector("div > a.transition-y"), "CLICK ME UP!"));

        //Act
        buttonElement.click();

        //Assert
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.textToBe(By.cssSelector("div > a.anibtn"), "CLICK ME DOWN!"));

    }


    @Test
    public void FillInFormTest() {

        //Arrange
        driver.findElement(By.cssSelector("[aria-label=forms]")).click();
        assertEquals("Forms", driver.findElement(By.cssSelector("h1.mb-3")).getText());

        //Act
        Form form = new Form(driver);
        form.enterName("Tim Manser");
        form.enterEmail("manserdev@protonmail.com");
        form.selectState("vic");
        form.clickAgree();
        form.submit();

        //Assert
        By ByPopUpMessage = By.className("popup-message");

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(ByPopUpMessage));

    }

    @Test
    public void exploreEarth() throws InterruptedException {
        driver.findElement(By.cssSelector("[aria-label=planets]")).click();
        PlanetPage planetPage = new PlanetPage(driver);
        planetPage.clickPlanet("earth");
        Thread.sleep(2000);
        }

    @AfterEach
    public void clean() {

        driver.quit();

    }

    public WebDriver getWebDriver() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        return driver;
    }
}
