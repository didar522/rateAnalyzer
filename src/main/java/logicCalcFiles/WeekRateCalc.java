package logicCalcFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import dataTypeTemplates.DataIssueTemplate;
import dataTypeTemplates.DataReleaseCategoryTemplate;
import dataTypeTemplates.WeekCalcTemplate;


public class WeekRateCalc {
	
	String filePath; 
	String fileName; 
	String outputFileName; 
	String outputSheetName;
	String outputLastValFileName;
	ArrayList<WeekCalcTemplate> weekRateData; 
	ArrayList<DataIssueTemplate> tempIssueData = new ArrayList<DataIssueTemplate>();
	boolean fileCreate; 
	boolean boolCalculationTypeisRate, booloutputLastValFileCreate; 
	ArrayList <DataReleaseCategoryTemplate> objDataReleaseCategoryTemplate = new ArrayList<DataReleaseCategoryTemplate> (); 
	
	
	
	public WeekRateCalc (
			String filePath, 
			String tmpFileName, 
			String tmpOutputFileName, 
			String tmpOutputSheetName, 
			ArrayList<DataIssueTemplate> IssueData, 
			ArrayList<WeekCalcTemplate> tmpweekRateData, 
			boolean tmpFileCreate, 
			boolean tmp_boolCalculationTypeisRate, 
			String temp_outputLastValFileName, 
			boolean temp_booloutputLastValFileCreate
	){
		
		this.filePath = filePath; 
		this.fileName= tmpFileName;
		this.outputFileName = tmpOutputFileName; 
		this.tempIssueData = IssueData; 
		this.weekRateData = tmpweekRateData;
		this.fileCreate = tmpFileCreate;
		this.boolCalculationTypeisRate = tmp_boolCalculationTypeisRate;
		this.outputSheetName = tmpOutputSheetName; 
		this.outputLastValFileName = temp_outputLastValFileName;
		this.booloutputLastValFileCreate = temp_booloutputLastValFileCreate; 
	}
	
	

//	public void weeklyRateCalc (String fileName, ArrayList<WeekCalcStr> weekRateData, ArrayList<IssueListStr> tempIssueData, boolean fileCreate){
	public void weeklyRateCalc (){
		String startDateCompare=null; 
		String closeDateCompare=null; 
		String startWKDateCompare=null; 
		
		Date issueCreated, issueClosed, weekStart, weekEnd, lastWeekEnd=null; 
		
		for (int i=0;i<weekRateData.size();i++){
			for (int j=1;j<tempIssueData.size();j++){
				
				issueCreated = tempIssueData.get(j).getDateCreated(); 
				issueClosed = tempIssueData.get(j).getDateResolved(); 
				weekStart = weekRateData.get(i).getWeekStart(); 
				weekEnd = weekRateData.get(i).getWeekEnd(); 
				if (weekRateData.get(i).getWeekNum()>1){
					lastWeekEnd= weekRateData.get(i-1).getWeekEnd();
				}
				else if (weekRateData.get(i).getWeekNum()==1) {
					lastWeekEnd = weekStart;
				}
				
				
				if (issueCreated.before(weekStart)) startDateCompare="before";  // -1 means issue created before week
				else if (issueCreated.after (weekEnd)) startDateCompare="after";
				else if (issueCreated.after(weekStart) && 	issueCreated.before (weekEnd))  startDateCompare="inWeek"; 
				else if (issueCreated.equals(weekStart) || issueCreated.equals(weekEnd)) startDateCompare="inWeek"; 		
						
				
				if (issueClosed == null) closeDateCompare="open"; 
				else if (issueClosed.before(weekStart)) closeDateCompare="before";  // -1 means issue created before week
				else if (issueClosed.after (weekEnd)) closeDateCompare="after";
				else if (issueClosed.after(weekStart) && 	issueClosed.before (weekEnd))  closeDateCompare="inWeek"; 
				else if (issueClosed.equals(weekStart) || issueClosed.equals(weekEnd)) closeDateCompare="inWeek"; 	
				
				//// this is something to fix ----------------------------XXXXXXX
				if(boolCalculationTypeisRate==false){
					if (issueCreated.before(lastWeekEnd)) startWKDateCompare="sngbefore";  // -1 means issue created before week
					else if (issueCreated.after (weekEnd)) startWKDateCompare="sngafter";
					else if (issueCreated.after(lastWeekEnd) && 	issueCreated.before (weekEnd))  startWKDateCompare="snginWeek"; 
					else if (issueCreated.equals(lastWeekEnd) || issueCreated.equals(weekEnd)) startWKDateCompare="snginWeek"; 
					if (startWKDateCompare.equals("snginWeek")) weekRateData.get(i).weeklyVal++;
				}	
				
				if (startDateCompare.equals("inWeek") && closeDateCompare.equals("inWeek"))  {
					weekRateData.get(i).inOinC++;
					
					weekRateData.get(i).dblTotalFiles =  weekRateData.get(i).dblTotalFiles + tempIssueData.get(j).getDblNumberofFiles();
					weekRateData.get(i).dblTotalAddition =  weekRateData.get(i).dblTotalAddition + tempIssueData.get(j).getDblAdditionChurn(); 
					weekRateData.get(i).dblTotalDeletion = weekRateData.get(i).dblTotalDeletion + tempIssueData.get(j).getDblDeletionChurn(); 
				}
				if (startDateCompare.equals("before") && closeDateCompare.equals("inWeek")) weekRateData.get(i).erOinC++;
				if (startDateCompare.equals("inWeek") && closeDateCompare.equals("after")) weekRateData.get(i).inOltC++;
				if (startDateCompare.equals("before") && closeDateCompare.equals("after")) weekRateData.get(i).erOltC++;
				if (startDateCompare.equals("inWeek") && closeDateCompare.equals("open")) weekRateData.get(i).inO++; 
				if (startDateCompare.equals("before") && closeDateCompare.equals("open")) weekRateData.get(i).erO++;
				
				
			}
		
//			double tmpTotalInWeekValues=0, tmpTotalAllInOutWeekValues=0, tmpTotalTransferFromEarlyRelease=0,tmpTotalOpenThisRelease=0,tmpTotalerOleftOpen=0,tmpTotalinOleftOpen=0; 
			
			// this is to be fixed -----------------------XXXXXXX 
			if (boolCalculationTypeisRate==false) {
				weekRateData.get(i).tmpTotalInWeekValues = weekRateData.get(i).weeklyVal; 
			}
			else {
				weekRateData.get(i).tmpTotalInWeekValues = (weekRateData.get(i).inOinC+weekRateData.get(i).erOinC); // total close this rel
				weekRateData.get(i).tmpTotalTransferFromEarlyRelease = weekRateData.get(i).erO + weekRateData.get(i).erOinC+weekRateData.get(i).erOltC; 
				weekRateData.get(i).tmpTotalOpenThisRelease = weekRateData.get(i).inOinC+ weekRateData.get(i).inOltC + weekRateData.get(i).inO; 
			
			// early open in close ---- weekRateData.get(i).erOinC
			// in open in close ---- weekRateData.get(i).inOinC
				weekRateData.get(i).tmpTotalerOleftOpen =  weekRateData.get(i).erOltC + weekRateData.get(i).erO; 
				weekRateData.get(i).tmpTotalinOleftOpen = weekRateData.get(i).inOltC + weekRateData.get(i).inO; 
			
			
			
			}
			weekRateData.get(i).tmpTotalAllInOutWeekValues = (weekRateData.get(i).inOinC+weekRateData.get(i).erOinC+ weekRateData.get(i).inOltC+weekRateData.get(i).erOltC+weekRateData.get(i).inO+weekRateData.get(i).erO);
			
			weekRateData.get(i).settotalRateVal(weekRateData.get(i).tmpTotalInWeekValues/weekRateData.get(i).tmpTotalAllInOutWeekValues); /// This is the rate value 
			
			if (weekRateData.get(i).gettotalRateVal()>=0){
				
			}else{
				weekRateData.get(i).settotalRateVal(0); 
			}
		}
		
		
//		---------------for debugging purpose only ------------ 
//		for (int i=0;i<weekRateData.size();i++){
//			System.out.print(DateFormat.getDateInstance(DateFormat.SHORT).format(weekRateData.get(i).getWeekStart())+" --- ");
//			System.out.print(DateFormat.getDateInstance(DateFormat.SHORT).format(weekRateData.get(i).getWeekEnd()) +" --- ");
//			System.out.print(weekRateData.get(i).inOinC+" --- ");
//			System.out.print(weekRateData.get(i).erOinC+" --- ");
//			System.out.print(weekRateData.get(i).inOltC+" --- ");
//			System.out.print(weekRateData.get(i).erOltC+" --- ");
//			System.out.print(weekRateData.get(i).inO+" --- ");
//			System.out.print(weekRateData.get(i).erO+" --- ");
//			System.out.print(weekRateData.get(i).totalVal+" --- ");
//			System.out.println();
//		}
		
		
		
		// ---------------- for calculating normal values out of the total values achieved in this process.  
		//calcNormalval (weekRateData);
		
		
		//================================ Info: Output for weekly rates per attribute ===================================
		
		try {
			
			
			FileOutputStream fileOut=null; 
			FileInputStream inputWeeklyRateData=null; 
			HSSFWorkbook workbook=null; 
			
			if (fileCreate==true){
				fileOut = new FileOutputStream(filePath+outputFileName);
				workbook = new HSSFWorkbook();
			}
			else {
				inputWeeklyRateData = new FileInputStream(new File(filePath+outputFileName));
				workbook = new HSSFWorkbook(inputWeeklyRateData);
				
			}
			
			System.out.println("------------------"+outputFileName);
			HSSFSheet worksheetRate = workbook.createSheet(outputFileName);
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
			cell= row.createCell(9);	cell.setCellValue("Total Rate Val");
			cell= row.createCell(10);	cell.setCellValue("tmpTotalTransferFromEarlyRelease");
			cell= row.createCell(11);	cell.setCellValue("tmpTotalOpenThisRelease");
			cell= row.createCell(12);	cell.setCellValue("tmpTotalerOleftOpen");
			cell= row.createCell(13);	cell.setCellValue("tmpTotalinOleftOpen");
			cell= row.createCell(14);	cell.setCellValue("early open in close");
			cell= row.createCell(15);	cell.setCellValue("in open in close");
			cell= row.createCell(16);	cell.setCellValue("total close");
//			cell= row.createCell(10);	cell.setCellValue(weekRateData.get(i).normalTotalVal);
			cell= row.createCell(17); cell.setCellValue("Release Number");
			cell= row.createCell(18); cell.setCellValue("Release Start");
			cell= row.createCell(19); cell.setCellValue("Release End");
			cell= row.createCell(20); cell.setCellValue("Release Duration");
			cell= row.createCell(21); cell.setCellValue("Release Completion");
			cell= row.createCell(22); cell.setCellValue("Release Category");
			cell= row.createCell(23); cell.setCellValue("Normal value based on Duration");
			cell= row.createCell(24); cell.setCellValue("Project Name");
			cell= row.createCell(25); cell.setCellValue("Project Name & Release Num");
			
			cell= row.createCell(26); cell.setCellValue("Total Number of Files");
			cell= row.createCell(27); cell.setCellValue("Total Addition Churn");
			cell= row.createCell(28); cell.setCellValue("Total Deletion Churn");
			
			
			objDataReleaseCategoryTemplate.add(new DataReleaseCategoryTemplate ()); 
			objDataReleaseCategoryTemplate.get(objDataReleaseCategoryTemplate.size()-1).setIntReleaseNum(1);
			
			for (int i=1;i<weekRateData.size();i++){
				
				if (objDataReleaseCategoryTemplate.get(objDataReleaseCategoryTemplate.size()-1).getIntReleaseNum()==weekRateData.get(i).getReleaseNum()){
					objDataReleaseCategoryTemplate.get(objDataReleaseCategoryTemplate.size()-1).setStrReleaseName(tempIssueData.get(1).getStrProject()+"-"+weekRateData.get(i).getReleaseNum());	
					objDataReleaseCategoryTemplate.get(objDataReleaseCategoryTemplate.size()-1).setDblReleaseAttLastValRecorded(weekRateData.get(i).totalRateVal);
					objDataReleaseCategoryTemplate.get(objDataReleaseCategoryTemplate.size()-1).setDblReleaseAttLastOpenRecorded(weekRateData.get(i).inO + weekRateData.get(i).erO);
				}
				
				else if (objDataReleaseCategoryTemplate.get(objDataReleaseCategoryTemplate.size()-1).getIntReleaseNum()!=weekRateData.get(i).getReleaseNum()){
					objDataReleaseCategoryTemplate.add(new DataReleaseCategoryTemplate ()); 
					objDataReleaseCategoryTemplate.get(objDataReleaseCategoryTemplate.size()-1).setIntReleaseNum(weekRateData.get(i).getReleaseNum());
					
					objDataReleaseCategoryTemplate.get(objDataReleaseCategoryTemplate.size()-1).setStrReleaseName(tempIssueData.get(1).getStrProject()+"-"+weekRateData.get(i).getReleaseNum());		
					objDataReleaseCategoryTemplate.get(objDataReleaseCategoryTemplate.size()-1).setDblReleaseAttLastValRecorded(weekRateData.get(i).totalRateVal);
					objDataReleaseCategoryTemplate.get(objDataReleaseCategoryTemplate.size()-1).setDblReleaseAttLastOpenRecorded(weekRateData.get(i).inO + weekRateData.get(i).erO);
				}
				
				row= worksheetRate.createRow(i); 
				cell= row.createCell(0);	cell.setCellValue(weekRateData.get(i).getWeekNum());
				cell= row.createCell(1);	cell.setCellValue(weekRateData.get(i).getWeekStart());
				cell= row.createCell(2);	cell.setCellValue(weekRateData.get(i).getWeekEnd());
				cell= row.createCell(3);	cell.setCellValue(weekRateData.get(i).inOinC);
				cell= row.createCell(4);	cell.setCellValue(weekRateData.get(i).erOinC);
				cell= row.createCell(5);	cell.setCellValue(weekRateData.get(i).inOltC);
				cell= row.createCell(6);	cell.setCellValue(weekRateData.get(i).erOltC);
				cell= row.createCell(7);	cell.setCellValue(weekRateData.get(i).inO);
				cell= row.createCell(8);	cell.setCellValue(weekRateData.get(i).erO);
				cell= row.createCell(9);	cell.setCellValue(weekRateData.get(i).totalRateVal);
//				cell= row.createCell(10);	cell.setCellValue(weekRateData.get(i).normalTotalVal);
				cell= row.createCell(10);	cell.setCellValue(weekRateData.get(i).tmpTotalTransferFromEarlyRelease);
				cell= row.createCell(11);	cell.setCellValue(weekRateData.get(i).tmpTotalOpenThisRelease);
				cell= row.createCell(12);	cell.setCellValue(weekRateData.get(i).tmpTotalerOleftOpen);
				cell= row.createCell(13);	cell.setCellValue(weekRateData.get(i).tmpTotalinOleftOpen);
				cell= row.createCell(14);	cell.setCellValue(weekRateData.get(i).erOinC);
				cell= row.createCell(15);	cell.setCellValue(weekRateData.get(i).inOinC);
				cell= row.createCell(16);	cell.setCellValue(weekRateData.get(i).tmpTotalInWeekValues);
				cell= row.createCell(17); cell.setCellValue(weekRateData.get(i).getReleaseNum());
				cell= row.createCell(18); cell.setCellValue(weekRateData.get(i).getReleaseStart());
				cell= row.createCell(19); cell.setCellValue(weekRateData.get(i).getReleaseEnd());
				cell= row.createCell(20); cell.setCellValue(weekRateData.get(i).getReleaseDuration());
				cell= row.createCell(21); cell.setCellValue(weekRateData.get(i).getReleaseCompletion());
				cell= row.createCell(22); cell.setCellValue(weekRateData.get(i).getReleaseCategory());
				cell= row.createCell(23); cell.setCellValue(weekRateData.get(i).gettotalRateVal()/weekRateData.get(i).getReleaseDuration());
				cell= row.createCell(24); cell.setCellValue(tempIssueData.get(1).getStrProject());
				cell= row.createCell(25); cell.setCellValue(tempIssueData.get(1).getStrProject()+"-"+weekRateData.get(i).getReleaseNum());
				
				cell= row.createCell(26); cell.setCellValue(weekRateData.get(i).dblTotalFiles);
				cell= row.createCell(27); cell.setCellValue(weekRateData.get(i).dblTotalAddition);
				cell= row.createCell(28); cell.setCellValue(weekRateData.get(i).dblTotalDeletion);
				
				//need to be corrected -----------------------XXXXXXXXXXXXXXX
//				if (boolCalculationTypeisRate==false){
//					cell= row.createCell(10);	cell.setCellValue(weekRateData.get(i).weeklyVal/7);
//				}
				
			}
			
			
			
			if (fileCreate!=true){
				inputWeeklyRateData.close();
				fileOut = new FileOutputStream(filePath+outputFileName);
			}
			
			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
			System.out.println("Success: written "+ outputFileName);
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Error: writing "+ outputFileName);
		}catch (IOException e2){
			e2.printStackTrace();
			System.out.println("Error: writing "+ outputFileName);
		}
		
		//================================ Info: Output for weekly rates per attribute ===================================
		
//		---------------for debugging purpose only ------------ 
		
//		for (DataReleaseCategoryTemplate counter_objDataReleaseCategoryTemplate: objDataReleaseCategoryTemplate){
//			System.out.println(counter_objDataReleaseCategoryTemplate.getIntReleaseNum());
//			System.out.println(counter_objDataReleaseCategoryTemplate.getStrReleaseName());
//			System.out.println(counter_objDataReleaseCategoryTemplate.getDblReleaseAttLastValRecorded());
//			System.out.println(counter_objDataReleaseCategoryTemplate.getDblReleaseAttLastOpenRecorded());
//			System.out.println(counter_objDataReleaseCategoryTemplate.getIntReleaseAttRankFound());
//			System.out.println("============================");
//		}
		
//		---------------for debugging purpose only ------------ 
		
		
		//================================ Info: Output for Last vals per attribute ===================================
		
		methodSortandRank (objDataReleaseCategoryTemplate); 
		method_writeSortandRanking (objDataReleaseCategoryTemplate, outputSheetName);
		//method_ReleaseVCategorizeWriting (); 
		
		//================================ Info: Output for Last vals per attribute ===================================
		
	}// end of weekly rate method. 
	
	
	
