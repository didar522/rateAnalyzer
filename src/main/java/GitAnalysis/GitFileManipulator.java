package GitAnalysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class GitFileManipulator {

//	String strGitFileName = null; 
//	String strGitFilePath = null;
	
	
	
	
//	public GitFileManipulator (String tmp_strGitFileName, String tmp_strGitFilePath){
//		strGitFileName = tmp_strGitFileName; 
//		strGitFilePath = tmp_strGitFilePath; 
//	}
	
	
	
	
	
	
	
	
	public static ArrayList <al_GitInfo> objal_GitInfo = new ArrayList <al_GitInfo> (); 
	public static ArrayList <String> al_CommitTraceable = new ArrayList <String> (); 
	public static ArrayList <releaseinfo> al_releaseInfo = new ArrayList <releaseinfo> (); 
	
	
	public static String strFilePath = "C:/Users/S.M.Didar/OneDrive/Work Cloud/Eclipse Analysis/";
	
	public static String strInFileName = "platform-gitlogs.txt"; 
	public static String strOutFileNamePart = "platform"; 
		
	
	
	
	
	public static void main (String args[]){
		
		
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
		try {
			
			al_releaseInfo.add(new releaseinfo (df.parse("2016-06-16"), "Release-3.1.0")); 
			al_releaseInfo.add(new releaseinfo (df.parse("2016-05-26"), "Release-3.0.1")); 
			al_releaseInfo.add(new releaseinfo (df.parse("2016-05-13"), "Release-3.0.0")); 
			al_releaseInfo.add(new releaseinfo (df.parse("2016-02-12"), "dental-release-2.11.1")); 
			al_releaseInfo.add(new releaseinfo (df.parse("2016-02-05"), "dental-release-2.11.0")); 
			al_releaseInfo.add(new releaseinfo (df.parse("2016-01-22"), "dental-release-2.10.0")); 
			al_releaseInfo.add(new releaseinfo (df.parse("2015-12-18"), "dental-release-2.9.3")); 
			al_releaseInfo.add(new releaseinfo (df.parse("2015-12-11"), "dental-release-2.9.2")); 
			al_releaseInfo.add(new releaseinfo (df.parse("2015-11-30"), "dental-release-2.9.0")); 
			al_releaseInfo.add(new releaseinfo (df.parse("2015-11-06"), "dental-release-2.8.1")); 
			al_releaseInfo.add(new releaseinfo (df.parse("2015-10-16"), "dental-release-2.8.0")); 
			al_releaseInfo.add(new releaseinfo (df.parse("2015-09-23"), "dental-release-2.7.1")); 
			al_releaseInfo.add(new releaseinfo (df.parse("2015-09-18"), "dental-release-2.7.0")); 
			al_releaseInfo.add(new releaseinfo (df.parse("2015-09-08"), "dental-release-2.6.1")); 
			al_releaseInfo.add(new releaseinfo (df.parse("2015-08-21"), "dental-release-2.6.0")); 
			al_releaseInfo.add(new releaseinfo (df.parse("2015-07-28"), "dental-release-2.5.1")); 
			al_releaseInfo.add(new releaseinfo (df.parse("2015-07-24"), "dental-release-2.5.0")); 
			al_releaseInfo.add(new releaseinfo (df.parse("2015-06-18"), "dental-release-2.4.2"));
			al_releaseInfo.add(new releaseinfo (df.parse("2015-06-02"), "dental-release-2.4.1")); 
			al_releaseInfo.add(new releaseinfo (df.parse("2015-05-22"), "dental-release-2.4.0")); 
			
		} catch (ParseException e) {
		        e.printStackTrace();
		    }
		
		
		
		
		
		
		
		
		
		
		fileTextExtractor ();		
		System.out.println("Reading done----------"+ objal_GitInfo.size()+" issues ");
		
		writeExcelMatchGitLogs(); 
		System.out.println("Writing done------"+ strFilePath+strOutFileNamePart+"-MatchGitLogs.xls");
		
		writeExcelMatchNoMatchGitLogs  (); 
		System.out.println("Writing done------"+ strFilePath+strOutFileNamePart+"-Match/NoMatchGitLogs.xls");
		
		writeExcelTraceableCommits ();
		System.out.println("Writing done------"+ strFilePath+strOutFileNamePart+"-TraceCommits.xls");
		
		
		
		
		
		
		
		
		
		
//		System.out.println(convertToRegularDateFormat ("  Thu Jun 3 15:57:25 2016 -0600"));
		
		
	}
	
	
	
	public static void fileTextExtractor () {
		
		
		File file = new File(strFilePath+strInFileName);
//		File file = new File("C:/Users/S.M.Didar/OneDrive/Didar DBPC/PhD Research/Workspaces/Eclipse Analysis/web-gitlogs.txt");
		
		BufferedReader br = null;
		try {
		    
			br = new BufferedReader(new FileReader(file));
						


		    //now read the file line by line...
		    int intLineNum = 0, intCommitNum = 0;
		    
		    String line = null; 
		    boolean bool_treaceable = false; 
		    
		    
		    
		    while ((line = br.readLine()) != null) {
		    	
		    	
//		    	System.out.println("$$$$$$$$$$$$"+line.length());
//		    	System.out.println("------"+objal_GitInfo.size()+"---------"+line);
		    	
//		    	if (line.length()>=100){
//		    		line = line.substring(0,100); 
//		    	}
		    		    	
		    	
			        intLineNum++;
			        intCommitNum = objal_GitInfo.size()-1; 
			        
			        
			        
			        
			        if(line.contains("commit") && line.indexOf("commit")<2) { 
			        	
			        	
			        	
			        	if (bool_treaceable==true){
					    	al_CommitTraceable.add(objal_GitInfo.get(intCommitNum).strCommitSha);
					    }

			        	bool_treaceable = false;
			        	
			        	
			        	objal_GitInfo.add(new al_GitInfo ()); 
			        	intCommitNum++; 
			        	
			        	objal_GitInfo.get(intCommitNum).strCommitSha = line.substring(line.indexOf("commit"), line.indexOf("commit")+47).replace("commit ", ""); 
			        	
			        	System.out.println("Commit: "+intCommitNum+" ::"+ objal_GitInfo.get(intCommitNum).strCommitSha+"---------------------------------");
			        }
			        
			        if (line.contains("DENTAL-")){
			        	
			        	bool_treaceable = true;
			        	ArrayList<String> al_strtokens = new ArrayList <String> (); 
			        	
			        	StringTokenizing (al_strtokens, line); 
			        	
			        	for (String iterator:al_strtokens){
			        		if (iterator.contains("DENTAL-")){
			        			objal_GitInfo.get(intCommitNum).al_IssueNum.add(iterator);
			        			System.out.println("Issue ID: "+objal_GitInfo.get(intCommitNum).al_IssueNum.get(objal_GitInfo.get(intCommitNum).al_IssueNum.size()-1));
			        		}
			        	}
			        	
			        	
//			        	objal_GitInfo.get(intCommitNum).al_IssueNum.add(line.substring(line.indexOf("DENTAL"), line.indexOf("DENTAL")+11));
//			        	
//			        	System.out.println("Issue ID: "+objal_GitInfo.get(intCommitNum).al_IssueNum.get(objal_GitInfo.get(intCommitNum).al_IssueNum.size()-1));
			        }
			        
			        if (line.contains("MAIL-")){
			        	bool_treaceable = true;
			        	
			        	ArrayList<String> al_strtokens = new ArrayList <String> (); 
			        	
			        	StringTokenizing (al_strtokens, line); 
			        	
			        	for (String iterator:al_strtokens){
			        		if (iterator.contains("MAIL-")){
			        			objal_GitInfo.get(intCommitNum).al_IssueNum.add(iterator);
			        			System.out.println("Issue ID: "+objal_GitInfo.get(intCommitNum).al_IssueNum.get(objal_GitInfo.get(intCommitNum).al_IssueNum.size()-1));
			        		}
			        	}
			        	
			        	
//			        	objal_GitInfo.get(intCommitNum).al_IssueNum.add(line.substring(line.indexOf("MAIL"), line.indexOf("MAIL")+9));
//			        	
//			        	System.out.println("Issue ID: "+objal_GitInfo.get(intCommitNum).al_IssueNum.get(objal_GitInfo.get(intCommitNum).al_IssueNum.size()-1));
			        }
			        
			        if (line.contains("Author:")){
			        	objal_GitInfo.get(intCommitNum).strAuthor = line.replace("Author: ", ""); 
			        	
			        	System.out.println("Author: "+objal_GitInfo.get(intCommitNum).strAuthor);
			        }
			        
			        if (line.contains("Date:")){
			        	objal_GitInfo.get(intCommitNum).strDate = line.replace("Date: ", ""); 
			        	
			        	System.out.println("Date: "+objal_GitInfo.get(intCommitNum).strDate);
			        }
			        
			        if (line.contains("file changed,")||line.contains("files changed,")){
			        	String temp = line.substring(0, line.indexOf("file")); 
			        	temp = temp.trim(); 
			        	       	
				        objal_GitInfo.get(intCommitNum).intFileChanged = Integer.valueOf(temp); 
				        
			        	System.out.println("File changed: "+ objal_GitInfo.get(intCommitNum).intFileChanged);
			        }
			        
			        if (line.contains("insertion") && (line.contains("file changed")||line.contains("files changed,"))){
			        	String temp=null; 
			        	
	//		        	System.out.println(line.indexOf("file changed,"));
	//		        	System.out.println(line.indexOf("insertion"));
	//		        	System.out.println(line.substring(line.indexOf("files changed,")+17, line.indexOf("insertion")).trim());
			        
			        	 if (line.contains("file changed,")){
			        		 temp = line.substring(line.indexOf("file changed,")+13, line.indexOf("insertion")).trim(); 
			        	 }
			        	 
			        	 else if (line.contains("files changed,")) {
			        		 temp = line.substring(line.indexOf("files changed,")+15, line.indexOf("insertion")).trim(); 
			        	 }
	
			        	
			        	objal_GitInfo.get(intCommitNum).intInsertion = Integer.valueOf(temp); 
				        
			        	System.out.println("Insertion: "+ objal_GitInfo.get(intCommitNum).intInsertion);
			        }
	
					if (line.contains("deletion")&& (line.contains("file changed")||line.contains("files changed,"))){
						
						String temp = null; 
						
						if (line.contains("insertion")){
							temp = line.substring(line.indexOf("(+),")+4, line.indexOf("deletion"));
						}
						
						else if (line.contains("file changed,")){
			        		 temp = line.substring(line.indexOf("file changed,")+13, line.indexOf("deletion")).trim(); 
			        	 }
			        	 
			        	 else if (line.contains("files changed,")) {
			        		 temp = line.substring(line.indexOf("files changed,")+15, line.indexOf("deletion")).trim(); 
			        	 }
	
						temp = temp.trim(); 
						
			        	objal_GitInfo.get(intCommitNum).intDeletion = Integer.valueOf(temp); 
			        	
						System.out.println("Deletion: "+ line.substring(line.indexOf("(+),")+4, line.indexOf("deletion")));
					}
			        
			        if (line.contains ("|")){
			        	objal_GitInfo.get(intCommitNum).al_FileCommitDiff.add(new clsFileCommitDiff()); 
			        	
			        		objal_GitInfo.get(intCommitNum).al_FileCommitDiff.get(objal_GitInfo.get(intCommitNum).al_FileCommitDiff.size()-1).strFileName = line.substring(0,line.indexOf("|"));
			        		
			        		String temp = line.substring(line.indexOf("|")+1); 
			        		
			        		boolean isbin = false; 
			        		boolean isAddition = false; 
			        		boolean isDeletion = false; 
			        		
			        		if (temp.contains("Bin")) isbin = true;
			        		if (temp.contains("+")) isAddition = true;
			        		if (temp.contains("-")) isDeletion = true;
			        		
//			        		System.out.println(temp+"--------------------");
			        		
			        		if (isbin == false){
			        			
			        			temp = temp.replace("+", ""); 
				        		temp = temp.replace("-", ""); 
				        		temp = temp.trim();
			        			
			        			objal_GitInfo.get(intCommitNum).al_FileCommitDiff.get(objal_GitInfo.get(intCommitNum).al_FileCommitDiff.size()-1).intDiff = Integer.valueOf(temp);
			        			
			        			if (isAddition == true &&  isDeletion == false) { objal_GitInfo.get(intCommitNum).al_FileCommitDiff.get(objal_GitInfo.get(intCommitNum).al_FileCommitDiff.size()-1).intAddition = Integer.valueOf(temp); }
			        			if (isAddition == false &&  isDeletion == true) { objal_GitInfo.get(intCommitNum).al_FileCommitDiff.get(objal_GitInfo.get(intCommitNum).al_FileCommitDiff.size()-1).intDeletion = Integer.valueOf(temp); }
			        			if (isAddition == true &&  isDeletion == true) { objal_GitInfo.get(intCommitNum).al_FileCommitDiff.get(objal_GitInfo.get(intCommitNum).al_FileCommitDiff.size()-1).intModification = Integer.valueOf(temp); }
			        		}
		        	
			        		else if (isbin == true) {
//				        		objal_GitInfo.get(intCommitNum).al_FileCommitDiff.get(objal_GitInfo.get(intCommitNum).al_FileCommitDiff.size()-1).strFileName = line; 
			        			
			        			temp = temp.substring(temp.indexOf(">"), temp.indexOf("bytes")); 
			        			temp = temp.replace(">", "");
			        			temp =  temp.trim();
			        			
			        			objal_GitInfo.get(intCommitNum).al_FileCommitDiff.get(objal_GitInfo.get(intCommitNum).al_FileCommitDiff.size()-1).intBytes = Integer.valueOf(temp);
				        	}
				        	
				        	
//				        	System.out.println("File Name: "+objal_GitInfo.get(intCommitNum).al_FileCommitDiff.get(objal_GitInfo.get(intCommitNum).al_FileCommitDiff.size()-1).strFileName);
//				        	System.out.println("Diff: "+objal_GitInfo.get(intCommitNum).al_FileCommitDiff.get(objal_GitInfo.get(intCommitNum).al_FileCommitDiff.size()-1).intDiff);
			        
//			        }
		    	}  
			        
			    
			        
			        
			        
		    }
		} catch(FileNotFoundException e) { 
		    //handle this
			System.out.println("FileNotFoundException e");
			
		} catch (IOException e) {
	         e.printStackTrace();
	         System.out.println("IOException e");
	    } catch (IndexOutOfBoundsException e){
	    	System.out.println("IndexOutOfBoundsException e");
	    }
	}
	
	
	public static void StringTokenizing (ArrayList <String> al_strtokens, String tmpstr_line){
		StringTokenizer st = new StringTokenizer(tmpstr_line);
	    while (st.hasMoreTokens()) {
	    	System.out.println(tmpstr_line);
	    	al_strtokens.add(st.nextToken()); 
	    }
	}
	
	
	public static void writeExcelMatchGitLogs (){
		try {
//			FileOutputStream fileOutputCombineMemFiles = new FileOutputStream("C:/Users/S.M.Didar/OneDrive/Didar DBPC/PhD Research/Workspaces/Eclipse Analysis/platform-MatchGitLogs.xls");
			FileOutputStream fileOutputCombineMemFiles = new FileOutputStream(strFilePath+strOutFileNamePart+"MatchGitLogs.xls");
			
			System.out.println(strFilePath+strOutFileNamePart+"MatchGitLogs.xls");
			
			
			HSSFWorkbook workbookOutputCombineMemFiles = new HSSFWorkbook();
			HSSFSheet worksheetOutputCombineMemFiles = workbookOutputCombineMemFiles.createSheet(strOutFileNamePart+"-MatchGitLogs.xls");
			
			
			
			int intDataCounter =0; 
			
			for (int counterCommit=0;counterCommit<objal_GitInfo.size();counterCommit++){
				for (int counterIssue = 0; counterIssue<objal_GitInfo.get(counterCommit).al_IssueNum.size(); counterIssue ++){
					for (int counterFiles = 0; counterFiles<objal_GitInfo.get(counterCommit).al_FileCommitDiff.size();counterFiles++){
						HSSFRow row= null; 
						HSSFCell cell=null;
						
						row =  worksheetOutputCombineMemFiles.createRow(intDataCounter);
						intDataCounter++;
						
						
						cell = row.createCell(0);
						cell.setCellValue(objal_GitInfo.get(counterCommit).strCommitSha);
						cell = row.createCell(1);
						cell.setCellValue(objal_GitInfo.get(counterCommit).strAuthor);
						cell = row.createCell(2);
						cell.setCellValue(objal_GitInfo.get(counterCommit).strDate);
						
						cell = row.createCell(3);
						cell.setCellValue(objal_GitInfo.get(counterCommit).intFileChanged);
						cell = row.createCell(4);
						cell.setCellValue(objal_GitInfo.get(counterCommit).intInsertion);
						cell = row.createCell(5);
						cell.setCellValue(objal_GitInfo.get(counterCommit).intDeletion);
						
						if (objal_GitInfo.get(counterCommit).al_IssueNum.size()>0){
							cell = row.createCell(6);
							cell.setCellValue(objal_GitInfo.get(counterCommit).al_IssueNum.get(counterIssue));
						}
						
						if (objal_GitInfo.get(counterCommit).al_FileCommitDiff.size()>0){
							cell = row.createCell(7);
							cell.setCellValue(objal_GitInfo.get(counterCommit).al_FileCommitDiff.get(counterFiles).strFileName);
							cell = row.createCell(8);
							cell.setCellValue(objal_GitInfo.get(counterCommit).al_FileCommitDiff.get(counterFiles).intDiff);
							
							cell = row.createCell(9);
							cell.setCellValue(objal_GitInfo.get(counterCommit).al_FileCommitDiff.get(counterFiles).intAddition);
							cell = row.createCell(10);
							cell.setCellValue(objal_GitInfo.get(counterCommit).al_FileCommitDiff.get(counterFiles).intDeletion);
							cell = row.createCell(11);
							cell.setCellValue(objal_GitInfo.get(counterCommit).al_FileCommitDiff.get(counterFiles).intModification);
							cell = row.createCell(12);
							cell.setCellValue(objal_GitInfo.get(counterCommit).al_FileCommitDiff.get(counterFiles).intBytes);
							
							
							
							
							
						}
					}
				}
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
	
		
	public static void writeExcelMatchNoMatchGitLogs (){
		try {
//			FileOutputStream fileOutputCombineMemFiles = new FileOutputStream("C:/Users/S.M.Didar/OneDrive/Didar DBPC/PhD Research/Workspaces/Eclipse Analysis/platform-gitlogsJoint.xls");
			FileOutputStream fileOutputCombineMemFiles = new FileOutputStream(strFilePath+strOutFileNamePart+"NoMatchGitLogs.xls");
			HSSFWorkbook workbookOutputCombineMemFiles = new HSSFWorkbook();
			HSSFSheet worksheetOutputCombineMemFiles = workbookOutputCombineMemFiles.createSheet(strOutFileNamePart+"-MatchGitLogs.xls");
			
			int intDataCounter =0; 
			
			HSSFRow row= null; 
			HSSFCell cell=null;
			
			for (int counterCommit=0;counterCommit<objal_GitInfo.size();counterCommit++){
				
				row =  worksheetOutputCombineMemFiles.createRow(intDataCounter);
				intDataCounter++;
				
				
				cell = row.createCell(0);
				cell.setCellValue(objal_GitInfo.get(counterCommit).strCommitSha);
				cell = row.createCell(1);
				cell.setCellValue(objal_GitInfo.get(counterCommit).strAuthor);
				cell = row.createCell(2);
				
				
				
				
				cell.setCellValue(convertToRegularDateFormat (objal_GitInfo.get(counterCommit).strDate));
				cell = row.createCell(3);
				cell.setCellValue(findRelease (convertToRegularDateFormat (objal_GitInfo.get(counterCommit).strDate)));
				
				
	
				
				cell = row.createCell(4);
				cell.setCellValue(objal_GitInfo.get(counterCommit).intFileChanged);
				cell = row.createCell(5);
				cell.setCellValue(objal_GitInfo.get(counterCommit).intInsertion);
				cell = row.createCell(6);
				cell.setCellValue(objal_GitInfo.get(counterCommit).intDeletion);
				
				for (int counterIssue = 0; counterIssue<objal_GitInfo.get(counterCommit).al_IssueNum.size(); counterIssue ++){
					
					if (objal_GitInfo.get(counterCommit).al_IssueNum.size()>0){
						row =  worksheetOutputCombineMemFiles.createRow(intDataCounter);
						intDataCounter++;
						
						cell = row.createCell(7);
						cell.setCellValue(objal_GitInfo.get(counterCommit).al_IssueNum.get(counterIssue));
					}
					
					for (int counterFiles = 0; counterFiles<objal_GitInfo.get(counterCommit).al_FileCommitDiff.size();counterFiles++){
						
						if (objal_GitInfo.get(counterCommit).al_FileCommitDiff.size()>0){
							
							row =  worksheetOutputCombineMemFiles.createRow(intDataCounter);
							intDataCounter++;
							
							cell = row.createCell(8);
							cell.setCellValue(objal_GitInfo.get(counterCommit).al_FileCommitDiff.get(counterFiles).strFileName);
							cell = row.createCell(9);
							cell.setCellValue(objal_GitInfo.get(counterCommit).al_FileCommitDiff.get(counterFiles).intDiff);
						}
					}
				}
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
	
	
	public static void writeExcelTraceableCommits (){
		try {
//			FileOutputStream fileOutputCombineMemFiles = new FileOutputStream("C:/Users/S.M.Didar/OneDrive/Didar DBPC/PhD Research/Workspaces/Eclipse Analysis/CommitTraceable.xls");
			FileOutputStream fileOutputCombineMemFiles = new FileOutputStream(strFilePath+strOutFileNamePart+"-TraceCommits.xls");
			
			
			HSSFWorkbook workbookOutputCombineMemFiles = new HSSFWorkbook();
			HSSFSheet worksheetOutputCombineMemFiles = workbookOutputCombineMemFiles.createSheet(strOutFileNamePart+"-TraceCommits.xls");
			
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
	
	public static Date convertToRegularDateFormat (String strDateText){
		
		int intTmpIndex = -1; 
//		String strTmpDate = null; 
		
		if (strDateText.contains("-")){
			intTmpIndex = strDateText.indexOf("-"); 
			strDateText =  strDateText.replace(strDateText.substring(intTmpIndex, intTmpIndex+5), ""); 
		}
		
		if (strDateText.contains("+")){
			intTmpIndex = strDateText.indexOf("+"); 
			strDateText =  strDateText.replace(strDateText.substring(intTmpIndex, intTmpIndex+5), ""); 
		}
		
		strDateText =  strDateText.replace(strDateText.substring(0, 6), ""); 
		strDateText = strDateText.trim(); 
		
		
		intTmpIndex = strDateText.indexOf(":"); 
		strDateText =  strDateText.replace(strDateText.substring(intTmpIndex-3, intTmpIndex+6), ""); 
		
		strDateText =  strDateText.replace(" ", "-"); 
		
		strDateText =  strDateText.replace("Jan", "01"); 
		strDateText =  strDateText.replace("Feb", "02"); 
		strDateText =  strDateText.replace("Mar", "03"); 
		strDateText =  strDateText.replace("Apr", "04"); 
		strDateText =  strDateText.replace("May", "05"); 
		strDateText =  strDateText.replace("Jun", "06"); 
		strDateText =  strDateText.replace("Jul", "07"); 
		strDateText =  strDateText.replace("Aug", "08"); 
		strDateText =  strDateText.replace("Sep", "09"); 
		strDateText =  strDateText.replace("Oct", "10"); 
		strDateText =  strDateText.replace("Nov", "11"); 
		strDateText =  strDateText.replace("Dec", "12"); 
		
		
		
		
	    DateFormat df = new SimpleDateFormat("MM-dd-yyyy"); 
	    Date startDate = null;
	    try {
	        startDate = df.parse(strDateText);
	        String newDateString = df.format(startDate);
//	        System.out.println(newDateString);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
		
		return startDate; 
	}
	
	public static String findRelease (Date tmpCommitDate){
		
		String tempReleaseName = null; 
		
		for (int releaseFindCounter =0;releaseFindCounter < al_releaseInfo.size()-1; releaseFindCounter ++){
			if (tmpCommitDate.before(al_releaseInfo.get(releaseFindCounter).dtReleaseDate) && tmpCommitDate.after(al_releaseInfo.get(releaseFindCounter+1).dtReleaseDate)){
				tempReleaseName = al_releaseInfo.get(releaseFindCounter).strReleaseName; 
			}
			
			else if (tmpCommitDate.equals(al_releaseInfo.get(releaseFindCounter).dtReleaseDate)){
				tempReleaseName = al_releaseInfo.get(releaseFindCounter).strReleaseName; 
			}
		}
		
		return tempReleaseName; 
		
		
		
		
		
	}
	
	
	
	
}

class releaseinfo{ 
	
	Date dtReleaseDate = null; 
	String strReleaseName = null; 
	
	public releaseinfo (Date tmpReleaseDate, String strReleaseName){
		this.dtReleaseDate = tmpReleaseDate; 
		this.strReleaseName = strReleaseName; 
	}
}

