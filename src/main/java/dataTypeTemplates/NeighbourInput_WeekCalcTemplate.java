package dataTypeTemplates;



import java.util.Date;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NeighbourInput_WeekCalcTemplate {

	private int weekNum, releaseNum; 
	/**
	 * @return the releaseNum
	 */
	public int getReleaseNum() {
		return releaseNum;
	}
	/**
	 * @param releaseNum the releaseNum to set
	 */
	public void setReleaseNum(int releaseNum) {
		this.releaseNum = releaseNum;
	}
	public int inOinC,erOinC,inOltC, erOltC,inO,erO; 
	public double weeklyVal, uptoWeekVal; 
	public double totalVal, normalTotalVal, releaseDuration, releaseCompletion; 
	public String strReleaseCategoryRuf, strProjectName, strProjectNameRel, strOrgCategory; 
	
	/**
	 * @return the weeklyVal
	 */
	public double getWeeklyVal() {
		return weeklyVal;
	}
	/**
	 * @param weeklyVal the weeklyVal to set
	 */
	public void setWeeklyVal(double weeklyVal) {
		this.weeklyVal = weeklyVal;
	}
	/**
	 * @return the uptoWeekVal
	 */
	public double getUptoWeekVal() {
		return uptoWeekVal;
	}
	/**
	 * @param uptoWeekVal the uptoWeekVal to set
	 */
	public void setUptoWeekVal(double uptoWeekVal) {
		this.uptoWeekVal = uptoWeekVal;
	}
	/**
	 * @return the normalTotalVal
	 */
	public double getNormalTotalVal() {
		return normalTotalVal;
	}
	/**
	 * @param normalTotalVal the normalTotalVal to set
	 */
	public void setNormalTotalVal(double normalTotalVal) {
		this.normalTotalVal = normalTotalVal;
	}
	/**
	 * @return the strReleaseCategoryRuf
	 */
	public String getStrReleaseCategoryRuf() {
		return strReleaseCategoryRuf;
	}
	/**
	 * @param strReleaseCategoryRuf the strReleaseCategoryRuf to set
	 */
	public void setStrReleaseCategoryRuf(String strReleaseCategoryRuf) {
		this.strReleaseCategoryRuf = strReleaseCategoryRuf;
	}
	/**
	 * @return the strProjectName
	 */
	public String getStrProjectName() {
		return strProjectName;
	}
	/**
	 * @param strProjectName the strProjectName to set
	 */
	public void setStrProjectName(String strProjectName) {
		this.strProjectName = strProjectName;
	}
	/**
	 * @return the strProjectNameRel
	 */
	public String getStrProjectNameRel() {
		return strProjectNameRel;
	}
	/**
	 * @param strProjectNameRel the strProjectNameRel to set
	 */
	public void setStrProjectNameRel(String strProjectNameRel) {
		this.strProjectNameRel = strProjectNameRel;
	}
	/**
	 * @return the strOrgCategory
	 */
	public String getStrOrgCategory() {
		return strOrgCategory;
	}
	/**
	 * @param strOrgCategory the strOrgCategory to set
	 */
	public void setStrOrgCategory(String strOrgCategory) {
		this.strOrgCategory = strOrgCategory;
	}
	/**
	 * @return the releaseCompletion
	 */
	public double getReleaseCompletion() {
		return releaseCompletion;
	}
	/**
	 * @param releaseCompletion the releaseCompletion to set
	 */
	public void setReleaseCompletion(double releaseCompletion) {
		this.releaseCompletion = releaseCompletion;
	}
	private Date weekStart, weekEnd, releaseStart, releaseEnd; 
	/**
	 * @return the releaseDuration
	 */
	public double getReleaseDuration() {
		return releaseDuration;
	}
	/**
	 * @param releaseDuration the releaseDuration to set
	 */
	public void setReleaseDuration(double releaseDuration) {
		this.releaseDuration = releaseDuration;
	}
	/**
	 * @return the releaseStart
	 */
	public Date getReleaseStart() {
		return releaseStart;
	}
	/**
	 * @param releaseStart the releaseStart to set
	 */
	public void setReleaseStart(Date releaseStart) {
		this.releaseStart = releaseStart;
	}
	/**
	 * @return the releaseEnd
	 */
	public Date getReleaseEnd() {
		return releaseEnd;
	}
	/**
	 * @param releaseEnd the releaseEnd to set
	 */
	public void setReleaseEnd(Date releaseEnd) {
		this.releaseEnd = releaseEnd;
	}
	/**
	 * @return the releaseCategory
	 */
	public String getReleaseCategory() {
		return releaseCategory;
	}
	/**
	 * @param releaseCategory the releaseCategory to set
	 */
	public void setReleaseCategory(String releaseCategory) {
		this.releaseCategory = releaseCategory;
	}
	String releaseCategory;  
	
	
	public int getWeekNum() {
		return weekNum;
	}
	public void setWeekNum(int weekNum) {
		this.weekNum = weekNum;
	}
	public int getInOinC() {
		return inOinC;
	}
	public void setInOinC(int inOinC) {
		this.inOinC = inOinC;
	}
	public int getErOinC() {
		return erOinC;
	}
	public void setErOinC(int erOinC) {
		this.erOinC = erOinC;
	}
	public int getInOltC() {
		return inOltC;
	}
	public void setInOltC(int inOltC) {
		this.inOltC = inOltC;
	}
	public int getErOltC() {
		return erOltC;
	}
	public void setErOltC(int erOltC) {
		this.erOltC = erOltC;
	}
	public int getInO() {
		return inO;
	}
	public void setInO(int inO) {
		this.inO = inO;
	}
	public int getErO() {
		return erO;
	}
	public void setErO(int erO) {
		this.erO = erO;
	}
	public double getTotalVal() {
		return totalVal;
	}
	public void setTotalVal(double totalVal) {
		this.totalVal = totalVal;
	}
	public Date getWeekStart() {
		return weekStart;
	}
	public void setWeekStart(Date weekStart) {
		this.weekStart = weekStart;
	}
	public Date getWeekEnd() {
		return weekEnd;
	}
	public void setWeekEnd(Date weekEnd) {
		this.weekEnd = weekEnd;
	}
	
	public void durationCompletionCalculation (){
		releaseDuration=getDateDiff(releaseStart,releaseEnd,TimeUnit.DAYS);
		releaseCompletion = getDateDiff(releaseStart,weekEnd,TimeUnit.DAYS)/releaseDuration*100;
	}
	
	public long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
	    long diffInMillies = date2.getTime() - date1.getTime();
	    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}
	
}