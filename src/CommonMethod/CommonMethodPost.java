package CommonMethod;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class CommonMethodPost 
{
	public static int responseStatuscode_Extractor(String baseuri,String resource,String req_body)
	{
		//Declare baseuri through arguments
		RestAssured.baseURI = baseuri;
		
		//configure and return the status code
		int responseStatuscode = given().header("Content-Type","application/json").body(req_body).when().post(resource).then().extract().statusCode();	
		return responseStatuscode;
	}
	
	public static String responseBodyExtractor(String baseuri, String resource, String req_body)
	{
		//Declare baseuri through arguments
		RestAssured.baseURI = baseuri;
		
		//Configure and return the responsebody
		String responsebody = given().header("Content-Type","application/json").body(req_body).when().post(resource).then().extract().response().asString();
		return responsebody;
		
	}
}
