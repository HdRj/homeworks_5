package pages;

import org.openqa.selenium.WebDriver;

public class Page {
    protected WebDriver driver;

    public Page(WebDriver driver){
        this.driver=driver;
    }

    public void maximize(){
        driver.manage().window().maximize();
    }

    public void refresh(){
        driver.navigate().refresh();
    }
}
