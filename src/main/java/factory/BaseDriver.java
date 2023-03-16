package factory;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import design.Browser;
import design.Element;
import utils.ExtentReporter;

@Component
public class BaseDriver extends ExtentReporter implements Browser, Element{

    @Autowired
	private static final ThreadLocal<RemoteWebDriver> remoteWebDriver = new ThreadLocal<RemoteWebDriver>();
	
    @Autowired
    private static final ThreadLocal<WebDriverWait> wait = new ThreadLocal<WebDriverWait>();

	public void setWait() {
		wait.set(new WebDriverWait(getDriver(), Duration.ofSeconds(30)));
	}

	public WebDriverWait getWait() {
		return wait.get();
	}
	
	public void setDriver(String browser) {
		switch (browser) {
		case "chrome":
		    ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			remoteWebDriver.set(new ChromeDriver(options));
			break;
		case "firefox":
			remoteWebDriver.set(new FirefoxDriver());
			break;
		case "ie":
			remoteWebDriver.set(new InternetExplorerDriver());
		default:
			break;
		}

	}
	
	public RemoteWebDriver getDriver() {
		return remoteWebDriver.get();
	}
	
	
	@Override
	public void click(WebElement ele) {
		int counter = 0;
		String text="";
		try {
			wait.get().until(ExpectedConditions.elementToBeClickable(ele));
			text = ele.getText();
			ele.click();
			reportStep("The Element "+text+" clicked", "pass"); 
		} catch (StaleElementReferenceException e) {
			if(counter++ < 3) {
				click(ele);
			}else {
				reportStep("The Element "+text+" could not be clicked", "fail");
				throw new RuntimeException();
			}
		} 
	}
	public void clickWithNoSnap(WebElement ele) {
		String text = "";
		try {
			text = ele.getText();
			
			getWait().until(ExpectedConditions.elementToBeClickable(ele));
			ele.click();
			reportStep("The Element with text: "+text+" clicked", "pass", false);
		} catch (StaleElementReferenceException e) {
			reportStep("The Element "+ele+" could not be clicked", "fail");
			throw new RuntimeException();
		}catch(Exception e) {
			System.err.println(e);
		}

	}

	@Override
	public void append(WebElement ele, String data) {
		ele.sendKeys(data);
	}

	@Override
	public void clear(WebElement ele) {
		try {
			ele.clear();
			reportStep("The field is cleared Successfully", "pass");
		} catch (ElementNotInteractableException e) {
			reportStep("The field is not Interactable", "fail");
			throw new RuntimeException();
		}
	}

	@Override
	public void clearAndType(WebElement ele, String data) {
		try {
			ele.clear();
			ele.sendKeys(data);
			reportStep("The Data :"+data+" entered Successfully", "pass");
		} catch (ElementNotInteractableException e) {
			reportStep("The Element "+ele+" is not Interactable", "fail");
			throw new RuntimeException();
		}

	}

	@Override
	public String getElementText(WebElement ele) {
		return ele.getText();
	}

	@Override
	public String getBackgroundColor(WebElement ele) {
		return ele.getCssValue("color");
	}

	@Override
	public String getTypedText(WebElement ele) {
		return ele.getAttribute("value");
	}

	@Override
	public void selectDropDownUsingText(WebElement ele, String value) {
		if(ele.getTagName().equalsIgnoreCase("select"))
			new Select(ele).selectByVisibleText(value);
		else {
			ele.click();
			ele.findElement(By.xpath("//../following::*[text()='"+value+"']")).click();
		}
	}

	@Override
	public void selectDropDownUsingIndex(WebElement ele, int index) {
		new Select(ele)
		.selectByIndex(index);
	}

	@Override
	public void selectDropDownUsingValue(WebElement ele, String value) {
		new Select(ele)
		.selectByValue(value);
	}

	@Override
	public boolean verifyExactText(WebElement ele, String expectedText) {
		try {
			if(ele.getText().equals(expectedText)) {
				reportStep("The expected text contains the actual "+expectedText,"pass");
				return true;
			}else {
				reportStep("The expected text doesn't contain the actual "+expectedText,"fail");
			}
		} catch (WebDriverException e) {
			System.out.println("Unknown exception occured while verifying the Text");
		} 

		return false;
	}

