package src;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;

public class TestCass {

	public static void main(String[] args) throws IOException {

		System.setProperty("webdriver.edge.driver", "C:\\edge driver\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();

		driver.get("http://127.0.0.1:5500/Qa-automation-7thmar/index.html");
		driver.manage().window().maximize();

//		Date currentDate = new Date();
//		Date currentDate = new Date();
//		System.out.println(currentDate);
//		System.out.println(currentDate2);

		Date currentDate = new Date();
		String theAcutalDate = currentDate.toString().replace(":", "-");

		TakesScreenshot src = ((TakesScreenshot) driver);
		File SrcFile = src.getScreenshotAs((OutputType.FILE));
		File Dest = new File(".//mypictures/" + theAcutalDate + ".png");
		FileUtils.copyFile(SrcFile, Dest);

		List<WebElement> thestudents = driver.findElements(By.tagName("option"));
		int theTotalNumberOfStudents = thestudents.size();
		
		System.out.println(theTotalNumberOfStudents + "this is the orginal number");

//		         ------------ i need to remove three items ------------
		int HowMonyItems = 3;
		
		System.out.println(HowMonyItems + "this is the number of items i want remove");
		
		for (int i = 0; i < HowMonyItems; i++) {
			driver.findElement(By.xpath("//*[@id=\"remove\"]")).click();
		}

//        ------------ i need to remove all items ------------
//		for (int i=0; i<thestudents.size(); i++ ) {
//           driver.findElement(By.xpath("//*[@id=\"remove\"]")).click();
//	    }
		List <WebElement> thestudentsAfterRemove = driver.findElements(By.tagName("option"));
		int ActualNumber = thestudentsAfterRemove.size() ;
		
		System.out.println("this is the actual number"+ActualNumber);
		
		int expectedItems = theTotalNumberOfStudents - HowMonyItems;
		
		System.out.println(expectedItems + "this is the nuber i have expected");
		Assert.assertEquals(ActualNumber, expectedItems);

	}
}
