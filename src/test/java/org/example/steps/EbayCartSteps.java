package org.example.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.EbayPage;

import java.time.Duration;

public class EbayCartSteps {
    
    private WebDriver driver;
    private EbayPage ebayPage;
    
    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        ebayPage = new EbayPage(driver);
    }
    
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    @Given("User navigate to ebay website")
    public void user_navigate_to_ebay_website() {
        ebayPage.navigateToEbay();
    }
    
    @And("User serach for Book in search")
    public void user_search_for_book_in_search() {
        ebayPage.searchForItem("book");
    }
    
    @And("Select search button")
    public void select_search_button() {
        // Search button is already clicked in the search method
    }
    
    @And("Click on first book")
    public void click_on_first_book() throws InterruptedException {
        ebayPage.clickFirstBookInList();
    }
    
    @And("Click on Add to cart")
    public void click_on_add_to_cart() throws InterruptedException {
        ebayPage.clickAddToCartButton();
    }
    
    @Then("User should see successfully added to cart with cart count")
    public void user_should_see_successfully_added_to_cart_with_cart_count() throws InterruptedException {
        ebayPage.verifyCartUpdated();
    }
} 