	@Override
	public boolean verifyPartialText(WebElement ele, String expectedText) {
		try {
			if(ele.getText().contains(expectedText)) {
				reportStep("The expected text contains the actual "+expectedText,"pass");
				return true;
			}else {
				reportStep("The expected text doesn't contain the actual "+expectedText,"fail");
			}
		} catch (WebDriverException e) {
			System.out.println("Unknown exception occured while verifying the Text");
		} 

		return false;
	}

	@Override
	public boolean verifyExactAttribute(WebElement ele, String attribute, String value) {
		try {
			if(ele.getAttribute(attribute).equals(value)) {
				reportStep("The expected attribute :"+attribute+" value contains the actual "+value,"pass");
				return true;
			}else {
				reportStep("The expected attribute :"+attribute+" value does not contains the actual "+value,"fail");
			}
		} catch (WebDriverException e) {
			System.out.println("Unknown exception occured while verifying the Attribute Text");
		}
		return false;
	}

	@Override
	public void verifyPartialAttribute(WebElement ele, String attribute, String value) {
		try {
			if(ele.getAttribute(attribute).contains(value)) {
				reportStep("The expected attribute :"+attribute+" value contains the actual "+value,"pass");
			}else {
				reportStep("The expected attribute :"+attribute+" value does not contains the actual "+value,"fail");
			}
		} catch (WebDriverException e) {
			System.out.println("Unknown exception occured while verifying the Attribute Text");
		}

	}

	@Override
	public boolean verifyDisplayed(WebElement ele) {
		try {
			if(ele.isDisplayed()) {
				reportStep("The element "+ele+" is visible","pass");
				return true;
			} else {
				reportStep("The element "+ele+" is not visible","fail");
			}
		} catch (WebDriverException e) {
			System.out.println("WebDriverException : "+e.getMessage());
		} 
		return false;

	}

	@Override
	public boolean verifyDisappeared(WebElement ele) {
		return false;

	}

	@Override
	public boolean verifyEnabled(WebElement ele) {
		try {
			if(ele.isEnabled()) {
				reportStep("The element "+ele+" is Enabled","pass");
				return true;
			} else {
				reportStep("The element "+ele+" is not Enabled","fail");
			}
		} catch (WebDriverException e) {
			System.out.println("WebDriverException : "+e.getMessage());
		}
		return false;
	}

	@Override
	public void verifySelected(WebElement ele) {
		try {
			if(ele.isSelected()) {
				reportStep("The element "+ele+" is selected","pass");
				//				return true;
			} else {
				reportStep("The element "+ele+" is not selected","fail");
			}
		} catch (WebDriverException e) {
			System.out.println("WebDriverException : "+e.getMessage());
		}
		//		return false;

	}

	
	public void startApp(String url) {
		 startApp("chrome", url);
	}

	
	public void startApp(String browser, String url) {
		try {
			setDriver(browser);
			setWait();
			getDriver().navigate().to(url);
			getDriver().manage().window().maximize();
			getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		} catch (Exception e) {
			reportStep("The Browser Could not be Launched. Hence Failed", "fail");
			throw new RuntimeException();
		} 
		
	}

	@Override
	public WebElement locateElement(String locatorType, String value) {
		try {
			switch(locatorType.toLowerCase()) {
			case "id": return getDriver().findElement(By.id(value));
			case "name": return getDriver().findElement(By.name(value));
			case "class": return getDriver().findElement(By.className(value));
			case "link": return getDriver().findElement(By.linkText(value));
			case "xpath": return getDriver().findElement(By.xpath(value));
			}
		} catch (NoSuchElementException e) {
			reportStep("The Element with locator:"+locatorType+" Not Found with value: "+value, "fail");
			throw new RuntimeException();
		}catch (Exception e) {
			reportStep("The Element with locator:"+locatorType+" Not Found with value: "+value, "fail");
		}
		return null;
	}

	@Override
	public WebElement locateElement(String value) {
		WebElement findElementById = getDriver().findElement(By.id(value));
		return findElementById;
	}

