package Tasks;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class Assessment {

	static HashMap<String, Integer> hmap = new HashMap<String, Integer>(); // to store repo name and corresponding stars got from API
	static String userInput = "torvalds";// to store the user name input received from CLI

	@Test
	public static void Task1() {
		RestAssured.baseURI = "https://api.github.com";
		Task1.getUserDetails(userInput); // to get User name, Name and Created On
		hmap = Task1.getRepoDetails(userInput); // to get repository name, corresponding stars and releases
	}

	@Test
	public static void Task2() {
		Task2.UIvalidation(userInput, hmap); // to validate API response for star count with UI for the same user
	}
}
