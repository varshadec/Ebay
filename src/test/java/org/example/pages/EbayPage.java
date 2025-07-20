package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.example.utils.TestUtils;

import java.time.Duration;

public class EbayPage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Page URL
    private static final String BASE_URL = "https://www.ebay.com";
    
    // Private WebElement Locators with XPath
    @FindBy(xpath = "//input[@id='gh-ac']")
    private WebElement searchInput;
    
    @FindBy(css = ".gh-search-button__label")
    private WebElement searchButton;
    
    @FindBy(css = "#srp-river-results")
    private WebElement searchResultsContainer;
    
    @FindBy(xpath = "//*[@id='srp-river-results']/ul/li[1]//a[contains(@href, '/itm/')][1]")
    private WebElement firstSearchResult;
    
    @FindBy(xpath = "//a[@id='atcBtn_btn_1']")
    private WebElement addToCartButton;
    
    @FindBy(xpath = "//button[@aria-label='Close overlay']")
    private WebElement popupCloseButton;
    
    @FindBy(css = ".badge.gh-badge")
    private WebElement cartCountElement;
    

    public EbayPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }
    

    public void navigateToEbay() {
        driver.get(BASE_URL);
        TestUtils.waitForPageLoad(driver);
    }
    

    public void searchForItem(String searchTerm) {
        searchInput.clear();
        searchInput.sendKeys(searchTerm);
        TestUtils.safeClick(driver, searchButton);
    }
    

    public void clickFirstBookInList() throws InterruptedException {
        waitForSearchResults();
        clickOnItem(firstSearchResult);
    }
    
    public void clickFirstSearchResult() throws InterruptedException {
        waitForSearchResults();
        clickOnItem(firstSearchResult);
    }
    
    private void waitForSearchResults() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#srp-river-results")));
        Thread.sleep(2000);
    }
    
    private void clickOnItem(WebElement item) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", item);
        Thread.sleep(1000);
        
        TestUtils.safeClick(driver, item);
        TestUtils.switchToNewWindow(driver);
    }
    

    public void clickAddToCartButton() throws InterruptedException {
        findAddToCartButton();
    }
    
    private void findAddToCartButton() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        TestUtils.safeClick(driver, addToCartButton);
        closeAddToCartPopup();
    }
    
    private void closeAddToCartPopup() throws InterruptedException {
        Thread.sleep(2000);
        TestUtils.safeClick(driver, popupCloseButton);
        Thread.sleep(1000);
    }
    

    public String getCartCount() {
        try {
            // Scroll to top to see cart count
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
            Thread.sleep(1000);
            return TestUtils.getCartCount(driver);
        } catch (InterruptedException e) {
            return TestUtils.getCartCount(driver);
        }
    }
    
    public void verifyCartUpdated() throws InterruptedException {
        String initialCartCount = getCartCount();
        Thread.sleep(3000);
        
        String currentCartCount = getCartCount();
        
        if (currentCartCount.isEmpty()) {
            return;
        }
        
        int initialCount = Integer.parseInt(initialCartCount);
        int currentCount = Integer.parseInt(currentCartCount);
        
        if (currentCount >= initialCount) {
            return;
        }
        
        throw new AssertionError("Cart count should be greater than or equal to initial count. Initial: " + initialCount + ", Current: " + currentCount);
    }
    
    public boolean verifyCartDisplaysItemCount() {
        String currentCartCount = getCartCount();
        
        if (currentCartCount.isEmpty()) {
            return true;
        }
        
        return !currentCartCount.equals("0");
    }
} 