package nikos.selenium_testing;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

/**
 *
 * @author nikolis93
 */
public class Actions {

    public static void LogOut(WebDriver driver, String url) {        
        String html = driver.getPageSource();
        try {
            WebElement logout = driver.findElement(By.id("logout2"));
            System.out.println("logouttext: " + logout.getTagName());

            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            logout.click();
            System.out.println("logout is displayed");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("logout is not displayed");
        }

    }

    public static void LoginIn(WebDriver driver, String url) {
        driver.get(url);
        WebElement login = driver.findElement(By.id("login2"));
        login.click();

        WebElement signUpUserNamerevealed = driver.findElement(By.id("loginusername"));
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(2))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(ElementNotInteractableException.class);
        wait.until(d -> {
            signUpUserNamerevealed.sendKeys("Pomolos");
            return true;
        });
        WebElement signUpPassword = driver.findElement(By.id("loginpassword"));
        signUpPassword.sendKeys("nikolis");

        List<WebElement> signUpButton = driver.findElements(By.tagName("button"));
        for (WebElement button : signUpButton) {
            try {
                if (button.getText().equalsIgnoreCase("Log in")) {
                    button.click();

                    Alert alert = wait.until(ExpectedConditions.alertIsPresent());
                    String text = alert.getText();
                    if (text.contains("Wrong password")) {
                        System.out.println("Wrong password!");
                    } else if (text.contains("User does not exist.")) {
                        System.out.println("User does not exist!");
                    } else {
                        System.out.println("Login Failed!");
                    }
                }
            } catch (Exception e) {

            }
        }
    }

    public static void SignUp(WebDriver driver, String url) {//this function automates the sign up process
        driver.get(url);
        WebElement signUp = driver.findElement(By.id("signin2"));
        signUp.click();

        WebElement signUpUserNamerevealed = driver.findElement(By.id("sign-username"));
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(2))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(ElementNotInteractableException.class);
        wait.until(d -> {
            signUpUserNamerevealed.sendKeys("Pomolos2");
            return true;
        });

        WebElement signUpPassword = driver.findElement(By.id("sign-password"));
        signUpPassword.sendKeys("nikolis");

        List<WebElement> signUpButton = driver.findElements(By.tagName("button"));
        for (WebElement button : signUpButton) {
            try {
                if (button.getText().equalsIgnoreCase("Sign up")) {
                    button.click();

                    Alert alert = wait.until(ExpectedConditions.alertIsPresent());
                    String text = alert.getText();

                    if (text.contains("Sign up successful.")) {
                        System.out.println("The sign up was successfull!");
                    } else if (text.contains("This user already exist.")) {
                        System.out.println("Username already exists!");
                    } else {
                        System.out.println("Sign up failed");
                    }
                }
            } catch (Exception e) {

            }
        }
    }
}
