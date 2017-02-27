package first;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LinkWebService
{
public static void main(String[] args) 
{
	WebDriver driver;
	System.setProperty("webdriver.gecko.driver", "D:\\Alok\\jar\\geckodriver-v0.11.1-win64\\geckodriver.exe");
	driver=new FirefoxDriver();
	driver.get("http://flipkart.com/");
	List<WebElement> we=driver.findElements(By.tagName("a"));
	System.out.println("Number of link is "+we.size());
	for(int i=0;i<we.size();i++)
	{
		WebElement we1=we.get(i);
		String st=we1.getAttribute("href");
		verifyLinkActive(st);
	}
	
}
public static void verifyLinkActive(String linkURL)
	{
	try{
		URL url=new URL(linkURL);
		HttpURLConnection httpUrlConnect=(HttpURLConnection)url.openConnection();
		httpUrlConnect.setConnectTimeout(3000);
		httpUrlConnect.connect();
		if(httpUrlConnect.getResponseCode()==200)
		{
			System.out.println(linkURL+"-"+httpUrlConnect.getResponseMessage());
		}
		if(httpUrlConnect.getResponseCode()==HttpURLConnection.HTTP_NOT_FOUND)
		{
			System.out.println(linkURL+"-"+httpUrlConnect.getResponseMessage()+"-"+HttpURLConnection.HTTP_NOT_FOUND);
		}
	}
	catch(Exception e)
	{
		
	}
	}
}
