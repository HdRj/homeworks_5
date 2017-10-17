package tests;

import org.junit.Assert;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.*;
import utils.Browsers;

public class HomeWork4  /*extends BaseTest*/ {

    private String nameProduct;
    private int countProduct;
    private double priceProduct;

    private BaseTest baseTest;
    EventFiringWebDriver driver;

    /*public HomeWork4(Browsers browser) {
        super(browser);
    }*/


    public void run() {
        /*this.run1();
        this.run2();*/
    }


    @Test(dataProvider = "getLogin")
    public void run1(String email,String password){
        //1
        LoginPage loginPage=new LoginPage(driver, email, password);

        loginPage.open();
        loginPage.maximize();
        loginPage.fillEmailInput();
        loginPage.fillPasswordInput();
        loginPage.clickLoginButton();
        //2
        DashboardPage dashboardPage =new DashboardPage(driver);
        dashboardPage.clickProducts();
        //3
        ProductsPage productsPage=new ProductsPage(driver);
        productsPage.clickNewProduct();

        //4
        AddProductPage addProductPage=new AddProductPage(driver);
        this.nameProduct=addProductPage.fillProdName();
        this.countProduct=addProductPage.fillProdCount();
        this.priceProduct=addProductPage.fillProgPrice();

        //5
        addProductPage.onSwitch();

        //6
        addProductPage.clickSave();

        //
        System.out.println(nameProduct);
        System.out.println(countProduct);
        System.out.println(priceProduct);
    }


    @Test(dependsOnMethods = "run1")
    public void run2(){
        //PartB.1
        MainPage mainPage=new MainPage(driver);
        mainPage.open();
        //PartB.2
        mainPage.clickAllProd();
        //PartB.3
        AllProductPage allProductPage=new AllProductPage(driver,nameProduct);
        Assert.assertTrue("The product is missing",allProductPage.clickTestProduct());

        TestProductPage testProductPage=new TestProductPage(driver,nameProduct,countProduct,priceProduct);

        SoftAssert softlyAssert=new SoftAssert();
        softlyAssert.assertTrue(testProductPage.checkName(),"The name of product is incorrect");
        softlyAssert.assertTrue(testProductPage.checkCount(),"The count of product is incorrect");
        softlyAssert.assertTrue(testProductPage.checkPrice(),"The price of product is incorrect");
        softlyAssert.assertAll();

    }

    @Parameters({"nameBrowser"})
    @BeforeTest
    public void setDriver(Browsers browser){
        baseTest=new HomeWork3(browser);
        driver=baseTest.driver;
    }

    @Parameters({"finishDelay"})
    @AfterTest
    public void finish(int delay){
        baseTest.finish(delay);
    }

    @DataProvider
    public Object [][] getLogin(){
        return new String [][]{
                {"webinar.test@gmail.com","Xcg7299bnSmMuRLp9ITw"}
        };
    }
}
