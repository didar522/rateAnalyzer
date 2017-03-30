package GitAnalysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class CopyOfGitFileManipulator {

//	String strGitFileName = null; 
//	String strGitFilePath = null;
	ArrayList <al_GitInfo> objList_ListTemplateGitMining = new ArrayList <al_GitInfo> (); 
	
	
	
//	public GitFileManipulator (String tmp_strGitFileName, String tmp_strGitFilePath){
//		strGitFileName = tmp_strGitFileName; 
//		strGitFilePath = tmp_strGitFilePath; 
//	}
	
	
	public void fileTextExtractor (String strGitFileName, String strGitFilePath){
		
		System.out.println("QQQQQQQQQQQQQQQQ");
//		File file = new File(strGitFilePath+strGitFileName);
		File file = new File("C:/Users/S.M.Didar/OneDrive/Didar DBPC/PhD Research/Workspaces/Eclipse Analysis/platform-gitlogs.txt");

		try {
		    Scanner scanner = new Scanner(file);

		    //now read the file line by line...
		    int lineNum = 0;
		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        lineNum++;
		        if(line.contains("commit")) { 
		        	objList_ListTemplateGitMining.add(new al_GitInfo ()); 
		        	objList_ListTemplateGitMining.get(objList_ListTemplateGitMining.size()-1).strCommitSha = line.substring(line.indexOf("commit"), line.indexOf("commit")+47); 
		        	System.out.println(objList_ListTemplateGitMining.get(objList_ListTemplateGitMining.size()-1).strCommitSha+"---------------------------------");
		        }
		        
		        if (line.contains("DENTAL-")){
		        	objList_ListTemplateGitMining.get(objList_ListTemplateGitMining.size()-1).lstIssueNum.add(line.substring(line.indexOf("DENTAL"), line.indexOf("DENTAL")+11));
		        	System.out.println(objList_ListTemplateGitMining.get(objList_ListTemplateGitMining.size()-1).lstIssueNum.get(objList_ListTemplateGitMining.get(objList_ListTemplateGitMining.size()-1).lstIssueNum.size()-1));
		        }
		        
		        if (line.contains("MAIL-")){
		        	objList_ListTemplateGitMining.get(objList_ListTemplateGitMining.size()-1).lstIssueNum.add(line.substring(line.indexOf("MAIL"), line.indexOf("MAIL")+9));
		        	System.out.println(objList_ListTemplateGitMining.get(objList_ListTemplateGitMining.size()-1).lstIssueNum.get(objList_ListTemplateGitMining.get(objList_ListTemplateGitMining.size()-1).lstIssueNum.size()-1));
		        }
		        
		        if (line.contains("Author:")){
		        	objList_ListTemplateGitMining.get(objList_ListTemplateGitMining.size()-1).strAuthor = line; 
		        	System.out.println(objList_ListTemplateGitMining.get(objList_ListTemplateGitMining.size()-1).strAuthor);
		        }
		        
		        if (line.contains("Date:")){
		        	objList_ListTemplateGitMining.get(objList_ListTemplateGitMining.size()-1).strDate = line; 
		        	System.out.println(objList_ListTemplateGitMining.get(objList_ListTemplateGitMining.size()-1).strDate);
		        }
		        
		        if (line.contains("file changed")){
		        	System.out.println("File changed@@@@@@@@@@@@"+ line.substring(0, line.indexOf("file changed")));
		        	System.out.println("Insert@@@@@@@@@@@@"+ line.indexOf("file changed"), line.indexOf("insertions"));
		        	System.out.println("Deletion@@@@@@@@@@@@"+ line.substring(line.indexOf("insertions")+10, line.indexOf("deletion")));
		        	
		        }
		        
		        
		        
		        
		        
		    }
		} catch(FileNotFoundException e) { 
		    //handle this
		}
	}
	
	
	public void writeExcel (){
//		try {
//			FileOutputStream fileOutputCombineMemFiles = new FileOutputStream(strGitFileName+strOutputCombineMemFilesName+".xls" );
//			HSSFWorkbook workbookOutputCombineMemFiles = new HSSFWorkbook();
//			HSSFSheet worksheetOutputCombineMemFiles = workbookOutputCombineMemFiles.createSheet(strOutputCombineMemFilesName+".xls" );
//			
//			int intweeklyDataCounter =0; 
//			
//			for (int intCounterMemData=0;intCounterMemData<listBFR_classMembershipDataInputTemplate.size();intCounterMemData++){
//				HSSFRow row= null; 
//				HSSFCell cell=null;
//				
//				row =  worksheetOutputCombineMemFiles.createRow(intweeklyDataCounter);
//				intweeklyDataCounter++;
//				
//				
//				cell = row.createCell(0);
//				cell.setCellValue(listBFR_classMembershipDataInputTemplate.get(intCounterMemData).getDblWeekNum());
//				cell = row.createCell(1);
//				cell.setCellValue(listBFR_classMembershipDataInputTemplate.get(intCounterMemData).getStrReleaseName());
//				cell = row.createCell(2);
//			
//				workbookOutputCombineMemFiles .write(fileOutputCombineMemFiles);
//				fileOutputCombineMemFiles.flush();
//				fileOutputCombineMemFiles.close();
////				System.out.println("Success: Combine mem data written");
//				
//			} catch (FileNotFoundException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//				System.out.println("Error: ombine mem data written");
//			}catch (IOException e2){
//				e2.printStackTrace();
//				System.out.println("Error: ombine mem data written");
//			}
//			
	}
	
	
}
