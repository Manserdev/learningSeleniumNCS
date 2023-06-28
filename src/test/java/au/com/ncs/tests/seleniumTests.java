package au.com.ncs.tests;


import au.com.ncs.model.Form;
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

        Form form = new Form(driver);
        form.enterName("Tim Manser");
        form.enterEmail("manserdev@protonmail.com");
        form.selectState("vic");
        form.clickAgree();
        form.submit();


        //Arrange
        driver.findElement(By.cssSelector("[aria-label=forms]")).click();
        assertEquals("Forms", driver.findElement(By.cssSelector("h1.mb-3")).getText());


        //Act
        driver.findElement(By.id("name")).sendKeys("Timothy Manser");
        driver.findElement(By.id("email")).sendKeys("manserdev@protonmail.com");
        driver.findElement(By.className("v-select__selections")).click();

        for (WebElement option : driver.findElements(By.cssSelector("[role=option]"))) {
            if (option.getText().equalsIgnoreCase("vic")) {
                option.click();
                break;
            }

        }

        driver.findElement(By.cssSelector("[for=agree]")).click();

        for (WebElement button : driver.findElements(By.cssSelector("[type=button]"))) {
            if (button.getText().equalsIgnoreCase("submit")) {
                button.click();
            }

        }


        //Assert
        By ByPopUpMessage = By.className("popup-message");

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(ByPopUpMessage));

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
