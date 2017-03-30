package CommitFileAnalysis;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import CommitFileAnalysis.tempcommitfiledata;






///////////////////////////////////


public class commitfilenalysis {
	
	static ArrayList<tempcommitfiledata> CommitData = new ArrayList<tempcommitfiledata>(); 
	static int intStartingRowofData = 1; 
	static HashMap<String, Integer> columnIndex = new HashMap <String, Integer> ();
	
	static String strFilePath = "C:/Users/S.M.Didar/OneDrive/Work Cloud/Eclipse Analysis/";
	static String fileName = "All commits.xls"; 
	static String sheetName="CommitData"; 
	
	
	
	static ArrayList <releaseInfo> alReleaseInfo = new ArrayList <releaseInfo> (); 
	
	static ArrayList <fileinfocount> alfileinfocount = new ArrayList <fileinfocount> (); 
	
	
	public static void fillingReleaseInfo (){
	
		alReleaseInfo.add(new releaseInfo (1,"2013-01-15"));
		alReleaseInfo.add(new releaseInfo (2,"2013-02-06"));
		alReleaseInfo.add(new releaseInfo (3,"2013-02-12"));
		alReleaseInfo.add(new releaseInfo (4,"2013-03-04"));
		alReleaseInfo.add(new releaseInfo (5,"2013-03-25"));
		alReleaseInfo.add(new releaseInfo (6,"2013-03-28"));
		alReleaseInfo.add(new releaseInfo (7,"2013-04-02"));
		alReleaseInfo.add(new releaseInfo (8,"2013-05-09"));
		alReleaseInfo.add(new releaseInfo (9,"2013-05-15"));
		alReleaseInfo.add(new releaseInfo (10,"2013-06-17"));
		alReleaseInfo.add(new releaseInfo (11,"2013-08-15"));
		alReleaseInfo.add(new releaseInfo (12,"2013-09-18"));
		alReleaseInfo.add(new releaseInfo (13,"2013-10-03"));
		alReleaseInfo.add(new releaseInfo (14,"2014-01-17"));
		alReleaseInfo.add(new releaseInfo (15,"2014-01-23"));
		alReleaseInfo.add(new releaseInfo (16,"2014-05-02"));
		alReleaseInfo.add(new releaseInfo (17,"2014-05-02"));
		alReleaseInfo.add(new releaseInfo (18,"2014-05-30"));
		alReleaseInfo.add(new releaseInfo (19,"2014-06-04"));
		alReleaseInfo.add(new releaseInfo (20,"2014-09-18"));
		alReleaseInfo.add(new releaseInfo (21,"2014-09-18"));
		alReleaseInfo.add(new releaseInfo (22,"2014-09-29"));
		alReleaseInfo.add(new releaseInfo (23,"2014-10-16"));
		alReleaseInfo.add(new releaseInfo (24,"2014-11-06"));
		alReleaseInfo.add(new releaseInfo (25,"2014-11-27"));
		alReleaseInfo.add(new releaseInfo (26,"2015-03-20"));
		alReleaseInfo.add(new releaseInfo (27,"2015-03-22"));
		alReleaseInfo.add(new releaseInfo (28,"2015-04-08"));
		alReleaseInfo.add(new releaseInfo (29,"2015-05-14"));
		alReleaseInfo.add(new releaseInfo (30,"2015-05-21"));
		alReleaseInfo.add(new releaseInfo (31,"2015-06-01"));
		alReleaseInfo.add(new releaseInfo (32,"2015-06-17"));
		alReleaseInfo.add(new releaseInfo (33,"2015-07-28"));
		alReleaseInfo.add(new releaseInfo (34,"2015-07-28"));
		alReleaseInfo.add(new releaseInfo (35,"2015-08-20"));
		alReleaseInfo.add(new releaseInfo (36,"2015-08-20"));
		alReleaseInfo.add(new releaseInfo (37,"2015-09-04"));
		alReleaseInfo.add(new releaseInfo (38,"2015-09-17"));
		alReleaseInfo.add(new releaseInfo (39,"2015-09-17"));
		alReleaseInfo.add(new releaseInfo (40,"2015-09-18"));
		alReleaseInfo.add(new releaseInfo (41,"2015-10-15"));
		alReleaseInfo.add(new releaseInfo (42,"2015-10-15"));
		alReleaseInfo.add(new releaseInfo (43,"2015-11-05"));
		alReleaseInfo.add(new releaseInfo (44,"2015-11-26"));
		alReleaseInfo.add(new releaseInfo (45,"2015-11-27"));
		alReleaseInfo.add(new releaseInfo (46,"2015-12-10"));
		alReleaseInfo.add(new releaseInfo (47,"2015-12-17"));
		alReleaseInfo.add(new releaseInfo (48,"2016-01-21"));
		alReleaseInfo.add(new releaseInfo (49,"2016-02-04"));
		alReleaseInfo.add(new releaseInfo (50,"2016-02-11"));
		alReleaseInfo.add(new releaseInfo (51,"2016-05-13"));
		alReleaseInfo.add(new releaseInfo (52,"2016-05-26"));
		alReleaseInfo.add(new releaseInfo (53,"2016-06-16"));
		alReleaseInfo.add(new releaseInfo (54,"2016-06-22"));
		alReleaseInfo.add(new releaseInfo (55,"2016-07-14"));
		alReleaseInfo.add(new releaseInfo (56,"2016-07-28"));
		alReleaseInfo.add(new releaseInfo (57,"2016-08-16"));
		alReleaseInfo.add(new releaseInfo (58,"2016-08-24"));
		alReleaseInfo.add(new releaseInfo (59,"2016-08-31"));
		alReleaseInfo.add(new releaseInfo (60,"2016-09-01"));
		alReleaseInfo.add(new releaseInfo (61,"2016-09-07"));
		alReleaseInfo.add(new releaseInfo (62,"2016-09-09"));
		alReleaseInfo.add(new releaseInfo (63,"2016-11-10"));
		alReleaseInfo.add(new releaseInfo (64,"2016-11-18"));
		alReleaseInfo.add(new releaseInfo (65,"2016-12-22"));

	
	
	}
	
	
	
	
	public static void main (String args[]){
		 createColumnIndex (0);
		 readExcelFiles ();
		 
		 fillingReleaseInfo (); 
		 
		 
		 for (int i=0;i<CommitData.size();i++){
			 for (int j=1;j<alReleaseInfo.size();j++){
				 
				 if(CommitData.get(i).commitDate.before(alReleaseInfo.get(0).releaseDate)){
					 CommitData.get(i).releaseNum = alReleaseInfo.get(0).ReleaseNum;
				 }
				 
				 
				 else if(CommitData.get(i).commitDate.after(alReleaseInfo.get(j-1).releaseDate) && CommitData.get(i).commitDate.before(alReleaseInfo.get(j).releaseDate)) {
					 CommitData.get(i).releaseNum = alReleaseInfo.get(j).ReleaseNum; 
//					 System.out.println("***************************"+ alReleaseInfo.get(j).ReleaseNum);		 
				}
			 }
		 }
		 
		 
		 
		 try {
		        BufferedWriter out = new BufferedWriter(new FileWriter("C:/Users/S.M.Didar/OneDrive/Work Cloud/Eclipse Analysis/commitVSrelease.csv"));
		        
		        
		        for (int i=0;i<CommitData.size();i++){
		        	out.write(
		        			CommitData.get(i).commitSha+","+
		        			CommitData.get(i).author+","+
		        			CommitData.get(i).commitDate +","+
		        			CommitData.get(i).numFiles+","+
		        			CommitData.get(i).numChurn+","+
		        			CommitData.get(i).numAddition+","+
		        			CommitData.get(i).numDeletion+","+
		        			CommitData.get(i).fileName+","+
//		        			CommitData.get(i).numCommitMessage+","+
//		        			CommitData.get(i).commitmessage+","+
		        			CommitData.get(i).releaseNum
		        			);
		        	out.newLine();
				}
		        
		        out.close();
		  } catch (IOException e) {}
		 
		 
		 
		 
		 
		 
		 
//		 
		 
		
		 
		 boolean flagFileMatch = false; 
		 
		 for (int i=0;i<CommitData.size();i++){
			 
			 flagFileMatch = false;
			 
			 for (int j=0;j<alfileinfocount.size();j++){
				 if (alfileinfocount.get(j).fileName.equalsIgnoreCase(CommitData.get(i).fileName)){
					 alfileinfocount.get(j).releaseCount[CommitData.get(i).releaseNum]++; 
					 alfileinfocount.get(j).additionCount[CommitData.get(i).releaseNum]= alfileinfocount.get(j).additionCount[CommitData.get(i).releaseNum]+ CommitData.get(i).numAddition;
					 alfileinfocount.get(j).deletionCount[CommitData.get(i).releaseNum]= alfileinfocount.get(j).deletionCount[CommitData.get(i).releaseNum]+ CommitData.get(i).numDeletion; 
					 flagFileMatch = true; 
				 }
			 }
			 
			 if (flagFileMatch == false){
				 
				 alfileinfocount.add(new fileinfocount ());
				 
				 alfileinfocount.get(alfileinfocount.size()-1).fileName = CommitData.get(i).fileName;
				 alfileinfocount.get(alfileinfocount.size()-1).releaseCount[CommitData.get(i).releaseNum]++; 
				 alfileinfocount.get(alfileinfocount.size()-1).additionCount[CommitData.get(i).releaseNum]= CommitData.get(i).numAddition;
				 alfileinfocount.get(alfileinfocount.size()-1).deletionCount[CommitData.get(i).releaseNum]= CommitData.get(i).numDeletion;
			 
			 }
		 }
		 
		 try {
		        BufferedWriter out = new BufferedWriter(new FileWriter("C:/Users/S.M.Didar/OneDrive/Work Cloud/Eclipse Analysis/fileVSrelease.csv"));
		        
		        for (int i=0;i<alfileinfocount.size();i++){
		        	
		        	out.write(alfileinfocount.get(i).fileName + ","); 
		        	for (int j=0;j<66;j++){
						 out.write(alfileinfocount.get(i).releaseCount[j]+","); 
					}
		        	out.newLine();
		        }
		                 
				out.close();
		  } catch (IOException e) {}
		 
		 
		 
		 
		 try {
		        BufferedWriter out = new BufferedWriter(new FileWriter("C:/Users/S.M.Didar/OneDrive/Work Cloud/Eclipse Analysis/fileVSreleaseaddition.csv"));
		        
		        for (int i=0;i<alfileinfocount.size();i++){
		        	
		        	out.write(alfileinfocount.get(i).fileName + ","); 
		        	for (int j=0;j<66;j++){
						 out.write(alfileinfocount.get(i).additionCount[j]+","); 
					}
		        	out.newLine();
		        }
		                 
				out.close();
		  } catch (IOException e) {}
		 
		 
		 
		 
		 try {
		        BufferedWriter out = new BufferedWriter(new FileWriter("C:/Users/S.M.Didar/OneDrive/Work Cloud/Eclipse Analysis/fileVSreleasedeletion.csv"));
		        
		        for (int i=0;i<alfileinfocount.size();i++){
		        	
		        	out.write(alfileinfocount.get(i).fileName + ","); 
		        	for (int j=0;j<66;j++){
						 out.write(alfileinfocount.get(i).deletionCount[j]+","); 
					}
		        	out.newLine();
		        }
		                 
				out.close();
		  } catch (IOException e) {}
		 
		 
		 try {
		        BufferedWriter out = new BufferedWriter(new FileWriter("C:/Users/S.M.Didar/OneDrive/Work Cloud/Eclipse Analysis/fileVSreleasechurn.csv"));
		        double dblTempChurn =0; 
		        for (int i=0;i<alfileinfocount.size();i++){
		        	
		        	out.write(alfileinfocount.get(i).fileName + ","); 
		        	for (int j=0;j<66;j++){
						 
		        		dblTempChurn = alfileinfocount.get(i).deletionCount[j] + alfileinfocount.get(i).additionCount[j]; 
		        		out.write(dblTempChurn+","); 
					}
		        	out.newLine();
		        }
		                 
				out.close();
		  } catch (IOException e) {}
		 
		 
		 
		 
	}//end main 
	
	
	
//	public commitfilenalysis (ArrayList<tempcommitfiledata> tmp_IssueData, int tmp_intStartingRowofData, String tmp_strFilePath, String tmp_strFileName, String tmp_strSheetName){
//		this.CommitData = tmp_IssueData; 
//		this.intStartingRowofData = tmp_intStartingRowofData;
//		this.fileName= tmp_strFilePath+tmp_strFileName;
//		this.strFilePath=tmp_strFilePath; 
//		this.sheetName= tmp_strSheetName; 
//		
//		//this.columnIndex = tmp_columnIndex; 
//	}
	
	
//	public static void main (String args []){
//		ArrayList<DataIssueTemplate> IssueData = new ArrayList<DataIssueTemplate>();  
//		String strFilePath = "C:/Users/S.M.Didar/Dropbox/Didar DBPC/PhD Research/Fall 2015/ICSE -Journal 2016/Evaluation/JIRA client/Archetype-451 - Copy/";
//		String strWrFile = "Out.xls"; 
//		int intStartingRowofData = 3; 
//		
//		createColumnIndex (strFilePath+strWrFile, strWrFile, IssueData); 
//		readExcelFiles (strFilePath+strWrFile, strWrFile, IssueData, intStartingRowofData); 
//		
//	}
	
	

