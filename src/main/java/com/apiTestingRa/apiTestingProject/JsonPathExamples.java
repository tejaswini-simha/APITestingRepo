package com.apiTestingRa.apiTestingProject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers.*;
//import static org.hamcrest.Matchers.hasItem;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import io.restassured.path.json.*;


public class JsonPathExamples {
	
//	@Test
	public void booksJsonEvaluator() {
		String filePath = "./src/main/resources/books.json";
		File booksJsonFile = new File(filePath);
		JsonPath booksJson = new JsonPath(booksJsonFile);
		System.out.println("BooksJson is :: " + booksJson.prettify());	
		System.out.println(booksJson.getString("store.book[0].category"));
		System.out.println(booksJson.getString("store.bicycle.color"));
		System.out.println(booksJson.getDouble("store.bicycle.price"));
//		System.out.println(booksJson.get("store.bicycle").toString());	
		Map<String, String> bicycle = booksJson.getMap("store.bicycle");
		System.out.println("Bicycle has the data :: " +bicycle);
		List<String> categoryList = booksJson.getList("store.book.category");
		System.out.println("Category List :: " + categoryList);
		Map lastBook = booksJson.getMap("store.book[-1]");
		System.out.println("Last book is :: " + lastBook);
	}
	
	@Test
	public void usersJsonAssertions() throws IOException {
		String filePath = "./src/main/resources/users.json";
	    String currentDirectory = System.getProperty("user.dir");
	    System.out.println("Current working directory: " + currentDirectory);
		File booksJsonFile = new File(filePath);
		JsonPath booksJson = new io.restassured.path.json.JsonPath(booksJsonFile);
		System.out.println("JSON IS :: " + booksJson.prettify());
		List<Object> streetList = booksJson.getList("address.street");
		String firstStreet = booksJson.getString("address[0].street");
		System.out.println("Street List :: " + streetList);
		System.out.println("First Street is :: " + firstStreet);
		double firstLat = booksJson.getDouble("address[0].geo.lat");
		System.out.println("First Lat is :: " + firstLat);
		System.out.println("Geo Locations of all users :: " + booksJson.getList("address.geo"));
		MatcherAssert.assertThat(booksJson.getList("address.street"), Matchers.hasItem("Victor Plains"));
//		assertThat(booksJson.getList("address.street"), Matchers.hasItem("Victor Plains"));
		System.out.println("Website of the 4th user :: " + booksJson.getString("website[3]"));
		System.out.println("CatchPhrase of the 5th user :: " + booksJson.getString("company.catchPhrase[4]"));				
	}
}
