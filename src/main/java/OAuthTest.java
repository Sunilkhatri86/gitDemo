import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class OAuthTest {

	
	
	@Test
	public void run()
	{
		
		String response= given().formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.formParams("grant_type","client_credentials")
		.when().log().all().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
	
		
		JsonPath js = new JsonPath(response);
		String token = js.getString("access_token");
		
	System.out.println("Token"+ token);
	
	
	
String response2=	given().queryParams("access_token", token)
	.when().log().all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
	.asString();
		
System.out.println("response2"+ response2);
	}

}
