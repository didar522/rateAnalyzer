package weeklyRateCalc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import logicCalcFiles.FindNeighbourDataset;
import logicCalcFiles.PrepareTarInputFiles;
import logicCalcFiles.ReleaseCategorize;
import logicCalcFiles.ReleaseInfoCollection;
import logicCalcFiles.UpdateWeekRateWithReleaseCategory;
import logicCalcFiles.WeekDateCalc;
import logicCalcFiles.WeekDt_RelCat_MembershipIdentify;
import logicCalcFiles.WeekRateCalc;
import dataDownload.JiraIssueDownload;
import dataDownload.JiraManipulation;
import dataDownload.createTopicFiles;
import dataTypeTemplates.DataIssueTemplate;
import dataTypeTemplates.WeekCalcTemplate;
import dataTypeTemplates.ReleaseCalendarTemplate;
import excelManipulation.DataCombineExcelFiles;
import excelManipulation.DataFindUniqueTags;
import excelManipulation.DataReadExcelFiles;
import excelManipulation.DataReadUniqueTags;

public class MainModelFile {

//	public static String strFilePath = "C:/Users/S.M.Didar/Dropbox/Didar DBPC/PhD Research/Fall 2015/ICSE -Journal 2016/Evaluation/JIRA client/Test V3.1/Exp3.6/";
//	public static String strFilePath = "C:/Users/S.M.Didar/Dropbox/Didar DBPC/PhD Research/Fall 2015/ICSE Workshop/Evaluation/V8/";
	public static String strFilePath = "C:/Users/Didar/Desktop/Summary/";
	public static String strJiraFilePath = "C:/Users/Didar/Desktop/Summary/";   
	public static String strFileName = "Summary_Out.xls"; 
	public static String strSheetName = "Summary_Out.xls"; 
	public static String strOutputReleaseCategoriesFileName = "LastValues_AllAtt.xls";
	
	public static int intWeeksinCurRel = 18; 
	
	public static int startingOfExcelFiles = 1; 
	public static int numberOfExcelFiles = 19;
	
	public static ArrayList<DataIssueTemplate> IssueData = new ArrayList<DataIssueTemplate>();  
	public static HashMap<String, Integer> excelFileIndex = new HashMap <String, Integer> ();  
	public static ArrayList<String> strlistUniqueTags = new ArrayList<String> (); 
	
	
	public static ArrayList<DataIssueTemplate> bugIssueData = new ArrayList<DataIssueTemplate>();  
	public static ArrayList<DataIssueTemplate> ftrIssueData = new ArrayList<DataIssueTemplate>(); 
	public static ArrayList<DataIssueTemplate> commitIssueData = new ArrayList<DataIssueTemplate>(); 
	
	
	public static HashMap<String, Integer> bur_ftr_FileIndex = new HashMap <String, Integer> ();  
	
	public static ArrayList<ReleaseCalendarTemplate> releaseInfo = new ArrayList<ReleaseCalendarTemplate> ();
	public static ArrayList<WeekCalcTemplate> weekRateData_BFR = new ArrayList <WeekCalcTemplate> ();
	public static ArrayList<WeekCalcTemplate> weekRateData_DFR = new ArrayList <WeekCalcTemplate> ();
	public static ArrayList<WeekCalcTemplate> weekRateData_FCR = new ArrayList <WeekCalcTemplate> ();
	public static ArrayList<WeekCalcTemplate> weekRateData_FOR = new ArrayList <WeekCalcTemplate> ();
	public static ArrayList<WeekCalcTemplate> weekRateData_Commit = new ArrayList <WeekCalcTemplate> ();
	
	 
	public static void main (String args []) throws Exception{
		
		int runningAction = 2; 
		
		if (runningAction ==1) {StepOne_MergingAllExcelfiles (); }
		else if (runningAction ==2) {StepTwo_ReadingExcelsheet ();}  // NEED TO ALTER THE DATE CONVERSION THING IN DataReadExcelFiles CLASS BASED ON IF CONVERSION IS REQUIRED --- HERE REQUIRED. 
		else if (runningAction ==3) {StepThree_IdentifyingUniqueTagsFromAllIssues();}
		else if (runningAction ==4) {StepFour_DistributingIssuesInMultipleTags ();}
		else if (runningAction ==5) {StepFive_PerformCalculation ();}
		else if (runningAction ==61) {StepSix_ReleaseCategorize ("Total Rank");}
		else if (runningAction ==62) {StepSix_ReleaseCategorize ("Category");		}
		else if (runningAction ==7) {StepSeven_UpdateWeekRateWithReleaseCategory (); 	}	
		
		// Combine all files prior to this. 
		else if (runningAction ==75) {StepOne_MergingAllExcelfiles (); }
		
		else if (runningAction ==8) {StepEight_FindNeighbourDataset (); }
		else if (runningAction ==9) {StepNine_WeekDt_RelCat_MembershipIdentify (); }
		else if (runningAction ==10) {StepTen_PrepareTarFiles (); }
		else if (runningAction ==11) {StepElevenJiraManipulation (1, 1577);}
		else if (runningAction ==12) {StepTwelveTopicFileCreation ();}
		
		
		
		else if (runningAction ==13) StepThirteenJiraDownloadIssues (1,157, "MAHOUT-"); 
		
		
		
////////////////////////==============================ruf tasks 
		

		
		
		
		
		
///////////////////////==============================ruf tasks 
	
	}// End of public static main
	
	
	public static void collectedReleasInfo(){
		
		ReleaseInfoCollection obj_ReleaseInfoCollection = new ReleaseInfoCollection (releaseInfo); 
	
	

		
		
		
		
		
//		Brightsquid project 
		
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-05-22", true, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-06-02", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-06-18", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-07-24", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-07-28", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-08-21", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-09-08", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-09-18", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-09-23", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-10-16", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-11-06", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-11-30", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-12-11", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-12-18", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-01-22", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-02-05", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-02-12", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-05-13", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-05-26", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-06-16", false, "NR");
		
		
		
		
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2012-12-23", true, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2013-01-15", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2013-02-06", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2013-02-12", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2013-03-04", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2013-03-25", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2013-03-28", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2013-04-02", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2013-05-09", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2013-05-15", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2013-06-17", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2013-08-15", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2013-09-18", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2013-10-03", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-01-17", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-01-23", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-05-02", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-05-02", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-05-30", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-06-04", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-09-18", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-09-18", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-09-29", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-10-16", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-11-06", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-11-27", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-03-20", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-03-22", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-04-08", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-05-14", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-05-21", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-06-01", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-06-17", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-07-28", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-07-28", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-08-20", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-08-20", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-09-04", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-09-17", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-09-17", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-09-18", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-10-15", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-10-15", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-11-05", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-11-26", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-11-27", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-12-10", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-12-17", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-01-21", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-02-04", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-02-11", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-05-13", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-05-26", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-06-16", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-06-22", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-07-14", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-07-28", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-08-16", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-08-24", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-08-31", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-09-01", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-09-07", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-09-09", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-11-10", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-11-18", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-12-22", false, "R");

		
		
		//---------------------BrightSquid Platfor/Dental releases from Jira------------------//
		
		
