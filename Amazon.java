package selenium.week4;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {

	
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		 WebDriverManager.chromedriver().setup();
		 ChromeDriver driver=new ChromeDriver();
//		1.Load the URL 
		 driver.get("https://www.amazon.in/");
		 driver.manage().window().maximize();
		 
//			2.search as oneplus 9 pro 
		WebElement element = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		element.sendKeys("One Plus 9 Pro");
		driver.findElement(By.id("nav-search-submit-button")).click();
		
//			3.Get the price of the first product
		 WebElement element2 = driver.findElement(By.xpath("//span[@class='a-price-whole']"));
		String text = element2.getText();
		System.out.println("The price of OnePlus 9 Pro is Rs." + text);
//			4. Print the number of customer ratings for the first displayed product
	WebElement element3 = driver.findElement(By.xpath("//span[@class='a-icon-alt']"));
	String text2 = element3.getText();
	System.out.println("The customer rating is " + text2);
	
//			5. Click the first text link of the first image
	driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']")).click();
	 Set<String> windowHandles = driver.getWindowHandles();
		ArrayList<String> arrayList = new ArrayList<String>(windowHandles);

driver.switchTo().window(arrayList.get(1));
		
//			6. Take a screen shot of the product displayed
	File source = driver.getScreenshotAs(OutputType.FILE);
	File destination = new File("./manisha.png");
	FileUtils.copyFile(source, destination);
		
//			7. Click 'Add to Cart' button
	driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
//			8. Get the cart subtotal and verify if it is correct.
	Thread.sleep(4000);
WebElement element4 = driver.findElement(By.xpath("//span[@id='attach-accessory-cart-subtotal']"));
		
			String text3 = element4.getText();
			System.out.println("The total is " + text3 );
			if(text2.equals(text3))
			{
				System.out.println("Correct");
			}
			else
			{
				System.out.println("Incorrect");
			}
			driver.close();
	}

}
