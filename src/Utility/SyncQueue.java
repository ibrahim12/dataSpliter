/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;


/**
 *
 * @author Ibrahim
 */
public class SyncQueue {

    private final Object lock = new Object();
    private Object o;
    private int timeout = 0;
   

    public SyncQueue(int timeout) {
	this.timeout = timeout;
    }

    public SyncQueue() {
    }



    /*
     * One thread sends "event" ...
     */

    public void send(Object o)
	    throws InterruptedException {
	if (o == null) {
	    throw new IllegalArgumentException();
	}
	synchronized (lock) {
	    while (this.o != null) {
		long start = System.currentTimeMillis();		
		if (timeout != 0) {
		    lock.wait(timeout);
		} else {
		    lock.wait();
		}
		if ( ( System.currentTimeMillis() - start ) >= timeout ){
		    lock.notifyAll();	    
		}
	    }
	    this.o = o;
	    lock.notifyAll();
	}
    }

    /*
     * Another blocks (without burning CPU) until event is received.
     */
    public Object recv()
	    throws InterruptedException {
	Object o;
	synchronized (lock) {
	    while (this.o == null) {
		long start = System.currentTimeMillis();		
		if (timeout != 0) {
		    lock.wait(timeout);
		} else {
		    lock.wait();
		}
		if ( ( System.currentTimeMillis() - start ) >= timeout ){
		    lock.notifyAll();	
		    return "NULL";
		}  		
	    }
	    o = this.o;
	    this.o = null;
	    lock.notifyAll();
	}
	return o;
    }
}