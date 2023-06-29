package au.com.ncs.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PlanetPage {

    private WebDriver webDriver;

    public PlanetPage(WebDriver driver){
        webDriver = driver;
    }

    public List<WebElement> getAllPlanets(){
        return webDriver.findElements(By.className("planet"));
    }

    public void clickPlanet(String planet){
        for (WebElement webElement : getAllPlanets()){
            Planet currentPlanet = new Planet(webElement);
            if (currentPlanet.getName().equalsIgnoreCase(planet)) {
                currentPlanet.clickExplore();
            }
        }
    }


}
