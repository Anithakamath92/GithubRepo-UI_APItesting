package objects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPage {
	
	WebDriver driver;
	By searchTextBox = By.xpath("//input[@placeholder=\"Search GitHub\"]");
	By repoList= By.xpath("//*[@class=\"repo-list\"]");
	By repoListResult = By.xpath("//ul[@class=\"repo-list\"]//li//a[@class=\"v-align-middle\"]");
	
	
	public SearchPage(WebDriver driver){
        this.driver = driver;
    }
	
	public WebElement searchTextBox() {
		return driver.findElement(searchTextBox);
	}

	public <WebElements> List<WebElement> repoList() {
		return driver.findElements(searchTextBox);
	}
	
	public WebElement repoListResult() {
		return driver.findElement(repoListResult);
	}

}