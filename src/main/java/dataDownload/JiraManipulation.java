package dataDownload;


import com.atlassian.jira.rest.client.JiraRestClient;
import com.atlassian.jira.rest.client.JiraRestClientFactory;
import com.atlassian.jira.rest.client.domain.Issue;
import com.atlassian.jira.rest.client.domain.IssueLink;
import com.atlassian.jira.rest.client.domain.SearchResult;
import com.atlassian.jira.rest.client.domain.BasicIssue;
//import com.atlassian.jira.rest.client.domain.SearchResult;
import com.atlassian.jira.rest.client.domain.User;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
//import com.atlassian.sal.api.ApplicationProperties;
//import com.atlassian.sal.api.UrlMode;
//import com.atlassian.sal.api.user.UserManager;
//import com.atlassian.sal.api.user.UserProfile;
import com.atlassian.util.concurrent.Promise;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
//import java.net.URISyntaxException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple Java Servlet that triggers a REST call back to JIRA when the servlet URL is accessed. This is a slightly
 * contrived example, as it is not efficient to use REST to communicate with the JIRA instance that the plugin is
 * actually deployed on (you should use the JIRA Java API instead -
 * https://developer.atlassian.com/display/JIRADEV/JIRA+Java+API+Reference).
 *
 * The URL to access this servlet is defined in the atlassian-plugin.xml file.
 */


public class JiraManipulation {
	
	
	
	String filePath = null; //"C:/Users/S.M.Didar/Dropbox/Didar DBPC/PhD Research/Fall 2015/ICSE Workshop/Evaluation/V4/Aurora/";
	int issueStart, issueEnd;

//	public JiraManipulation (String filePath, int issueStart, int issueEnd){
//		this.filePath = filePath; 
//		this.issueStart = issueStart; 
//		this.issueEnd = issueEnd; 
//	}

	
	static	String  extractText(String strToParse, String strStart, String strFinish){
	  
    	Pattern pattern = Pattern.compile(strStart+"(.*?)"+strFinish);
		Matcher matcher = pattern.matcher(strToParse);
		String fixVersion = null;
		
		if (matcher.find())
		{
			fixVersion = matcher.group(0); 
			fixVersion = fixVersion.replace(strStart, ""); 
			fixVersion = fixVersion.replace(strFinish, ""); 
		}
		
		return fixVersion; 
	}
	

	
	public void methodJiraManipulation (String filePath, int issueStart, int issueEnd, int fileCounter) throws Exception{
		
	
		
		JiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
		URI jiraServerUri = null;	
		
		try{
		        jiraServerUri = new URI("https://issues.apache.org/jira");
		    }
	        catch (URISyntaxException e)
	        {
	           System.out.println("Could not understand the JIRA Base URL: " + e.getMessage());
	        }
		
		JiraRestClient jc = factory.createWithBasicHttpAuthentication(jiraServerUri, "didar522@gmail.com", "ammu69JIRA05ma");
		String tempversionFlag = null, comment = null, description = null, summary= null, commentDate= null, commentBU= null; 
		 
//		String filePath = "C:/Users/S.M.Didar/Dropbox/Didar DBPC/PhD Research/Fall 2015/ICSE Workshop/Evaluation/V4/Aurora/";
//		int issueStart=1577, issueEnd = 1550;
		
		String fileName = fileCounter+".xls";
	        try {
				FileOutputStream fileOut = new FileOutputStream(filePath+fileName);
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet worksheetBug = workbook.createSheet("comments.xls");
				
				int rowCounter=0; 
				
				System.out.println ("test test"); 
					for (int issueCounter=issueEnd;issueCounter>issueStart;issueCounter--){
						
						
						
						HSSFRow row; 
						rowCounter++; 
						Issue issue = null;
						row = worksheetBug.createRow(rowCounter);
						

						try{
						
							issue = jc.getIssueClient().getIssue("AURORA-"+issueCounter).claim();
							 
							
						}catch (Exception ex){
							System.out.println("Isse done exists "+ issueCounter );
							continue; 
							
						}

					        tempversionFlag = issue.getFixVersions().toString(); 
					        
					        HSSFCell cell = row.createCell(0);
							cell.setCellValue(issue.getKey());
							
					        cell = row.createCell(1);
							cell.setCellValue(extractText(tempversionFlag, "name=", ","));
							
							cell = row.createCell(2);
							cell.setCellValue(extractText(issue.getStatus().toString(), "name=", "}"));
							
							cell = row.createCell(3);
							cell.setCellValue(extractText(issue.getCreationDate().toString(), "", "T"));
									
							
							
							cell = row.createCell(4);
							cell.setCellValue(extractText(issue.getIssueType().toString(), "name=", ",")); 
									
							
						
							
							cell = row.createCell(5);
							cell.setCellValue(issue.getIssueLinks().toString());
							
							cell = row.createCell(6);
							summary =issue.getSummary();
							if (summary != null){
								if (summary.length()>32766){
									summary  = summary.substring(0, 32700);
								}
							}
							cell.setCellValue(summary);
							
							cell = row.createCell(7);
							description =issue.getDescription();
							if (description != null){
								if (description.length()>32766){
									description = description.substring(0, 32700);
								}
							}
							cell.setCellValue(description);
					       
							commentBU = issue.getComments().toString();

							cell = row.createCell(8);
							commentDate = commentBU; 
							if (commentDate != null && commentDate.equalsIgnoreCase("[]")==false){
								commentDate = extractText(commentDate, "updateDate=", "}");
							}
							cell.setCellValue(commentDate);
							
							
							
							
							cell = row.createCell(9);
							comment = commentBU; 
							
							if (comment != null && comment.equalsIgnoreCase("[]")==false){
	
								comment = comment.replace("self="+extractText(comment, "self=", ", body=")+", body=", " ");
								comment = comment.replace("author="+extractText(comment, "author=", "updateDate=")+"updateDate=", " ");
							if (comment.length()>32766){
									comment = comment.substring(0, 32700);
								}
							}
							cell.setCellValue(comment);
							
							cell = row.createCell(10);
							cell.setCellValue(tempversionFlag);
							System.out.println("Written row "+ rowCounter +" Issue Number "+ issueCounter);
						
						}				
		
				workbook.write(fileOut);
				fileOut.flush();
				fileOut.close();
				System.out.println("Success: Unique labels written");
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("Error: Unique labels writing");
			}catch (IOException e2){
				e2.printStackTrace();
				System.out.println("Error: Unique labels writing");
			}
    

	}//end main
	
	
}//end class   
		
		
		
		
		
	