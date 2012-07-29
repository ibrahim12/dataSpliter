/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Settings;

/**
 *
 * @author Ibrahim
 */
public class Config {
    
    
    public static String WEKA_FORMAT_DATA_SET_PATH = "src\\Files\\weka_dataset.arff";
    public static String DATA_SET_PATH = "src\\Files\\dataset.txt";
    public static String TRAIN_DATA_SET_PATH = "src\\Files\\trianset.txt";
    public static String TEST_DATA_SET_PATH = "src\\Files\\testset.txt";
    
    public static String WEKA_FORMAT_TRAIN_DATA_SET_PATH = "src\\Files\\weka_trianset.arff";
    public static String WEKA_FORMAT_TEST_DATA_SET_PATH = "src\\Files\\weka_testset.arff";
    
    public static final String LOG_FILE = "logs\\log.txt";
    
    public static final double DATA_SET_TRAIN_PERCENTAGE = .40;
    
    public static final int[] TRAIN_CLASS_INNSTANCES_COUNT = new int[]{100,35,20};
    public static final int[] TEST_CLASS_INNSTANCES_COUNT = new int[]{50,19,7};
    
    public static final int[] classes = new int[]{0,1,2};
    
    public static final String WEKA_HEADER = "Data set is not generated from weka file.";
     
}
