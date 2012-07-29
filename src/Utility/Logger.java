/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;


import Settings.Config;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 *
 * @author Ibrahim
 */
public class Logger {

    
    private static void log(String Logfile, String logMessage){
	try {
	    String currentDateTime = Helper.getCurrentDateTime();
	    BufferedWriter fout = new BufferedWriter(new FileWriter(Logfile, true));
	    if(Logfile.equals(Config.LOG_FILE))
		Printer.print("[" + currentDateTime + "] " + ": " + logMessage + "\n");
	    fout.write("[" + currentDateTime + "] " + ": " + logMessage + "\n");
	    fout.close();
	} catch (Exception e) { 
	    e.printStackTrace();
	}
    }
    
    public static void log(String logMessage) {
	log(Config.LOG_FILE,logMessage);
    }

}
