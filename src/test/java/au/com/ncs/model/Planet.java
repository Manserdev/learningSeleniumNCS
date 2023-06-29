package au.com.ncs.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Planet {

    private WebElement currentPlanet;

    public Planet(WebElement planet){
        currentPlanet = planet;
    }
    public String getName(){
       return currentPlanet.findElement(By.className("name")).getText();
    }

    public void clickExplore(){
        currentPlanet.findElement(By.tagName("button")).click();
    }




}
