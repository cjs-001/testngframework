package datas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.format.CellFormatter;
import org.apache.poi.ss.format.CellTextFormatter;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelData {
	
	
	
	public Object[][] grtdata(String sheetname) throws IOException
	{
		Object[][] data =new Object[0][0];
		FileInputStream fis=new FileInputStream("C:\\\\charan's\\\\Book1.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		int sheets=wb.getNumberOfSheets();
		for(int i=0;i<sheets;i++)
		{
		if(wb.getSheetName(i).equalsIgnoreCase(sheetname))
		{
		
		
			XSSFSheet sheet = wb.getSheetAt(i);
			int rowcount = sheet.getPhysicalNumberOfRows();
			XSSFRow row=sheet.getRow(0);
			int cellcount = row.getLastCellNum();
			 data  = new Object[rowcount-1][cellcount];
			
			DataFormatter formatter = new DataFormatter();
			for(int j=0;j<rowcount-1;j++)
			{
				row=sheet.getRow(j+1);
				for(int k=0;k<cellcount;k++)
				{
					XSSFCell cell = row.getCell(k);
					
					data[j][k] =formatter.formatCellValue(cell);
				}
				
			}
		}
		}
		
			return data;
			
		
		
	}

}
