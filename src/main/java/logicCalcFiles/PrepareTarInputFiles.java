package logicCalcFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;







import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class PrepareTarInputFiles {
	
	String filePath = null; 
	String newFilePath = null; 
	ArrayList <String> strInputRelCatMembershipFilesName = new ArrayList <String> (); 
	String strOutputCombineMemFilesName = null ; 
	int a= 0; 
	ArrayList <classMembershipDataInputTemplate> listBFR_classMembershipDataInputTemplate = new ArrayList <classMembershipDataInputTemplate> (); 
	ArrayList <classMembershipDataInputTemplate> listDFR_classMembershipDataInputTemplate = new ArrayList <classMembershipDataInputTemplate> (); 
	ArrayList <classMembershipDataInputTemplate> listFCR_classMembershipDataInputTemplate = new ArrayList <classMembershipDataInputTemplate> (); 
	ArrayList <classMembershipDataInputTemplate> listFOR_classMembershipDataInputTemplate = new ArrayList <classMembershipDataInputTemplate> (); 
	
	
	
	public PrepareTarInputFiles (
			String temp_filePath, String temp_newFilePath, 
			ArrayList <String> temp_strInputRelCatMembershipFilesName, 
			String temp_strOutputCombineMemFilesName	){
		
		this.filePath = temp_filePath; 
		this.strInputRelCatMembershipFilesName = temp_strInputRelCatMembershipFilesName; 
		this.strOutputCombineMemFilesName = temp_strOutputCombineMemFilesName; 
		this.newFilePath = temp_newFilePath; 
		
		
	}
	
	public void method_combineRelCatMembershipFiles(){
		
		
//		========= due to shortage of time the naming issue is not fixed yet. Need to output membership as a same name and fix this naming issues of the file. 
		
		method_readRelCatMembershipFiles (strInputRelCatMembershipFilesName.get(0), listBFR_classMembershipDataInputTemplate ); 
		method_readRelCatMembershipFiles (strInputRelCatMembershipFilesName.get(1), listDFR_classMembershipDataInputTemplate ); 
		method_readRelCatMembershipFiles (strInputRelCatMembershipFilesName.get(2), listFCR_classMembershipDataInputTemplate ); 
		method_readRelCatMembershipFiles (strInputRelCatMembershipFilesName.get(3), listFOR_classMembershipDataInputTemplate ); 
	
	
	
	
	
		try {
			FileOutputStream fileOutputCombineMemFiles = new FileOutputStream(newFilePath+strOutputCombineMemFilesName+".xls" );
			HSSFWorkbook workbookOutputCombineMemFiles = new HSSFWorkbook();
			HSSFSheet worksheetOutputCombineMemFiles = workbookOutputCombineMemFiles.createSheet(strOutputCombineMemFilesName+".xls" );
			
			int intweeklyDataCounter =0; 
			
				
			for (int intCounterMemData=0;intCounterMemData<listBFR_classMembershipDataInputTemplate.size();intCounterMemData++){
				HSSFRow row= null; 
				HSSFCell cell=null;
				
				row =  worksheetOutputCombineMemFiles.createRow(intweeklyDataCounter);
				intweeklyDataCounter++;
//				System.out.println(listBFR_classMembershipDataInputTemplate.size()+"====================================");
//				System.out.println(listDFR_classMembershipDataInputTemplate.size()+"====================================");
//				System.out.println(listFCR_classMembershipDataInputTemplate.size()+"====================================");
//				System.out.println(listFOR_classMembershipDataInputTemplate.size()+"====================================");
				
				
				
				
				
				cell = row.createCell(0);
				cell.setCellValue(listBFR_classMembershipDataInputTemplate.get(intCounterMemData).getDblWeekNum());
				cell = row.createCell(1);
				cell.setCellValue(listBFR_classMembershipDataInputTemplate.get(intCounterMemData).getStrReleaseName());
				cell = row.createCell(2);
				cell.setCellValue(listBFR_classMembershipDataInputTemplate.get(intCounterMemData).getStrCategory());
				
				
				cell = row.createCell(3);
				cell.setCellValue(listBFR_classMembershipDataInputTemplate.get(intCounterMemData).getDblHighMem());
				cell = row.createCell(4);
				cell.setCellValue(listBFR_classMembershipDataInputTemplate.get(intCounterMemData).getDblMidMem());
				cell = row.createCell(5);
				cell.setCellValue(listBFR_classMembershipDataInputTemplate.get(intCounterMemData).getDblLowMem());
				
				
				cell = row.createCell(6);
				cell.setCellValue(listDFR_classMembershipDataInputTemplate.get(intCounterMemData).getDblHighMem());
				cell = row.createCell(7);
				cell.setCellValue(listDFR_classMembershipDataInputTemplate.get(intCounterMemData).getDblMidMem());
				cell = row.createCell(8);
				cell.setCellValue(listDFR_classMembershipDataInputTemplate.get(intCounterMemData).getDblLowMem());
				
							
				cell = row.createCell(9);
				cell.setCellValue(listFCR_classMembershipDataInputTemplate.get(intCounterMemData).getDblHighMem());
				cell = row.createCell(10);
				cell.setCellValue(listFCR_classMembershipDataInputTemplate.get(intCounterMemData).getDblMidMem());
				cell = row.createCell(11);
				cell.setCellValue(listFCR_classMembershipDataInputTemplate.get(intCounterMemData).getDblLowMem());
				
				
				cell = row.createCell(12);
				cell.setCellValue(listFOR_classMembershipDataInputTemplate.get(intCounterMemData).getDblHighMem());
				cell = row.createCell(13);
				cell.setCellValue(listFOR_classMembershipDataInputTemplate.get(intCounterMemData).getDblMidMem());
				cell = row.createCell(14);
				cell.setCellValue(listFOR_classMembershipDataInputTemplate.get(intCounterMemData).getDblLowMem());
				
			}
			
			workbookOutputCombineMemFiles .write(fileOutputCombineMemFiles);
			fileOutputCombineMemFiles.flush();
			fileOutputCombineMemFiles.close();
//			System.out.println("Success: Combine mem data written");
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Error: ombine mem data written");
		}catch (IOException e2){
			e2.printStackTrace();
			System.out.println("Error: ombine mem data written");
		}
	}
		
	
public void method_combineRelCatMembershipFilestoCSV() {
		
	 	
	
	
//		========= due to shortage of time the naming issue is not fixed yet. Need to output membership as a same name and fix this naming issues of the file. 
		
		method_readRelCatMembershipFiles (strInputRelCatMembershipFilesName.get(0), listBFR_classMembershipDataInputTemplate ); 
		method_readRelCatMembershipFiles (strInputRelCatMembershipFilesName.get(1), listDFR_classMembershipDataInputTemplate ); 
		method_readRelCatMembershipFiles (strInputRelCatMembershipFilesName.get(2), listFCR_classMembershipDataInputTemplate ); 
		method_readRelCatMembershipFiles (strInputRelCatMembershipFilesName.get(3), listFOR_classMembershipDataInputTemplate ); 
	
	try{
//		PrintStream out = new PrintStream(new FileOutputStream(newFilePath+strOutputCombineMemFilesName+".data"));
		PrintStream out = new PrintStream(new FileOutputStream(newFilePath+"jira.data"));
		System.setOut(out);
					
			for (int intCounterMemData=0;intCounterMemData<listBFR_classMembershipDataInputTemplate.size();intCounterMemData++){
//			
				
				System.out.print(
						listBFR_classMembershipDataInputTemplate.get(intCounterMemData).getDblLowMem()+","+
						listBFR_classMembershipDataInputTemplate.get(intCounterMemData).getDblMidMem()+","+
						listBFR_classMembershipDataInputTemplate.get(intCounterMemData).getDblHighMem()+","+
						
						
						listDFR_classMembershipDataInputTemplate.get(intCounterMemData).getDblLowMem()+","+
						listDFR_classMembershipDataInputTemplate.get(intCounterMemData).getDblMidMem()+","+
						listDFR_classMembershipDataInputTemplate.get(intCounterMemData).getDblHighMem()+","+
						
						listFCR_classMembershipDataInputTemplate.get(intCounterMemData).getDblLowMem()+","+
						listFCR_classMembershipDataInputTemplate.get(intCounterMemData).getDblMidMem()+","+	
						listFCR_classMembershipDataInputTemplate.get(intCounterMemData).getDblHighMem()+","+

						
						listFOR_classMembershipDataInputTemplate.get(intCounterMemData).getDblLowMem()+","+
						listFOR_classMembershipDataInputTemplate.get(intCounterMemData).getDblMidMem()+","+
						listFOR_classMembershipDataInputTemplate.get(intCounterMemData).getDblHighMem()+","+
						
						
						listBFR_classMembershipDataInputTemplate.get(intCounterMemData).getStrCategory()+"\n"
						
//						listBFR_classMembershipDataInputTemplate.get(intCounterMemData).getDblWeekNum()+","+
//						listBFR_classMembershipDataInputTemplate.get(intCounterMemData).getStrReleaseName()+","+
						
						);
			}
	}
	catch (Exception ex){
		
	}
	}
	
	
	
	public void method_readRelCatMembershipFiles (String temp_strInputRelCatMembershipFilesName, ArrayList <classMembershipDataInputTemplate> temp_listAAA_MembershipDataInputTemplate ){
		
		Row row = null; 
		Cell cell= null;
		
		int intWeekNumColNum=0, intReleaseNameColNum=0, intCategoryColNum=0, intLowMemColNum=0,intHighMemColNum=0, intMidMemColNum=0;
		
		try {
			 
			FileInputStream inputRelCatMembershipFiles=null; 
			HSSFWorkbook workbook_RelCatMembershipFiles=null; 
			HSSFSheet worksheet_RelCatMembershipFiles = null; 
			
			inputRelCatMembershipFiles = new FileInputStream(new File(filePath+temp_strInputRelCatMembershipFilesName));
			workbook_RelCatMembershipFiles = new HSSFWorkbook(inputRelCatMembershipFiles);
			worksheet_RelCatMembershipFiles = workbook_RelCatMembershipFiles.getSheet(temp_strInputRelCatMembershipFilesName);
				
//				System.out.println("------------------"+temp_strInputRelCatMembershipFilesName);
				
				Iterator<Row> rowIterator = worksheet_RelCatMembershipFiles.iterator();
			    while (rowIterator.hasNext()) {
			        
			    	row = rowIterator.next();
			     
			      if (row.getRowNum()==0){
			    	  
			    	  
			    	  
			    	  Iterator <Cell> cellIterator = row.cellIterator();
				      while (cellIterator.hasNext()) {
				        cell = cellIterator.next();
				        if (cell.getStringCellValue().matches("Week Num")) intWeekNumColNum = cell.getColumnIndex(); 
				        if (cell.getStringCellValue().matches("Project Name & Release Num")) intReleaseNameColNum = cell.getColumnIndex(); 
				        if (cell.getStringCellValue().matches("Category")) intCategoryColNum = cell.getColumnIndex(); 
				        if (cell.getStringCellValue().matches("Low_Membership")) intLowMemColNum = cell.getColumnIndex(); 
				        if (cell.getStringCellValue().matches("Mid_Membership")) intMidMemColNum = cell.getColumnIndex(); 
				        if (cell.getStringCellValue().matches("High_Membership")) intHighMemColNum = cell.getColumnIndex(); 
				      
				      }
				      
				      row.createCell(row.getLastCellNum()+0).setCellValue("Category");
			      }
			      
			      else if (row.getRowNum()>0){
			    	 
			    	  classMembershipDataInputTemplate temp_objMembershipDataInputTemplate = new classMembershipDataInputTemplate (); 
			    	  
			    	  
			    	  temp_objMembershipDataInputTemplate.setDblWeekNum(row.getCell(intWeekNumColNum).getNumericCellValue());
			    	  temp_objMembershipDataInputTemplate.setStrReleaseName(row.getCell(intReleaseNameColNum).getStringCellValue());
			    	  temp_objMembershipDataInputTemplate.setStrCategory(row.getCell(intCategoryColNum).getStringCellValue());
			    	  temp_objMembershipDataInputTemplate.setDblHighMem(row.getCell(intHighMemColNum ).getNumericCellValue());
			    	  temp_objMembershipDataInputTemplate.setDblMidMem(row.getCell(intMidMemColNum).getNumericCellValue());
			    	  temp_objMembershipDataInputTemplate.setDblLowMem(row.getCell(intLowMemColNum).getNumericCellValue());			    	  
			    	  
			    	  temp_listAAA_MembershipDataInputTemplate.add (temp_objMembershipDataInputTemplate); 
			    	 
			    	  
			      }
			    }
				
			    inputRelCatMembershipFiles.close();
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Error: reading "+temp_strInputRelCatMembershipFilesName);
		}catch (IOException e2){
			e2.printStackTrace();
			System.out.println("Error: reading "+ temp_strInputRelCatMembershipFilesName);
		}
	
	}

	class classMembershipDataInputTemplate {
		double dblWeekNum=0;
		String strReleaseName= null; 
		String strCategory = null; 
		double dblLowMem=0; 
		double dblHighMem=0; 
		double dblMidMem=0;
		
		
		
		/**
		 * @return the intWeekNum
		 */
		public double getDblWeekNum() {
			return dblWeekNum;
		}
		/**
		 * @param intWeekNum the intWeekNum to set
		 */
		public void setDblWeekNum(double dblWeekNum) {
			this.dblWeekNum = dblWeekNum;
		}
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
		 * @return the strCategory
		 */
		public String getStrCategory() {
			return strCategory;
		}
		/**
		 * @param strCategory the strCategory to set
		 */
		public void setStrCategory(String strCategory) {
			this.strCategory = strCategory;
		}
		/**
		 * @return the dblLowMem
		 */
		public double getDblLowMem() {
			return dblLowMem;
		}
		/**
		 * @param dblLowMem the dblLowMem to set
		 */
		public void setDblLowMem(double dblLowMem) {
			this.dblLowMem = dblLowMem;
		}
		/**
		 * @return the dblHighMem
		 */
		public double getDblHighMem() {
			return dblHighMem;
		}
		/**
		 * @param dblHighMem the dblHighMem to set
		 */
		public void setDblHighMem(double dblHighMem) {
			this.dblHighMem = dblHighMem;
		}
		/**
		 * @return the dblMidMem
		 */
		public double getDblMidMem() {
			return dblMidMem;
		}
		/**
		 * @param dblMidMem the dblMidMem to set
		 */
		public void setDblMidMem(double dblMidMem) {
			this.dblMidMem = dblMidMem;
		}
		
		
		
		
	}

	
}
