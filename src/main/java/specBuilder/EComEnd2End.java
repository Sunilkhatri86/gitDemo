package specBuilder;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class EComEnd2End {

	@Test
	public void run()
	{
		
		//---------------------Login-----------------------------------------
		
		RequestSpecification req= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").setContentType(ContentType.JSON).build();

		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUserEmail("sunilkhatri9560@gmail.com");
		loginRequest.setUserPassword("Test@1234");

		RequestSpecification reqLogin =  given().relaxedHTTPSValidation().spec(req).body(loginRequest);

		LoginResponse loginResponse= reqLogin.when().post("/api/ecom/auth/login").then().extract().response().as(LoginResponse.class);

		String token = loginResponse.getToken();
		String userId = loginResponse.getUserId();

		//----------------------Create product
		
		
		RequestSpecification addProductreqBase= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
				.addHeader("authorization", token).build();
		
		RequestSpecification addProductreq = given().spec(addProductreqBase)
		.param("productName", "fddd")
		.param("productAddedBy", userId)
		.param("productCategory", "fashion")
		.param("productSubCategory", "shirts")
		.param("productPrice", "11500")
		.param("productDescription", "Addias Originals")
		.param("productFor", "men")
		.multiPart("productImage", new File ("C:\\Users\\sunil.cx.kumar\\Postman\\files\\FileDg.jpg"));		
		
		
		String responseAddProduct = addProductreq.when().post("/api/ecom/product/add-product")
		.then().extract().response().asString();
	
		
		JsonPath js = new JsonPath(responseAddProduct);
		String productid = js.getString("productId");
		System.out.println("Sunil"+ productid);
		
		
		
		
		//--------------------------------Create\Place Order
		

		RequestSpecification createOrderReq= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").setContentType(ContentType.JSON).build();

		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setCountry("India");
		orderDetail.setProductOrderedId(productid);
		
		List<OrderDetail> listOrderDetails = new ArrayList<OrderDetail>();
		listOrderDetails.add(orderDetail);
		
		Orders orders = new Orders();
		orders.setOrders(listOrderDetails);
				
		RequestSpecification createOrderSpec = given().spec(createOrderReq).body(orders);
		
		String responseCreateOrder = createOrderSpec.when().log().all().post("/api/ecom/order/create-order")
		.then().log().all().extract().response().asString();
		System.out.println(responseCreateOrder);
		
		//---------------------------------Delete Order------------------------------
		

		RequestSpecification deleteorderBase= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
				.addHeader("authorization", token).build();
		

		RequestSpecification deleteorderSpec = given().spec(deleteorderBase).pathParam("productId", productid);
		
		String deleteResponse= deleteorderSpec.when().delete("https://rahulshettyacademy.com/api/ecom/product/delete-product/{productId}")
		.then().extract().response().asString();
		System.out.println(deleteResponse);
		
		
	}
}
