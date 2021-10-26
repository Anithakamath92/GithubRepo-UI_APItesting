package Tasks;

import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Task2 {

	public static void UIvalidation(String userInput, HashMap<String, Integer> hmap) {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\anith\\Documents\\Selenium\\Browser drivers\\New\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://github.com/"); // open the browser and open the GitHub page
		driver.manage().window().maximize();

		driver.findElement(By.xpath("//input[@placeholder=\"Search GitHub\"]")).sendKeys("user:" + userInput + "");
		driver.findElement(By.xpath("//input[@placeholder=\"Search GitHub\"]")).sendKeys(Keys.ENTER); //Search the given user name

		String repo = driver.findElement(By.xpath("//ul[@class=\"repo-list\"]//li//a[@class=\"v-align-middle\"]"))
				.getText().split("/")[1];
		driver.findElement(By.xpath("//ul[@class=\"repo-list\"]//li//a[@class=\"v-align-middle\"]")).click();// open the first repo from the list
		String starUI = driver.findElement(By.xpath("//*[@id='repository-container-header']//a[@class='social-count js-social-count']"))
				.getText();//fetch the stars from UI for the repo
		
		int starAPI = hmap.get(repo); // get the stars for repo from hashmap[which has values from API]
		String starAPIstr = Integer.toString(starAPI); //convert the starAPI to string to compare with the value from UI(starUI)

		if (starAPI > 1000) {
			starAPIstr = Integer.toString(Math.round((float) hmap.get(repo) / (float) 1000)) + "k"; //if the value is >1000 round of to 1000s value and add k
		}

		System.out.println("Username = "+userInput+"\nRepo name = " + repo + "\n Stars from UI = " + starUI+ "\n Stars from API  = " + starAPI + 
				"\n Stars from API(k)  = " + starAPIstr);

		Assert.assertEquals(starUI, starAPIstr); // compare stars obtained from UI and API 
		
		System.out.println("\nTest Passed- Stars for the repo "+ repo+ " from UI("+starUI+ ") matches stars from API("+starAPI+")");

	}

}