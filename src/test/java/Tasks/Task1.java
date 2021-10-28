package Tasks;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import io.restassured.path.json.JsonPath;

public class Task1 {

	public static void getUserDetails(String userInput) {

		String response = given().header("Content-Type", "application/json").
				when().get("users/" + userInput + "")
				.then().assertThat().statusCode(200).extract().response().asString(); // fetches user details

		JsonPath js = new JsonPath(response);

		String userName = js.getString("login");
		String name = js.getString("name");
		String createdOn = js.getString("created_at").split("T")[0]; // to separate date from date and time format

		System.out.println("\nUsername = " + userName + "\nName = " + name + "\nCreated on = " + createdOn);
	}

	public static HashMap<String, Integer> getRepoDetails(String userInput) {

		String response = given().header("Content-Type", "application/json").
				when().get("users/" + userInput + "/repos")
				.then().assertThat().statusCode(200).extract().response().asString(); // to fetch repo details for user
		String response1;

		JsonPath js = new JsonPath(response);
		JsonPath js1;

		int count = js.getInt("array.size()"); // this is count of number of repos in the response
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();// to store repo name and corresponding stars got from API
		
		if(count==0)
		{
			System.out.println("There are no repositories corresponding to this user.");
		}
			
		else {
	
		String repoName;
		int starCount;
		
		for (int i = 0; i < count; i++) {
			repoName = js.get("name[" + i + "]");
			starCount = js.get("stargazers_count[" + i + "]");
			hmap.put(repoName, starCount); //adding repo and star count to hashmap
			
			response1 = given().header("Content-Type", "application/json").
					when().get("repos/"+userInput+"/"+repoName+"/releases")
					.then().assertThat().statusCode(200).extract().response().asString();// to fetch all the releases for the repo
			js1 = new JsonPath(response1);
			
			System.out.println("\nRepository "+(i+1)+ " = " + repoName+ "\n  Stars = " + starCount);
			System.out.println("  Releases = " + js1.getInt("array.size()") );
		}
		
	}
		return hmap;

	}
}
