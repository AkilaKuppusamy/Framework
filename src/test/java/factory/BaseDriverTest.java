package factory;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseDriverTest {

	WebDriver driver;
	WebDriverWait wait;

	BaseDriver baseDriver = new BaseDriver();


	@Test
	public void test_browser_chrome() {
		baseDriver.setDriver("chrome");
		driver = baseDriver.getDriver();
		driver.get("https://www.google.com");
		Assert.assertEquals("Google", driver.getTitle());
		driver.quit();
	}

	@Test
	public void test_browser_firefox() {
		baseDriver.setDriver("firefox");
		driver = baseDriver.getDriver();
		driver.get("https://www.google.com");
		Assert.assertEquals("Google", driver.getTitle());
		driver.quit();
	}

	@Test
	public void test_browser_ie() {
		baseDriver.setDriver("edge");
		driver = baseDriver.getDriver();

		try {
			driver.get("https://www.google.com");
		}
		catch(org.openqa.selenium.NoSuchSessionException e){
			Assert.assertTrue(true,e.toString());		
		}
	}

	@Test
	public void test_click(){
		baseDriver.setDriver("chrome");
		driver = baseDriver.getDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));   
		driver.get("https://www.google.com/");

		WebElement ele = driver.findElement(By.name("btnK"));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			baseDriver.click(ele);
			Assert.fail("Element is clickable");
		} catch (org.openqa.selenium.TimeoutException e) {
			Assert.assertTrue(true,e.toString());		}
		driver.quit();
	}

	@Test
	public void test_NotInteractableElement(){
		baseDriver.setDriver("chrome");
		driver = baseDriver.getDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");

		driver.findElement(By.name("q")).sendKeys("Google");
		driver.findElement(By.name("q")).sendKeys(Keys.TAB);
		WebElement ele = driver.findElement(By.name("btnK"));
		try {
			baseDriver.click(ele);
			Assert.fail("Element is interactable");	
		}catch (org.openqa.selenium.ElementNotInteractableException e) {
			System.out.println(e.toString());
			Assert.assertTrue(true);		}
		driver.quit();
	}

	//JavascriptExecutor executor = (JavascriptExecutor)driver;
	//executor.executeScript("arguments[0].click();", ele);
	//Assert.assertEquals(driver.getTitle(), "Google");


	@Test
	public void test_locateElementbyId() {
		baseDriver.setDriver("chrome");
		driver = baseDriver.getDriver();
		driver.manage().window().maximize();
		driver.get("http://leaftaps.com/opentaps");
		WebElement ele;
		try{
			ele = baseDriver.locateElement("id", "usernme");
			Assert.fail("Located incorrect element");
		}catch (RuntimeException e) {
			System.out.println(e.toString());
			Assert.assertTrue(true);		}
		driver.quit();
	}

	@Test
	public void test_locateElementbyName() {
		baseDriver.setDriver("chrome");
		driver = baseDriver.getDriver();
		driver.manage().window().maximize();
		driver.get("http://leaftaps.com/opentaps");

		try {
			baseDriver.locateElement("name", "username");
			Assert.fail("Located incorrect element");
		}catch(RuntimeException e) {
			System.out.println(e.toString());
			Assert.assertTrue(true);		}
		driver.quit();

	}
	
	@Test
	public void test_locateElementbyClass() {
		baseDriver.setDriver("chrome");
		driver = baseDriver.getDriver();
		driver.manage().window().maximize();
		driver.get("http://leaftaps.com/opentaps");

		try {
			baseDriver.locateElement("class", "decorativesubmit");
			Assert.fail("Located incorrect element");
		}catch(RuntimeException e) {
			System.out.println(e.toString());
			Assert.assertTrue(true);		}
		driver.quit();

	}
	
	@Test
	public void test_locateElementbyXpath() {
		baseDriver.setDriver("chrome");
		driver = baseDriver.getDriver();
		driver.manage().window().maximize();
		driver.get("http://leaftaps.com/opentaps");

		try {
			baseDriver.locateElement("xPath", "//label[text='Password']");
			Assert.fail("Located incorrect element");
		}catch(RuntimeException e) {
			System.out.println(e.toString());
			Assert.assertTrue(true);		}
		driver.quit();

	}
	
	@Test
	public void test_switchToAlert() {
	baseDriver.setDriver("chrome");
	driver = baseDriver.getDriver();
	driver.manage().window().maximize();
	driver.get("http://leaftaps.com/opentaps");

	try {
		baseDriver.switchToAlert();
		Assert.fail("Not handled Alert failure properly");
	}catch(WebDriverException e) {
		System.out.println(e.toString());
		Assert.assertTrue(true);		}
	driver.quit();

}
	
	
}
