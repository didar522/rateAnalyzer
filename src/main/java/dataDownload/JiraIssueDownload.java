//package dataDownload;
//
//
//import com.atlassian.jira.rest.client.JiraRestClient;
//import com.atlassian.jira.rest.client.JiraRestClientFactory;
//import com.atlassian.jira.rest.client.domain.Issue;
//import com.atlassian.jira.rest.client.domain.IssueLink;
//import com.atlassian.jira.rest.client.domain.Resolution;
//import com.atlassian.jira.rest.client.domain.SearchResult;
//import com.atlassian.jira.rest.client.domain.BasicIssue;
////import com.atlassian.jira.rest.client.domain.SearchResult;
//import com.atlassian.jira.rest.client.domain.User;
//import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
////import com.atlassian.sal.api.ApplicationProperties;
////import com.atlassian.sal.api.UrlMode;
////import com.atlassian.sal.api.user.UserManager;
////import com.atlassian.sal.api.user.UserProfile;
//import com.atlassian.util.concurrent.Promise;
//
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintStream;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.net.URL;
//import java.util.Iterator;
////import java.net.URISyntaxException;
//
//
//
//
//
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//
//
//
//
//
//
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//
///**
// * A simple Java Servlet that triggers a REST call back to JIRA when the servlet URL is accessed. This is a slightly
// * contrived example, as it is not efficient to use REST to communicate with the JIRA instance that the plugin is
// * actually deployed on (you should use the JIRA Java API instead -
// * https://developer.atlassian.com/display/JIRADEV/JIRA+Java+API+Reference).
// *
// * The URL to access this servlet is defined in the atlassian-plugin.xml file.
// */
//
//
//public class JiraIssueDownload {
//	
//	
//	
//	String filePath = null; //"C:/Users/S.M.Didar/Dropbox/Didar DBPC/PhD Research/Fall 2015/ICSE Workshop/Evaluation/V5/";
//	int issueStart, issueEnd;
//
////	public JiraManipulation (String filePath, int issueStart, int issueEnd){
////		this.filePath = filePath; 
////		this.issueStart = issueStart; 
////		this.issueEnd = issueEnd; 
////	}
//
//	
//	static	String  extractText(String strToParse, String strStart, String strFinish){
//	  
//    	Pattern pattern = Pattern.compile(strStart+"(.*?)"+strFinish);
//		Matcher matcher = pattern.matcher(strToParse);
//		String fixVersion = null;
//		
//		if (matcher.find())
//		{
//			fixVersion = matcher.group(0); 
//			fixVersion = fixVersion.replace(strStart, ""); 
//			fixVersion = fixVersion.replace(strFinish, ""); 
//		}
//		
//		return fixVersion; 
//	}
//	
//
//	
//	public void methodJiraManipulation (String filePath, int issueStart, int issueEnd, int fileCounter, String issueName) throws Exception{
//		
//	
//		
//		JiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
//		URI jiraServerUri = null;	
//		
//		try{
//		        jiraServerUri = new URI("https://issues.apache.org/jira");
//		    }
//	        catch (URISyntaxException e)
//	        {
//	           System.out.println("Could not understand the JIRA Base URL: " + e.getMessage());
//	        }
//		
//		JiraRestClient jc = factory.createWithBasicHttpAuthentication(jiraServerUri, "didar522@gmail.com", "ammu69JIRA05ma");
//		String tempversionFlag = null, comment = null, description = null, summary= null, commentDate= null, commentBU= null; 
//		 
////		String filePath = "C:/Users/S.M.Didar/Dropbox/Didar DBPC/PhD Research/Fall 2015/ICSE Workshop/Evaluation/V4/Aurora/";
////		int issueStart=1577, issueEnd = 1550;
//		
//		String fileName = fileCounter+".xls";
//	        try {
//				FileOutputStream fileOut = new FileOutputStream(filePath+fileName);
//				HSSFWorkbook workbook = new HSSFWorkbook();
//				HSSFSheet worksheetBug = workbook.createSheet("comments.xls");
//				
//				
//				HSSFRow row;
//				HSSFCell cell;
//				int rowCounter=0;
//				
//				row = worksheetBug.createRow(rowCounter);
//				cell = row.createCell(0); cell.setCellValue ("Project"); 
//				cell = row.createCell(1); cell.setCellValue ("Key"); 
//				cell = row.createCell(2); cell.setCellValue ("Issue Type"); 
//				cell = row.createCell(3); cell.setCellValue ("Status"); 
//				cell = row.createCell(4); cell.setCellValue ("Created"); 
//				cell = row.createCell(5); cell.setCellValue ("Updated"); 
//				cell = row.createCell(6); cell.setCellValue ("Resolved"); 
//				cell = row.createCell(7); cell.setCellValue ("Affects Version/s"); 
//				cell = row.createCell(8); cell.setCellValue ("Fix Version/s"); 
//				cell = row.createCell(9); cell.setCellValue ("Summary"); 
//				cell = row.createCell(10); cell.setCellValue ("Description"); 
//				cell = row.createCell(11); cell.setCellValue ("Reporter"); 
//				cell = row.createCell(12); cell.setCellValue ("Priority"); 
//				cell = row.createCell(13); cell.setCellValue ("Resolution"); 
//				cell = row.createCell(14); cell.setCellValue ("Assignee"); 
//				cell = row.createCell(15); cell.setCellValue ("Component/s"); 
//				cell = row.createCell(16); cell.setCellValue ("Comment Date"); 
//				cell = row.createCell(17); cell.setCellValue ("Comments"); 
//				
//				
//				
//				
//				
//				
//				System.out.println ("test test"); 
//					for (int issueCounter=issueEnd;issueCounter>issueStart;issueCounter--){
//						
//						System.out.println("XXXXXXXXXXXXXXXXXXXX");
//						
//						rowCounter++; 
//						Issue issue = null;
//						Resolution resolution = null ; 
//						row = worksheetBug.createRow(rowCounter);
//						
//
//						try{
//						
//							issue = jc.getIssueClient().getIssue(issueName+issueCounter).claim();
//							
//							
//							
//							
//						}catch (Exception ex){
//							System.out.println("Isse dont exists "+ issueCounter );
//							continue; 
//							
//						}
//
//					      
//					        
//					       cell = row.createCell(0);
//					        if (issue.getProject() != null) {					        
//					        	cell.setCellValue(extractText(issue.getProject().toString(), "name=", "}"));
//					        }
//					        
//					        
//					        cell = row.createCell(1);
//					        if (issue.getKey() != null) {
//					        	cell.setCellValue(issue.getKey());
//					        }
//					        
//					        cell = row.createCell(2);
//					        if (issue.getIssueType() != null) {
//					        	cell.setCellValue(extractText(issue.getIssueType().toString(), "name=", ","));
//					        }
//					        
//							cell = row.createCell(3);
//							if (issue.getStatus() != null) {
//								cell.setCellValue(extractText(issue.getStatus().toString(), "name=", "}"));
//							}
//							
//							cell = row.createCell(4);
//							if (issue.getCreationDate() != null) {
//								cell.setCellValue(extractText(issue.getCreationDate().toString(), "", "T"));
//							}
//							
//							cell = row.createCell(5);
//							if (issue.getUpdateDate() != null) {
//								cell.setCellValue(extractText(issue.getUpdateDate().toString(), "", "T"));
//							}
//							
//							cell = row.createCell(6);
//							String reolvedDate = extractText(URIreader (issue.getSelf().toURL()), "\"resolutiondate\":\"", "T");
//														
//							if (reolvedDate != null) {
//								cell.setCellValue(reolvedDate);
//							}
//							
//							cell = row.createCell(7);
//							if (issue.getAffectedVersions() != null) {
//								cell.setCellValue(extractText(issue.getAffectedVersions().toString(), "name=", ","));
//							}
//							
//							cell = row.createCell(8);
//							if (issue.getFixVersions() != null) {
//								cell.setCellValue(extractText(issue.getFixVersions().toString(), "name=", ","));
//							}
//							
//							cell = row.createCell(9);
//							summary =issue.getSummary();
//							if (summary != null){
//								if (summary.length()>32766){
//									summary  = summary.substring(0, 32700);
//								}
//							}
//							cell.setCellValue(summary);
//							
//							cell = row.createCell(10);
//							description =issue.getDescription();
//							if (description != null){
//								if (description.length()>32766){
//									description = description.substring(0, 32700);
//								}
//							}
//							cell.setCellValue(description);
//							
//							
//							
//							cell = row.createCell(11);
//							if (issue.getReporter() != null) {
//								cell.setCellValue(extractText(issue.getReporter().toString(), "username=", "}"));
//							}	
//							
//							cell = row.createCell(12);
//							if (issue.getPriority() != null) {
//								cell.setCellValue(extractText(issue.getPriority().toString(), "name=", ", id"));
//							}
//							
//							cell = row.createCell(13);
//							if (issue.getResolution() != null) {
//								cell.setCellValue(issue.getResolution().toString());
//							}
//							
//							
//							cell = row.createCell(14);
//							if (issue.getAssignee() != null) {
//								cell.setCellValue(issue.getAssignee().toString());
//							}
//							
//							
//							cell = row.createCell(15);
//							if (issue.getComponents() != null) {
//								cell.setCellValue(extractText(issue.getComponents().toString(), "name=", ", description"));
//							}
//							
//							commentBU = issue.getComments().toString();
//
//							cell = row.createCell(16);
//							commentDate = commentBU; 
//							if (commentDate != null && commentDate.equalsIgnoreCase("[]")==false){
//								commentDate = extractText(commentDate, "updateDate=", "}");
//							}
//							cell.setCellValue(commentDate);
//							
//							
//							cell = row.createCell(17);
//							comment = commentBU; 
//							
//							if (comment != null && comment.equalsIgnoreCase("[]")==false){
//	
//								comment = comment.replace("self="+extractText(comment, "self=", ", body=")+", body=", " ");
//								comment = comment.replace("author="+extractText(comment, "author=", "updateDate=")+"updateDate=", " ");
//							if (comment.length()>32766){
//									comment = comment.substring(0, 32700);
//								}
//							}
//							cell.setCellValue(comment);
//							
//							cell = row.createCell(10);
//							cell.setCellValue(tempversionFlag);
//							System.out.println("Written row "+ rowCounter +" Issue Number "+ issueCounter);
//						
//						}				
//		
//				workbook.write(fileOut);
//				fileOut.flush();
//				fileOut.close();
//				System.out.println("Success: Unique labels written");
//				
//			} catch (FileNotFoundException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//				System.out.println("Error: Unique labels writing");
//			}catch (IOException e2){
//				e2.printStackTrace();
//				System.out.println("Error: Unique labels writing");
//			}
//    
//
//	}//end main
//	
//	
//	
//	    public String URIreader (URL url) throws Exception {
//
////	        URL oracle = new URL("http://www.oracle.com/");
//	        BufferedReader in = new BufferedReader(
//	        new InputStreamReader(url.openStream()));
//	        String content = ""; 
//	        String inputLine;
//	        while ((inputLine = in.readLine()) != null)
//	            content = content + inputLine;
//	        in.close();
//	        
//	        return content; 
//	    }
//	
//	
//	
//}//end class   
//		
//		
//		
//		
//		
//	