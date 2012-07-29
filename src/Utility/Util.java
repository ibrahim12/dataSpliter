/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author Ibrahim
 */
public class Util {

    public static int RandomInt(int max) {
	return new Random().nextInt(max);
    }

    public static int RandomInt(int min, int max) {
	return new Random().nextInt(max - min + 1) + min;
    }

    public static int[] GetNRandomInt(int n, int min, int max) {
	ArrayList<Integer> indexArray = new ArrayList();
	for (int i = min; i < max; i++) {
	    indexArray.add(i);
	}
	Collections.shuffle(indexArray);
	int[] randomInt = new int[n];
	for (int i = 0; i < n; i++) {
	    randomInt[i] = indexArray.get(i);
	}
	return randomInt;
    }

    public static float GetRandom() {
	return new Random().nextFloat();
    }

    public static String join(Collection s, String delimiter) {
	StringBuffer buffer = new StringBuffer();
	Iterator iter = s.iterator();
	while (iter.hasNext()) {
	    buffer.append(iter.next());
	    if (iter.hasNext()) {
		buffer.append(delimiter);
	    }
	}
	return buffer.toString();
    }

    public static void SleepThread(long mills) {
	try {
	    Thread.currentThread().sleep(mills);
	} catch (InterruptedException ex) {
	    ex.printStackTrace();
	}
    }
    
    public static Integer parseInt(String s){
	Integer data = 0;
	try{
	    data = Integer.parseInt(s);
	}catch(Exception ex){
	    ex.printStackTrace();
	    Logger.log("Integer Parsing Error for String " + s);
	}
	return data;
    }
    
     public static Double parseDouble(String s){
	Double data = 0.0;
	try{
	    data = Double.parseDouble(s);
	}catch(Exception ex){
	    ex.printStackTrace();
	    Logger.log("Integer Parsing Error for String " + s);
	}
	return data;
    }
    
    public static boolean isMobileNumberValid(String s ){
	
	if(s.length() == 11 && s.replaceAll("[0-9]+", "").equals("")){
	    return true;
	}
	return false;	
    }
    
    public static boolean isAmountValid(String s){
	
	Integer amountInt = parseInt(s);	
	if( amountInt < 10){
	    return false;
	}
	return true;
    }
    

}
