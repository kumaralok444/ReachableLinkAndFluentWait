package first;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;


public class GmailLogin {
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver;
		System.setProperty("webdriver.gecko.driver", "D:\\Alok\\jar\\geckodriver-v0.11.1-win64\\geckodriver.exe");
		driver=new FirefoxDriver();
		driver.get("https://accounts.google.com/ServiceLogin?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1#identifier");
		WebElement we=driver.findElement(By.id("Email"));
		we.sendKeys("alok@ma");
		we=driver.findElement(By.id("next"));
		we.click();
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			    .withTimeout(30, TimeUnit.SECONDS)
			    .pollingEvery(2, TimeUnit.SECONDS)
			    .ignoring(NoSuchElementException.class);

			WebElement foo = wait.until(new Function<WebDriver, WebElement>() 
			{
			  public WebElement apply(WebDriver driver) 
			  {
				  WebElement we1=driver.findElement(By.id("errormsg_0_Email"));
				  if(we1.getAttribute("innerHTML").length()>0)
				  {
					  return we1;
				  }
				  else
				  {
					  //System.out.println("Text is coming is "+we1.getText());
					  return null;
				  }
			  }
			});
		//we=driver.findElement(By.id("errormsg_0_Email"));
		
		String st=foo.getText();
		System.out.println("Error message is "+st);
	}

}
