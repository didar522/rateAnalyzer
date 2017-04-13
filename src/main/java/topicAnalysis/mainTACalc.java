package topicAnalysis;

import java.awt.PageAttributes.OriginType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.nio.file.Files;

import org.apache.poi.hssf.record.FilePassRecord;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

import cc.mallet.util.FileUtils;
import topicAnalysis.malletTopicAnalysis;
import topicAnalysis.postTopicAnalysis;
import topicAnalysis.topicAnalysis;
import topicAnalysis.topicAnalysisListStr;
import topicAnalysis.topicPreProcessing;

public class mainTACalc {

	
//	public static String filePath = "C:/Users/S.M.Didar/Onedrive/Didar DBPC/PhD Research/Summer 2016/New start with OSS/Analysis/V5 Weekly rate data identify/Topic Modeling/";

//	public static String filePath = "C:/Users/S.M.Didar/OneDrive/Didar DBPC/PhD Research/Winter 2017/EASE 2017/Analysis/R rezaul analysis/";
	public static String filePath = "C:/Users/Didar/OneDrive/Didar DBPC/PhD Research/Winter 2017/BS & PhD/Step 3 - Topic analysis on manual analyzed release/";
	
	public static int numTrainingWeeks=1;//=80;   /// change also in precision recall msr file outlebelling function 
	public static int commentAnalysisUptoWeek=5; //= 110; 
	
	public static int numTopics=10; 
	public static double taAlpha = 0.1; 
	public static int taIteration = 2000; 
	
	
	public static void main(String[] args) {
		

		
//		------------------Level 6:  PreProcessing all documents and copying from Pre-Process to Backup files.  	
		
		
//		File BackUpTopics = new File (filePath+"/Topic backup files"); 
//		if (!BackUpTopics.exists() || !BackUpTopics.isDirectory()){
//			new File(filePath+"/Topic backup files").mkdir();
//		}
//		
//		topicPreProcessing objTopicPreProcessing = new topicPreProcessing (); 
//		objTopicPreProcessing.preProcessing(numTrainingWeeks, commentAnalysisUptoWeek, filePath);
		
	
//		------------------Level 7:  Copy all training files, copy analysis files and perform topic analysis 		
		
		
		File topics = new File (filePath+"/Topic files"); 
		File results = new File (filePath+"/Results"); 
		
		if (!topics.exists() || !topics.isDirectory()){
				new File(filePath+"/Topic files").mkdir();
		}
		if (!results.exists() || !results.isDirectory()){
			new File(filePath+"/Results").mkdir();
		}

		
		topicAnalysis TA = new topicAnalysis(); 
		
		
		TA.copyTrainingFiles(numTrainingWeeks, filePath); 
		TA.topicAnalysis (numTrainingWeeks, commentAnalysisUptoWeek, filePath,  numTopics, taAlpha, taIteration); 
//		
		
	}// end of main method
	
}// end of class	
	
	
	