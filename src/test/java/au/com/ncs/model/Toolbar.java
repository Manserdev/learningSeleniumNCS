package au.com.ncs.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Toolbar {


    private final WebDriver driver;

    public Toolbar(WebDriver driver) {

        this.driver = driver;

    }


    public void click() {

        driver.findElement(By.cssSelector("[aria-label=planets]")).click();
    }
}
