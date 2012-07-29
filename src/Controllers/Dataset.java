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
}

public class Dataset {

    public int[] classes;
    ListInt2D dataset = new ListInt2D();
    ListInt2D testset = new ListInt2D();
    ListInt2D trainset = new ListInt2D();
    ListInt2D[] dataSetSeparated;

    public void generateDataSetFromWekaPreprocessedDataset() {

	ListString allLines = new ListString(Reader.ReadInList(Config.WEKA_FORMAT_DATA_SET_PATH));

	ListString stringDataset = new ListString();

	int dataIndex = 0;
	for (int i = 0; i < allLines.size(); i++) {
	    if (allLines.get(i).contains("@attribute c")) {
		String[] stringClasses = allLines.get(i).replaceAll("(@attribute c)|\\{|\\}", "").split(",");
		this.classes = new int[stringClasses.length];
		int j = 0;
		for (String aClass : stringClasses) {
		    this.classes[j++] = Util.parseInt(aClass.trim());
		}

	    }
	    if (allLines.get(i).equals("@data")) {
		dataIndex = i;
		break;
	    }
	}
	for (int i = dataIndex + 2; i < allLines.size(); i++) {
	    String[] attr = allLines.get(i).split(",");
	    ArrayList<Integer> temp = new ArrayList<Integer>();
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
	    ArrayList<Integer> anExample = aDataset.get(i);
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

    public void printInfo() {
	Printer.print("Total Instaces in dataset: " + dataset.size());
	for (int i = 0; i < classes.length; i++) {
	    Printer.print("Instance Count Class " + i + ": " + dataSetSeparated[i].size());
	}
    }

    public static void main(String[] args) {

	Dataset dataset = new Dataset();
	dataset.initDataset();
	//dataset.generateDataSetFromWekaPreprocessedDataset();
	dataset.initClassSeperatedDataset(dataset.dataset);
	dataset.printInfo();
	




    }
}
