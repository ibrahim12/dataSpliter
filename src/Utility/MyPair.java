/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

/**
 *
 * @author Ibrahim
 */
public class MyPair implements Comparable {

    public double value;
    public int index;
    boolean assendingSort = false;

    public MyPair(){
	
    }
    public MyPair(int index,double value,boolean sortType){
	this.index = index;
	this.value = value;
	this.assendingSort = sortType;
    }
    
    public void SetAcessdingSort(){
	this.assendingSort = true;
    }
    public void SetDescendingSort(){
	this.assendingSort = false;
    }
    /*
     * Overload compareTo method
     */
    @Override
    public int compareTo(Object obj) {
	MyPair tmp = (MyPair) obj;
	if (this.value < tmp.value) {
	    /*
	     * instance lt received
	     */
	    return assendingSort ?  -1 : 1;	    
	} else if (this.value > tmp.value) {
	    /*
	     * instance gt received
	     */
	    return assendingSort ?  1 : -1;	    
	    
	}
	/*
	 * instance == received
	 */
	return 0;
    }
}
