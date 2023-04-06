package test_class;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.LocalDate;
import org.testng.Assert;

import CommonMethod.CommonMethodPost;
import CommonMethod.CommonMethodUtilities;
import io.restassured.path.json.JsonPath;
import request_repository.PostRequestRepository;

public class PostTc1 
{ 
	@Test
	public static void orchestrator() throws IOException
	{
		String responsebody = "";
		int responseStatuscode = 0;
		String baseuri = PostRequestRepository.baseuri();
		String resource = PostRequestRepository.resource();
		String requestbody = PostRequestRepository.postRequestTC1();
		
		for(int i=0; i<5; i++)
		{
			responseStatuscode = CommonMethodPost.responseStatuscode_Extractor(baseuri, resource, requestbody);
			if(responseStatuscode == 201)
			{
				responsebody = CommonMethodPost.responseBodyExtractor(baseuri, resource, requestbody);
				responseBodyValidator(responsebody);
				
				break;
			}
			else
			{
				System.out.println("Correct status code is not found in the iteration" +i);
			}
		}
		
		CommonMethodUtilities.evidanceFilecreator("PostTc1", requestbody, responsebody);
		AssertJUnit.assertEquals(responseStatuscode, 201);
		
	}

	private static void responseBodyValidator(String responsebody)
		{
		// create object
			JsonPath jsp = new JsonPath(responsebody);
			
		// Extract response body parameters
			String res_name = jsp.getString("name");
			String res_job = jsp.getString("job");
			String res_id = jsp.getString("id");
			String res_createdAt = jsp.getString("createdAt");
			
			//System.out.println("name : " + res_name + "\njob : " +res_job + "\nid : " + res_id + "\ncreatedAt : " + res_createdAt);
			
		// validate response body parameters
			AssertJUnit.assertEquals(res_name,"Sai");
			AssertJUnit.assertEquals(res_job, "CEO");
			Assert.assertNotNull(res_id);
			
		// Extract date from created parameter
			String actual_date = res_createdAt.substring(0,10);
			String current_date = LocalDate.now().toString();
			AssertJUnit.assertEquals(actual_date, current_date);
			//System.out.println("Actual date : " + actual_date + "\nCurrent date : " + current_date);
		}
}