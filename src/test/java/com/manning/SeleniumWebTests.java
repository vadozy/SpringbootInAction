package com.manning;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by vadim on 8/14/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=ReadingListApplication.class)
@WebIntegrationTest(randomPort=true) // Starts embedded server on a random port
public class SeleniumWebTests {

    private static ChromeDriver browser;

    @Value("${local.server.port}")
    private int port;

    @BeforeClass
    public static void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "/Users/vadim/IdeaProjects/WebDriver/chromedriver");

        browser = new ChromeDriver();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void emptyTest() {

    }

    @Test
    public void addBookToEmptyList() {
        String baseUrl = "http://localhost:" + port;
        browser.get(baseUrl);
        // Now the server redirects the browser to the login page
        String currentUrl = browser.getCurrentUrl();
        assertEquals(baseUrl +"/login", currentUrl);

        // Now log in
        browser.findElementByName("username").sendKeys("vadim");
        browser.findElementByName("password").sendKeys("password");
        browser.findElementByTagName("form").submit();

        currentUrl = browser.getCurrentUrl();
        assertEquals(baseUrl +"/readingList/vadozy", currentUrl);
        // Yes we have succesfully logged in

        assertEquals("You have no books in your book list",
                browser.findElementByTagName("div").getText());

        browser.findElementByName("title").sendKeys("BOOK TITLE");
        browser.findElementByName("author").sendKeys("BOOK AUTHOR");
        browser.findElementByName("isbn").sendKeys("1234567890");
        browser.findElementByName("description").sendKeys("DESCRIPTION");
        browser.findElementByTagName("form").submit();

        WebElement dl =
                browser.findElementByCssSelector("dt.bookHeadline");
        assertEquals("BOOK TITLE by BOOK AUTHOR (ISBN: 1234567890)",
                dl.getText());
        WebElement dt =
                browser.findElementByCssSelector("dd.bookDescription");
        assertEquals("DESCRIPTION", dt.getText());
    }

    @AfterClass
    public static void closeBrowser() {
        browser.quit();
    }

}
