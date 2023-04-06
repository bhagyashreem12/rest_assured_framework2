package CommonMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CommonDataDriven 
{
	public static ArrayList<String> getDataExcel(String testSheetName, String testCaseName) throws IOException
	{
		ArrayList<String> arrayData = new ArrayList<String>();
		
	//step 1 locate the file, by creating the object of fileinput stream
		FileInputStream fis = new FileInputStream ("C:\\Users\\Dell\\Desktop\\test_data.xlsx");
		
	//step 2 create the object of XSSFWorkbook to open the excel file.
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
	// step 3 access the desired sheet
		// step 3.1 fetch the count of sheets available in the excel file 
		int countOfSheet = workbook.getNumberOfSheets();
		
	// step 3.2 fetch the name o sheet and compare against the desired sheet name
		for(int i=0 ;i < countOfSheet ; i++)
		{
			String sheetname = workbook.getSheetName(i);
			//System.out.println(sheetname);
			
			if(sheetname.equalsIgnoreCase(testSheetName)) 
			{
	 // step 4 Access the sheet and iterate through rows to fetch the col in which testcase name col is found
			  XSSFSheet Sheet = workbook.getSheetAt(i);
			  
	 // step 4.1 create iteration for rows
			  Iterator<Row> Rows = Sheet.iterator(); 
			  Row firstrow = Rows.next(); // this will give you the first row A1
			  
	 // step 4.2 create iteration for cells
			  Iterator<Cell> Cells = firstrow.cellIterator();
			  int j=0;
			  int tc_column = 0;
			  
	 //Step 4.3 Read the cell values of row no1 to compare against the testcase name
			  while(Cells.hasNext())
			  {
				  Cell cellvalue = Cells.next();
				  if(cellvalue.getStringCellValue().equalsIgnoreCase("Tc_name"))
				  {
					  tc_column = j;
					 // System.out.println(tc_column);
				  }
				  	j++;
			  }
	// Step 5 fetch the data for designeted test case
			  
			  while(Rows.hasNext())
			  {
				  Row dataRow = Rows.next();
				  if (dataRow.getCell(tc_column).getStringCellValue().equalsIgnoreCase(testCaseName))
				  {
					  Iterator<Cell> dataCellvalue = dataRow.cellIterator();
					  while(dataCellvalue.hasNext())
					  {
						  Cell dataOfCell = dataCellvalue.next();
						  
	//Method 1 To extract cell values using try and catch
						  try
						  {
							  String testdata = dataOfCell.getStringCellValue();
							  arrayData.add(testdata);
						  }
						  catch (IllegalStateException e)
						  {
							  int inttestData = (int) dataOfCell.getNumericCellValue();
							  String stringtestData = Integer.toString(inttestData);
							  arrayData.add(stringtestData);
						  }
						  
	 /*Method 2-- To Extract the cell value by using if and else
						  CellType datatype = dataOfCell.getCellType();
					      if(datatype.toString() == "NUMERIC")
						  {
							  int inttestData = (int) dataOfCell.getNumericCellValue();
						      String stringtestData = Integer.toString(inttestData);
							  arrayData.add(stringtestData); 
						  }
						  else if(datatype.toString() == "STRING")
						  {
							  String testdata = dataCellvalue.next().getStringCellValue();
							  arrayData.add(testdata);
						  }
	 // Method 3 -- Extract the data by converting it into String
						  String testData =dataCellvalue.next().toString().replaceAll("\\.\\d+$", "");
						  System.out.println(testData);
			
	  // Method 4 -- Extract the data by using Dataformatter
						  DataFormatter format =new DataFormatter();
						  String testforData=format.formatCellValue(dataCellvalue.next());
						  System.out.println(testforData);*/
					  }
				  }
			  }
			}
		}
	 return arrayData;
	}
}
