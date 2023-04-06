package CommonMethod;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class CommonMethodGet
{
	public static int responseStatuscode_Extractor(String baseuri,String resource)
	{
		//Declare baseuri through arguments
		RestAssured.baseURI = baseuri;
		
		//configure and return the status code
		int responseStatuscode = given().when().get(resource).then().extract().statusCode();	
		return responseStatuscode;
	}
	
	public static String responseBodyExtractor(String baseuri, String resource)
	{
		//Declare baseuri through arguments
		RestAssured.baseURI = baseuri;
		
		//Configure and return the responsebody
		String responsebody = given().when().get(resource).then().extract().response().asString();
		return responsebody;
		
	}
}