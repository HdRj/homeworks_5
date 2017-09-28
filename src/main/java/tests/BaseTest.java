package tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import utils.Browsers;

import java.io.File;

public abstract class BaseTest {

    protected WebDriver driver;

    public BaseTest(Browsers browser){
        switch (browser) {
            case Firefox:
                System.setProperty(
                        "webdriver.gecko.driver",
                        new File(BaseTest.class.getResource("/geckodriver.exe").getFile()).getPath());
                driver = new FirefoxDriver();
                break;
            case IE:
                System.setProperty(
                        "webdriver.ie.driver",
                        new File(BaseTest.class.getResource("/IEDriverServer.exe").getFile()).getPath());
                driver = new InternetExplorerDriver();
                break;
            case Chrome:
            default:
                System.setProperty(
                        "webdriver.chrome.driver",
                        new File(BaseTest.class.getResource("/chromedriver.exe").getFile()).getPath());
                driver = new ChromeDriver();
        }
    }

    public abstract void  run();

    public void finish(){
        driver.quit();
    }

    public  void finish(int delay){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }

}