	// Creating an index for all the columns in excel file to easily search for values. 
	public static HashMap<String, Integer> createColumnIndex (int rowNumForIndex){
		 
		
		System.out.println("Reading Excel file" + fileName + " sheet "+ sheetName);
        
		try{
				FileInputStream file = new FileInputStream(new File(strFilePath+fileName));		
				//Create Workbook instance holding reference to .xlsx file
		        HSSFWorkbook workbook = new HSSFWorkbook(file);
		        //Get first/desired sheet from the workbook
		        HSSFSheet sheet = workbook.getSheet(sheetName);
		        System.out.println(rowNumForIndex);
	        	Row row = sheet.getRow(rowNumForIndex); 
	        	
	        	
	        	 
	        	for (int cellCounter = 0; cellCounter <= row.getLastCellNum(); cellCounter++){
	        		if (row.getCell(cellCounter)!=null) {
	        			columnIndex.put(row.getCell(cellCounter).getStringCellValue(), cellCounter); 
		        	}	
		        }	
	        	System.out.println("Seccuessfully read "+ fileName + " sheet "+ sheetName +", created the columnIndex");
	        	file.close();
		}
		catch (Exception e){
			e.printStackTrace (); 
			System.out.println("Problem in reading " + fileName + " sheet "+ sheetName);
		}
		
		return columnIndex; 
		
		
//		---------------Just checking the index by printing it out , required for debugging ---------------
//		Iterator<String> keySetIterator = columnIndex.keySet().iterator(); 
//		while(keySetIterator.hasNext()){ 
//			String key = keySetIterator.next(); 
//			System.out.println("key: " + key + " value: " + columnIndex.get(key)); 
//		}

	}

	
	public Date convertStringDate (String datestr){
		String startDateString = datestr; //"06/27/2007";
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
	    Date startDate= null;
	    try {
	        startDate = df.parse(startDateString);
	        String newDateString = df.format(startDate);
//	        System.out.println(newDateString);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    
	    return startDate; 
	}
	
	
	public static void readExcelFiles (){
		try {
			System.out.println("Reading Excel file" + fileName + " sheet "+ sheetName);
	        
			FileInputStream file = new FileInputStream(new File(strFilePath+fileName));		
			//Create Workbook instance holding reference to .xlsx file
	        HSSFWorkbook workbook = new HSSFWorkbook(file);
	        //Get first/desired sheet from the workbook
	        HSSFSheet sheet = workbook.getSheet(sheetName);
			
	        
	        Iterator<Row> rowIterator = sheet.iterator();
	        
	        while (rowIterator.hasNext()) {
	        	Row row = rowIterator.next();
	        	intStartingRowofData--; // reducing starting row number, Wheen the number is zero we will start intaking data from the file 
	        	
	        	if (intStartingRowofData<0){
	        	
	        		tempcommitfiledata tempIssueData = new tempcommitfiledata(); 
		    		
		    		if (row.getCell(columnIndex.get("CommitSha"))!=null) tempIssueData.commitSha=row.getCell(columnIndex.get("CommitSha")).getStringCellValue();
		    		
//		    		System.out.println(tempIssueData.commitSha);
		    		if (row.getCell(columnIndex.get("Author"))!=null) tempIssueData.author=row.getCell(columnIndex.get("Author")).getStringCellValue();
		    		if (row.getCell(columnIndex.get("CommitDate"))!=null && row.getCell(columnIndex.get("CommitDate")).getCellType()!=HSSFCell.CELL_TYPE_STRING) tempIssueData.commitDate=row.getCell(columnIndex.get("CommitDate")).getDateCellValue();
		    		if (row.getCell(columnIndex.get("numFiles"))!=null) tempIssueData.numFiles=row.getCell(columnIndex.get("numFiles")).getNumericCellValue();
		    		if (row.getCell(columnIndex.get("totalChurn"))!=null) tempIssueData.numChurn=row.getCell(columnIndex.get("totalChurn")).getNumericCellValue();
		    		if (row.getCell(columnIndex.get("totalAddition"))!=null) tempIssueData.numAddition=row.getCell(columnIndex.get("totalAddition")).getNumericCellValue();
		    		if (row.getCell(columnIndex.get("totalDeletion"))!=null) tempIssueData.numDeletion=row.getCell(columnIndex.get("totalDeletion")).getNumericCellValue();
		    		if (row.getCell(columnIndex.get("fileName"))!=null) tempIssueData.fileName=row.getCell(columnIndex.get("fileName")).getStringCellValue();
		    		if (row.getCell(columnIndex.get("numMessage"))!=null) tempIssueData.numCommitMessage=row.getCell(columnIndex.get("numMessage")).getNumericCellValue();
		    		if (row.getCell(columnIndex.get("message"))!=null) tempIssueData.commitmessage=row.getCell(columnIndex.get("message")).getStringCellValue();
		    		
		    		
		    		
		    		
//		    		if (row.getCell(columnIndex.get("Component/s"))!=null) tempIssueData.setStrComponent(row.getCell(columnIndex.get("Component/s")).getStringCellValue());
//		    		if (row.getCell(columnIndex.get("Description"))!=null) tempIssueData.setStrDescription(row.getCell(columnIndex.get("Description")).getStringCellValue());
		    		
		    		
//		    		For analyzing commit information we need to use the following three fields. We can give create and resolve date same as the commit date and use this files to understand comkit erate , loc arate and files rate per week/day 
		    		
		    		
//		    		
//		    		if (row.getCell(columnIndex.get("Files"))!=null) tempIssueData.setDblNumberofFiles(Double.parseDouble(row.getCell(columnIndex.get("Files")).getStringCellValue()));
//		    		System.out.println("---------"+tempIssueData.getDblNumberofFiles());
//		    		if (row.getCell(columnIndex.get("Churn_Addition"))!=null) tempIssueData.setDblAdditionChurn (Double.parseDouble(row.getCell(columnIndex.get("Churn_Addition")).getStringCellValue()));
//		    		if (row.getCell(columnIndex.get("Churn_Deletion"))!=null) tempIssueData.setDblDeletionChurn(Double.parseDouble(row.getCell(columnIndex.get("Churn_Deletion")).getStringCellValue()));
		    		
		    		
		    		
		    		
		    		// --------------------- DATA READ EXCEL SETTINGS NEED TO BE ALTERED BASED ON IF A CONVERSION IS REQUIRED. ----------------------
		    		
		    		
		    		// Conversion not required
//		    		if (bool_ConversionRequired == false){
//		    		
//			    		if (row.getCell(columnIndex.get("Created"))!=null && row.getCell(columnIndex.get("Created")).getCellType()!=HSSFCell.CELL_TYPE_STRING) {
//			    			tempIssueData.setDateCreated(row.getCell(columnIndex.get("Created")).getDateCellValue());
//			    		}
//			    		if (row.getCell(columnIndex.get("Updated"))!=null && row.getCell(columnIndex.get("Updated")).getCellType()!=HSSFCell.CELL_TYPE_STRING) tempIssueData.setDateUpdated(row.getCell(columnIndex.get("Updated")).getDateCellValue());
//			    		if (row.getCell(columnIndex.get("Resolved"))!=null && row.getCell(columnIndex.get("Resolved")).getCellType()!=HSSFCell.CELL_TYPE_STRING) tempIssueData.setDateResolved(row.getCell(columnIndex.get("Resolved")).getDateCellValue());
//		    		}
//		    		
//		    		//CONVERSION REQUIRED-----------
//		    		
//		    		if (bool_ConversionRequired == true){
//			    		if (row.getCell(columnIndex.get("Created"))!=null && row.getCell(columnIndex.get("Created")).getCellType()==HSSFCell.CELL_TYPE_STRING){
//			    			tempIssueData.setDateCreated(convertStringDate(row.getCell(columnIndex.get("Created")).getStringCellValue()));
//			    		}
//			    		if (row.getCell(columnIndex.get("Updated"))!=null && row.getCell(columnIndex.get("Updated")).getCellType()==HSSFCell.CELL_TYPE_STRING) tempIssueData.setDateUpdated(convertStringDate(row.getCell(columnIndex.get("Updated")).getStringCellValue()));
//			    		if (row.getCell(columnIndex.get("Resolved"))!=null && row.getCell(columnIndex.get("Resolved")).getCellType()==HSSFCell.CELL_TYPE_STRING) tempIssueData.setDateResolved(convertStringDate(row.getCell(columnIndex.get("Resolved")).getStringCellValue()));
//		    		}
		    		
		    		// --------------------- DATA READ EXCEL SETTINGS NEED TO BE ALTERED BASED ON IF A CONVERSION IS REQUIRED. ----------------------
		    		
		    		
		    		
//		    		System.out.println( "XXXXXXXx"+ tempIssueData.getStrKey());
//		    		System.out.println( "XXXXXXXx"+ tempIssueData.getDateCreated());
		    		
		    		
		    		
//		    		if (row.getCell(columnIndex.get("Affects Version/s"))!=null){
//		    			if (row.getCell(columnIndex.get("Affects Version/s")).getCellType() != HSSFCell.CELL_TYPE_STRING ){
//		    				tempIssueData.setStrAffectVersion(Double.toString(row.getCell(columnIndex.get("Affects Version/s")).getNumericCellValue()));
//		    			}
//		    			else tempIssueData.setStrAffectVersion(row.getCell(columnIndex.get("Affects Version/s")).getStringCellValue());
//		    		}
//		    		
//		    		if (row.getCell(columnIndex.get("Fix Version/s"))!=null){
//		    			if (row.getCell(columnIndex.get("Fix Version/s")).getCellType() != HSSFCell.CELL_TYPE_STRING ){
//		    				tempIssueData.setStrFixVersion(Double.toString(row.getCell(columnIndex.get("Fix Version/s")).getNumericCellValue()));
//		    			}
//		    			else tempIssueData.setStrFixVersion(row.getCell(columnIndex.get("Fix Version/s")).getStringCellValue());
//		    		}
		    				
		    		CommitData.add(tempIssueData); 
	        	}
	        }
	        
	        System.out.println("Seccuessfully read "+ fileName + " sheet "+ sheetName +", created the isslueList");
	        file.close();
		}
		catch (Exception e){
			e.printStackTrace (); 
			System.out.println("Problem in reading "+ fileName + " sheet "+ sheetName);
		}
	} 
	
	
}//End of class



//-----------------Imp : Not a part of this program but useful later for Date manipulation ------------------------------		    		

//Date date = row.getCell(columnIndex.get("Created")).getDateCellValue(); 
//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//Calendar c = Calendar.getInstance();
//c.setTime(date); // Now use today date.
//c.add(Calendar.DATE, 5); // Adding 5 days
//String output = sdf.format(c.getTime());
//System.out.println("-------------"+output);


class releaseInfo {
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public int ReleaseNum=0; 
	public Date releaseDate = null;
	
	public releaseInfo (int tmpReleaseNum, String strReleaseDate){
		this.ReleaseNum = tmpReleaseNum; 
		
		try{
			releaseDate  = sdf.parse(strReleaseDate); 
		} 
		catch (Exception e){ 
			System.out.println("Problem in parsing release dates");
		}
		
	}
	
}



class fileinfocount {
	
	String fileName = null; 
	
	int releaseCount [] = new int [66]; 
	double additionCount [] = new double [66]; 
	double deletionCount [] = new double [66]; 
	
}