	 void method_writeSortandRanking (ArrayList <DataReleaseCategoryTemplate> objDataReleaseCategoryTemplate, String tmpOutputSheetName){
		 try {
			FileOutputStream fileLastValOut=null; 
			FileInputStream inputLastValData=null; 
			HSSFWorkbook workbook=null; 
			HSSFSheet worksheetRate = null; 
			
			
			if (booloutputLastValFileCreate==true){
				fileLastValOut = new FileOutputStream(filePath+outputLastValFileName);
				workbook = new HSSFWorkbook();
				worksheetRate = workbook.createSheet(outputLastValFileName);
				
				System.out.println("------------------"+outputLastValFileName);
				
				HSSFRow row; 
				HSSFCell cell;
				
				int rowCounter =0; 
				row= worksheetRate.createRow(rowCounter); 
				
				cell= row.createCell(0);	cell.setCellValue("ReleaseNum");
				cell= row.createCell(1);	cell.setCellValue("ReleaseName");
				cell= row.createCell(2);	cell.setCellValue("ReleaseLastVal");
				cell= row.createCell(3);	cell.setCellValue("ReleaseLastOpenVal");
				cell= row.createCell(4);	cell.setCellValue("ReleaseAttRankRate");
				cell= row.createCell(5);	cell.setCellValue("ReleaseAttRankOpen");
				
				for (DataReleaseCategoryTemplate counter_objDataReleaseCategoryTemplate: objDataReleaseCategoryTemplate){
					rowCounter++;
					row= worksheetRate.createRow(rowCounter); 
					cell= row.createCell(0);	cell.setCellValue(counter_objDataReleaseCategoryTemplate.getIntReleaseNum());
					cell= row.createCell(1);	cell.setCellValue(counter_objDataReleaseCategoryTemplate.getStrReleaseName());
					cell= row.createCell(2);	cell.setCellValue(counter_objDataReleaseCategoryTemplate.getDblReleaseAttLastValRecorded());
					cell= row.createCell(3);	cell.setCellValue(counter_objDataReleaseCategoryTemplate.getDblReleaseAttLastOpenRecorded());
					cell= row.createCell(4);	cell.setCellValue(counter_objDataReleaseCategoryTemplate.getIntReleaseAttRateRankFound());
					cell= row.createCell(5);	cell.setCellValue(counter_objDataReleaseCategoryTemplate.getIntReleaseAttOpenRankFound());
				}
			}
			
			else {
				inputLastValData = new FileInputStream(new File(filePath+outputLastValFileName));
				workbook = new HSSFWorkbook(inputLastValData);
				worksheetRate = workbook.getSheet(outputLastValFileName);
				
				System.out.println("------------------"+outputLastValFileName);
				
				HSSFRow row; 
				HSSFCell cell;
				
				int rowCounter =0; 
				row= worksheetRate.getRow(rowCounter); 
				
				cell= row.createCell(row.getLastCellNum()+0);	cell.setCellValue("ReleaseLastVal_"+tmpOutputSheetName);
				cell= row.createCell(row.getLastCellNum()+0);	cell.setCellValue("ReleaseLastOpenVal_"+tmpOutputSheetName);
				cell= row.createCell(row.getLastCellNum()+0);	cell.setCellValue("ReleaseAttRankRate_"+tmpOutputSheetName);
				cell= row.createCell(row.getLastCellNum()+0);	cell.setCellValue("ReleaseAttRankRate_"+tmpOutputSheetName);
				
				for (DataReleaseCategoryTemplate counter_objDataReleaseCategoryTemplate: objDataReleaseCategoryTemplate){
					rowCounter++;
					row= worksheetRate.getRow(rowCounter); 
					cell= row.createCell(row.getLastCellNum()+0);	cell.setCellValue(counter_objDataReleaseCategoryTemplate.getDblReleaseAttLastValRecorded());
					cell= row.createCell(row.getLastCellNum()+0);	cell.setCellValue(counter_objDataReleaseCategoryTemplate.getDblReleaseAttLastOpenRecorded());
					cell= row.createCell(row.getLastCellNum()+0);	cell.setCellValue(counter_objDataReleaseCategoryTemplate.getIntReleaseAttRateRankFound());
					cell= row.createCell(row.getLastCellNum()+0);	cell.setCellValue(counter_objDataReleaseCategoryTemplate.getIntReleaseAttOpenRankFound());
				}
			}
			
			if (booloutputLastValFileCreate!=true){
				inputLastValData.close();
				fileLastValOut = new FileOutputStream(filePath+outputLastValFileName);
			}
			
			workbook.write(fileLastValOut);
			fileLastValOut.flush();
			fileLastValOut.close();
			System.out.println("Success: written "+ outputLastValFileName);
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Error: writing "+ outputLastValFileName);
		}catch (IOException e2){
			e2.printStackTrace();
			System.out.println("Error: writing "+ outputLastValFileName);
		}
	}
	
