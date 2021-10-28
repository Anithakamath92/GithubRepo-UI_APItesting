package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RepoPage {
	
	By starCount = By.xpath("//*[@id='repository-container-header']//a[@class='social-count js-social-count']");
	
	public WebElement starCount(WebDriver driver) {
		return driver.findElement(starCount);
	}
	
}
