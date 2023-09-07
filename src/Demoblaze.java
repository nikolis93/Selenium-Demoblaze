package nikos.selenium_testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author nikolis93
 */
public class Demoblaze {

    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\30690\\Desktop\\selenium apps\\geckodriver-v0.33.0-win64\\geckodriver.exe");

        WebDriver driver = new FirefoxDriver();

        //Actions.SignUp(driver, "https://www.demoblaze.com/");
        Actions.LoginIn(driver, "https://www.demoblaze.com/");
       // Actions.LogOut(driver, "https://www.demoblaze.com/"); 
    }

}