//		All releases
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2013-01-15", true, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2013-02-06", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2013-02-12", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2013-03-04", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2013-03-25", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2013-04-02", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2013-05-09", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2013-05-15", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2013-06-17", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2013-08-15", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2013-09-18", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2013-10-03", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-01-17", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-01-23", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-05-02", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-05-30", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-06-04", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-09-18", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-09-29", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-10-16", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-11-06", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-11-27", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-03-20", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-04-08", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-05-14", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-05-21", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-06-01", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-06-17", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-07-28", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-08-20", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-09-04", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-09-18", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-10-15", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-11-05", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-11-27", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-12-10", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-12-17", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-01-21", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-02-04", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-02-11", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-06-16", false, "R");

		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-03-22", true, "R");
		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-04-08", false, "R");
		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-05-14", false, "R");
		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-05-21", false, "R");
		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-06-01", false, "R");
		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-06-17", false, "R");
		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-07-28", false, "R");
		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-08-20", false, "R");
		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-09-04", false, "R");
		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-09-17", false, "R");
		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-10-15", false, "R");
		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-11-05", false, "R");
		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-11-26", false, "R");
		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-12-10", false, "R");
		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-12-17", false, "R");
		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-01-21", false, "R");


		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		


//Aurora new release info 
		
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2013-10-22", true, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2013-12-17", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-01-02", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-08-01", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-11-25", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-02-09", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-05-13", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-07-24", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-11-15", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-12-22", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-02-07", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-04-14", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-06-14", false, "R");
//		



		
		
		
	}
	
	
	
	public static void old_collectedReleasInfo(){
		
//		ReleaseInfoCollection obj_ReleaseInfoCollection = new ReleaseInfoCollection (releaseInfo); 
		

		
		
// Helix
		
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2013-09-10", true, "NR");		
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2013-10-02", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-08-08", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-08-28", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-09-11", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-10-09", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-09-23", false, "NR");

		
		// Offbiz
		
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-02-17", true, "NR");		
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-02-21", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-02-26", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-06-19", false, "Unknown");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-06-22", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-09-18", false, "Unknown");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-09-22", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-12-18", false, "Unknown");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-12-28", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-03-18", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-03-26", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-03-31", false, "NR");

		
		
		
		
		
		
		
		
		
		// Kylin - 50 
		
		
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-02-01", true, "R");		
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-03-28", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-04-13", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-04-27", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-05-28", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-06-19", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-06-29", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-07-27", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-08-24", false, "R");

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-11-09", true, "NR");		
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-11-23", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-12-07", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-12-21", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-01-04", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-01-18", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-02-01", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-02-15", false, "R");

		
		
		
		
		//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-03-04", false, "NR");
//	obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-03-11", false, "NR");
//obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-03-16", false, "NR");
//obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-03-24", false, "R");
//obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-04-01", false, "NR");
//obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-04-08", false, "NR");
	
		
		
		
		
		
		
		
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-01-05", true, "NR");		
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-01-12", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-01-20", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-01-26", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-02-02", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-02-09", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-02-16", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-02-18", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-03-04", false, "NR");
//	obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-03-11", false, "NR");
//obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-03-16", false, "NR");
//obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-03-24", false, "R");
//obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-04-01", false, "NR");
//obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-04-08", false, "NR");
//	obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-04-21", false, "NR");
//	obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-04-29", false, "R");
//obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-05-06", false, "NR");
//obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-05-13", false, "NR");
//obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-05-20", false, "NR");
//obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-05-27", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-06-03", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-06-10", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-06-17", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-07-01", false, "NR");
//	obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-07-08", false, "NR");
//obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-07-22", false, "NR");
//obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-07-29", false, "NR");
//obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-08-25", false, "NR");
//obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-09-02", false, "NR");
//	obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-09-18", false, "NR");
//	obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-10-27", false, "NR");
//obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-11-09", false, "R");
//obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-01-05", false, "R");
//obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-01-21", false, "NR");



//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-05-05", true, "NR");		
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-05-21", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-06-03", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-06-18", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-07-01", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-08-04", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-08-21", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-09-04", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-10-02", false, "NR");
//	obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-10-16", false, "NR");
//obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-11-04", false, "NR");
//obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-11-17", false, "Unknown");
//obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-12-11", false, "NR");
//obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-01-06", false, "NR");
//	obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-01-27", false, "NR");
//	obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-02-19", false, "NR");
//obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-03-08", false, "NR");
//obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-09-02", false, "NR");
//obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-09-11", false, "R");
		
		
		
		
		
		
		
		
		
		// Release not spring info Aurora -----------
		
