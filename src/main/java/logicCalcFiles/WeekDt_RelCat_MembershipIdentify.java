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

public class WeekDt_RelCat_MembershipIdentify {

	
	
	String strInputWeeklyValFileName = null; 
	String strInputBUPWeeklyRateFileName = null; 
	String filePath = null; 
	
	
	class_RelCatMembershipTemplate obj_class_RelCatMembershipTemplate = new class_RelCatMembershipTemplate () ; 
	
//	ArrayList <listReleaseCategoryTemplate> obj_listReleaseCategoryTemplate = new ArrayList <listReleaseCategoryTemplate> (); 
	
	public WeekDt_RelCat_MembershipIdentify  (String temp_filePath, String temp_strInputWeeklyValFileName, String temp_strInputBUPWeeklyRateFileName){
		this.strInputWeeklyValFileName = temp_strInputWeeklyValFileName; 
		this.strInputBUPWeeklyRateFileName = temp_strInputBUPWeeklyRateFileName; 
		this.filePath = temp_filePath; 
	}
	
	
	
	
public void method_IOWeeklyVal (){
		
		Row row=null; 
		Cell cell=null;
		int intTotalValColNum=0;

		
		
		
		try {
			FileInputStream fileInputWeeklyVal=null; 
			FileOutputStream outputModifiedInputWeeklyVal=null;
			
			HSSFWorkbook workbookInputWeeklyVal=null; 
			HSSFSheet worksheetInputWeeklyVal = null; 
			
			fileInputWeeklyVal = new FileInputStream(new File(filePath+strInputWeeklyValFileName));
			workbookInputWeeklyVal = new HSSFWorkbook(fileInputWeeklyVal);
			worksheetInputWeeklyVal = workbookInputWeeklyVal.getSheet(strInputWeeklyValFileName);
			
			System.out.println("------------------"+strInputWeeklyValFileName);
			
			
			
			Iterator<Row> rowIterator = worksheetInputWeeklyVal.iterator();
		    while (rowIterator.hasNext()) {
		    	row = rowIterator.next(); 
		    	
		    	
		    	if (row.getRowNum()==0){
		    		Iterator <Cell> cellIterator = row.cellIterator();
				      while (cellIterator.hasNext()) {
				        cell = cellIterator.next();
				        
				        if (cell.getStringCellValue().matches("Normal value based on Duration")) intTotalValColNum = cell.getColumnIndex(); 
//				        if (cell.getStringCellValue().matches("Category")) intReleaseCatColNum = cell.getColumnIndex(); 
				      }
				      
				    row.createCell(row.getLastCellNum()+0).setCellValue("Low_Membership");   
				    row.createCell(row.getLastCellNum()+0).setCellValue("Mid_Membership");
				    row.createCell(row.getLastCellNum()+0).setCellValue("High_Membership");
				      
		    	}
		    	if (row.getRowNum()>0){
		    		
		    		
		    		
		    		
		    		method_findRelCatMembership (row.getCell(intTotalValColNum).getNumericCellValue()); 
		    		
		    		row.createCell(row.getLastCellNum()+0).setCellValue(obj_class_RelCatMembershipTemplate.getDblLowMembership());
		    		row.createCell(row.getLastCellNum()+0).setCellValue(obj_class_RelCatMembershipTemplate.getDblMidMembership());
		    		row.createCell(row.getLastCellNum()+0).setCellValue(obj_class_RelCatMembershipTemplate.getDblHighMembership());
		    		
		    		obj_class_RelCatMembershipTemplate.flushCounterObject();
	    		  }
	    	  }
	     
		    outputModifiedInputWeeklyVal = new FileOutputStream(filePath+strInputWeeklyValFileName);
		    workbookInputWeeklyVal.write(outputModifiedInputWeeklyVal);
		    outputModifiedInputWeeklyVal.flush();
		    outputModifiedInputWeeklyVal.close();
		    System.out.println("Success: written "+ strInputWeeklyValFileName);
		   	    
		}catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Error: writing "+ strInputWeeklyValFileName);
		}catch (IOException e2){
			e2.printStackTrace();
			System.out.println("Error: writing "+ strInputWeeklyValFileName);
		}
		
}
	
	
	class class_RelCatMembershipTemplate {
		double dblLowMembership=0;
		double dblMidMembership=0;
		double dblHighMembership=0;
		
		double intCounterMember, intCounterLowMember, intCounterMidMember, intCounterHighMember; 
		
		
		public void flushCounterObject (){
			dblLowMembership=0;
			dblMidMembership=0;
			dblHighMembership=0;
			intCounterMember=0; 
			intCounterLowMember=0; 
			intCounterMidMember=0; 
			intCounterHighMember=0; 
		}
		
		/**
		 * @return the dblLowMembership
		 */
		public double getDblLowMembership() {
			return dblLowMembership;
		}
		/**
		 * @param dblLowMembership the dblLowMembership to set
		 */
		public void setDblLowMembership(double dblLowMembership) {
			this.dblLowMembership = dblLowMembership;
		}
		/**
		 * @return the dblMidMembership
		 */
		public double getDblMidMembership() {
			return dblMidMembership;
		}
		/**
		 * @param dblMidMembership the dblMidMembership to set
		 */
		public void setDblMidMembership(double dblMidMembership) {
			this.dblMidMembership = dblMidMembership;
		}
		/**
		 * @return the dblHighMembership
		 */
		public double getDblHighMembership() {
			return dblHighMembership;
		}
		/**
		 * @param dblHighMembership the dblHighMembership to set
		 */
		public void setDblHighMembership(double dblHighMembership) {
			this.dblHighMembership = dblHighMembership;
		}
	}

	public void method_findRelCatMembership (double dblCurrentWeeklyRateVal){
		Row row = null; 
		Cell cell= null;
		int intTotalValColNum=0;
		int intCategoryColNum=0;
		
		
		double dblCurrentWeeklyRateUpThold = dblCurrentWeeklyRateVal + dblCurrentWeeklyRateVal*5/100; 
		double dblCurrentWeeklyRateDownThold = dblCurrentWeeklyRateVal - dblCurrentWeeklyRateVal*5/100; 
		
		
		try {
			FileInputStream inputBUPWeeklyRateData =null; 
			HSSFWorkbook workbook_BUPWeeklyRateData =null; 
			HSSFSheet worksheet_BUPWeeklyRateData = null; 
		
			inputBUPWeeklyRateData = new FileInputStream(new File(filePath+strInputBUPWeeklyRateFileName));
			workbook_BUPWeeklyRateData = new HSSFWorkbook(inputBUPWeeklyRateData);
			worksheet_BUPWeeklyRateData = workbook_BUPWeeklyRateData.getSheet(strInputBUPWeeklyRateFileName);
		
			System.out.println("------------------"+strInputBUPWeeklyRateFileName);
			
			Iterator<Row> rowIterator = worksheet_BUPWeeklyRateData.iterator();
		    while (rowIterator.hasNext()) {
		        
		    	row = rowIterator.next();
		     
		      if (row.getRowNum()==0){
		    	  
		    	  Iterator <Cell> cellIterator = row.cellIterator();
			      while (cellIterator.hasNext()) {
			        cell = cellIterator.next();
			        if (cell.getStringCellValue().matches("Normal value based on Duration")) intTotalValColNum = cell.getColumnIndex(); 
			        if (cell.getStringCellValue().matches("Category")) intCategoryColNum = cell.getColumnIndex();
			      }
		      }
		
		
		      else if (row.getRowNum()>0){
			    	 
		    	  if (row.getCell(intTotalValColNum).getNumericCellValue()>=dblCurrentWeeklyRateDownThold && row.getCell(intTotalValColNum).getNumericCellValue()<=dblCurrentWeeklyRateUpThold){
		    		  obj_class_RelCatMembershipTemplate.intCounterMember++; 
		    		  

		    		 
		    		  if (row.getCell(intCategoryColNum).getStringCellValue().matches("High")) obj_class_RelCatMembershipTemplate.intCounterHighMember++;
		    		  else if (row.getCell(intCategoryColNum).getStringCellValue().matches("Mid")) obj_class_RelCatMembershipTemplate.intCounterMidMember++;
		    		  else if (row.getCell(intCategoryColNum).getStringCellValue().matches("Low")) obj_class_RelCatMembershipTemplate.intCounterLowMember++;
		    	  }
		      }
		    }
		
		    obj_class_RelCatMembershipTemplate.setDblHighMembership(obj_class_RelCatMembershipTemplate.intCounterHighMember/obj_class_RelCatMembershipTemplate.intCounterMember*100);
		    obj_class_RelCatMembershipTemplate.setDblLowMembership(obj_class_RelCatMembershipTemplate.intCounterLowMember/obj_class_RelCatMembershipTemplate.intCounterMember*100);
		    obj_class_RelCatMembershipTemplate.setDblMidMembership(obj_class_RelCatMembershipTemplate.intCounterMidMember/obj_class_RelCatMembershipTemplate.intCounterMember*100);
		}catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Error: writing "+ strInputBUPWeeklyRateFileName);
		}catch (IOException e2){
			e2.printStackTrace();
			System.out.println("Error: writing "+ strInputBUPWeeklyRateFileName);
		}
		
	}
	
	
	public void method_UpdateWeeklyRate (String strInputWeeklyRateFileName ){
//		
//		Row row = null; 
//		Cell cell= null;
//		int intReleaseNameColNum=0;
//		
//		try {
//			FileOutputStream outputModifiedWeeklyRateData=null; 
//			FileInputStream inputWeeklyRateData=null; 
//			HSSFWorkbook workbook_WeeklyRateData=null; 
//			HSSFSheet worksheet_WeeklyRateData = null; 
//			
//			inputWeeklyRateData = new FileInputStream(new File(filePath+strInputWeeklyRateFileName));
//			workbook_WeeklyRateData = new HSSFWorkbook(inputWeeklyRateData);
//			worksheet_WeeklyRateData = workbook_WeeklyRateData.getSheet(strInputWeeklyRateFileName);
//				
//				System.out.println("------------------"+strInputWeeklyRateFileName);
//				
//				Iterator<Row> rowIterator = worksheet_WeeklyRateData.iterator();
//			    while (rowIterator.hasNext()) {
//			        
//			    	row = rowIterator.next();
//			     
//			      if (row.getRowNum()==0){
//			    	  
//			    	  Iterator <Cell> cellIterator = row.cellIterator();
//				      while (cellIterator.hasNext()) {
//				        cell = cellIterator.next();
//				        if (cell.getStringCellValue().matches("Project Name & Release Num")) intReleaseNameColNum = cell.getColumnIndex(); 
//				      }
//			      }
//			      
//			      else if (row.getRowNum()>0){
//			    	 
//			    	  for (listReleaseCategoryTemplate counter_listReleaseCategoryTemplate : obj_listReleaseCategoryTemplate){
//			    		  if (counter_listReleaseCategoryTemplate.getStrReleaseName().matches(row.getCell(intReleaseNameColNum).getStringCellValue())){
//			    			  row.createCell(row.getLastCellNum()+0).setCellValue(counter_listReleaseCategoryTemplate.getStrReleaseCategory());
//			    		  }
//			    	  }
//			      }
//			    }
//				
//			outputModifiedWeeklyRateData = new FileOutputStream(filePath+strInputWeeklyRateFileName);
//			workbook_WeeklyRateData.write(outputModifiedWeeklyRateData);
//			outputModifiedWeeklyRateData.flush();
//			outputModifiedWeeklyRateData.close();
//			System.out.println("Success: written "+ strInputWeeklyRateFileName);
//			
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//			System.out.println("Error: writing "+ strInputWeeklyRateFileName);
//		}catch (IOException e2){
//			e2.printStackTrace();
//			System.out.println("Error: writing "+ strInputWeeklyRateFileName);
//		}
	
	}
	
	
	
}