	void methodSortandRank (ArrayList <DataReleaseCategoryTemplate> temp_objDataReleaseCategoryTemplate){
		Collections.sort(temp_objDataReleaseCategoryTemplate, new Comparator<DataReleaseCategoryTemplate>(){
			   public int compare(DataReleaseCategoryTemplate o1, DataReleaseCategoryTemplate o2){
				   return o1.getDblReleaseAttLastValRecorded()  > o2.getDblReleaseAttLastValRecorded()  ? 1 : o1.getDblReleaseAttLastValRecorded()  < o2.getDblReleaseAttLastValRecorded()  ? -1 : 0;
			   }
			});

		
		int temp_RankCounter =0; 
		double temp_RateValStorage =-1; // as values can be 0 as well by giving it 0 does not give a rank 1 to value 0. however -1 is not possible in the value of the db and even 0 value is ranked 1 by incrementing it 1. 
		
		for (DataReleaseCategoryTemplate tempIterator : temp_objDataReleaseCategoryTemplate){
			if (temp_RateValStorage<tempIterator.getDblReleaseAttLastValRecorded()){
				temp_RankCounter++;
				temp_RateValStorage= tempIterator.getDblReleaseAttLastValRecorded();
			}
			tempIterator.setIntReleaseAttRateRankFound(temp_RankCounter);
		}
		
		
		
		
		Collections.sort(temp_objDataReleaseCategoryTemplate, new Comparator<DataReleaseCategoryTemplate>(){
			   public int compare(DataReleaseCategoryTemplate o1, DataReleaseCategoryTemplate o2){
				   return o1.getDblReleaseAttLastOpenRecorded() > o2.getDblReleaseAttLastOpenRecorded() ? 1 : o1.getDblReleaseAttLastOpenRecorded() < o2.getDblReleaseAttLastOpenRecorded() ? -1 : 0;
			   }
			});
		
		int temp_OpenCounter =0; 
		double temp_OpenValStorage =-1; // as values can be 0 as well by giving it 0 does not give a rank 1 to value 0. however -1 is not possible in the value of the db and even 0 value is ranked 1 by incrementing it 1.
		
		for (DataReleaseCategoryTemplate tempIterator : temp_objDataReleaseCategoryTemplate){
			if (temp_OpenValStorage<tempIterator.getDblReleaseAttLastOpenRecorded()){
				temp_OpenCounter++; 
				temp_OpenValStorage= tempIterator.getDblReleaseAttLastOpenRecorded();
			}
			
			tempIterator.setIntReleaseAttOpenRankFound(temp_OpenCounter);
		}
		
		
		Collections.sort(temp_objDataReleaseCategoryTemplate, new Comparator<DataReleaseCategoryTemplate>(){
			   public int compare(DataReleaseCategoryTemplate o1, DataReleaseCategoryTemplate o2){
				   return o1.getIntReleaseNum() > o2.getIntReleaseNum() ? 1 : o1.getIntReleaseNum() < o2.getIntReleaseNum() ? -1 : 0;
			   }
			});
	
	}
	
	
	
