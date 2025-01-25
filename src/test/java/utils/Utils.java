package utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Utils {
    public static void selectOptionByName(WebElement webElement, String name) {
        Select selectElement = new Select(webElement);
        selectElement.selectByValue(name);

    }
}