//					releaseInfo.add(sdf.parse("2013-10-22"));		
//					releaseInfo.add(sdf.parse("2013-12-17"));		
//					releaseInfo.add(sdf.parse("2014-01-02"));		
//					releaseInfo.add(sdf.parse("2014-08-01"));		
//					releaseInfo.add(sdf.parse("2014-11-25"));
//					releaseInfo.add(sdf.parse("2015-02-09"));
//					releaseInfo.add(sdf.parse("2015-05-13"));
//					releaseInfo.add(sdf.parse("2015-07-24"));
//					releaseInfo.add(sdf.parse("2015-11-15"));
//					releaseInfo.add(sdf.parse("2015-12-22"));
////					releaseInfo.add(sdf.parse("2016-02-07"));
////					releaseInfo.add(sdf.parse("2016-04-14"));
//					releaseInfo.add(sdf.parse("2016-06-14"));
					
		
		
		
		
		//Mozilla for Android
		
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-02-04", true, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-03-18", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-04-29", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-06-10", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-07-22", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-09-02", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-10-13", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-12-01", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-01-13", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-02-27", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-03-31", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-05-12", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-07-02", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-08-11", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-09-22", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-11-03", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-12-15", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-01-26", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-03-08", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-04-26", false, "R");
		

// Brightsquid tentative releases
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-03-11", true, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-07-16", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-09-15", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-09-23", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-10-08", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-10-13", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-10-28", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-11-18", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-11-30", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-12-16", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-12-23", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-01-04", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-01-21", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-02-04", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-04-16", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-06-09", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-06-16", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-12-15", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-01-26", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-03-08", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-04-26", false, "R");



		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		Project msos
		
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-10-19", true, "NR");		
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-11-04", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-11-17", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-02-06", false, "Unknown");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-02-20", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-03-09", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-03-20", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-04-03", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-04-17", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-05-01", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-05-15", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-05-27", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-06-08", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-06-22", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-07-06", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-07-20", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-08-03", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-08-17", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-08-31", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-09-14", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-09-29", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-10-12", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-11-09", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-11-23", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-12-07", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-12-21", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-01-04", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2016-01-18", false, "NR");


		
		
		
		
		
		
		
		
//		Project kylin 
		
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-02-01", true, "NR");		
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-03-28", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-04-13", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-04-29", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-05-28", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-06-19", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-06-30", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-08-06", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-08-25", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-09-07", false, "NR");
		


		
		
		
//		Project Name of Release Info -----Archetype-451
		
		
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2010-10-28", true, "High");		
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2011-09-04", false, "High");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2011-11-23", false, "Medium");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-04-13", false, "Low");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-08-15", false, "Low");
//		
		

//		Project Name of Release Info -----Maven Assembly Plugin-777		
		
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2008-02-29", true, "Low");		
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2009-01-05", false, "Low");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2009-12-13", false, "Low");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2010-10-11", false, "Low");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2011-02-27", false, "Low");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2011-12-02", false, "Medium");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2012-02-01", false, "Medium");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2012-11-18", false, "Medium");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-07-30", false, "Medium");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-10-26", false, "Medium");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-11-21", false, "High");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-12-17", false, "High");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-04-27", false, "High");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-06-04", false, "High");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-10-10", false, "High");

		
		
//		Project Name of Release Info -----Maven Changes Plugin - ASF JIRA--361
		
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2006-09-25", true, "Low");		
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2007-10-25", false, "Low");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2008-04-16", false, "Low");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2008-11-25", false, "Low");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2009-11-23", false, "Low");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2009-12-09", false, "Medium");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2011-01-29", false, "Medium");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2011-05-23", false, "Medium");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2011-06-22", false, "Medium");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2012-04-30", false, "Medium");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2012-05-10", false, "High");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2012-09-14", false, "High");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2013-04-07", false, "High");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-04-13", false, "High");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-09-24", false, "High");
		
		
		
//		Project Name of Release Info -----Maven Checkstyle Plugin - ASF JIRA--301
		
		
		
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2006-05-14", true, "Low");		
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2008-06-04", false, "Low");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2009-07-14", false, "Low");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2009-11-18", false, "Low");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2010-02-12", false, "Low");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2010-09-25", false, "Medium");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2011-08-11", false, "Medium");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2011-11-01", false, "Medium");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2012-02-22", false, "Medium");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2013-11-15", false, "Medium");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-04-12", false, "High");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-05-19", false, "High");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-09-18", false, "High");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-02-05", false, "High");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-04-20", false, "High");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-07-20", false, "High");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-10-15", false, "High");

		
//		Project Name of Release Info -----Maven Dependency Plugin - ASF JIRA--486
		
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2007-01-09", true, "Low");		
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2007-04-24", false, "Low");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2008-01-22", false, "Low");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2009-01-10", false, "Low");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2011-02-15", false, "Medium");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2011-07-10", false, "Medium");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2011-12-05", false, "Medium");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2012-08-27", false, "Medium");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2012-11-25", false, "High");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2013-04-13", false, "High");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2013-05-18", false, "High");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-09-18", false, "High");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-01-27", false, "High");

		
		
		
		
		
//		old infor test sessions 
		
		
		
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2006-09-25", true, "Low");		
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2007-10-25", false, "Low");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2008-04-16", false, "Low");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2008-11-25", false, "Low");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2009-11-23", false, "Low");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2009-12-09", false, "Medium");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2011-01-29", false, "Medium");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2011-05-23", false, "Medium");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2011-06-22", false, "Medium");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2012-04-30", false, "Medium");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2012-05-10", false, "High");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2012-09-14", false, "High");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2013-04-07", false, "High");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-04-13", false, "High");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-09-24", false, "High");

		
		
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2007-01-09", true, "Low");		
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2007-04-24", false, "Low");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2008-01-22", false, "Low");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2009-01-10", false, "Low");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2011-02-15", false, "Medium");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2011-07-10", false, "Medium");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2011-12-05", false, "Medium");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2012-08-27", false, "Medium");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2012-11-25", false, "High");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2013-04-13", false, "High");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2013-05-18", false, "High");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-09-18", false, "High");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-01-27", false, "High");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-02-05", false, "High");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-04-20", false, "High");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-07-20", false, "High");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-10-15", false, "High");
//		releaseInfo.add(sdf.parse("2010-10-28"));
//		releaseInfo.add(sdf.parse("2011-09-04"));
//		releaseInfo.add(sdf.parse("2011-11-23"));
//		releaseInfo.add(sdf.parse("2015-04-13"));
//		releaseInfo.add(sdf.parse("2015-08-15"));
	
		
		
