/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Settings.Config;
import Utility.Printer;
import Utility.Reader;
import Utility.Util;
import Utility.Writer;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Ibrahim
 */
class ListString extends ArrayList<String> {

    public ListString() {
    }

    public ListString(ArrayList<String> list) {
	super(list);
    }
    public ArrayList<String> toArrayList(){
	return this;
    }
}

class ListInt extends ArrayList<Integer> {

    public ListInt() {
    }

    public ListInt(ArrayList<Integer> list) {
	super(list);
    }
}

class ListInt2D extends ArrayList<ArrayList<Integer>> {

    public ListInt2D() {
    }

    public ListInt2D(ArrayList<ArrayList<Integer>> list) {
	super(list);
    }

    public ArrayList<ArrayList<Integer>> toArrayList(){
	return this;
    }
}

public class Dataset {

    public int[] classes;
    ListInt2D dataset = new ListInt2D();
    ListInt2D testset = new ListInt2D();
    ListInt2D trainset = new ListInt2D();
    ListInt2D[] dataSetSeparated;
    
    ListString wekaHeader = new ListString();
    

    public void generateDataSetFromWekaPreprocessedDataset() {

	ListString allLines = new ListString(Reader.ReadInList(Config.WEKA_FORMAT_DATA_SET_PATH));

	ListString stringDataset = new ListString();

	int dataIndex = 0;
	for (int i = 0; i < allLines.size(); i++) {
	    String aLine = allLines.get(i);
	    wekaHeader.add(aLine);
	    if (aLine.contains("@attribute c")) {
		String[] stringClasses = allLines.get(i).replaceAll("(@attribute c)|\\{|\\}", "").split(",");
		this.classes = new int[stringClasses.length];
		int j = 0;
		for (String aClass : stringClasses) {
		    this.classes[j++] = Util.parseInt(aClass.trim());
		}

	    }
	    if (aLine.equals("@data")) {
		dataIndex = i;
		break;
	    }
	}
	wekaHeader.add("");
	for (int i = dataIndex + 2; i < allLines.size(); i++) {
	    String[] attr = allLines.get(i).split(",");
	    ListInt temp = new ListInt();
	    for (String s : attr) {
		temp.add(Util.parseInt(s));
	    }
	    this.dataset.add(temp);
	    stringDataset.add(allLines.get(i));
	}
	Writer.WriteFromList(Config.DATA_SET_PATH, stringDataset, "\n");
    }

    public void initDataset() {
	this.classes = Config.classes;
	this.dataset = new ListInt2D(Reader.ReadInDataSet(Config.DATA_SET_PATH));
    }

    public void initClassSeperatedDataset(ArrayList< ArrayList<Integer>> aDataset) {
	dataSetSeparated = new ListInt2D[this.classes.length];
	for (int i = 0; i < aDataset.size(); i++) {
	    ListInt anExample = new ListInt(aDataset.get(i));
	    int index = anExample.get(7);
	    if (dataSetSeparated[index] == null) {
		dataSetSeparated[index] = new ListInt2D();
	    }
	    dataSetSeparated[index].add(anExample);
	}
    }

    public void initTrainTestSet() {
	int size = (int) (dataset.size() * Config.DATA_SET_TRAIN_PERCENTAGE);
	int[] indexArray = Util.GetNRandomInt(dataset.size(), 0, dataset.size());
	for (int i = 0; i < size; i++) {
	    this.trainset.add(dataset.get(indexArray[i]));
	}
	for (int i = size; i < indexArray.length; i++) {
	    this.testset.add(dataset.get(indexArray[i]));
	}
    }
    
    public void initSpecifiedTestSet(){
	for(int i=0;i<this.classes.length;i++){
	    int numberOfinstance = Config.TEST_CLASS_INNSTANCES_COUNT[i];
	    if(numberOfinstance > this.dataSetSeparated[i].size()){
		numberOfinstance = this.dataSetSeparated[i].size();
		Printer.print("Specified instance count is more than the available instance."
			+ "Max Instance count selected.");
	    }
	    int[] indexArray = Util.GetNRandomInt(numberOfinstance, 0, dataSetSeparated[i].size());
	    for(int j=0; j < numberOfinstance; j++){
		this.testset.add(this.dataSetSeparated[i].get(indexArray[j]));
	    }
	}
    }
    
    public void initSpecifiedTrainSet(){
	for(int i=0;i<this.classes.length;i++){
	    int numberOfinstance = Config.TRAIN_CLASS_INNSTANCES_COUNT[i];
	    if(numberOfinstance > this.dataSetSeparated[i].size()){
		numberOfinstance = this.dataSetSeparated[i].size();
		Printer.print("Specified instance count is more than the available instance."
			+ "Max Instance count selected.");
	    }
	    int[] indexArray = Util.GetNRandomInt(numberOfinstance, 0, dataSetSeparated[i].size());
	    for(int j=0; j < numberOfinstance; j++){
		this.trainset.add(this.dataSetSeparated[i].get(indexArray[j]));
	    }
	}
    }
    
    public void dumpTrainset(){
	Writer.WriteFromArrayList(Config.TRAIN_DATA_SET_PATH, trainset.toArrayList());
    }
    public void dumpTestset(){
	Writer.WriteFromArrayList(Config.TEST_DATA_SET_PATH, testset.toArrayList());
    }
    
    public void dumpWekaFormatTestSet(){		
	Writer.WriteFromList(Config.WEKA_FORMAT_TEST_DATA_SET_PATH, wekaHeader.toArrayList(),"\n");
	Writer.WriteFromArrayList(Config.WEKA_FORMAT_TEST_DATA_SET_PATH, testset.toArrayList(),true);
    }
    
    public void dumpWekaFormatTrainSet(){	
	Writer.WriteFromList(Config.WEKA_FORMAT_TRAIN_DATA_SET_PATH, wekaHeader.toArrayList(),"\n");
	Writer.WriteFromArrayList(Config.WEKA_FORMAT_TRAIN_DATA_SET_PATH, trainset.toArrayList(),true);
    }
    
    public void printInfo() {
	Printer.print("Total Instaces in dataset: " + dataset.size());
	Printer.print("Total Instaces in trainset: " + trainset.size());
	Printer.print("Total Instaces in testset: " + testset.size());
	for (int i = 0; i < classes.length; i++) {
	    Printer.print("Instance Count Class " + i + ": " + dataSetSeparated[i].size());
	}
	Printer.print("Weka Header");
	//Printer.print(wekaHeader);
    }

    public static void main(String[] args) {

	Dataset dataset = new Dataset();
//	dataset.initDataset();
	dataset.generateDataSetFromWekaPreprocessedDataset();	
	dataset.initClassSeperatedDataset(dataset.dataset);
	dataset.initSpecifiedTrainSet();
	dataset.initSpecifiedTestSet();
	dataset.dumpTestset();
	dataset.dumpTrainset();	
	dataset.dumpWekaFormatTrainSet();
	dataset.dumpWekaFormatTestSet();
	dataset.printInfo();	
	
	

    }
}