	void method_ReleaseVCategorizeWriting (){
		 try {
			FileOutputStream fileLastValOut=null; 
			FileInputStream inputLastValData=null; 
			HSSFWorkbook workbook=null; 
			HSSFSheet worksheetRate = null; 
			
			
			
				inputLastValData = new FileInputStream(new File(filePath+outputLastValFileName));
				workbook = new HSSFWorkbook(inputLastValData);
				worksheetRate = workbook.getSheet(outputLastValFileName);
				
				System.out.println("------------------"+outputLastValFileName);
				
				Row row; 
				Cell cell;
				double totalRank; 
				
//				int rowCounter =0; 
//				row= worksheetRate.getRow(rowCounter); 
//				
//				cell= row.createCell(row.getLastCellNum()+0);	cell.setCellValue("Total Rank");
//				cell= row.createCell(row.getLastCellNum()+0);	cell.setCellValue("Category");
				
				
				
				
				Iterator<Row> rowIterator = worksheetRate.iterator();
			    while (rowIterator.hasNext()) {
			        totalRank =0; 
			    	row = rowIterator.next();
			     
			      if (row.getRowNum()==0){
			    	  row= worksheetRate.getRow(0); 
			    	  cell= row.createCell(row.getLastCellNum()+0);	cell.setCellValue("Total Rank");
					  cell= row.createCell(row.getLastCellNum()+0);	cell.setCellValue("Category");
			      }
			      
			      else {
			    	  Iterator <Cell> cellIterator = row.cellIterator();
				      while (cellIterator.hasNext()) {
				        cell = cellIterator.next();
				        
				        if (cell.getColumnIndex()==4 ||cell.getColumnIndex()==5||cell.getColumnIndex()==8||cell.getColumnIndex()==9||cell.getColumnIndex()==12||cell.getColumnIndex()==13||cell.getColumnIndex()==16||cell.getColumnIndex()==17){
				        	totalRank= totalRank + cell.getNumericCellValue(); 
				        }
				      }
			      
				      row.createCell(row.getLastCellNum()+0).setCellValue(totalRank);
			      }
			    }
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
//				for (DataReleaseCategoryTemplate counter_objDataReleaseCategoryTemplate: objDataReleaseCategoryTemplate){
//					rowCounter++;
//					row= worksheetRate.getRow(rowCounter); 
//					cell= row.createCell(row.getLastCellNum()+0);	cell.setCellValue(counter_objDataReleaseCategoryTemplate.getDblReleaseAttLastValRecorded());
//					cell= row.createCell(row.getLastCellNum()+0);	cell.setCellValue(counter_objDataReleaseCategoryTemplate.getDblReleaseAttLastOpenRecorded());
//					cell= row.createCell(row.getLastCellNum()+0);	cell.setCellValue(counter_objDataReleaseCategoryTemplate.getIntReleaseAttRateRankFound());
//					cell= row.createCell(row.getLastCellNum()+0);	cell.setCellValue(counter_objDataReleaseCategoryTemplate.getIntReleaseAttOpenRankFound());
//				}
			
			fileLastValOut = new FileOutputStream(filePath+outputLastValFileName);
			workbook.write(fileLastValOut);
			fileLastValOut.flush();
			fileLastValOut.close();
			System.out.println("Success: written "+ outputLastValFileName);
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Error: writing "+ outputLastValFileName);
		}catch (IOException e2){
			e2.printStackTrace();
			System.out.println("Error: writing "+ outputLastValFileName);
		}
	}
	
	
	
	
	
}// end of class	
	
	

