package test_class;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import CommonMethod.CommonMethodGet;
import CommonMethod.CommonMethodUtilities;
import io.restassured.path.json.JsonPath;
import request_repository.GetRequestRepository;

public class GetTc1 
{
	@Test
	public static void orchestrator() throws IOException
	{
		
		String responsebody = "";
		int responseStatuscode = 0;
		String baseuri = GetRequestRepository.baseuri();
		String resource = GetRequestRepository.resource();
		
		for(int i=0; i<5; i++)
		{
			responseStatuscode = CommonMethodGet.responseStatuscode_Extractor(baseuri, resource);
			if(responseStatuscode == 200)
			{
				responsebody = CommonMethodGet.responseBodyExtractor(baseuri, resource);
				responseBodyValidator(responsebody);
				
				break;
			}
			else
			{
				System.out.println("Correct status code is not found in the iteration" +i);
			}
		}
		
		CommonMethodUtilities.evidanceFilecreator("GetTc1", null, responsebody);
		AssertJUnit.assertEquals(responseStatuscode, 200);
		
	}

	private static void responseBodyValidator(String responsebody)
		{
			// Create object
			JsonPath jsp = new JsonPath(responsebody);
			
			// Array lenght
			int dataarraylength = jsp.getInt("data.size()");
			System.out.println("Length of Data Array is : " +dataarraylength);
			
			// Array values declaration	
			int id[] = {7,8,9,10,11,12};
			String email [] = {"michael.lawson@reqres.in","lindsay.ferguson@reqres.in","tobias.funke@reqres.in","byron.fields@reqres.in","george.edwards@reqres.in","rachel.howell@reqres.in"};
			String first_name[] = {"Michael","Lindsay","Tobias","Byron","George","Rachel"};
			String last_name[] = {"Lawson","Ferguson","Funke","Fields","Edwards","Howell"};
			String avatar[] = {"https://reqres.in/img/faces/7-image.jpg","https://reqres.in/img/faces/8-image.jpg","https://reqres.in/img/faces/9-image.jpg","https://reqres.in/img/faces/10-image.jpg","https://reqres.in/img/faces/11-image.jpg","https://reqres.in/img/faces/12-image.jpg"};
			//System.out.println("Data Array");
			
			for(int i=0; i<dataarraylength; i++)
			{
				// Extract response body parameters
				int res_id = jsp.getInt("data ["+i+"].id");
				String res_email = jsp.getString("data ["+i+"].email");
				String res_fname = jsp.getString("data ["+i+"].first_name");
				String res_lname = jsp.getString("data ["+i+"].last_name");
				String res_avatar = jsp.getString("data ["+i+"].avatar");
				
				System.out.println(res_id);
				System.out.println(res_email);
				System.out.println(res_fname);
				System.out.println(res_lname);
				System.out.println(res_avatar);
			
				// validate response body parameters
				AssertJUnit.assertEquals(res_id,id[i]);
				AssertJUnit.assertEquals(res_email,email[i]);
				AssertJUnit.assertEquals(res_fname,first_name[i]);
				AssertJUnit.assertEquals(res_lname,last_name[i]);
				AssertJUnit.assertEquals(res_avatar,avatar[i]);
			}
    }
}