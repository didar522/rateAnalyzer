package Weka;

import java.awt.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

import weka.core.*;
import weka.classifiers.*;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.EvaluationUtils;
import weka.classifiers.evaluation.ThresholdCurve;
import weka.classifiers.trees.J48;
import weka.clusterers.SimpleKMeans;
import weka.gui.visualize.*;

/**
  * Generates and displays a ROC curve from a dataset. Uses a default
  * NaiveBayes to generate the ROC data.
  *
  * @author FracPete
  */
public class GenerateROC {

  /**
   * takes one argument: dataset in ARFF format (expects class to
   * be last attribute)
   */
  public static void main(String[] args) throws Exception {
	  
//	  PredRRA_RR (); 
	  
	  ClusPRS ();
  }
  
  public static void PredRRA_RR () throws Exception{
	  
    // load data
    Instances data = new Instances(
                          new BufferedReader(
                            new FileReader("C:/Users/S.M.Didar/OneDrive/Work Cloud/Eclipse Analysis/BS46 - RRA Attributes All.arff")));//args[0]
    data.setClassIndex(data.numAttributes() - 1);

    // train classifier
    

    for (double confidence=0;confidence<=50;confidence=confidence+0.25){
    	for (int objects=0;objects<=100;objects++){
    		Classifier cl = new J48(); 
    	    String[] options = {"-c",Double.toString(confidence), "-M",Integer.toString(objects)};
    	    cl.setOptions(options);
    	    Evaluation eval = new Evaluation(data);
    	    eval.crossValidateModel(cl, data, 10, new Random(1));
    	    
    	    //System.out.println(eval.toSummaryString("Results--------------", false));
    	    
    	    
    	    System.out.println("confidence-"+confidence+" objects-"+objects+" ROC-C1-"+ eval.areaUnderROC(0)+" ROC-C2-"+  eval.areaUnderROC(1));
    	    
    	    
    	    
//    	    System.out.println(eval.areaUnderROC(0));
//    	    System.out.println(eval.areaUnderROC(1));
    	}
    }
    
      

//    // generate curve
//    ThresholdCurve tc = new ThresholdCurve();
//    int classIndex = 0;
//    Instances result = tc.getCurve(eval.predictions(), classIndex);
//
//    // plot curve
//    ThresholdVisualizePanel vmc = new ThresholdVisualizePanel();
//    vmc.setROCString("(Area under ROC = " +
//        Utils.doubleToString(tc.getROCArea(result), 4) + ")");
//    vmc.setName(result.relationName());
//    PlotData2D tempd = new PlotData2D(result);
//    tempd.setPlotName(result.relationName());
//    tempd.addInstanceNumberAttribute();
//    // specify which points are connected
//    boolean[] cp = new boolean[result.numInstances()];
//    for (int n = 1; n < cp.length; n++)
//      cp[n] = true;
//    tempd.setConnectPoints(cp);
//    // add plot
//    vmc.addPlot(tempd);
//
//    // display curve
//    String plotName = vmc.getName();
//    final javax.swing.JFrame jf =
//      new javax.swing.JFrame("Weka Classifier Visualize: "+plotName);
//    jf.setSize(500,400);
//    jf.getContentPane().setLayout(new BorderLayout());
//    jf.getContentPane().add(vmc, BorderLayout.CENTER);
//    jf.addWindowListener(new java.awt.event.WindowAdapter() {
//      public void windowClosing(java.awt.event.WindowEvent e) {
//      jf.dispose();
//      }
//    });
//    jf.setVisible(true);
  }

  public static void ClusPRS () throws Exception{
	  
	    
	  
	  
	  Instances dataa = new Instances(
			  				new BufferedReader(
			  						new FileReader("C:/Users/S.M.Didar/OneDrive/Work Cloud/Eclipse Analysis/BS46 - PRS metrics.arff")));


	    // create the model 
	    
	  
	    SimpleKMeans kMeans = new SimpleKMeans();
	    kMeans.setNumClusters(3);
	    kMeans.setSeed(285);
	    kMeans.buildClusterer(dataa); 
	    System.out.println("Iteration, "+kMeans.getMaxIterations()+", Error,"+kMeans.getSquaredError()+", Custersize, "+ kMeans.getClusterSizes()[0]+", "+kMeans.getClusterSizes()[1]+", "+kMeans.getClusterSizes()[2]);
	    
	     
	    
//	    PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
//	    for (int i=0;i<5000;i++){
//	    	kMeans.setSeed(i);
//		    kMeans.buildClusterer(dataa); 
//		    
//		    
//		    PrintWriter out = new PrintWriter(new FileWriter("C:/Users/S.M.Didar/OneDrive/Work Cloud/Eclipse Analysis/SeedInvestigation Clus3.txt", true), true);
////		    out.write("test");
////		    out.close();
//		    
//		    out.write("Seed, "+i+", Iteration, "+kMeans.getMaxIterations()+", Error,"+kMeans.getSquaredError()+", Custersize, "+ kMeans.getClusterSizes()[0]+", "+kMeans.getClusterSizes()[1]+", "+kMeans.getClusterSizes()[2]);
//		    out.write("\n");
//		    
//		    out.close();
//	    
//	    }
	    
	
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
//	    System.out.println(kMeans.displayStdDevsTipText());
//	    System.out.println(kMeans.distanceFunctionTipText());

	    // print out the cluster centroids
	    Instances centroids = kMeans.getClusterCentroids(); 
	    for (int i = 0; i < centroids.numInstances(); i++) { 
	      System.out.println( "Centroid " + i+1 + ": " + centroids.instance(i)); 
	    } 

	    // get cluster membership for each instance 
	    for (int i = 0; i < dataa.numInstances(); i++) { 
	      System.out.println( dataa.instance(i) + " is in cluster " + kMeans.clusterInstance(dataa.instance(i)) + 1); 

	    } 
	  }










}// End of class