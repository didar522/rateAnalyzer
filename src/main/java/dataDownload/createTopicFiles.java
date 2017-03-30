package dataDownload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

//import pkgRRcalc.WeekCalcStr;

import java.io.*; 
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class createTopicFiles {

	ArrayList <String> SprintList = new ArrayList <String> (); 
	String inputFileName = "C:/Users/S.M.Didar/Dropbox/Didar DBPC/PhD Research/Fall 2015/ICSE Workshop/Evaluation/V4/Aurora/Combined.xls";  
	String filePath = "C:/Users/S.M.Didar/Dropbox/Didar DBPC/PhD Research/Fall 2015/ICSE Workshop/Evaluation/V4/";
	
	public void createTopics (String topicOutFileName){
	
		
		String outputFileName = filePath+"/Topic preprocess files/"+topicOutFileName;
		PrintStream stdout = System.out; 
		
		
		try {
			
			PrintStream out = new PrintStream(new FileOutputStream(outputFileName));
			System.setOut(out);
			
			
		FileInputStream file = new FileInputStream(new File(inputFileName));		
		//Create Workbook instance holding reference to .xlsx file
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        //Get first/desired sheet from the workbook
        HSSFSheet sheet = workbook.getSheetAt(0);
        
        //Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
        	Row row = rowIterator.next();	
		
        	boolean flagMatchIssue = false; 
        	
        	for (int i=0;i<SprintList.size();i++){
        		if (row.getCell(0).getStringCellValue().equals(SprintList.get(i))){
	        			flagMatchIssue = true; 
	        	}
        	}
        	
        	
        	if (flagMatchIssue==true){
        		out.println(row.getCell(6).getStringCellValue());
        		out.println(row.getCell(7).getStringCellValue());
        		out.println(row.getCell(9).getStringCellValue());
        	}	
        }  			
        
        out.close();
    }
	
	catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
//		System.out.println(outputFileName + " Caused problem");
	}
		
	catch (Exception e){
//		System.out.println(" other Caused problem");
		e.printStackTrace();
	}
        	
	System.setOut(stdout);    	
    System.out.println("Topic files successfully written ");
}

	
	
	
	
public void creatingTopicsfromSprints (String sheetName, String keyText, String topicOutFileName){
		
		System.out.println("For sprint "+ sheetName);
		readSprintExcelFile ("C:/Users/S.M.Didar/Dropbox/Didar DBPC/PhD Research/Fall 2015/ICSE Workshop/Evaluation/V4/Sprints.xls", sheetName, keyText);
//		"AURORA-"
		for (int i=0;i<SprintList.size();i++){
			System.out.println(SprintList.get(i));
		}
		
		createTopics (topicOutFileName); 
		
	}
	
	

public void readSprintExcelFile (String fileName, String sheetName, String keyText){
	
	
	
	try {
		
		System.out.println("Reading Sprint Excel file");
        FileInputStream file = new FileInputStream(new File(fileName));		
		//Create Workbook instance holding reference to .xlsx file
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        //Get first/desired sheet from the workbook
        HSSFSheet sheet = workbook.getSheet(sheetName);
        		
        		
        
        //Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
        	Row row = rowIterator.next();
        	
        	String temptext = row.getCell(0).getStringCellValue(); 
        	
        	if (temptext.contains(keyText)){
        		if (temptext.contains("*")){
        			temptext = temptext.replace("*", "");
   			    }
        		SprintList.add (temptext); 
        	}
        }	
        	
        	  	
        file.close();
	}
	catch (Exception e){
		e.printStackTrace (); 
//		System.out.println("Problem in reading");
	}
	
	
            	
} // end of read excel method


public String  extractText(String strToParse, String strStart, String strFinish){
	  
	Pattern pattern = Pattern.compile(strStart+"(.*?)"+strFinish);
	Matcher matcher = pattern.matcher(strToParse);
	String fixVersion = null;
	
	if (matcher.find())
	{
		fixVersion = matcher.group(0); 
//		fixVersion = fixVersion.replace(strStart, ""); 
//		fixVersion = fixVersion.replace(strFinish, ""); 
	}
	
	return fixVersion; 
}





} // End of class 