//		Project Name of Release Info -----Apache Aurora Twitter Scrum - AURORA-1579
		
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-05-12", true, "NR");		
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-05-19", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-06-02", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-07-14", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-07-25", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-09-07", false, "Unknown");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-09-23", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-10-06", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-10-20", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-11-04", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-11-17", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-12-1", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2014-12-15", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-01-17", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-01-31", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-02-17", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-03-02", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-03-16", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-03-31", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-04-13", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-04-27", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-05-10", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-05-26", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-06-05", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-06-22", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-07-06", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-07-20", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-08-03", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-08-17", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-08-31", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-09-14", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-09-28", false, "R");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-10-09", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-10-26", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-11-06", false, "NR");
//		obj_ReleaseInfoCollection.fillUpReleaseInfo("2015-11-21", false, "NR");
		

		
		
		
		
		
		
		
		
		
		
	}
	
	
	
		
	public static void StepOne_MergingAllExcelfiles () throws Exception{
		
		
		DataCombineExcelFiles objDataCombineExcelFiles = new DataCombineExcelFiles (strFilePath, strFileName, startingOfExcelFiles,numberOfExcelFiles); 
		objDataCombineExcelFiles.mergeExcelFiles();
		
		
//		For Topic analysis part
//		DataCombineExcelFiles objDataCombineExcelFiles = new DataCombineExcelFiles ("C:/Users/S.M.Didar/Dropbox/Didar DBPC/PhD Research/Fall 2015/ICSE Workshop/Evaluation/V4/Aurora/", "comments.xls", 1,17); 
//		objDataCombineExcelFiles.mergeExcelFiles();
	}
	
	public static void StepTwo_ReadingExcelsheet (){
		int intStartingRowofData = 1;
		DataReadExcelFiles objDataReadExcelFiles = new DataReadExcelFiles(IssueData, intStartingRowofData, strFilePath, strFileName, strFileName); 
		excelFileIndex = objDataReadExcelFiles.createColumnIndex(0); 
		objDataReadExcelFiles.readExcelFiles(true);
		
		
//		---------------For sake of checking excel index output only 0---------------------
//		Iterator<String> keySetIterator = excelFileIndex.keySet().iterator(); 
//		while(keySetIterator.hasNext()){ 
//			String key = keySetIterator.next(); 
//			System.out.println("key: " + key + " value: " + excelFileIndex.get(key)); 
//		}
		
//		---------------for sake of checking output of the issue data list ------------------
//		for (DataIssueTemplate issueCounter:IssueData ){
//			System.out.println(issueCounter.getStrAffectVersion());	
//		}
	}
	
	public static void StepThree_IdentifyingUniqueTagsFromAllIssues (){
		
		StepTwo_ReadingExcelsheet (); 
		
		DataFindUniqueTags objDataFindUniqueTags = new DataFindUniqueTags (strFilePath, strFileName, strFileName, IssueData, strlistUniqueTags); 
		objDataFindUniqueTags.identifyUniqueLabels();
		
		
		 for (String counter:strlistUniqueTags){
			 System.out.println(counter);
		 }
	}
	
	public static void StepFour_DistributingIssuesInMultipleTags (){
		int intStartingRowofData = 1;
		DataReadExcelFiles objDataReadExcelFiles = new DataReadExcelFiles(IssueData, intStartingRowofData, strFilePath, strFileName, strFileName); 
		excelFileIndex = objDataReadExcelFiles.createColumnIndex(0); 
		objDataReadExcelFiles.readExcelFiles(false);
		
		
		String uniqueTagFileName = "UniqueTags.xls";
		ArrayList<String> unTagSheetName = new ArrayList <String> ();
		unTagSheetName.add("Bug"); 
		unTagSheetName.add("Feature");
		DataReadUniqueTags objDataReadUniqueTags = new DataReadUniqueTags (strFilePath, strFileName, uniqueTagFileName, unTagSheetName, IssueData); 
		objDataReadUniqueTags.readUniqueLabels();
		objDataReadUniqueTags.differentiateBugVsFtr();
	}
	
	public static void StepFive_PerformCalculation (){
//		================== Explanation: Declaration of different variable to pass and store values so that they are available to all the functions. ===============================
		ArrayList<String> unTagSheetName = new ArrayList <String> ();
		unTagSheetName.add("Issue_Bug"); 
		unTagSheetName.add("Issue_Ftr");	
		unTagSheetName.add("Issue_Commit");
		int intStartingRowofData;
		
		HashMap<String, Integer> excelFileIndex_bug = new HashMap <String, Integer> ();  
		HashMap<String, Integer> excelFileIndex_ftr = new HashMap <String, Integer> ();  
		HashMap<String, Integer> excelFileIndex_commit = new HashMap <String, Integer> (); 
		
//		=============Explanation: Reading main issue file to perform action on this file ================== 
		intStartingRowofData = 3;
		DataReadExcelFiles objDataReadExcelFiles = new DataReadExcelFiles(IssueData, intStartingRowofData, strFilePath, strFileName, strFileName); 
		excelFileIndex = objDataReadExcelFiles.createColumnIndex(1); 
		objDataReadExcelFiles.readExcelFiles(true);
		
		
//		=========================Explanation: Reading issue and feature tabs to get and utilize the data ======================
		intStartingRowofData = 1;
		DataReadExcelFiles objDataReadExcelFiles_bug = new DataReadExcelFiles(bugIssueData, intStartingRowofData, strFilePath, strFileName, unTagSheetName.get(0)); 
		excelFileIndex_bug = objDataReadExcelFiles_bug.createColumnIndex(0); 
		objDataReadExcelFiles_bug.readExcelFiles(false);
		
		intStartingRowofData = 1;
		DataReadExcelFiles objDataReadExcelFiles_ftr = new DataReadExcelFiles(ftrIssueData, intStartingRowofData, strFilePath, strFileName, unTagSheetName.get(1)); 
		excelFileIndex_ftr = objDataReadExcelFiles_ftr.createColumnIndex(0); 
		objDataReadExcelFiles_ftr.readExcelFiles(false);
		
		
		//turn on for commit 
		
//		intStartingRowofData = 1;
//		DataReadExcelFiles objDataReadExcelFiles_commit = new DataReadExcelFiles(commitIssueData, intStartingRowofData, strFilePath, strFileName, unTagSheetName.get(2)); 
//		excelFileIndex_commit = objDataReadExcelFiles_commit.createColumnIndex(0); 
//		objDataReadExcelFiles_commit.readExcelFiles(false);
		

//		------------------For sake of debugging ------------------
//		System.out.println("+++++++++++++++"+bugIssueData.get(1).getStrKey());
//		System.out.println("+++++++++++++++"+bugIssueData.get(1).getDateCreated());
//		System.out.println("+++++++++++++++"+bugIssueData.get(1).getDateResolved());

//		============================Explanation: Perform week date calculation ==========================
		collectedReleasInfo();
		WeekDateCalc objWeekDateCalc_BFR = new WeekDateCalc (weekRateData_BFR, releaseInfo); 
		objWeekDateCalc_BFR.weekDateCalc();
		
		WeekDateCalc objWeekDateCalc_DFR = new WeekDateCalc (weekRateData_DFR, releaseInfo); 
		objWeekDateCalc_DFR.weekDateCalc();
		
		WeekDateCalc objWeekDateCalc_FCR = new WeekDateCalc (weekRateData_FCR, releaseInfo); 
		objWeekDateCalc_FCR.weekDateCalc();
		
		WeekDateCalc objWeekDateCalc_FOR = new WeekDateCalc (weekRateData_FOR, releaseInfo); 
		objWeekDateCalc_FOR.weekDateCalc();
		
		
//		for commit start this
		
//		WeekDateCalc objWeekDateCalc_Commit = new WeekDateCalc (weekRateData_Commit, releaseInfo); 
//		objWeekDateCalc_Commit.weekDateCalc();
		
		
//		===========================Explanation: calculation of different rate files ===================
		String outputFileName,  outputSheetName, outputLastValFileName; 
		
		
		outputLastValFileName = "LastValues_AllAtt.xls";
		
		outputFileName = "WeeklyRate_BFR.xls";
		outputSheetName = "BFR"; 
		WeekRateCalc objWeekRateCalc_BFR = new WeekRateCalc (strFilePath, strFileName, outputFileName, outputSheetName, bugIssueData, weekRateData_BFR, true, true, outputLastValFileName, true); 
		objWeekRateCalc_BFR.weeklyRateCalc();
		
		outputFileName = "WeeklyRate_DFR.xls";
		outputSheetName = "DFR"; 
		WeekRateCalc objWeekRateCalc_DFR = new WeekRateCalc (strFilePath, strFileName, outputFileName, outputSheetName, bugIssueData, weekRateData_DFR, true, false, outputLastValFileName, false); 
		objWeekRateCalc_DFR.weeklyRateCalc();
		
		outputFileName = "WeeklyRate_FCR.xls";
		outputSheetName = "FCR"; 
		WeekRateCalc objWeekRateCalc_FCR = new WeekRateCalc (strFilePath, strFileName, outputFileName, outputSheetName, ftrIssueData, weekRateData_FCR, true, true, outputLastValFileName, false); 
		objWeekRateCalc_FCR.weeklyRateCalc();
		
		outputFileName = "WeeklyRate_FOR.xls";
		outputSheetName = "FOR"; 
		WeekRateCalc objWeekRateCalc_FOR = new WeekRateCalc (strFilePath, strFileName, outputFileName, outputSheetName, ftrIssueData, weekRateData_FOR, true, false, outputLastValFileName, false); 
		objWeekRateCalc_FOR.weeklyRateCalc();
		
		
//		for commit start this
		
//		outputFileName = "WeeklyRate_Commit.xls";
//		outputSheetName = "Commit"; 
//		WeekRateCalc objWeekRateCalc_Commit = new WeekRateCalc (strFilePath, strFileName, outputFileName, outputSheetName, commitIssueData , weekRateData_Commit, true, true, outputLastValFileName, false); 
//		objWeekRateCalc_Commit.weeklyRateCalc();
		
		
		
//		------------for sake of debugging---------
//		for (WeekCalcTemplate counter_WeekCalcTemplate: weekRateData){
//			System.out.println(counter_WeekCalcTemplate.getWeekStart());
//			System.out.println(counter_WeekCalcTemplate.getWeekEnd());
//		}
				

	}
	
	
	public static void StepFourteen_PerformChurnCommitCalculation (int issueStart, int issueEnd, String issueName){
		
	}
	
	
	
	
	
	public static void StepSix_ReleaseCategorize (String optionSelection){
		
		ReleaseCategorize obj_ReleaseCategorize = new ReleaseCategorize (strFilePath, strOutputReleaseCategoriesFileName); 
		
		
		if (optionSelection.matches("Total Rank")){
			obj_ReleaseCategorize.method_CalcTotalRank();
		}
		
		else if (optionSelection.matches("Category")){
			obj_ReleaseCategorize.method_findCategory();
		}
	}
	
	public static void StepSeven_UpdateWeekRateWithReleaseCategory (){
		UpdateWeekRateWithReleaseCategory obj_UpdateWeekRateWithReleaseCategory = new UpdateWeekRateWithReleaseCategory (strOutputReleaseCategoriesFileName, strFilePath); 
		obj_UpdateWeekRateWithReleaseCategory.method_ReadReleaseCategories();
		
		obj_UpdateWeekRateWithReleaseCategory.method_UpdateWeeklyRate("WeeklyRate_BFR.xls");
		obj_UpdateWeekRateWithReleaseCategory.method_UpdateWeeklyRate("WeeklyRate_DFR.xls");
		obj_UpdateWeekRateWithReleaseCategory.method_UpdateWeeklyRate("WeeklyRate_FCR.xls");
		obj_UpdateWeekRateWithReleaseCategory.method_UpdateWeeklyRate("WeeklyRate_FOR.xls");
	}
		
	public static void StepEight_FindNeighbourDataset (){
		FindNeighbourDataset obj_FindNeighbourDataset = new FindNeighbourDataset (strFilePath); 
		obj_FindNeighbourDataset.getNeighbourDataset();
	}

	
	public static void StepNine_WeekDt_RelCat_MembershipIdentify (){
		WeekDt_RelCat_MembershipIdentify obj_WeekDt_RelCat_MembershipIdentify; 
		
		
		for (int curRelWeekCounter=1;curRelWeekCounter<intWeeksinCurRel;curRelWeekCounter++){
			obj_WeekDt_RelCat_MembershipIdentify = new WeekDt_RelCat_MembershipIdentify (strFilePath, "WeeklyRate_BFR_Combine_"+curRelWeekCounter+".xls", "WeeklyRate_BFR_Combine_"+curRelWeekCounter+".xls"); 
			obj_WeekDt_RelCat_MembershipIdentify.method_IOWeeklyVal();
			
			obj_WeekDt_RelCat_MembershipIdentify = new WeekDt_RelCat_MembershipIdentify (strFilePath, "WeeklyRate_DFR_Combine_"+curRelWeekCounter+".xls", "WeeklyRate_DFR_Combine_"+curRelWeekCounter+".xls"); 
			obj_WeekDt_RelCat_MembershipIdentify.method_IOWeeklyVal();
			
			obj_WeekDt_RelCat_MembershipIdentify = new WeekDt_RelCat_MembershipIdentify (strFilePath, "WeeklyRate_FCR_Combine_"+curRelWeekCounter+".xls", "WeeklyRate_FCR_Combine_"+curRelWeekCounter+".xls"); 
			obj_WeekDt_RelCat_MembershipIdentify.method_IOWeeklyVal();
			
			obj_WeekDt_RelCat_MembershipIdentify = new WeekDt_RelCat_MembershipIdentify (strFilePath, "WeeklyRate_FOR_Combine_"+curRelWeekCounter+".xls", "WeeklyRate_FOR_Combine_"+curRelWeekCounter+".xls"); 
			obj_WeekDt_RelCat_MembershipIdentify.method_IOWeeklyVal();
		}
		
		
		
		
	}

	public static void StepTen_PrepareTarFiles (){
		
		
		boolean success_tar3Data = (new File(strFilePath+"/Tar3Data")).mkdirs();
		if (!success_tar3Data) {
		    System.out.println("Problem in creating Tar3Data directory ");
		}
		
		for (int curRelWeekCounter=1;curRelWeekCounter<intWeeksinCurRel;curRelWeekCounter++){
			ArrayList <String> strInputRelCatMembershipFilesName = new ArrayList <String> (); 
			strInputRelCatMembershipFilesName.add("WeeklyRate_BFR_Combine_"+curRelWeekCounter+".xls"); 
			strInputRelCatMembershipFilesName.add("WeeklyRate_DFR_Combine_"+curRelWeekCounter+".xls"); 
			strInputRelCatMembershipFilesName.add("WeeklyRate_FCR_Combine_"+curRelWeekCounter+".xls"); 
			strInputRelCatMembershipFilesName.add("WeeklyRate_FOR_Combine_"+curRelWeekCounter+".xls"); 
			
			
			String new_strFilePath = strFilePath+"Tar3Data/Tar"+curRelWeekCounter+"/"; 
			boolean success = (new File(strFilePath+"Tar3Data/Tar"+curRelWeekCounter)).mkdirs();
			if (!success) {
			    System.out.println("Problem in creating directory "+ new_strFilePath);
			}
			
			PrepareTarInputFiles obj_PrepareTarInputFiles = new PrepareTarInputFiles (strFilePath, new_strFilePath, strInputRelCatMembershipFilesName, "PreparationTarInputFiles-"+curRelWeekCounter); 
			obj_PrepareTarInputFiles.method_combineRelCatMembershipFiles();
			obj_PrepareTarInputFiles.method_combineRelCatMembershipFilestoCSV();
//			System.out.println("=============================================================");
			
		}
	}
	
	
	public static void StepNineFive_WeekDt_RelCat_MembershipIdentify (){
		WeekDt_RelCat_MembershipIdentify obj_WeekDt_RelCat_MembershipIdentify; 
		
		
		for (int curRelWeekCounter=1;curRelWeekCounter<intWeeksinCurRel;curRelWeekCounter++){
			obj_WeekDt_RelCat_MembershipIdentify = new WeekDt_RelCat_MembershipIdentify (strFilePath, "WeeklyRate_BFR_Combine_"+curRelWeekCounter+".xls", "WeeklyRate_BFR_Combine_"+curRelWeekCounter+".xls"); 
			obj_WeekDt_RelCat_MembershipIdentify.method_IOWeeklyVal();
			
			obj_WeekDt_RelCat_MembershipIdentify = new WeekDt_RelCat_MembershipIdentify (strFilePath, "WeeklyRate_DFR_Combine_"+curRelWeekCounter+".xls", "WeeklyRate_DFR_Combine_"+curRelWeekCounter+".xls"); 
			obj_WeekDt_RelCat_MembershipIdentify.method_IOWeeklyVal();
			
			obj_WeekDt_RelCat_MembershipIdentify = new WeekDt_RelCat_MembershipIdentify (strFilePath, "WeeklyRate_FCR_Combine_"+curRelWeekCounter+".xls", "WeeklyRate_FCR_Combine_"+curRelWeekCounter+".xls"); 
			obj_WeekDt_RelCat_MembershipIdentify.method_IOWeeklyVal();
			
			obj_WeekDt_RelCat_MembershipIdentify = new WeekDt_RelCat_MembershipIdentify (strFilePath, "WeeklyRate_FOR_Combine_"+curRelWeekCounter+".xls", "WeeklyRate_FOR_Combine_"+curRelWeekCounter+".xls"); 
			obj_WeekDt_RelCat_MembershipIdentify.method_IOWeeklyVal();
		}
		
		
		
		
	}
	
	
	
	
	public static void OLDMAINmain (String args []) throws Exception{
		
		
//		=================Explanation: In case we need output in file instead of console ------------------
		PrintStream out = new PrintStream(new FileOutputStream(strFilePath+"consoleOutput.txt"));
		System.setOut(out);
		
		//--------Step 1: Merging all Excel files--------------
		
//		int startingOfExcelFiles = 1; 
//		int numberOfExcelFiles = 7;
//		
//		DataCombineExcelFiles objDataCombineExcelFiles = new DataCombineExcelFiles (strFilePath, strFileName, startingOfExcelFiles,numberOfExcelFiles); 
//		objDataCombineExcelFiles.mergeExcelFiles();
		
		
		//--------Step 2: Reading Excel sheet--------------

//		int intStartingRowofData = 3;
//		DataReadExcelFiles objDataReadExcelFiles = new DataReadExcelFiles(IssueData, intStartingRowofData, strFilePath, strFileName, strFileName); 
//		excelFileIndex = objDataReadExcelFiles.createColumnIndex(); 
//		objDataReadExcelFiles.readExcelFiles();
		
		
//		---------------For sake of checking excel index output only 0---------------------
//		Iterator<String> keySetIterator = excelFileIndex.keySet().iterator(); 
//		while(keySetIterator.hasNext()){ 
//			String key = keySetIterator.next(); 
//			System.out.println("key: " + key + " value: " + excelFileIndex.get(key)); 
//		}
		
//		---------------for sake of checking output of the issue data list ------------------
//		for (DataIssueTemplate issueCounter:IssueData ){
//			System.out.println(issueCounter.getStrAffectVersion());	
//		}
		
		
		
		//--------Step 3: Identifying unique tags from all issues --------------
		
//		DataFindUniqueTags objDataFindUniqueTags = new DataFindUniqueTags (strFilePath, strFileName, strFileName, IssueData, strlistUniqueTags); 
//		objDataFindUniqueTags.identifyUniqueLabels();
//		
//		
//		 for (String counter:strlistUniqueTags){
//			 System.out.println(counter);
//		 }
		 
		
		//---------Step 4: Distributing issues in multiple tags-----------------
		
//		int intStartingRowofData = 3;
//		DataReadExcelFiles objDataReadExcelFiles = new DataReadExcelFiles(IssueData, intStartingRowofData, strFilePath, strFileName, strFileName); 
//		excelFileIndex = objDataReadExcelFiles.createColumnIndex(); 
//		objDataReadExcelFiles.readExcelFiles();
//		
//		
//		String uniqueTagFileName = "UniqueTags.xls";
//		ArrayList<String> unTagSheetName = new ArrayList <String> ();
//		unTagSheetName.add("Bug"); 
//		unTagSheetName.add("Feature");
//		DataReadUniqueTags objDataReadUniqueTags = new DataReadUniqueTags (strFilePath, strFileName, uniqueTagFileName, unTagSheetName, IssueData); 
//		objDataReadUniqueTags.readUniqueLabels();
//		objDataReadUniqueTags.differentiateBugVsFtr();
		
		
		//-----------Step 5: Perform Calculation----------------------
		
////		================== Explanation: Declaration of different variable to pass and store values so that they are available to all the functions. ===============================
//		ArrayList<String> unTagSheetName = new ArrayList <String> ();
//		unTagSheetName.add("Issue_Bug"); 
//		unTagSheetName.add("Issue_Ftr");		
//		int intStartingRowofData;
//		
//		HashMap<String, Integer> excelFileIndex_bug = new HashMap <String, Integer> ();  
//		HashMap<String, Integer> excelFileIndex_ftr = new HashMap <String, Integer> ();  
//		
////		=============Explanation: Reading main issue file to perform action on this file ================== 
//		intStartingRowofData = 3;
//		DataReadExcelFiles objDataReadExcelFiles = new DataReadExcelFiles(IssueData, intStartingRowofData, strFilePath, strFileName, strFileName); 
//		excelFileIndex = objDataReadExcelFiles.createColumnIndex(1); 
//		objDataReadExcelFiles.readExcelFiles();
//		
//		
////		=========================Explanation: Reading issue and feature tabs to get and utilize the data ======================
//		intStartingRowofData = 1;
//		DataReadExcelFiles objDataReadExcelFiles_bug = new DataReadExcelFiles(bugIssueData, intStartingRowofData, strFilePath, strFileName, unTagSheetName.get(0)); 
//		excelFileIndex_bug = objDataReadExcelFiles_bug.createColumnIndex(0); 
//		objDataReadExcelFiles_bug.readExcelFiles();
//		
//		intStartingRowofData = 1;
//		DataReadExcelFiles objDataReadExcelFiles_ftr = new DataReadExcelFiles(ftrIssueData, intStartingRowofData, strFilePath, strFileName, unTagSheetName.get(1)); 
//		excelFileIndex_ftr = objDataReadExcelFiles_ftr.createColumnIndex(0); 
//		objDataReadExcelFiles_ftr.readExcelFiles();
//		
//
////		------------------For sake of debugging ------------------
////		System.out.println("+++++++++++++++"+bugIssueData.get(1).getStrKey());
////		System.out.println("+++++++++++++++"+bugIssueData.get(1).getDateCreated());
////		System.out.println("+++++++++++++++"+bugIssueData.get(1).getDateResolved());
//
//		
//		
//		
//		
//		
//		
//				
////		============================Explanation: Perform week date calculation ==========================
//		collectedReleasInfo();
//		WeekDateCalc objWeekDateCalc_BFR = new WeekDateCalc (weekRateData_BFR, releaseInfo); 
//		objWeekDateCalc_BFR.weekDateCalc();
//		
//		WeekDateCalc objWeekDateCalc_DFR = new WeekDateCalc (weekRateData_DFR, releaseInfo); 
//		objWeekDateCalc_DFR.weekDateCalc();
//		
//		WeekDateCalc objWeekDateCalc_FCR = new WeekDateCalc (weekRateData_FCR, releaseInfo); 
//		objWeekDateCalc_FCR.weekDateCalc();
//		
//		WeekDateCalc objWeekDateCalc_FOR = new WeekDateCalc (weekRateData_FOR, releaseInfo); 
//		objWeekDateCalc_FOR.weekDateCalc();
//		
////		===========================Explanation: calculation of different rate files ===================
//		String outputFileName,  outputSheetName; 
//		outputFileName = "WeeklyRate_BFR.xls";
//		outputSheetName = "BFR"; 
//		WeekRateCalc objWeekRateCalc_BFR = new WeekRateCalc (strFilePath, strFileName, outputFileName, outputSheetName, bugIssueData, weekRateData_BFR, true, true); 
//		objWeekRateCalc_BFR.weeklyRateCalc();
//		
//		outputFileName = "WeeklyRate_DFR.xls";
//		outputSheetName = "DFR"; 
//		WeekRateCalc objWeekRateCalc_DFR = new WeekRateCalc (strFilePath, strFileName, outputFileName, outputSheetName, bugIssueData, weekRateData_DFR, true, false); 
//		objWeekRateCalc_DFR.weeklyRateCalc();
//		
//		outputFileName = "WeeklyRate_FCR.xls";
//		outputSheetName = "FCR"; 
//		WeekRateCalc objWeekRateCalc_FCR = new WeekRateCalc (strFilePath, strFileName, outputFileName, outputSheetName, ftrIssueData, weekRateData_FCR, true, true); 
//		objWeekRateCalc_FCR.weeklyRateCalc();
//		
//		outputFileName = "WeeklyRate_FOR.xls";
//		outputSheetName = "FOR"; 
//		WeekRateCalc objWeekRateCalc_FOR = new WeekRateCalc (strFilePath, strFileName, outputFileName, outputSheetName, ftrIssueData, weekRateData_FOR, true, false); 
//		objWeekRateCalc_FOR.weeklyRateCalc();
//		
//		
//		
//		
//		
//		
//		
////		------------for sake of debugging---------
////		for (WeekCalcTemplate counter_WeekCalcTemplate: weekRateData){
////			System.out.println(counter_WeekCalcTemplate.getWeekStart());
////			System.out.println(counter_WeekCalcTemplate.getWeekEnd());
////		}
		
	}
	
	
	public static void StepElevenJiraManipulation (int issueStart, int issueEnd) {
		JiraManipulation objJiraManipulation = new JiraManipulation (); 
		
		int fileCounter =0; 
		for (int counter =issueEnd; counter>issueStart; counter=counter-100){
			System.out.println("Working on issues :"+ counter +" to "+ (counter-99));
			fileCounter++; 
			
			
			try {
				objJiraManipulation.methodJiraManipulation(strJiraFilePath, counter-100, counter, fileCounter);  
			}
			catch (Exception ex){
				System.out.println("Exception");
			}
			
			
			
			
		}
	}
	
	
	public static void StepTwelveTopicFileCreation (){
		createTopicFiles obj_createTopicFiles = new createTopicFiles (); 
		
		for (int counterSprints =1; counterSprints<16; counterSprints++){
			obj_createTopicFiles.creatingTopicsfromSprints(Integer.toString(counterSprints), "AURORA-", Integer.toString(counterSprints)+".txt");
		}
		
//		obj_createTopicFiles.creatingTopicsfromSprints("15", "AURORA-", "1.txt");
	}
	
	public static void StepThirteenJiraDownloadIssues (int issueStart, int issueEnd, String issueName) {
		JiraIssueDownload objJiraIssueDownload = new JiraIssueDownload (); 
		
		int fileCounter =0; 
		for (int counter =issueEnd; counter>issueStart; counter=counter-100){
			System.out.println("Working on issues :"+ counter +" to "+ (counter-99));
			fileCounter++; 
			
			
			try {
				objJiraIssueDownload.methodJiraManipulation(strJiraFilePath, counter-100, counter, fileCounter, issueName);  
			}
			catch (Exception ex){
				System.out.println("Exception");
				ex.printStackTrace();
			}
			
			
			
			
		}
	}
	
	
}
