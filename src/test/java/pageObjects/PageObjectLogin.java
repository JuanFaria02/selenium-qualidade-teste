package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageObjectLogin {
    @FindBy(xpath = "//*[@id=\"user\"]")
    private WebElement loginInput;

    @FindBy(xpath = "//*[@id=\"password\"]")
    private WebElement passwordInput;

    @FindBy(xpath = "//*[@id=\"btn-login\"]")
    private WebElement buttonLogin;

    public void login(String username, String password) {
        loginInput.sendKeys(username);
        passwordInput.sendKeys(password);
        buttonLogin.click();
    }

}
