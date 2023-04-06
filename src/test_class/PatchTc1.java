package test_class;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;

import CommonMethod.CommonMethodPatch;
import CommonMethod.CommonMethodUtilities;
import io.restassured.path.json.JsonPath;
import request_repository.PatchRequestRepository;

public class PatchTc1 
{
	@Test
	public static void orchestrator() throws IOException
	{
		
		String responsebody = "";
		int responseStatuscode = 0;
		String baseuri = PatchRequestRepository.baseuri();
		String resource = PatchRequestRepository.resource();
		String requestbody = PatchRequestRepository.patchRequestTC1();
		
		for(int i=0; i<5; i++)
		{
			responseStatuscode = CommonMethodPatch.responseStatuscode_Extractor(baseuri, resource, requestbody);
			if(responseStatuscode == 200)
			{
				responsebody = CommonMethodPatch.responseBodyExtractor(baseuri, resource, requestbody);
				responseBodyValidator(responsebody);
				
				break;
			}
			else
			{
				System.out.println("Correct status code is not found in the iteration" +i);
			}
		}
		
		CommonMethodUtilities.evidanceFilecreator("PatchTc1", requestbody, responsebody);
		AssertJUnit.assertEquals(responseStatuscode, 200);
		
	}

	private static void responseBodyValidator(String responsebody)
		{
		// create object
			JsonPath jsp = new JsonPath(responsebody);
			
		// Extract response body parameters
			String res_name = jsp.getString("name");
			String res_job = jsp.getString("job");
			String res_updatedAt = jsp.getString("updatedAt");
			
			//System.out.println("name : " + res_name + "\njob : " +res_job +  "\nupdatedAt : " + res_updatedAt);
			
		// validate response body parameters
			AssertJUnit.assertEquals(res_name, "Pratham");
			AssertJUnit.assertEquals(res_job, "HR");
			
		// Extract date from created parameter
			String actual_date = res_updatedAt.substring(0,10);
			String current_date = LocalDate.now().toString();
			AssertJUnit.assertEquals(actual_date, current_date);
			//System.out.println("Actual date : " + actual_date + "\nCurrent date : " + current_date);
		}
}
