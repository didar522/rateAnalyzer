package logicCalcFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import dataTypeTemplates.DataIssueTemplate;
import dataTypeTemplates.DataReleaseCategoryTemplate;
import dataTypeTemplates.NeighbourInput_WeekCalcTemplate;

public class FindNeighbourDataset {

	String filePath; 
	ArrayList <NeighbourInput_WeekCalcTemplate> alist_CW_NeighbourInput_WeekCalcTemplate = new ArrayList <NeighbourInput_WeekCalcTemplate> (); 
	ArrayList <NeighbourInput_WeekCalcTemplate> alist_BFR_NeighbourInput_WeekCalcTemplate = new ArrayList <NeighbourInput_WeekCalcTemplate> (); 
	ArrayList <NeighbourInput_WeekCalcTemplate> alist_DFR_NeighbourInput_WeekCalcTemplate = new ArrayList <NeighbourInput_WeekCalcTemplate> (); 
	ArrayList <NeighbourInput_WeekCalcTemplate> alist_FCR_NeighbourInput_WeekCalcTemplate = new ArrayList <NeighbourInput_WeekCalcTemplate> (); 
	ArrayList <NeighbourInput_WeekCalcTemplate> alist_FOR_NeighbourInput_WeekCalcTemplate = new ArrayList <NeighbourInput_WeekCalcTemplate> (); 
	
	
	
