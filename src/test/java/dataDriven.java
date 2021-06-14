import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataDriven {
	
	
	public ArrayList<String> getData(String desiredTestCase, String desiredColumn, String desiredSheet) throws IOException 
	{	
		
		ArrayList<String> allData = new ArrayList<String>();
		FileInputStream fs = new FileInputStream("D:\\Cyborg Tech\\Automation Testing Selenium\\DataFile\\DemoData.xlsx");	
		XSSFWorkbook workbook = new XSSFWorkbook(fs);
		
		int sheetCount = workbook.getNumberOfSheets();
		
		for(int i=0; i<sheetCount; i++) 
		{
			if(workbook.getSheetName(i).equalsIgnoreCase(desiredSheet)) 
			{
			 	XSSFSheet sheet = workbook.getSheetAt(i);
			 	//Identify test cases colunm by scaning the first entire row
			 	
			 	Iterator<Row> rows = sheet.iterator();		//sheet is collection of rows
			 	rows.next();
			 	Row firstrow = rows.next();					
			 	Iterator<Cell> cell = firstrow.cellIterator(); // row is collection of cells
			 	int k =0;
			 	int column = 0;
			 	while(cell.hasNext()) 
			 	{
			 		Cell cellValue = cell.next();
			 		if(cellValue.getStringCellValue().equalsIgnoreCase(desiredColumn)) 
			 		{
			 			//desired column value found
			 			column = k;
			 		}
			 		k++;
			 	}
			 	
			 	System.out.println(column);
			 	
			 	//once column identify then scan entire testcase col to identify purchase testcase row
			 	while(rows.hasNext()) 
			 	{
			 		Row r = rows.next();
			 		if(r.getCell(column).getStringCellValue().equalsIgnoreCase(desiredTestCase)) 
			 		{
			 			//after garbing purchase testcase row then pul all data of that row and feed into test
			 			Iterator<Cell> cValue = r.cellIterator();
			 			while(cValue.hasNext()) 
			 			{
			 				Cell cVal = cValue.next();
			 				if(cVal.getCellType()==CellType.STRING) 
			 				{
			 					allData.add(cVal.getStringCellValue()); 
			 				}
			 				else 
			 				{
			 					allData.add(NumberToTextConverter.toText(cVal.getNumericCellValue()));	
							}
			 				
			 			}
			 		}
			 		
			 	}
			 	
			 	
			 	
			}
			
		}
		
		return allData;
		
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub


	}

}