////////////////////////////////////method_writeSortandRanking old version 


//void method_writeSortandRanking (ArrayList <DataReleaseCategoryTemplate> objDataReleaseCategoryTemplate){
//	 try {
//		FileOutputStream fileLastValOut=null; 
//		FileInputStream inputLastValData=null; 
//		HSSFWorkbook workbook=null; 
//		
//		if (booloutputLastValFileCreate==true){
//			fileLastValOut = new FileOutputStream(filePath+outputLastValFileName);
//			workbook = new HSSFWorkbook();
//		}
//		else {
//			inputLastValData = new FileInputStream(new File(filePath+outputLastValFileName));
//			workbook = new HSSFWorkbook(inputLastValData);
//			
//		}
//		
//		System.out.println("------------------"+outputLastValFileName);
//		HSSFSheet worksheetRate = workbook.createSheet(outputFileName);
//		HSSFRow row; 
//		HSSFCell cell;
//		
//		int rowCounter =0; 
//		row= worksheetRate.createRow(rowCounter); 
//		cell= row.createCell(0);	cell.setCellValue("ReleaseNum");
//		cell= row.createCell(1);	cell.setCellValue("ReleaseName");
//		cell= row.createCell(2);	cell.setCellValue("ReleaseLastVal");
//		cell= row.createCell(3);	cell.setCellValue("ReleaseLastOpenVal");
//		cell= row.createCell(4);	cell.setCellValue("ReleaseAttRankRate");
//		cell= row.createCell(5);	cell.setCellValue("ReleaseAttRankOpen");
//		
//		for (DataReleaseCategoryTemplate counter_objDataReleaseCategoryTemplate: objDataReleaseCategoryTemplate){
//			rowCounter++;
//			row= worksheetRate.createRow(rowCounter); 
//			cell= row.createCell(0);	cell.setCellValue(counter_objDataReleaseCategoryTemplate.getIntReleaseNum());
//			cell= row.createCell(1);	cell.setCellValue(counter_objDataReleaseCategoryTemplate.getStrReleaseName());
//			cell= row.createCell(2);	cell.setCellValue(counter_objDataReleaseCategoryTemplate.getDblReleaseAttLastValRecorded());
//			cell= row.createCell(3);	cell.setCellValue(counter_objDataReleaseCategoryTemplate.getDblReleaseAttLastOpenRecorded());
//			cell= row.createCell(4);	cell.setCellValue(counter_objDataReleaseCategoryTemplate.getIntReleaseAttRateRankFound());
//			cell= row.createCell(5);	cell.setCellValue(counter_objDataReleaseCategoryTemplate.getIntReleaseAttOpenRankFound());
//		}
//		
//		
//		
//		if (booloutputLastValFileCreate!=true){
//			inputLastValData.close();
//			fileLastValOut = new FileOutputStream(filePath+outputLastValFileName);
//		}
//		
//		workbook.write(fileLastValOut);
//		fileLastValOut.flush();
//		fileLastValOut.close();
//		System.out.println("Success: written "+ outputLastValFileName);
//		
//	} catch (FileNotFoundException e1) {
//		// TODO Auto-generated catch block
//		e1.printStackTrace();
//		System.out.println("Error: writing "+ outputLastValFileName);
//	}catch (IOException e2){
//		e2.printStackTrace();
//		System.out.println("Error: writing "+ outputLastValFileName);
//	}
//}