	HashMap<String, Integer> columnIndex = new HashMap <String, Integer> ();
//	HashMap<String, Integer> BU_FileIndex = new HashMap <String, Integer> ();
	
	
	public FindNeighbourDataset (String temp_FilePath){
		this.filePath = temp_FilePath ; 
	}
	
	
	
	
	
	
	public void getNeighbourDataset (){
		
		columnIndex =createColumnIndex (0, "CurrentRelease.xls");
		
		
		readExcelFiles (alist_CW_NeighbourInput_WeekCalcTemplate, "CurrentRelease.xls");
		readExcelFiles (alist_BFR_NeighbourInput_WeekCalcTemplate, "WeeklyRate_BFR_Combine.xls");
		readExcelFiles (alist_DFR_NeighbourInput_WeekCalcTemplate, "WeeklyRate_DFR_Combine.xls");
		readExcelFiles (alist_FCR_NeighbourInput_WeekCalcTemplate, "WeeklyRate_FCR_Combine.xls");
		readExcelFiles (alist_FOR_NeighbourInput_WeekCalcTemplate, "WeeklyRate_FOR_Combine.xls");
		
		
		
		
		//writeNeighBourFiles (alist_CW_NeighbourInput_WeekCalcTemplate, "CurrentRelease.xls-yyyy",  true);
		double highbound=0, lowbound=0;
		int fileCounter=0; 
		for (NeighbourInput_WeekCalcTemplate counterBFR : alist_CW_NeighbourInput_WeekCalcTemplate){
			
			fileCounter++; 
			if (counterBFR.getReleaseCompletion()<=95) {
				highbound = counterBFR.getReleaseCompletion() +5;
			}
			else highbound = counterBFR.getReleaseCompletion(); 
			
			
			if (counterBFR.getReleaseCompletion()>=5) {
				lowbound = counterBFR.getReleaseCompletion() -5;
			}
			else lowbound = counterBFR.getReleaseCompletion(); 
			
			writeNeighBourFiles (alist_BFR_NeighbourInput_WeekCalcTemplate, "WeeklyRate_BFR_Combine_"+fileCounter+".xls", true, highbound, lowbound);
			writeNeighBourFiles (alist_DFR_NeighbourInput_WeekCalcTemplate, "WeeklyRate_DFR_Combine_"+fileCounter+".xls", true, highbound, lowbound);
			writeNeighBourFiles (alist_FCR_NeighbourInput_WeekCalcTemplate, "WeeklyRate_FCR_Combine_"+fileCounter+".xls", true, highbound, lowbound);
			writeNeighBourFiles (alist_FOR_NeighbourInput_WeekCalcTemplate, "WeeklyRate_FOR_Combine_"+fileCounter+".xls", true, highbound, lowbound);
			
		}
		
	}
	

	
	public HashMap<String, Integer> createColumnIndex (int rowNumForIndex, String fileName){
		 
		HashMap<String, Integer> columnIndex = new HashMap <String, Integer> ();
		
		System.out.println("Reading Excel file" + fileName + " sheet "+ fileName);
        
		try{
				FileInputStream file = new FileInputStream(new File(filePath+fileName));		
				//Create Workbook instance holding reference to .xlsx file
		        HSSFWorkbook workbook = new HSSFWorkbook(file);
		        //Get first/desired sheet from the workbook
		        HSSFSheet sheet = workbook.getSheet(fileName);
		                
	        	Row row = sheet.getRow(rowNumForIndex); 
	        	 
	        	for (int cellCounter = 0; cellCounter <= row.getLastCellNum(); cellCounter++){
	        		if (row.getCell(cellCounter)!=null) {
	        			columnIndex.put(row.getCell(cellCounter).getStringCellValue(), cellCounter); 
		        	}	
		        }	
	        	System.out.println("Seccuessfully read "+ fileName + " sheet "+ fileName +", created the columnIndex");
	        	file.close();
		}
		catch (Exception e){
			e.printStackTrace (); 
			System.out.println("Problem in reading " + fileName + " sheet "+ fileName);
		}
		
		return columnIndex; 
		
		
//		---------------Just checking the index by printing it out , required for debugging ---------------
//		Iterator<String> keySetIterator = columnIndex.keySet().iterator(); 
//		while(keySetIterator.hasNext()){ 
//			String key = keySetIterator.next(); 
//			System.out.println("key: " + key + " value: " + columnIndex.get(key)); 
//		}

	}
	
	
	
	
	public void readExcelFiles (ArrayList <NeighbourInput_WeekCalcTemplate> temp_alist_NeighbourInput_WeekCalcTemplate, String fileName){
		try {
			System.out.println("Reading Excel file" + fileName + " sheet "+ fileName);
	        
			FileInputStream file = new FileInputStream(new File(filePath+fileName));		
			//Create Workbook instance holding reference to .xlsx file
	        HSSFWorkbook workbook = new HSSFWorkbook(file);
	        //Get first/desired sheet from the workbook
	        HSSFSheet sheet = workbook.getSheet(fileName);
			
	        
	        Iterator<Row> rowIterator = sheet.iterator();
	        
	        while (rowIterator.hasNext()) {
	        	Row row = rowIterator.next();
	        	
	        	
	        	if (row.getRowNum()>0){
	        	
	        		NeighbourInput_WeekCalcTemplate tempIssueData = new NeighbourInput_WeekCalcTemplate(); 
		    		
		    		if (row.getCell(columnIndex.get("Week Num"))!=null) tempIssueData.setWeekNum((int)row.getCell(columnIndex.get("Week Num")).getNumericCellValue());
		    		if (row.getCell(columnIndex.get("Week Start"))!=null) tempIssueData.setWeekStart(row.getCell(columnIndex.get("Week Start")).getDateCellValue());
		    		if (row.getCell(columnIndex.get("Week End"))!=null) tempIssueData.setWeekEnd(row.getCell(columnIndex.get("Week End")).getDateCellValue());
		    		
		    		if (row.getCell(columnIndex.get("inOinC"))!=null) tempIssueData.setInOinC((int)row.getCell(columnIndex.get("inOinC")).getNumericCellValue());
		    		if (row.getCell(columnIndex.get("erOinC"))!=null) tempIssueData.setErOinC((int)row.getCell(columnIndex.get("erOinC")).getNumericCellValue());
		    		if (row.getCell(columnIndex.get("inOltC"))!=null) tempIssueData.setInOltC((int)row.getCell(columnIndex.get("inOltC")).getNumericCellValue());
		    		if (row.getCell(columnIndex.get("erOltC"))!=null) tempIssueData.setErOltC((int)row.getCell(columnIndex.get("erOltC")).getNumericCellValue());
		    		if (row.getCell(columnIndex.get("inO"))!=null) tempIssueData.setInO((int)row.getCell(columnIndex.get("inO")).getNumericCellValue());
		    		if (row.getCell(columnIndex.get("erO"))!=null) tempIssueData.setErO((int)row.getCell(columnIndex.get("erO")).getNumericCellValue());
		    		
		    		if (row.getCell(columnIndex.get("Total Val"))!=null) tempIssueData.setTotalVal(row.getCell(columnIndex.get("Total Val")).getNumericCellValue());
		    		
		    		if (row.getCell(columnIndex.get("Release Number"))!=null) tempIssueData.setReleaseNum((int)row.getCell(columnIndex.get("Release Number")).getNumericCellValue());
		    		if (row.getCell(columnIndex.get("Release Start"))!=null) tempIssueData.setReleaseStart(row.getCell(columnIndex.get("Release Start")).getDateCellValue());
		    		if (row.getCell(columnIndex.get("Release End"))!=null) tempIssueData.setReleaseEnd(row.getCell(columnIndex.get("Release End")).getDateCellValue());
		    		if (row.getCell(columnIndex.get("Release Duration"))!=null) tempIssueData.setReleaseDuration(row.getCell(columnIndex.get("Release Duration")).getNumericCellValue());
		    		if (row.getCell(columnIndex.get("Release Completion"))!=null) tempIssueData.setReleaseCompletion(row.getCell(columnIndex.get("Release Completion")).getNumericCellValue());
		    		
		    		if (row.getCell(columnIndex.get("Release Category"))!=null) tempIssueData.setReleaseCategory(row.getCell(columnIndex.get("Release Category")).getStringCellValue());
		    		if (row.getCell(columnIndex.get("Normal value based on Duration"))!=null) tempIssueData.setNormalTotalVal(row.getCell(columnIndex.get("Normal value based on Duration")).getNumericCellValue());
		    		if (row.getCell(columnIndex.get("Project Name"))!=null) tempIssueData.setStrProjectName(row.getCell(columnIndex.get("Project Name")).getStringCellValue());
		    		if (row.getCell(columnIndex.get("Project Name & Release Num"))!=null) tempIssueData.setStrProjectNameRel(row.getCell(columnIndex.get("Project Name & Release Num")).getStringCellValue());
		    		if (row.getCell(columnIndex.get("Category"))!=null) tempIssueData.setStrOrgCategory(row.getCell(columnIndex.get("Category")).getStringCellValue());
		    		
		    			    				
		    		temp_alist_NeighbourInput_WeekCalcTemplate.add(tempIssueData); 
	        	}
	        }
	        
	        System.out.println("Seccuessfully read "+ fileName + " sheet "+ fileName +", created the isslueList");
	        file.close();
		}
		catch (Exception e){
			e.printStackTrace (); 
			System.out.println("Problem in reading "+ fileName + " sheet "+ fileName);
		}
	} 
	
	
	
