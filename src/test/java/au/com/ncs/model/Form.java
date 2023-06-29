package au.com.ncs.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Form {

    private WebDriver driver;

    public Form(WebDriver driver) {

        this.driver = driver;
    }

    public void enterName(String name) {

        driver.findElement(By.id("name")).sendKeys(name);
    }

    public void enterEmail(String email) {

        driver.findElement(By.id("email")).sendKeys(email);
    }

    public void selectState(String state) {

        driver.findElement(By.className("v-select__selections")).click();

        for (WebElement option : driver.findElements(By.cssSelector("[role=option]"))) {
            if (option.getText().equalsIgnoreCase(state)) {
                option.click();
                break;
            }

        }
    }

    public void clickAgree() {

        driver.findElement(By.cssSelector("[for=agree]")).click();
    }

    public void submit() {

        for (WebElement button : driver.findElements(By.cssSelector("[type=button]"))) {
            if (button.getText().equalsIgnoreCase("submit")) {
                button.click();
            }

        }
    }
}
