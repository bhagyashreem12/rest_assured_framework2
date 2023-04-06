package request_repository;

import java.io.IOException;
import java.util.ArrayList;

import CommonMethod.CommonDataDriven;

public class PutRequestRepository 
{
	public static String baseuri()
	{
		String baseuri = "https://reqres.in/";
		return baseuri;
	}
	public static String resource()
	{
		String resource = "api/users/2";
		return resource;
	}
	
	public static String putRequestTC1() throws IOException
	{
		ArrayList<String> data = CommonDataDriven.getDataExcel("Put_data","Tc_1");
		System.out.println(data);
		String Name = data.get(2);
		String Job = data.get(3);
			
		String requestbody = "{\r\n"
				+ "    \"name\": \""+Name+"\",\r\n"
				+ "    \"job\": \""+Job+"\"\r\n"
				+ "}";
		
		return requestbody;
	}
}