/////////////////////////////////





























	
	
	
	
	
// ======================= For reference======================
	
//	class AgentSummaryDTO{
//	    String id;
//	    Integer customerCount;
//	    
//	    AgentSummaryDTO (String id, int cus){
//	    	this.id = id;
//	    	this.customerCount = cus; 
//	    }
//	}
//	
//	ArrayList <AgentSummaryDTO> agentDtoList = new ArrayList<AgentSummaryDTO>();
//	
//	agentDtoList.add(new AgentSummaryDTO ("a",5)); 
//	agentDtoList.add(new AgentSummaryDTO ("b",8)); 
//	agentDtoList.add(new AgentSummaryDTO ("c",3)); 
//	agentDtoList.add(new AgentSummaryDTO ("d",1)); 
//	agentDtoList.add(new AgentSummaryDTO ("e",48)); 
//	agentDtoList.add(new AgentSummaryDTO ("f",50)); 
//	agentDtoList.add(new AgentSummaryDTO ("g",35)); 
//	agentDtoList.add(new AgentSummaryDTO ("h",25)); 
//	
//	
//	Collections.sort(agentDtoList, new Comparator<AgentSummaryDTO>(){
//		   public int compare(AgentSummaryDTO o1, AgentSummaryDTO o2){
//		      return o1.customerCount - o2.customerCount;
//		   }
//		});
//	
//	for (AgentSummaryDTO counter: agentDtoList){
//		System.out.println(counter.id);
//		System.out.println(counter.customerCount);
//		System.out.println("================");
//	}
	
// ======================= For reference======================
	
//	public static void calcNormalval (ArrayList<WeekCalcTemplate> TempWFRIssues){
//		double minVal=1, maxVal=0; 
//		for (int i=0;i<TempWFRIssues.size();i++){
//			if (TempWFRIssues.get(i).totalVal<minVal){
//				minVal= TempWFRIssues.get(i).totalVal;
//			}
//			if (TempWFRIssues.get(i).totalVal>maxVal){
//				maxVal=TempWFRIssues.get(i).totalVal; 
//			}
//		}
//		
//		for (int i=0; i<TempWFRIssues.size();i++){
//			TempWFRIssues.get(i).normalTotalVal = (TempWFRIssues.get(i).totalVal-minVal)/(maxVal-minVal); 
//			
//			if (TempWFRIssues.get(i).normalTotalVal>=0){
//				
//			}
//			else{
//				TempWFRIssues.get(i).normalTotalVal=0;
//			}
//		}
//	}
	

