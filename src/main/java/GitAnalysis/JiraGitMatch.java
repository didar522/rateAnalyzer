package GitAnalysis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import dataTypeTemplates.DataIssueTemplate;

public class JiraGitMatch {

	
	String filePath = "C:/Users/S.M.Didar/OneDrive/Didar DBPC/PhD Research/Workspaces/Eclipse Analysis/"; 
	String strFileName = ""; 
	String strGitSheetName = ""; 
	String strJiraSheetName = ""; 
	
	ArrayList <clsJiraDataset> al_clsJiraDataset = new ArrayList <clsJiraDataset> (); 
	ArrayList <clsGitLogs> al_clsGitLogs = new ArrayList <clsGitLogs> (); 
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		
		
	}

	
	public void readJiraFiles (){
		try {
			System.out.println("Reading JIRA Excel file");
	        
			FileInputStream file = new FileInputStream(new File(filePath+strFileName));		
			//Create Workbook instance holding reference to .xlsx file
	        HSSFWorkbook workbook = new HSSFWorkbook(file);
	        //Get first/desired sheet from the workbook
	        HSSFSheet Jirasheet = workbook.getSheet(strJiraSheetName);
	        HSSFSheet Gitsheet = workbook.getSheet(strGitSheetName);
	        
	        Iterator<Row> rowIteratorJira = Jirasheet.iterator();
	        
	        
	        while (rowIteratorJira.hasNext()) {
	        	Row row = rowIteratorJira.next();
	        	
	        		clsJiraDataset temp_clsJiraDataset = new clsJiraDataset ();   		
		        	
		    		
		    		if (row.getCell(0)!=null) temp_clsJiraDataset.strJiraID = row.getCell(0).getStringCellValue(); 
		    		if (row.getCell(1)!=null) temp_clsJiraDataset.strIssueType = row.getCell(1).getStringCellValue(); 
		    		if (row.getCell(2)!=null) temp_clsJiraDataset.strStatu = row.getCell(2).getStringCellValue(); 
		    		if (row.getCell(3)!=null) temp_clsJiraDataset.strPriority = row.getCell(3).getStringCellValue(); 
		    		if (row.getCell(4)!=null) temp_clsJiraDataset.strAssignee = row.getCell(4).getStringCellValue(); 
		    		if (row.getCell(5)!=null) temp_clsJiraDataset.strCreator= row.getCell(5).getStringCellValue(); 
		    		
		    		
		    		if (row.getCell(6)!=null && row.getCell(6).getCellType()!=HSSFCell.CELL_TYPE_STRING) temp_clsJiraDataset.dateCreated = row.getCell(6).getDateCellValue();
		    		if (row.getCell(7)!=null && row.getCell(7).getCellType()!=HSSFCell.CELL_TYPE_STRING) temp_clsJiraDataset.dateUpdated = row.getCell(7).getDateCellValue();
		    		if (row.getCell(8)!=null && row.getCell(8).getCellType()!=HSSFCell.CELL_TYPE_STRING) temp_clsJiraDataset.dateUpdated = row.getCell(8).getDateCellValue();
		    		
		    		if (row.getCell(9)!=null) temp_clsJiraDataset.strAffectversion = row.getCell(9).getStringCellValue(); 
		    		if (row.getCell(10)!=null) temp_clsJiraDataset.strFixversion = row.getCell(10).getStringCellValue(); 
		    		if (row.getCell(11)!=null) temp_clsJiraDataset.strComponent = row.getCell(11).getStringCellValue(); 
		    		
		    		al_clsJiraDataset.add(temp_clsJiraDataset); 
	        }
	        
	        Iterator<Row> rowIteratorGit = Gitsheet.iterator();
	        
	        while (rowIteratorJira.hasNext()) {
	        	Row row = rowIteratorJira.next();
	        	
	        		clsGitLogs temp_clsJiraDataset = new clsGitLogs ();   		
		        	
		    		
		    		if (row.getCell(0)!=null) temp_clsJiraDataset.strJiraID = row.getCell(0).getStringCellValue(); 
		    		if (row.getCell(1)!=null) temp_clsJiraDataset.strIssueType = row.getCell(1).getStringCellValue(); 
		    		if (row.getCell(2)!=null) temp_clsJiraDataset.strStatu = row.getCell(2).getStringCellValue(); 
		    		if (row.getCell(3)!=null) temp_clsJiraDataset.strPriority = row.getCell(3).getStringCellValue(); 
		    		if (row.getCell(4)!=null) temp_clsJiraDataset.strAssignee = row.getCell(4).getStringCellValue(); 
		    		if (row.getCell(5)!=null) temp_clsJiraDataset.strCreator= row.getCell(5).getStringCellValue(); 
		    		
		    		
		    		if (row.getCell(6)!=null && row.getCell(6).getCellType()!=HSSFCell.CELL_TYPE_STRING) temp_clsJiraDataset.dateCreated = row.getCell(6).getDateCellValue();
		    		if (row.getCell(7)!=null && row.getCell(7).getCellType()!=HSSFCell.CELL_TYPE_STRING) temp_clsJiraDataset.dateUpdated = row.getCell(7).getDateCellValue();
		    		if (row.getCell(8)!=null && row.getCell(8).getCellType()!=HSSFCell.CELL_TYPE_STRING) temp_clsJiraDataset.dateUpdated = row.getCell(8).getDateCellValue();
		    		
		    		if (row.getCell(9)!=null) temp_clsJiraDataset.strAffectversion = row.getCell(9).getStringCellValue(); 
		    		if (row.getCell(10)!=null) temp_clsJiraDataset.strFixversion = row.getCell(10).getStringCellValue(); 
		    		if (row.getCell(11)!=null) temp_clsJiraDataset.strComponent = row.getCell(11).getStringCellValue(); 
		    		
		    		al_clsJiraDataset.add(temp_clsJiraDataset); 
	        }
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        System.out.println("Seccuessfully read "+ fileName + " sheet "+ sheetName +", created the isslueList");
	        file.close();
		}
		catch (Exception e){
			e.printStackTrace (); 
			System.out.println("Problem in reading "+ fileName + " sheet "+ sheetName);
		}
	} 
	
	
	
	public static void writeExceltraceable (){
		try {
			FileOutputStream fileOutputCombineMemFiles = new FileOutputStream("C:/Users/S.M.Didar/OneDrive/Didar DBPC/PhD Research/Workspaces/Eclipse Analysis/CommitTraceable.xls");
			HSSFWorkbook workbookOutputCombineMemFiles = new HSSFWorkbook();
			HSSFSheet worksheetOutputCombineMemFiles = workbookOutputCombineMemFiles.createSheet("CommitTraceable"+".xls" );
			
			int intDataCounter =0; 
			
			HSSFRow row= null; 
			HSSFCell cell=null;
			
			row =  worksheetOutputCombineMemFiles.createRow(intDataCounter);
			intDataCounter++;
			cell = row.createCell(0);
			cell.setCellValue("Traceable");
			
			
			for (int counterCommit=0;counterCommit<al_CommitTraceable.size();counterCommit++){
				
				row =  worksheetOutputCombineMemFiles.createRow(intDataCounter);
				intDataCounter++;
				
				
				cell = row.createCell(0);
				cell.setCellValue(al_CommitTraceable.get(counterCommit));
			}
			
			
				workbookOutputCombineMemFiles.write(fileOutputCombineMemFiles);
				fileOutputCombineMemFiles.flush();
				fileOutputCombineMemFiles.close();
//				System.out.println("Success: Combine mem data written");
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("Error: ombine mem data written");
				
			}catch (IOException e2){
				e2.printStackTrace();
				System.out.println("Error: ombine mem data written");
			}
//			
	}
	
	
	
	
	
}



class clsJiraDataset {
	String strJiraID= null; 
	String strIssueType = null; 
	String strStatu = null; 
	String strPriority = null; 
	String strAssignee = null; 
	String strCreator = null; 
	Date dateCreated; 
	Date dateUpdated; 
	Date dateResolved; 
	String strAffectversion = null; 
	String strFixversion = null; 
	String strComponent = null; 
}


class clsGitLogs {
	String strCommitSha = null; 
	String strCommitter = null; 
	String strDate = null; 
	int intFileChange = 0; 
	int intInsertion = 0; 
	int intDeletion = 0; 
	String strIssueID = null; 
	String strFileName = null; 
	int intDiffFile = 0; 
	
	
	String strJiraID= null; 
	String strIssueType = null; 
	String strStatu = null; 
	String strPriority = null; 
	String strAssignee = null; 
	String strCreator = null; 
	Date dateCreated; 
	Date dateUpdated; 
	Date dateResolved; 
	String strAffectversion = null; 
	String strFixversion = null; 
	String strComponent = null; 
	
	
	
	
}