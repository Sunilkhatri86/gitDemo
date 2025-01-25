package pojo;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class DeserializationWay {

	@Test
	public void run() {

		String[] courseTitle = { "Selenium Webdriver Java", "Cypress", "Protractor" };

		String response = given()
				.formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W").formParams("grant_type", "client_credentials")
				.when().log().all().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
				.asString();

		JsonPath js = new JsonPath(response);
		String token = js.getString("access_token");

		System.out.println("Token" + token);

		GetCourse gc = given().queryParams("access_token", token).when().log().all()
				.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);

		System.out.println(gc.getLinkedIn());

		System.out.println(gc.getInstructor());

// instead of hard cording the xpath
		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());

		List<API> apiCources = gc.getCourses().getApi();

		for (int i = 0; i < apiCources.size(); i++) {
			if (apiCources.get(i).getCourseTitle().equals("SoapUI Webservices testing")) {
				System.out.print(apiCources.get(i).getPrice());
			}
		}

		for (int i = 0; i < apiCources.size(); i++) {
			System.out.print(apiCources.get(i).getCourseTitle());
		}

//--------------Printing the course Title of Web Automation

		List<WebAutomation> webAutomationCources = gc.getCourses().getWebAutomation();

		for (int i = 0; i < webAutomationCources.size(); i++) {
			System.out.println(webAutomationCources.get(i).getCourseTitle());
		}

//--------------Comparing the courseTitle of Web Automation

		ArrayList<String> al = new ArrayList<String>();

		List<WebAutomation> w = gc.getCourses().getWebAutomation();

		for (int i = 0; i < w.size(); i++) {
			al.add(w.get(i).getCourseTitle());
		}

		List<String> expetedList = Arrays.asList(courseTitle);

		Assert.assertTrue(al.equals(expetedList));

	}

}