	@Override
	public List<WebElement> locateElements(String type, String value) {
		try {
			switch(type.toLowerCase()) {
			case "id": return getDriver().findElements(By.id(value));
			case "name": return getDriver().findElements(By.name(value));
			case "class": return getDriver().findElements(By.className(value));
			case "link": return getDriver().findElements(By.linkText(value));
			case "xpath": return getDriver().findElements(By.xpath(value));
			}
		} catch (NoSuchElementException e) {
			System.err.println("The Element with locator:"+type+" Not Found with value: "+value);
			throw new RuntimeException();
		}
		return null;
	}

	@Override
	public void switchToAlert() {
		getDriver().switchTo().alert();
	}

	@Override
	public void acceptAlert() {
		String text = "";		
		try {
			
			getWait().until(ExpectedConditions.alertIsPresent());
			Alert alert = getDriver().switchTo().alert();
			text = alert.getText();
			alert.accept();
			reportStep("The alert "+text+" is accepted.", "pass");
		} catch (NoAlertPresentException e) {
			reportStep("There is no alert present.", "fail");
		} catch (WebDriverException e) {
			System.out.println("WebDriverException : "+e.getMessage());
		}  

	}

	@Override
	public void dismissAlert() {
		String text = "";		
		try {
			Alert alert = getDriver().switchTo().alert();
			text = alert.getText();
			alert.dismiss();
			System.out.println("The alert "+text+" is accepted.");
		} catch (NoAlertPresentException e) {
			System.out.println("There is no alert present.");
		} catch (WebDriverException e) {
			System.out.println("WebDriverException : "+e.getMessage());
		}  


	}

	@Override
	public String getAlertText() {
		String text = "";		
		try {
			Alert alert = getDriver().switchTo().alert();
			text = alert.getText();
		} catch (NoAlertPresentException e) {
			System.out.println("There is no alert present.");
		} catch (WebDriverException e) {
			System.out.println("WebDriverException : "+e.getMessage());
		} 
		return text;
	}

	@Override
	public void typeAlert(String data) {
		getDriver().switchTo().alert().sendKeys(data);

	}

	@Override
	public void switchToWindow(int index) {
		try {
			Set<String> allWindows = getDriver().getWindowHandles();
			List<String> allhandles = new ArrayList<String>(allWindows);
			String exWindow = allhandles.get(index);
			getDriver().switchTo().window(exWindow);
			System.out.println("The Window With index: "+index+
					" switched successfully");
		} catch (NoSuchWindowException e) {
			System.err.println("The Window With index: "+index+
					" not found");	
		}
	}

	@Override
	public void switchToWindow(String title) {
		try {
			Set<String> allWindows = getDriver().getWindowHandles();
			for (String eachWindow : allWindows) {
				getDriver().switchTo().window(eachWindow);
				if (getDriver().getTitle().equals(title)) {
					break;
				}
			}
			System.out.println("The Window With Title: "+title+
					"is switched ");
		} catch (NoSuchWindowException e) {
			System.err.println("The Window With Title: "+title+
					" not found");
		} finally {
			takeSnap();
		}
	}

	@Override
	public void switchToFrame(int index) {
		getDriver().switchTo().frame(index);

	}

	@Override
	public void switchToFrame(WebElement ele) {
		getDriver().switchTo().frame(ele);

	}

	@Override
	public void switchToFrame(String idOrName) {
		getDriver().switchTo().frame(idOrName);

	}

	@Override
	public void defaultContent() {
		getDriver().switchTo().defaultContent();

	}

	@Override
	public boolean verifyUrl(String url) {
		if (getDriver().getCurrentUrl().equals(url)) {
			System.out.println("The url: "+url+" matched successfully");
			return true;
		} else {
			System.out.println("The url: "+url+" not matched");
		}
		return false;
	}

	@Override
	public boolean verifyTitle(String title) {
		if (getDriver().getTitle().equals(title)) {
			System.out.println("Page title: "+title+" matched successfully");
			return true;
		} else {
			System.out.println("Page url: "+title+" not matched");
		}
		return false;
	}


	public long takeSnap(){
		long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L; 
		try {
			FileUtils.copyFile(getDriver().getScreenshotAs(OutputType.FILE) , new File("./reports/images/"+number+".jpg"));
		} catch (WebDriverException e) {
			System.out.println("The browser has been closed.");
		} catch (IOException e) {
			System.out.println("The snapshot could not be taken");
		}
		return number;
	}
	@Override
	public void close() {
		getDriver().close();

	}

	@Override
	public void quit() {
		getDriver().quit();

	}

}
