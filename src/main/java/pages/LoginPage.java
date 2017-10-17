package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends Page {


    private By emailInput = By.id("email");
    private By passwordInput = By.id("passwd");
    private By loginBtn = By.name("submitLogin");
    private String email;// = "webinar.test@gmail.com";
    private String password;// = "Xcg7299bnSmMuRLp9ITw";
    private String addressPage="http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/";

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public LoginPage(WebDriver driver, String email, String password){
        super(driver);
        this.email=email;
        this.password=password;
    }

    public void open(){
        driver.get(addressPage);
    }

    public void fillEmailInput(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions. elementToBeClickable(loginBtn));
        driver.findElement(emailInput).sendKeys(email);
    }

    public void fillPasswordInput(){
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginBtn).click();
    }

}
