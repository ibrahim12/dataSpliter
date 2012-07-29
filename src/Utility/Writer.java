/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;



import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 *
 * @author Ibrahim
 */
public class Writer {
    
    public static void WriteFromList(String path, ArrayList list) {	
	WriteFromList(path,list,"",false);
    }
    public static void WriteFromList(String path, ArrayList list , String endPad) {	
	WriteFromList(path,list,endPad,false);
    }
    public static void WriteFromList(String path, ArrayList list , String endPad,boolean isAppend) {	
	try {
	    // Open the file that is the first 
	    // command line parameter
	    FileOutputStream fstream = new FileOutputStream(path,isAppend);
	    // Get the object of DataInputStream
	    DataOutputStream in = new DataOutputStream(fstream);
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(in));	    
	    
	    for(int i=0;i<list.size();i++){		
		if(isAppend)
		    bw.append(list.get(i) + endPad);
		else
		    bw.write(list.get(i) + endPad);
	    }	    
	    //Close the input stream
	    bw.close();
	    in.close();
	} catch (Exception e) {//Catch exception if any	    
	    e.printStackTrace();	    
	}	
    }
   
    public static void WriteFromArrayList(String path, ArrayList<ArrayList<Integer>> list) {	
	WriteFromArrayList(path,list,false);
    }
    public static void WriteFromArrayList(String path, ArrayList<ArrayList<Integer>> list,boolean isAppend) {	
	try {
	    // Open the file that is the first 
	    // command line parameter
	    FileOutputStream fstream = new FileOutputStream(path,isAppend);
	    // Get the object of DataInputStream
	    DataOutputStream in = new DataOutputStream(fstream);
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(in));	    
	    
	    for(int i=0;i<list.size();i++){		
		ArrayList<Integer> temp = list.get(i);	
		String strLine = Util.join(temp, ",");	
		if(isAppend)
		    bw.append(strLine+"\n");
		else
		    bw.write(strLine+"\n");
		
	    }	    
	    //Close the input stream
	    bw.close();
	    in.close();
	} catch (Exception e) {//Catch exception if any	    
	    e.printStackTrace();	    
	}	
    }
    public static void WriteFromTreeSet(String path, TreeSet<String> treeSet) {	
	WriteFromTreeSet(path,treeSet,"");
    }
    public static void WriteFromTreeSet(String path, TreeSet<String> treeSet , String endPad) {	
	try {
	    // Open the file that is the first 
	    // command line parameter
	    FileOutputStream fstream = new FileOutputStream(path);
	    // Get the object of DataInputStream
	    DataOutputStream out = new DataOutputStream(fstream);
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));	    
	    	    
	    for (String element : treeSet) {
		bw.write(element + endPad);
	    }  
	    //Close the input stream
	    bw.close();
	    out.flush();
	    out.close();	    
	} catch (Exception e) {//Catch exception if any	    
	    e.printStackTrace();	    
	}	
    }

    public static void WriteFromHashMap(String path,HashMap<String,Integer> hashMap){
	WriteFromHashMap(path,hashMap,false);
    }
    public static void WriteFromHashMap(String path,HashMap<String,Integer> hashMap,boolean writeBoth){
	try {
	    // Open the file that is the first 
	    // command line parameter
	    FileOutputStream fstream = new FileOutputStream(path);
	    // Get the object of DataInputStream
	    DataOutputStream out = new DataOutputStream(fstream);
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));	   
	        
	    Iterator it = hashMap.entrySet().iterator();		
		while (it.hasNext()) {
		    Map.Entry pairs = (Map.Entry)it.next();
		    String data = "";
		    if(!writeBoth)
			data = (String)pairs.getKey();
		    else
			data = (String)pairs.getKey() + " " + pairs.getValue();;
		    bw.write(data + "\n");
		    it.remove(); // avoids a ConcurrentModificationException
		}
		
	    
	    bw.close();
	    out.flush();
	    out.close();	    
	} catch (Exception e) {//Catch exception if any	    
	    e.printStackTrace();	    
	}
    }
    
    
    
    
   
}
