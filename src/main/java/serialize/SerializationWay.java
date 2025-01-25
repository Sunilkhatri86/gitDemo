package serialize;

import java.net.ResponseCache;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SerializationWay {

	@Test
	public void run()
	{
		AddPlace ap = new AddPlace();
		

		//------------Set values-----------------
		ap.setAccuracy(50);
		ap.setAddress("Sunil adress");
		ap.setLanguage("Hindi");
		ap.setPhone_number("9868676");
		ap.setWebsite("https://rahul.com");
		ap.setName("Sunillll");
		

		//------------Set types-----------------
		List<String> list = new ArrayList<String>();
		list.add("Shoe");
		list.add("pass");
		ap.setTypes(list);
		
		
		//------------Set Location-----------------
		Location loc = new Location();
		loc.setLat(57.5237);
		loc.setLng(87.544);
		ap.setLocation(loc);
		
		//---All these values setted in Add Place so we can pass the AddPlace object
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
	
		Response response= given().log().all().queryParams("key","qaclick123")
		.body(ap)
		.when().log().all().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).extract().response();
		
		String resSting = response.asString();
		System.out.println("Response as String" + resSting);
		
		
		
		
	}
}
