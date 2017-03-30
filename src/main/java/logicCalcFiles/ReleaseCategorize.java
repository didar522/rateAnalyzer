package logicCalcFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ReleaseCategorize {
	String filePath;
	String outputLastValFileName;
	
	public ReleaseCategorize (String temp_filePath, String temp_outputLastValFileName){
		
		this.filePath = temp_filePath;
		this.outputLastValFileName = temp_outputLastValFileName;
		
	}
	
	
	
	
	public void method_CalcTotalRank (){
		
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
				
				Iterator<Row> rowIterator = worksheetRate.iterator();
			    while (rowIterator.hasNext()) {
			        totalRank =0; 
			    	row = rowIterator.next();
			     
			      if (row.getRowNum()==0){
			    	  row= worksheetRate.getRow(0); 
			    	  cell= row.createCell(row.getLastCellNum()+0);	cell.setCellValue("Total Rank");
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
	
	
	
	
	

	public void method_findCategory (){
		
		//column num for total rank and ranks are directly given as a raw number due to shortage of time. We need to later on update this to avoid error and also make it scalable with any other scenario. 
		
		
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
				double minTotalRank=1000, maxTotalRank=0, firstQuartile =0, secondQuartile = 0;
				
				Iterator<Row> rowIterator = worksheetRate.iterator();
			    while (rowIterator.hasNext()) {
			        row = rowIterator.next();
			     
			      if (row.getRowNum()>0){
//			    	  summationRank = summationRank + row.getCell(18).getNumericCellValue(); 
			    	  if (row.getCell(18).getNumericCellValue()<minTotalRank){
			    		  minTotalRank = row.getCell(18).getNumericCellValue();
			    	  }
			    	  if (row.getCell(18).getNumericCellValue()>maxTotalRank){
			    		  maxTotalRank = row.getCell(18).getNumericCellValue();
			    	  }
			      }
			    }  
			      
			    firstQuartile = minTotalRank+(maxTotalRank-minTotalRank)/3 ;
			    secondQuartile = minTotalRank+(maxTotalRank-minTotalRank)/3*2 ;
			    
			    Iterator<Row> rowIterator2 = worksheetRate.iterator();
			    while (rowIterator2.hasNext()) {
			        row = rowIterator2.next();
			     
			      if (row.getRowNum()==0){
			    	  row= worksheetRate.getRow(0); 
			    	   cell= row.createCell(row.getLastCellNum()+0);	cell.setCellValue("Category");
					
			      }
			      
			      else {
			    	  if (row.getCell(18).getNumericCellValue()>=minTotalRank && row.getCell(18).getNumericCellValue()<firstQuartile){
			    		  row.createCell(row.getLastCellNum()+0).setCellValue("High");
			    	  }
			    	  else if (row.getCell(18).getNumericCellValue()>=firstQuartile && row.getCell(18).getNumericCellValue()<secondQuartile){
			    		  row.createCell(row.getLastCellNum()+0).setCellValue("Mid");
			    	  }
			    	  if (row.getCell(18).getNumericCellValue()>=secondQuartile && row.getCell(18).getNumericCellValue()<=maxTotalRank){
			    		  row.createCell(row.getLastCellNum()+0).setCellValue("Low");
			    	  }
			      }
			    }
				
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




}

