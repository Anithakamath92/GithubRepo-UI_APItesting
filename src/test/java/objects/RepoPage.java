package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RepoPage {
	
	WebDriver driver;
	By starCount = By.xpath("//*[@id='repository-container-header']//a[@class='social-count js-social-count']");
	
	public RepoPage(WebDriver driver){
        this.driver = driver;
    }
	
	public WebElement starCount() {
		return driver.findElement(starCount);
	}
	
}
