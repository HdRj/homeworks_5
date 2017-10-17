package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;
import utils.Browsers;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HomeWork5 {

    private BaseTest baseTest;
    RemoteWebDriver rwDriver;
    WebDriver driver;

    private String firstName="Джон";
    private String lastName="Доу";
    private String address="Європейська, 10";
    private String postCode="36000";
    private String city="Полтава";



    @Test
    public void run1(){
        MainPage mainPage=new MainPage(driver);
        driver.get(mainPage.getAddressPage());
    }

    @Test(dependsOnMethods = "run1")
    public void run2(){
        //2
        driver.manage().window().maximize();
        MainPage mainPage=new MainPage(driver);
        //driver.get(mainPage.getAddressPage());
        mainPage.clickAllProd();
        //3
        AllProductPage allProductPage=new AllProductPage(driver);

        String nameProduct =allProductPage.randomProd();
        Reporter.log("Selected: "+nameProduct);
        //4
        TestProductPage productPage =new TestProductPage(driver);
        double priceProduct=productPage.getPrice();
        Reporter.log("Price of product: "+priceProduct);
        int countProduct=productPage.getCount();
        Reporter.log("Total: "+countProduct);
        productPage.clickBasket();
        //5
        productPage.clickToOrder();
        //6
        BasketPage basketPage=new BasketPage(driver);
        basketPage.info();

        SoftAssert softAssert=new SoftAssert();
        softAssert.assertTrue(basketPage.checkCount(),"The count of product is incorrect in basket");
        softAssert.assertTrue(basketPage.checkName(nameProduct),"The name of product is incorrect in basket");
        softAssert.assertTrue(basketPage.checkPrice(priceProduct),"The price of product is incorrest in basket ");

        //7
        basketPage.clickButtonOrder();

        //8
        OrderPage orderPage=new OrderPage(driver);
        orderPage.fillFirstName(this.firstName);
        orderPage.fiilLastName(this.lastName);
        orderPage.fillRandomEmail();
        orderPage.clickButton();

        //9
        orderPage.fillAddress(this.address);
        orderPage.fillPostCode(this.postCode);
        orderPage.fillCity(this.city);
        orderPage.clickButton2();

        //10
        orderPage.clickButton3();

        //11
        orderPage.clickRadio();
        orderPage.clickCheckBox();
        orderPage.clickButton4();

        //12
        FinalOrderPage finalOrderPage=new FinalOrderPage(driver);
        finalOrderPage.info();

        softAssert.assertTrue(finalOrderPage.checkMessage(),"The message is incorrect in final page");
        softAssert.assertTrue(finalOrderPage.checkCount(),"The count of product is incorrect in final page");
        softAssert.assertTrue(finalOrderPage.checkName(nameProduct),"The name of product is incorrect in final page");
        softAssert.assertTrue(finalOrderPage.checkPrice(priceProduct),"The price of product is incorrest in final page");


        //13
        driver.get(mainPage.getAddressPage());
        mainPage.clickAllProd();
        allProductPage=new AllProductPage(driver,nameProduct);
        allProductPage.clickTestProduct();
        productPage =new TestProductPage(driver);
        int newCountProduct=productPage.getCount();
        Reporter.log("New count: "+newCountProduct);
        softAssert.assertTrue(countProduct>newCountProduct,"Incorrect quantity");
        softAssert.assertAll();



    }




    @Parameters({"nameBrowser","mobile"})
    @BeforeTest
    public void setDriver(Browsers browser,boolean mobile) throws MalformedURLException {
        if(!mobile) {
            DesiredCapabilities capabilities=DesiredCapabilities.chrome();

            switch (browser) {
                case Firefox:
                    System.setProperty(
                            "webdriver.gecko.driver",
                            new File(BaseTest.class.getResource("/geckodriver.exe").getFile()).getPath());
                    capabilities=DesiredCapabilities.firefox();
                    driver=new FirefoxDriver(capabilities);
                    break;
                case IE:
                    System.setProperty(
                            "webdriver.ie.driver",
                            new File(BaseTest.class.getResource("/IEDriverServer.exe").getFile()).getPath());
                    capabilities = DesiredCapabilities.internetExplorer();

                    //capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS,false);
                    capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
                    //capabilities.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR,true);
                    capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
                    capabilities.setCapability("nativeEvents", false);
                    //capabilities.setCapability("unexpectedAlertBehaviour", "accept");
                    capabilities.setCapability("ignoreProtectedModeSettings", true);
                    capabilities.setCapability("disable-popup-blocking", true);
                    capabilities.setCapability("enablePersistentHover", true);
                    capabilities.setCapability("ignoreZoomSetting", true);
                    driver = new InternetExplorerDriver(capabilities);
                    break;
                case Chrome:
                default:
                    System.setProperty(
                            "webdriver.chrome.driver",
                            new File(BaseTest.class.getResource("/chromedriver.exe").getFile()).getPath());
                    driver = new ChromeDriver(capabilities);
            }


        } else{
            if(browser!=Browsers.Chrome){
                Reporter.log("Мобільна версія не налаштована для вибраного браузера, " +
                        "буде використовуватися Chrome");
            }
            Map<String, String> mobileEmulation = new HashMap<String, String>();
            mobileEmulation.put("deviceName", "Nexus 5");

            Map<String, Object> chromeOptions = new HashMap<String, Object>();
            chromeOptions.put("mobileEmulation", mobileEmulation);
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
            driver = new ChromeDriver(capabilities);
        }
    }



    public void setRemoteDriver(Browsers browser,boolean mobile) throws MalformedURLException {
        if(!mobile) {
            DesiredCapabilities capabilities=DesiredCapabilities.chrome();

            switch (browser) {
                case Firefox:
                    System.setProperty(
                            "webdriver.gecko.driver",
                            new File(BaseTest.class.getResource("/geckodriver.exe").getFile()).getPath());
                    capabilities=DesiredCapabilities.firefox();
                    break;
                case IE:
                    System.setProperty(
                            "webdriver.ie.driver",
                            new File(BaseTest.class.getResource("/IEDriverServer.exe").getFile()).getPath());
                    capabilities = DesiredCapabilities.internetExplorer();

                    //capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS,false);
                    capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
                    //capabilities.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR,true);
                    //capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
                    capabilities.setCapability("nativeEvents", false);
                    capabilities.setCapability("unexpectedAlertBehaviour", "accept");
                    capabilities.setCapability("ignoreProtectedModeSettings", true);
                    capabilities.setCapability("disable-popup-blocking", true);
                    capabilities.setCapability("enablePersistentHover", true);
                    capabilities.setCapability("ignoreZoomSetting", true);

                    break;
                case Chrome:
                default:
                    System.setProperty(
                            "webdriver.chrome.driver",
                            new File(BaseTest.class.getResource("/chromedriver.exe").getFile()).getPath());
            }

            rwDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
        } else{
            if(browser!=Browsers.Chrome){
                Reporter.log("Мобільна версія не налаштована для вибраного браузера, " +
                        "буде використовуватися Chrome");
            }
            Map<String, String> mobileEmulation = new HashMap<String, String>();
            mobileEmulation.put("deviceName", "Nexus 5");

            Map<String, Object> chromeOptions = new HashMap<String, Object>();
            chromeOptions.put("mobileEmulation", mobileEmulation);
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
            rwDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
        }
    }

    @Parameters({"finishDelay"})
    @AfterTest
    public void finish(int delay){

        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }




}
