package tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import utils.Browsers;
import utils.EventHandler;

import java.io.File;

public abstract class BaseTest {

    private WebDriver wDriver;
    protected EventFiringWebDriver driver;

    public BaseTest(Browsers browser){
        switch (browser) {
            case Firefox:
                System.setProperty(
                        "webdriver.gecko.driver",
                        new File(BaseTest.class.getResource("/geckodriver.exe").getFile()).getPath());
                wDriver = new FirefoxDriver();
                break;
            case IE:
                System.setProperty(
                        "webdriver.ie.driver",
                        new File(BaseTest.class.getResource("/IEDriverServer.exe").getFile()).getPath());
                DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();

                //capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS,false);
                capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION,true);
                //capabilities.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR,true);
                //capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
                capabilities.setCapability("nativeEvents", false);
                capabilities.setCapability("unexpectedAlertBehaviour", "accept");
                capabilities.setCapability("ignoreProtectedModeSettings", true);
                capabilities.setCapability("disable-popup-blocking", true);
                capabilities.setCapability("enablePersistentHover", true);
                capabilities.setCapability("ignoreZoomSetting", true);

                wDriver = new InternetExplorerDriver(capabilities);
                break;
            case Chrome:
            default:
                System.setProperty(
                        "webdriver.chrome.driver",
                        new File(BaseTest.class.getResource("/chromedriver.exe").getFile()).getPath());
                wDriver  = new ChromeDriver();
        }

        driver = new EventFiringWebDriver(wDriver);

        driver.register(new EventHandler());

    }

    public abstract void  run();

    public void finish(){
        driver.quit();
    }

    public  void finish(int delay){
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }

}
