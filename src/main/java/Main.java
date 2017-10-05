import tests.BaseTest;
import tests.HomeWork2Task1;
import tests.HomeWork2Task2;
import tests.HomeWork3;
import utils.Browsers;

public class Main {

    public static void main(String [] args){
        //Homework 2 part 1
        /*
        BaseTest test1 =new HomeWork2Task1(Browsers.Chrome);
        test1.run();
        test1.finish(5000);
        */
        //Homework 2 part 2
        /*
        BaseTest test2=new HomeWork2Task2(Browsers.Chrome);
        test2.run();
        test2.finish(5000);
        */

        //Homeweork 3
        BaseTest test3=new HomeWork3(Browsers.Chrome);
        test3.run();
        test3.finish(10000);




    }
}
