package GitAnalysis;

import java.util.ArrayList;
import java.util.Date;

public class al_GitInfo {
	
	
	public String strCommitSha = null; 
	public String strAuthor = null; 
	public String strDate = null; 
	public Date dtDate = null; 
	public String strComments = null; 
	
	public String releaseName = null; 

	public ArrayList <String> al_IssueNum = new ArrayList <String> (); 
	
	public int intFileChanged = 0; 
	public int intInsertion = 0; 
	public int intDeletion = 0; 
	
	public ArrayList <clsFileCommitDiff> al_FileCommitDiff = new ArrayList <clsFileCommitDiff> (); 
	
	
}


class clsFileCommitDiff {
	public String strFileName = null; 
	public int intDiff = 0; 
	public int intAddition = 0; 
	public int intDeletion = 0; 
	public int intModification = 0; 
	public int intBytes = 0; 
	
}


