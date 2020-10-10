import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.stream.Collectors;

public class SeleniumaWebAutomation
{
    private static final int MAX_PRICE = 30000;

    public static void main(String[] args) throws InterruptedException
    {
        WebDriver webDriver = openPaytmAndMaximizeWindow();

        openMobilesSection(webDriver);

        Actions actions = new Actions(webDriver);

        hoverToDisplaySortOptions(webDriver, actions);
        clickOnSortByNewArrivals(webDriver, actions);
        setMaxPriceFilter(webDriver);
        printFirstFiveMobilePhones(webDriver);
    }

    private static void printFirstFiveMobilePhones(WebDriver webDriver)
    {
        List<WebElement> mobilePhones = webDriver.findElements(By.className("UGUy"));
        System.out.println(mobilePhones.stream().limit(5).map(WebElement::getText).collect(Collectors.toList()));
    }

    private static void setMaxPriceFilter(WebDriver webDriver) throws InterruptedException
    {
        webDriver.findElement(By.cssSelector("input[placeholder='Max']")).click();
        ((JavascriptExecutor) webDriver).executeScript("document.querySelector(\"input[placeholder='Max']\").value = " + MAX_PRICE);
        Thread.sleep(2000);
    }

    private static void clickOnSortByNewArrivals(WebDriver webDriver, Actions actions)
    {
        WebElement optionToSortByNew = webDriver.findElements(By.className("_3vL1")).get(0);
        actions.moveToElement(optionToSortByNew).build().perform();
        optionToSortByNew.click();
    }

    private static void hoverToDisplaySortOptions(WebDriver webDriver, Actions actions) throws InterruptedException
    {
        actions.moveToElement(webDriver.findElement(By.className("_32-f"))).build().perform();
        Thread.sleep(2000);
    }

    private static void openMobilesSection(WebDriver webDriver)
    {
        webDriver.findElement(By.cssSelector("a[title='Mobiles']")).click();
    }

    private static WebDriver openPaytmAndMaximizeWindow()
    {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        WebDriver webDriver = new ChromeDriver();

        webDriver.get("http://www.paytm.com");
        webDriver.manage().window().maximize();

        return webDriver;
    }
}