	public void writeNeighBourFiles (ArrayList <NeighbourInput_WeekCalcTemplate> temp_alist_NeighbourInput_WeekCalcTemplate, String fileName,  boolean fileCreate, double highBound, double lowBound){
try {
			
			
			FileOutputStream fileOut=null; 
			FileInputStream inputWeeklyRateData=null; 
			HSSFWorkbook workbook=null; 
			
			if (fileCreate==true){
				fileOut = new FileOutputStream(filePath+fileName);
				workbook = new HSSFWorkbook();
			}
			else {
				inputWeeklyRateData = new FileInputStream(new File(filePath+fileName));
				workbook = new HSSFWorkbook(inputWeeklyRateData);
				
			}
			
			System.out.println("------------------"+fileName);
			HSSFSheet worksheetRate = workbook.createSheet(fileName);
			HSSFRow row; 
			HSSFCell cell;
			
			row= worksheetRate.createRow(0); 
			cell= row.createCell(0);	cell.setCellValue("Week Num");
			cell= row.createCell(1);	cell.setCellValue("Week Start");
			cell= row.createCell(2);	cell.setCellValue("Week End");
			cell= row.createCell(3);	cell.setCellValue("inOinC");
			cell= row.createCell(4);	cell.setCellValue("erOinC");
			cell= row.createCell(5);	cell.setCellValue("inOltC");
			cell= row.createCell(6);	cell.setCellValue("erOltC");
			cell= row.createCell(7);	cell.setCellValue("inO");
			cell= row.createCell(8);	cell.setCellValue("erO");
			cell= row.createCell(9);	cell.setCellValue("Total Val");

			cell= row.createCell(10); cell.setCellValue("Release Number");
			cell= row.createCell(11); cell.setCellValue("Release Start");
			cell= row.createCell(12); cell.setCellValue("Release End");
			cell= row.createCell(13); cell.setCellValue("Release Duration");
			cell= row.createCell(14); cell.setCellValue("Release Completion");
			cell= row.createCell(15); cell.setCellValue("Release Category");
			cell= row.createCell(16); cell.setCellValue("Normal value based on Duration");
			cell= row.createCell(17); cell.setCellValue("Project Name");
			cell= row.createCell(18); cell.setCellValue("Project Name & Release Num");
			cell= row.createCell(19); cell.setCellValue("Category");
			
			int rowCounter =1; 
			

			for (NeighbourInput_WeekCalcTemplate counter_NeighbourInput_WeekCalcTemplate : temp_alist_NeighbourInput_WeekCalcTemplate){
				if (counter_NeighbourInput_WeekCalcTemplate.getReleaseCompletion()>=lowBound && counter_NeighbourInput_WeekCalcTemplate.getReleaseCompletion()<=highBound){
					
				row= worksheetRate.createRow(rowCounter ); 
				rowCounter ++; 
				
				cell= row.createCell(0);    cell.setCellValue(counter_NeighbourInput_WeekCalcTemplate.getWeekNum());
				cell= row.createCell(1);	cell.setCellValue(counter_NeighbourInput_WeekCalcTemplate.getWeekStart());
				cell= row.createCell(2);	cell.setCellValue(counter_NeighbourInput_WeekCalcTemplate.getWeekEnd());
				cell= row.createCell(3);	cell.setCellValue(counter_NeighbourInput_WeekCalcTemplate.getInOinC());
				cell= row.createCell(4);	cell.setCellValue(counter_NeighbourInput_WeekCalcTemplate.getErOinC());
				cell= row.createCell(5);	cell.setCellValue(counter_NeighbourInput_WeekCalcTemplate.getInOltC());
				cell= row.createCell(6);	cell.setCellValue(counter_NeighbourInput_WeekCalcTemplate.getErOltC());
				cell= row.createCell(7);	cell.setCellValue(counter_NeighbourInput_WeekCalcTemplate.getInO());
				cell= row.createCell(8);	cell.setCellValue(counter_NeighbourInput_WeekCalcTemplate.getErO());
				cell= row.createCell(9);	cell.setCellValue(counter_NeighbourInput_WeekCalcTemplate.getTotalVal());
				
				cell= row.createCell(10); cell.setCellValue(counter_NeighbourInput_WeekCalcTemplate.getReleaseNum());
				cell= row.createCell(11); cell.setCellValue(counter_NeighbourInput_WeekCalcTemplate.getReleaseStart());
				cell= row.createCell(12); cell.setCellValue(counter_NeighbourInput_WeekCalcTemplate.getReleaseEnd());
				cell= row.createCell(13); cell.setCellValue(counter_NeighbourInput_WeekCalcTemplate.getReleaseDuration());
				cell= row.createCell(14); cell.setCellValue(counter_NeighbourInput_WeekCalcTemplate.getReleaseCompletion());
				cell= row.createCell(15); cell.setCellValue(counter_NeighbourInput_WeekCalcTemplate.getReleaseCategory());
				cell= row.createCell(16); cell.setCellValue(counter_NeighbourInput_WeekCalcTemplate.getNormalTotalVal());
				cell= row.createCell(17); cell.setCellValue(counter_NeighbourInput_WeekCalcTemplate.getStrProjectName());
				cell= row.createCell(18); cell.setCellValue(counter_NeighbourInput_WeekCalcTemplate.getStrProjectNameRel());
				cell= row.createCell(19); cell.setCellValue(counter_NeighbourInput_WeekCalcTemplate.getStrOrgCategory());
				
				
				}
				
			}
			
			
			
			if (fileCreate!=true){
				inputWeeklyRateData.close();
				fileOut = new FileOutputStream(filePath+fileName);
			}
			
			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
			System.out.println("Success: written "+ fileName);
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Error: writing "+ fileName);
		}catch (IOException e2){
			e2.printStackTrace();
			System.out.println("Error: writing "+ fileName);
		}
	}
	
	
	
}
