package org.example.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestUtils {
    
    /**
     * Wait for element to be clickable with custom timeout
     */
    public static WebElement waitForElementClickable(WebDriver driver, By locator, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    /**
     * Wait for element to be visible with custom timeout
     */
    public static WebElement waitForElementVisible(WebDriver driver, By locator, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    /**
     * Safe click with retry mechanism
     */
    public static void safeClick(WebDriver driver, WebElement element) {
        try {
            // Scroll element into view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(500); // Small pause after scroll
            
            // Try regular click first
            element.click();
        } catch (Exception e) {
            try {
                // If regular click fails, try JavaScript click
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            } catch (Exception jsException) {
                throw new RuntimeException("Failed to click element: " + jsException.getMessage());
            }
        }
    }
    
    /**
     * Switch to new window/tab if opened
     */
    public static void switchToNewWindow(WebDriver driver) {
        String originalWindow = driver.getWindowHandle();
        System.out.println("Original window handle: " + originalWindow);
        
        // Wait for new window to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            System.out.println("New window detected, switching...");
            
            // Switch to the new window
            for (String windowHandle : driver.getWindowHandles()) {
                if (!windowHandle.equals(originalWindow)) {
                    driver.switchTo().window(windowHandle);
                    System.out.println("Switched to new window: " + windowHandle);
                    
                    // Wait for the new page to load
                    wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                            .executeScript("return document.readyState").equals("complete"));
                    System.out.println("New window page loaded successfully");
                    return;
                }
            }
        } catch (Exception e) {
            System.out.println("No new window opened within timeout: " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Get cart count safely
     */
    public static String getCartCount(WebDriver driver) {
        try {
            // Try the new cart count selector first
            WebElement cartElement = driver.findElement(By.cssSelector(".badge.gh-badge"));
            return cartElement.getText().trim();
        } catch (Exception e) {
            try {
                // Fallback to old selector
                WebElement cartElement = driver.findElement(By.id("gh-cart-n"));
                return cartElement.getText().trim();
            } catch (Exception e2) {
                return "0";
            }
        }
    }
    
    /**
     * Wait for page to load completely
     */
    public static void waitForPageLoad(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }
    
    /**
     * Take screenshot on failure (placeholder for future implementation)
     */
    public static void takeScreenshot(WebDriver driver, String testName) {
        // This can be implemented to save screenshots on test failure
        System.out.println("Screenshot functionality can be added here for test: " + testName);
    }
} 