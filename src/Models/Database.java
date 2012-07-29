/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import DB.DBConfig;
import Utility.Array3D;
import Utility.Logger;
import Utility.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Ibrahim
 */
public class Database {

    private static Connection connection;

    public static Connection getConnection() {

	try {
	    if (Database.connection == null) {
		Class.forName("org.sqlite.JDBC");
		Database.connection = DriverManager.getConnection(DBConfig.SQLITE_URL + DBConfig.SQLITE_DB_NAME);
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	    Logger.log(ex.toString());

	}
	return Database.connection;

    }

    public static PreparedStatement getPreparedStatement(String query, Object[] values) throws SQLException {

	PreparedStatement preparedStatement = Database.getConnection().prepareStatement(query);
	if (values == null) {
	    return preparedStatement;
	}
	for (int index = 1; index <= values.length; index++) {	    	    
	    preparedStatement.setString(index, values[index - 1].toString());	    	    	
	}
	return preparedStatement;
    }

    public static boolean insertRecord(String insertQuery, Object[] values) {
	try {	   
	    PreparedStatement preparedStatement = getPreparedStatement(insertQuery, values);
	    int status = preparedStatement.executeUpdate();
	    
	    if(status == 1)
		return true;
	    return false;
	    
	} catch (SQLException ex) {
	    ex.printStackTrace();
	    Logger.log(ex.toString());
	    return false;
	}
    }

    public static int[] insertRecords(String insertQuery, ArrayList<Object[]> valueObjects) {
	try {
	    Connection connection = Database.getConnection();
	    PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

	    for (int objectIndex = 0; objectIndex < valueObjects.size(); objectIndex++) {

		for (int index = 1; index <= valueObjects.get(objectIndex).length; index++) {
		    preparedStatement.setString(index, valueObjects.get(objectIndex)[index-1].toString());
		}
		preparedStatement.addBatch();
	    }
	    connection.setAutoCommit(false);
	    int[] result = preparedStatement.executeBatch();	 
	    connection.setAutoCommit(true);
	    return result;
	} catch (SQLException ex) {
	    ex.printStackTrace();
	    Logger.log(ex.toString());
	    return null;
	}
    }
    
    public static int getInsertID(){
	
	HashMap<String, Object> row = getResultRow("select last_insert_rowid() id;");		
	return Util.parseInt((String)row.get("id"));	
    }
    
    public static boolean updateRecord(String updateQuery, Object[] values) {
	try {
	    PreparedStatement preparedStatement = getPreparedStatement(updateQuery, values);
	    return preparedStatement.execute();
	} catch (SQLException ex) {
	    ex.printStackTrace();
	    Logger.log(ex.toString());
	    return false;
	}
    }

    public static boolean deleteRecord(String deleteQuery, Object[] values) {
	try {
	    PreparedStatement preparedStatement = getPreparedStatement(deleteQuery, values);
	    return preparedStatement.execute();
	} catch (SQLException ex) {
	    ex.printStackTrace();
	    Logger.log(ex.toString());
	    return false;
	}
    }

    public static HashMap<String, Object> getResultRow(String resultQuery) {
	return Database.getResultRow(resultQuery, null);
    }

    public static HashMap<String, Object> getResultRow(String resultQuery, Object[] values) {
	HashMap<String, Object> results = new HashMap<String, Object>();
	try {
	    PreparedStatement preparedStatement = getPreparedStatement(resultQuery, values);
	    ResultSet resultSet = preparedStatement.executeQuery();

	    ResultSetMetaData rsmd = resultSet.getMetaData();
	    int numColumns = rsmd.getColumnCount();

	    for (int columIndex = 1; columIndex <= numColumns; columIndex++) {
		String columnName = rsmd.getColumnName(columIndex);
		results.put(columnName, resultSet.getString(columIndex));
	    }
	    resultSet.close();

	} catch (SQLException ex) {
	    ex.printStackTrace();
	    Logger.log(ex.toString());
	    return results;
	}
	return results;
    }

    public static ArrayList< HashMap<String, Object>> getResultsSet(String resultQuery) {
	return Database.getResultsSet(resultQuery, null);
    }

    public static Array3D getResultsSet(String resultQuery, Object[] values) {
	Array3D results = new Array3D();
	try {
	    PreparedStatement preparedStatement = getPreparedStatement(resultQuery, values);
	    ResultSet resultSet = preparedStatement.executeQuery();

	    ResultSetMetaData rsmd = resultSet.getMetaData();
	    int numColumns = rsmd.getColumnCount();

	    while (resultSet.next()) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		for (int columIndex = 1; columIndex <= numColumns; columIndex++) {
		    String columnName = rsmd.getColumnName(columIndex);
		    resultMap.put(columnName, resultSet.getString(columIndex));
		}
		results.add(resultMap);
	    }


	    resultSet.close();

	} catch (SQLException ex) {
	    ex.printStackTrace();
	    Logger.log(ex.toString());
	    return results;
	}
	return results;
    }
    
    
    
}
