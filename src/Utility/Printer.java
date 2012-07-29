/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

/**
 *
 * @author Ibrahim
 */
public class Printer {
    
    public static void print(ArrayList array){
	System.out.print("[ ");
	for (int i = 0; i < array.size(); i++) {
	    System.out.print(array.get(i) + " ");
	}
	System.out.println(" ]");
    }
    public static void print(int[] array){
	System.out.print("[ ");
	for (int i = 0; i < array.length; i++) {
	    System.out.print(array[i] + " ");
	}
	System.out.println(" ]");
    }
    public static void print(double[] array){
	System.out.print("[ ");
	for (int i = 0; i < array.length; i++) {
	    System.out.print(array[i] + " ");
	}
	System.out.println(" ]");
    }
    public static void print(String[] array){
	System.out.print("[ ");
	for (int i = 0; i < array.length; i++) {
	    System.out.print(array[i] + " ");
	}
	System.out.println(" ]");
    }
    public static void print(MyPair[] array){
	System.out.print("[ ");
	for (int i = 0; i < array.length; i++) {
	    System.out.print(array[i].index + ":" + array[i].value + " ");
	}
	System.out.println(" ]");
    }
    public static void print(TreeSet<String> treeSet){
	for (String element : treeSet) {
	    System.out.println(element);	    
	}	
    }     
    public static void print(String value){
	System.out.println(value);
    }
    public static void print(int value){
	System.out.println(value);
    }
    public static void print(double value){
	System.out.println(value);
    }
    public static void print(Map mp) {
	Iterator it = mp.entrySet().iterator();
	while (it.hasNext()) {
	    Map.Entry pairs = (Map.Entry)it.next();
	    print(pairs.getKey() + " = " + pairs.getValue());
	    it.remove(); // avoids a ConcurrentModificationException
	}
    }
    
}
