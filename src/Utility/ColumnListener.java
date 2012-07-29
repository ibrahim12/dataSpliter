/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Ibrahim
 */
public class ColumnListener extends MouseAdapter {

    protected JTable table;

    public ColumnListener(JTable t) {
	table = t;
    }

    public void mouseClicked(MouseEvent e) {
	TableColumnModel colModel = table.getColumnModel();
	int columnModelIndex = colModel.getColumnIndexAtX(e.getX());
	int modelIndex = colModel.getColumn(columnModelIndex).getModelIndex();

	if (modelIndex < 0) {
	    return;
	}
	
	table.setAutoCreateColumnsFromModel(false);
	sortAllRowsBy( (DefaultTableModel)table.getModel() , columnModelIndex , false);

    }
    
    // Regardless of sort order (ascending or descending), null values always appear last.
    // colIndex specifies a column in model.
    public void sortAllRowsBy(DefaultTableModel model, int colIndex, boolean ascending) {
	Vector data = model.getDataVector();
	Collections.sort(data, new ColumnSorter(colIndex, ascending));
	model.fireTableStructureChanged();
    }
    
}
