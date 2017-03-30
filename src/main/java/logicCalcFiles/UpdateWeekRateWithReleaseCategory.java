package logicCalcFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class UpdateWeekRateWithReleaseCategory {

	String strInputReleaseCatFileName = null; 
	String filePath = null; 
 
	
	ArrayList <listReleaseCategoryTemplate> obj_listReleaseCategoryTemplate = new ArrayList <listReleaseCategoryTemplate> (); 
	
	public UpdateWeekRateWithReleaseCategory  (String temp_strInputReleaseCatFileName, String temp_filePath){
		this.strInputReleaseCatFileName = temp_strInputReleaseCatFileName; 
		this.filePath = temp_filePath; 
	}
	
	private class listReleaseCategoryTemplate{
		private String strReleaseName; 
		private String strReleaseCategory;
		/**
		 * @return the strReleaseName
		 */
		public String getStrReleaseName() {
			return strReleaseName;
		}
		/**
		 * @param strReleaseName the strReleaseName to set
		 */
		public void setStrReleaseName(String strReleaseName) {
			this.strReleaseName = strReleaseName;
		}
		/**
		 * @return the strReleaseCategory
		 */
		public String getStrReleaseCategory() {
			return strReleaseCategory;
		}
		/**
		 * @param strReleaseCategory the strReleaseCategory to set
		 */
		public void setStrReleaseCategory(String strReleaseCategory) {
			this.strReleaseCategory = strReleaseCategory;
		}
	}
	
	public void method_ReadReleaseCategories (){
		
		Row row=null; 
		Cell cell=null;
		int intReleaseNameColNum=0;
		int intReleaseCatColNum=0; 
		
		
		try {
			FileInputStream fileInputReleaseCat=null; 
			HSSFWorkbook workbookInputReleaseCat=null; 
			HSSFSheet worksheetInputReleaseCat = null; 
			
			fileInputReleaseCat = new FileInputStream(new File(filePath+strInputReleaseCatFileName));
			workbookInputReleaseCat = new HSSFWorkbook(fileInputReleaseCat);
			worksheetInputReleaseCat = workbookInputReleaseCat.getSheet(strInputReleaseCatFileName);
			
			System.out.println("------------------"+strInputReleaseCatFileName);
			
			
			
			Iterator<Row> rowIterator = worksheetInputReleaseCat.iterator();
		    while (rowIterator.hasNext()) {
		    	row = rowIterator.next(); 
		    	
		    	
		    	if (row.getRowNum()==0){
		    		Iterator <Cell> cellIterator = row.cellIterator();
				      while (cellIterator.hasNext()) {
				        cell = cellIterator.next();
				        
				        if (cell.getStringCellValue().matches("ReleaseName")) intReleaseNameColNum = cell.getColumnIndex(); 
				        if (cell.getStringCellValue().matches("Category")) intReleaseCatColNum = cell.getColumnIndex(); 
				      }
		    	}
		    	if (row.getRowNum()>0){
		    		
		    		    listReleaseCategoryTemplate tempobj_listReleaseCategoryTemplate = new listReleaseCategoryTemplate() ; 
				    
		    		    tempobj_listReleaseCategoryTemplate.setStrReleaseName(row.getCell(intReleaseNameColNum ).getStringCellValue());
				        tempobj_listReleaseCategoryTemplate.setStrReleaseCategory(row.getCell(intReleaseCatColNum).getStringCellValue());
				        
				        obj_listReleaseCategoryTemplate.add(tempobj_listReleaseCategoryTemplate); 
		    	} 	
		
		    }
		}catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Error: writing "+ strInputReleaseCatFileName);
		}catch (IOException e2){
			e2.printStackTrace();
			System.out.println("Error: writing "+ strInputReleaseCatFileName);
		}
		
//		================== for debugging purpose only =====================
//		for (listReleaseCategoryTemplate counterobj_listReleaseCategoryTemplate : obj_listReleaseCategoryTemplate){
//			System.out.println(counterobj_listReleaseCategoryTemplate.getStrReleaseName());
//			System.out.println(counterobj_listReleaseCategoryTemplate.getStrReleaseCategory());
//		}		
//		================== for debugging purpose only =====================
		
		
		
	}
	
	
	

	
	
	
	public void method_UpdateWeeklyRate (String strInputWeeklyRateFileName ){
		
		Row row = null; 
		Cell cell= null;
		int intReleaseNameColNum=0;
		
		try {
			FileOutputStream outputModifiedWeeklyRateData=null; 
			FileInputStream inputWeeklyRateData=null; 
			HSSFWorkbook workbook_WeeklyRateData=null; 
			HSSFSheet worksheet_WeeklyRateData = null; 
			
			inputWeeklyRateData = new FileInputStream(new File(filePath+strInputWeeklyRateFileName));
			workbook_WeeklyRateData = new HSSFWorkbook(inputWeeklyRateData);
			worksheet_WeeklyRateData = workbook_WeeklyRateData.getSheet(strInputWeeklyRateFileName);
				
				System.out.println("------------------"+strInputWeeklyRateFileName);
				
				Iterator<Row> rowIterator = worksheet_WeeklyRateData.iterator();
			    while (rowIterator.hasNext()) {
			        
			    	row = rowIterator.next();
			     
			      if (row.getRowNum()==0){
			    	  
			    	  Iterator <Cell> cellIterator = row.cellIterator();
				      while (cellIterator.hasNext()) {
				        cell = cellIterator.next();
				        if (cell.getStringCellValue().matches("Project Name & Release Num")) intReleaseNameColNum = cell.getColumnIndex(); 
				      }
				      
				      row.createCell(row.getLastCellNum()+0).setCellValue("Category");
			      }
			      
			      else if (row.getRowNum()>0){
			    	 
			    	  for (listReleaseCategoryTemplate counter_listReleaseCategoryTemplate : obj_listReleaseCategoryTemplate){
			    		  if (counter_listReleaseCategoryTemplate.getStrReleaseName().matches(row.getCell(intReleaseNameColNum).getStringCellValue())){
			    			  row.createCell(row.getLastCellNum()+0).setCellValue(counter_listReleaseCategoryTemplate.getStrReleaseCategory());
			    		  }
			    	  }
			      }
			    }
				
			outputModifiedWeeklyRateData = new FileOutputStream(filePath+strInputWeeklyRateFileName);
			workbook_WeeklyRateData.write(outputModifiedWeeklyRateData);
			outputModifiedWeeklyRateData.flush();
			outputModifiedWeeklyRateData.close();
			System.out.println("Success: written "+ strInputWeeklyRateFileName);
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Error: writing "+ strInputWeeklyRateFileName);
		}catch (IOException e2){
			e2.printStackTrace();
			System.out.println("Error: writing "+ strInputWeeklyRateFileName);
		}
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}// end of class 
