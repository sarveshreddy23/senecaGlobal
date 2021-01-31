package com.senecaGlobal;

import java.util.concurrent.TimeUnit;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class App{

        @Test
        public void scenario1(){
            System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/src/test/resource/drivers/chromedriver");
            WebDriver driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            String baseUrl = "https://www.google.com/";
            driver.get(baseUrl);
            driver.findElement(By.xpath("(//*[contains(@value,\"I'm Feeling Lucky\")])[last()]")).click();
            String doodleTitle = driver.findElement(By.xpath("//div[@class=\"card\"]/div[@class=\"title\"]/a")).getText();
            System.out.println(doodleTitle);
            driver.quit();

        }
        @Test
        public void scenario2() throws InterruptedException {
            System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/src/test/resource/drivers/chromedriver");
            WebDriver driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            String baseUrl = "https://www.facebook.com/";
            driver.get(baseUrl);
            driver.findElement(By.xpath("//a[@id='u_0_2']")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//input[@name = \"firstname\"]")).sendKeys("Sarvesh");
            driver.findElement(By.xpath("//input[@name = \"lastname\"]")).sendKeys("Reddy");
            driver.findElement(By.xpath("//input[@name = \"reg_email__\"]")).sendKeys("Sarveshreddy@rocketmail.com");
            driver.findElement(By.xpath("//input[@name = \"reg_passwd__\"]")).sendKeys("Password");
            Select day = new Select(driver.findElement(By.id("day")));
            Select month = new Select(driver.findElement(By.id("month")));
            Select year = new Select(driver.findElement(By.id("year")));

            day.selectByVisibleText("10");
            month.selectByVisibleText("Jun");
            year.selectByVisibleText("1990");

            driver.findElement(By.xpath("//label[contains(text(),'Male')]/../input")).click();

            driver.quit();
        }


        @Test
        public void scenario3(){
            RestAssured.useRelaxedHTTPSValidation();
            Response response;
            String requestBody = "{“employee_name”:”Sarvesh”,”employee_salary”:2000,”employee_age”:30}";
            String baseUrl ="http://dummy.restapiexample.com/api/v1/create";

            response = given().baseUri(baseUrl)
                    .headers("Content-Type","text/plain",
                            "Connection","keep-alive",
                            "Accept","*/*")
                    .post();
            System.out.println(response.getBody().prettyPrint());
            Assert.assertTrue("Verifying Status Code: ", response.getStatusCode()==200);


        }

}